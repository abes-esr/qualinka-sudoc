package fr.abes.sudoqual.sudoqual1.feature.computed;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;
import fr.abes.sudoqual.linking_module.util.CollectionUtils;

public class ThesisTitleSA extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "title";
	}
    		
	@Override
	public boolean check(JSONObject json) {
		if(!json.has("role") || !json.has("scientificWork")) {
			return false;
		}
	
		JSONArray roleArray = json.getJSONArray("role");
		JSONArray scWorkArray = json.getJSONArray("scientificWork");
	
		return roleArray.length() > 0 
				&& roleArray.get(0) != null 
				&& "author".equals(roleArray.getJSONObject(0).get("role"))
				&& scWorkArray.length() > 0
				&& "http://www.sudoc.abes.fr/TheseDeDoctorat".equals(scWorkArray.get(0));
	}
    		
	@Override
	public Set<String> getRelatedFeatures() {
		return CollectionUtils.setFrom("title", "role", "scientificWork");
	}


	
}