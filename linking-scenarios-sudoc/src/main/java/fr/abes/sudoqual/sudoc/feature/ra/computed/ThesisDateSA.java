package fr.abes.sudoqual.sudoc.feature.ra.computed;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.linking_module.feature.AJSONArrayUnionComputedFeature;
import fr.abes.sudoqual.linking_module.util.CollectionUtils;

/**
 * Aggrégation des "pubDate" relatives à une thèse dont la rc est l'auteur.
 *
 */
public class ThesisDateSA extends AJSONArrayUnionComputedFeature {
	
	private static final Logger logger = LoggerFactory.getLogger(ThesisDateSA.class);

	@Override
	public String getRelatedFeature() {
		return "pubDate";
	}
    		
	@Override
	public boolean check(JSONObject json) {
		if(!json.has("isAuthor") || !json.has("scientificWork") || !json.has("pubDate")) {
			return false;
		}
	
		boolean isAuthor = json.getBoolean("isAuthor");
		JSONArray scWorkArray = json.getJSONArray("scientificWork");
	
		return isAuthor
				&& scWorkArray.length() > 0
				&& "http://www.sudoc.abes.fr/TheseDeDoctorat".equals(scWorkArray.get(0));
	}
    		
	@Override
	public Set<String> getRelatedFeatures() {
		return CollectionUtils.setFrom("title", "isAuthor", "scientificWork");
	}

	
}