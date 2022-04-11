package fr.abes.sudoqual.sudoc.feature.rc;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
 * MonoValue. <br/>
 * URI désignant le genre littéraire de la resource.
 * 
 * Exemple : 
 * "genre" : "http:\/\/www.sudoc.abes.fr\/Texte_non_litteraire" <br/>
 */
public class GenreFeature implements RawFeature {

	@Override
	public boolean checkValue(Object value) {
		return value instanceof String;
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string'}");
	}
}
