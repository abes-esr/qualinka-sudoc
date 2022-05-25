/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.aa;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: "roleSA", "roleGroupSA" <br/>
 * * target: "roleSA", "roleGroupSA"<br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * Sinon, on compare le groupe auquel appartient le rôle de la RA source
 * et on le compare au groupe de chaque rôle de la RA. Si un groupe correspond,
 * on renvoie 1<br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 1: la RA source et la RA target partagent un même groupe de rôle.<br/>
 * * neutral: ni 1, ni 2 et une "representativeness" < 3<br/>
 * * -1: ni 1, ni 2, ni neutral et une "representativeness" < 5<br/>
 * * -2: ni 1, ni 2 et une "representativeness" >= 5<br/>
 */
public class RoleAACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -2, true, 1, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("roleSA", "roleGroupSA");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("roleSA", "roleGroupSA");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if (!source.has("roleSA") || source.has("roleGroupSA")|| !target.has("roleSA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		JSONObject roleRa1 = source.getJSONObject("roleSA");
		JSONObject grproleRa1 = source.optJSONObject("roleGroupSA");
		
		JSONObject roleRa2 = target.getJSONObject("roleSA");
		JSONObject grproleRa2 = target.optJSONObject("roleGroupSA");

		int representativeness2 = (int) roleRa2.get("representativeness");

		JSONArray tabgrpRa1 = new JSONArray();
		if (grproleRa1 != null && grproleRa1.has("weightedValues")) {
			tabgrpRa1 = (JSONArray) grproleRa1.get("weightedValues");
		}
		
		JSONArray tabgrpRa2 = new JSONArray();
		if (grproleRa2 != null && grproleRa2.has("weightedValues")) {
			tabgrpRa2 = (JSONArray) grproleRa2.get("weightedValues");
		}
		
		for (Object o1 : tabgrpRa1) {
			JSONObject wgrpRa1 = (JSONObject) o1;
			Object gaa1 = wgrpRa1.get("value");	
			for (Object o2 : tabgrpRa2) {
				JSONObject wgrpRa2 = (JSONObject) o2;
				Object gaa2 = wgrpRa2.get("value");
				if (gaa2.equals(gaa1)) {
					return 1;
				}
			}
		}
// ON NE RENVOIE DU NEGATIF QUE SI LE SUPERATTRIBUT ROLE EST SUFFISAMMENT REPRESENTATIF
		if (representativeness2 < 3) {
			return DiscretCompType.NEUTRAL;
		} else if (representativeness2 < 5) {
			return -1;
		} else {
			return -2;
		}
	}
}
