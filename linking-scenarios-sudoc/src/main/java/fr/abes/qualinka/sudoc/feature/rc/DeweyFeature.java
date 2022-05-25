package fr.abes.qualinka.sudoc.feature.rc;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
 * Liste de codes Dewey. <br/>
 * Exemple: <br/>
 * "dewey":  ["629.83", "006.338"] <br/>
 *
 */
public class DeweyFeature implements RawFeature {

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'array', 'items': {'type': 'string'}}");
	}
}