package fr.abes.sudoqual.sudoc.feature.ra.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

/**
 * Union des titres.
 *
 */
public class TitleSA extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "title";
	}

	
}