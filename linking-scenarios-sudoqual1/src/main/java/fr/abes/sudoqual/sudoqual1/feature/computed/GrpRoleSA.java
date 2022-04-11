package fr.abes.sudoqual.sudoqual1.feature.computed;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.feature.AJSONObjectWeightedUnionComputedFeature;

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
