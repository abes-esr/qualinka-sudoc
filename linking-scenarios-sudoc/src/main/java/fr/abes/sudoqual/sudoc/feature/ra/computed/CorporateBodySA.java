package fr.abes.sudoqual.sudoc.feature.ra.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

/**
 * Union des corporateBody.
 */
public class CorporateBodySA extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "corporateBody";
	}

	
}