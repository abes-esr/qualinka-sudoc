package fr.abes.qualinka.sudoc.feature.ra.computed;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;
import fr.abes.sudoqual.linking_module.util.CollectionUtils;

/**
 * Aggrégation des titres des thèses dont la rc est l'auteur.
 *
 */
public class ThesisTitleSA extends AJSONArrayUnionComputedFeature {

	@Override
	public String getRelatedFeature() {
		return "title";
	}
	
	@Override
	public Set<String> getRelatedFeatures() {
		return CollectionUtils.setFrom("title", "isAuthor", "scientificWork");
	}
    		
	@Override
	public boolean check(JSONObject json) {
		if(!json.has("isAuthor") || !json.has("scientificWork")) {
			return false;
		}
	
		boolean isAuthor = json.getBoolean("isAuthor");
		JSONArray scWorkArray = json.getJSONArray("scientificWork");
	
		return isAuthor
				&& scWorkArray.length() > 0
				&& "http://www.sudoc.abes.fr/TheseDeDoctorat".equals(scWorkArray.get(0));
	}
    		
	
}