/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.ca;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: pubDate <br/>
 * * target: thesisDateSA <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On compare la date de publication de RC et la date de soutenance de la thèse
 * de RA. <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 1: la date de la publication > date de soutenance de thèse <br/>
 * * 0: date de publication == date de soutenance de thèse <br/>
 * * -1: date de publication < date de soutenance de thèse<br/>
 */
public class ThesisDateCACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 1, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("pubDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("thesisDateSA");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if (!source.has("pubDate") || !target.has("thesisDateSA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		int datePubRC = source.getInt("pubDate");
		JSONArray intThesisSA = target.getJSONArray("thesisDateSA");

		if (intThesisSA.length() == 0 || intThesisSA.get(0) == null) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		Integer thesisDateRA = (Integer) intThesisSA.get(0);
		if (datePubRC < thesisDateRA) {
			return -1;
		}
		if (datePubRC > thesisDateRA) {
			return 1;
		}

		return DiscretCompType.NEUTRAL;
	}
}
