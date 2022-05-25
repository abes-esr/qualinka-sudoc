package fr.abes.qualinka.sudoqual1.feature.computed;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.feature.AJSONObjectWeightedUnionComputedFeature;

public class RoleSA extends AJSONObjectWeightedUnionComputedFeature {
	
	@Override
	public String process(Object o) {
		return ((JSONArray)o).getJSONObject(0).getString("role");
	}
	
	@Override
	public String getRelatedFeature() {
		return "role";
	}

}