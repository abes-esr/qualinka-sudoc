/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.cc;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 *
 */
// RC-RC
public class TitleCCCriterion implements Criterion {

	final static int SEUIL1 = 2000;
	final static int SEUIL2 = 3000;

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 3, false);

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("title");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("title");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if (!source.has("title") || !target.has("title")) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		JSONObject jsonTitRc1 = source.getJSONArray("title").optJSONObject(0);
		String titRc1 = null;
		if (jsonTitRc1 != null) {
			titRc1 = (String) jsonTitRc1.get("raw");
		}
		JSONObject jsonTitRc2 = target.getJSONArray("title").optJSONObject(0);
		String titRc2 = null;
		if (jsonTitRc2 != null) {
			titRc2 = (String) jsonTitRc2.get("raw");
		}

		if (titRc1 == null || titRc2 == null) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		double simTitle = new Levenshtein().getSimilarity(titRc1.toLowerCase(), titRc2.toLowerCase());

		if (simTitle > 0.9) {
			return 3;
		}
		if (simTitle > 0.7) {
			return 2;
		}

		JSONObject maptitRc1 = (JSONObject) jsonTitRc1.get("uncommonWords");
		JSONObject maptitRc2 = (JSONObject) jsonTitRc2.get("uncommonWords");

		simTitle = 0.;
		for (String mottitRc2 : maptitRc2.keySet()) {
			for (String mottitRc1 : maptitRc1.keySet()) {
				if (new Levenshtein().getSimilarity(mottitRc1, mottitRc2) > 0.9) {
					simTitle = simTitle + (maptitRc1.getDouble(mottitRc1) + maptitRc2.getDouble(mottitRc2)) / 2.;
				}
			}
		}

		
		if (simTitle >= 1. / SEUIL1) {
			return 2;
		}
		if (simTitle >= 1. / SEUIL2) {
			return 1;
		}

		return DiscretCompType.NEUTRAL;
	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

}
