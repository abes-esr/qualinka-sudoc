package fr.abes.qualinka.sudoc.feature.ra.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

/**
 * Union des éditeurs.
 *
 */
public class PublisherSA extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "publisher";
	}

	
}