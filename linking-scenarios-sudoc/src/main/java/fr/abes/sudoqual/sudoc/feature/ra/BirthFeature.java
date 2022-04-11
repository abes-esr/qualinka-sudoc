package fr.abes.sudoqual.sudoc.feature.ra;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
 * MonoValue. <br/>
 * Exemples: <br/>
 * "birth":  "1956" <br/>
 * "birth":  "195" <br/>
 * "birth":  "195. " <br/>
 * "birth":  "195? " <br/>
 *
 */
public class BirthFeature implements RawFeature {

	@Override
	public boolean checkValue(Object value) {
		return value instanceof String && ((String)value).length() <= 4;
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string', 'maxLength': 4}");
	}
}
