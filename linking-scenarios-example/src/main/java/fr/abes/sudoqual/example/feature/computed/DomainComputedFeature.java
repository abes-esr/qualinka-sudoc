package fr.abes.sudoqual.example.feature.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

public class DomainComputedFeature extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "domain";
	}
	
}
