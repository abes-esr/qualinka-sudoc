package fr.abes.sudoqual.example.feature.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

public class TitleWordsComputedFeature extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "titleWords";
	}
	
}
