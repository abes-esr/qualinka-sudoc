package fr.abes.sudoqual.sudoc.feature.rc;

import org.json.JSONArray;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
 * Liste de mots clés. <br/>
 * 
 * Exemple: <br/>
 * "keyword": ["AUV", "Échantillonnage variable", "Système LPV", "Commande robuste"] <br/>
 *
 */
public class KeywordFeature implements RawFeature {
	
	public boolean checkValue(Object value) {
		boolean res = value != null && value instanceof JSONArray;
		if(res) {
			for(Object o : (JSONArray)value) {
				if(!(o instanceof String)) {
					return false;
				}
			}
		}
		return res;
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'array', 'items': {'type': 'string'}}");
	}
}
