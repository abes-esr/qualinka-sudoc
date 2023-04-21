package fr.abes.qualinka.these.feature.computed;

import fr.abes.sudoqual.linking_module.feature.AJSONObjectWeightedUnionComputedFeature;
import org.json.JSONArray;

public class GrpRoleSA extends AJSONObjectWeightedUnionComputedFeature {
	
	@Override
	public String process(Object o) {
		return ((JSONArray)o).getJSONObject(0).getString("group");
	}
	
	@Override
	public String getRelatedFeature() {
		return "role";
	}

}
