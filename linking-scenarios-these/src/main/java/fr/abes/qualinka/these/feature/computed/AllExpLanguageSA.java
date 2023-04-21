package fr.abes.qualinka.these.feature.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONObjectWeightedUnionComputedFeature;
import org.json.JSONArray;

public class AllExpLanguageSA extends AJSONObjectWeightedUnionComputedFeature {


	@Override
	public String process(Object o) {
		return ((JSONArray)o).getString(0);
	}
	

	@Override
	public String getRelatedFeature() {
		return "expLanguage";
	}

}
