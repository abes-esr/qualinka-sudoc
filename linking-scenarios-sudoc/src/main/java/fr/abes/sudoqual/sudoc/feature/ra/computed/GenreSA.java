package fr.abes.sudoqual.sudoc.feature.ra.computed;

import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.feature.AJSONObjectWeightedUnionComputedFeature;

/**
 * Aggrégation pondérée des "genre".
 *
 */
public class GenreSA extends AJSONObjectWeightedUnionComputedFeature {
	
	@Override
	public String process(Object o) {
		return (String) o;
	}
	
	public boolean check(JSONObject json) {
		return json.has(this.getRelatedFeature());
	}
	
	@Override
	public String getRelatedFeature() {
		return "genre";
	}

}
