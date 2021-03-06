package fr.abes.qualinka.sudoc.feature.ra.computed;

import org.json.JSONArray;

import fr.abes.sudoqual.linking_module.feature.AJSONObjectWeightedUnionComputedFeature;

/**
 * Aggrégation pondérée des "role".
 *
 */
public class RoleSA extends AJSONObjectWeightedUnionComputedFeature {
	
	@Override
	public String process(Object o) {
		return ((JSONArray)o).getString(0);
	}
	
	@Override
	public String getRelatedFeature() {
		return "role";
	}

}