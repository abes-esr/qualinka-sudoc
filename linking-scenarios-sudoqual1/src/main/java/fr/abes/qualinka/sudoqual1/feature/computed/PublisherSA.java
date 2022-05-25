package fr.abes.qualinka.sudoqual1.feature.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

public class PublisherSA extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "publisher";
	}

	
}