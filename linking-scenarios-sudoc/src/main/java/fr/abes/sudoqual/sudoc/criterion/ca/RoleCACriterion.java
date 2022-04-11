/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.ca;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: "filteredRole", "roleGroup" <br/>
 * * target: "roleSA", "roleGroupSA"<br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On compare le rôle de la RC à chaque rôle de la RA, si un rôle correspond,
 * on renvoie 2 Sinon, on compare le groupe auquel appartient le rôle de la RC
 * et on le compare au groupe de chaque rôle de la RA. Si un groupe correspond,
 * on renvoie 1<br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 2: la RC et la RA partage un même rôle<br/>
 * * 1: la RC et la RA partage un même groupe de rôle.<br/>
 * * neutral: ni 1, ni 2 et une "representativeness" < 3<br/>
 * * -1: ni 1, ni 2, ni neutral et une "representativeness" < 5<br/>
 * * -2: ni 1, ni 2 et une "representativeness" >= 5<br/>
 */
public class RoleCACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -2, true, 2, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("filteredRole", "roleGroup");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("roleSA", "roleGroupSA");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if (!source.has("filteredRole") || source.getJSONArray("filteredRole").isEmpty() || !target.has("roleSA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		String leRoleRc = source.getJSONArray("filteredRole").optString(0);
		String grpRc = source.has("roleGroup") ? source.getJSONArray("roleGroup").optString(0) : null;

		JSONObject roleRa = target.getJSONObject("roleSA");
		JSONObject grpRoleRa = target.optJSONObject("roleGroupSA");

		int representativeness = (int) roleRa.get("representativeness");
//   REVOIR LA NOTION DE PROPORTION DE VALEURS VIDES 
//		double proportionValeursVides;
//		if (rcRa.size()==0) proportionValeursVides = 0;
//		else proportionValeursVides = (new Double(rcRa.size() - representativeness))/rcRa.size();
//print("pVV "+ proportionValeursVides+ " "+ rcRa.size()+"-"+representativeness+"/"+rcRa.size());

		JSONArray tabgrpRa = new JSONArray();
		if (grpRoleRa != null && grpRoleRa.has("weightedValues")) {
			tabgrpRa = (JSONArray) grpRoleRa.get("weightedValues");
		}
		JSONArray tableRoleRa = (roleRa.has("weightedValues")) ? (JSONArray) roleRa.get("weightedValues")
				: new JSONArray();

		// print("AAA" + leRoleRc + " " + grpRc + " "+tabgrpRa+" "+tableRoleRa);
		for (Object o : tableRoleRa) {
			JSONObject wleRoleRa = (JSONObject) o;
			Object ga = wleRoleRa.get("value");
			// Object wa = wleRoleRa.get("weight");

//print("roleRc "+leRoleRc+" role ra :  "+ga+ " weight "+ wa);			
			if (leRoleRc.equals(ga)) {
				return 2;
			}
		}
		for (Object o : tabgrpRa) {
			JSONObject wgrpRa = (JSONObject) o;
			Object gaa = wgrpRa.get("value");
			// Object wa = wgrpRa.get("weight");
//print("roleRc "+roleRc+" roleRa "+ra+ " weight "+ wa);	
//print("roleRc "+roleRc+" groupe ra :  "+ga+ " weight "+ wa);			
			if (gaa.equals(grpRc)) {
				return 1;
			}
		}
// ON NE RENVOIE DU NEGATIF QUE SI LE SUPERATTRIBUT ROLE EST SUFFISAMMENT REPRESENTATIF
		if (representativeness < 3) {
			return DiscretCompType.NEUTRAL;
		} else if (representativeness < 5) {
			return -1;
		} else {
			return -2;
		}
	}
}
