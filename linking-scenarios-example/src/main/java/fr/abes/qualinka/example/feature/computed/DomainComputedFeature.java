package fr.abes.qualinka.example.feature.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

public class DomainComputedFeature extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "domain";
	}
	
}
