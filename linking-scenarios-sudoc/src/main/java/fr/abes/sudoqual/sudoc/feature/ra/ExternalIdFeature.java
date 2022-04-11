package fr.abes.sudoqual.sudoc.feature.ra;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import fr.abes.sudoqual.sudoc.util.FirstNameComparison;
import fr.abes.sudoqual.sudoc.util.NameComparison;

/**
 * Liste d'identifiants externes <br/>
 * Exemples: <br/>
 * "externalId": [ "ORCID:0000000293615295", "VIAF:238738325" ]
 */
public class ExternalIdFeature implements RawFeature {
	
	@Override
	public boolean checkValue(Object object) {
		return object instanceof JSONArray;
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'array', 'items': {'type': 'string'}}");
	}
}
