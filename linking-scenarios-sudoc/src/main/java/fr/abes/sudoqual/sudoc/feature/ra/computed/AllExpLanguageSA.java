package fr.abes.sudoqual.sudoc.feature.ra.computed;

import org.json.JSONArray;

import fr.abes.sudoqual.linking_module.feature.AJSONObjectWeightedUnionComputedFeature;

/**
 * Aggrégation pondérée des "expLanguage".
 *
 */
public class AllExpLanguageSA extends AJSONObjectWeightedUnionComputedFeature {


	@Override
	public String process(Object o) {
		return ((JSONArray)o).getString(0);
	}
	

	@Override
	public String getRelatedFeature() {
		return "expLanguage";
	}

}
