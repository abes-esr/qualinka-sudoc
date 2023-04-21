package fr.abes.qualinka.these.feature.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

public class TitleSA extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "title";
	}

	
}