package fr.abes.qualinka.example.feature.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

public class TitleWordsComputedFeature extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "titleWords";
	}
	
}
