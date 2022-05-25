package fr.abes.qualinka.sudoc.feature.ra.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;

/**
 * Union des concontributeurs.
 *
 */
public class CocontributorSA extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "cocontributor";
	}

	
}