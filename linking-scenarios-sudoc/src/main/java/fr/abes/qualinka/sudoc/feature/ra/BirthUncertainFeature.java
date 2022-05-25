package fr.abes.qualinka.sudoc.feature.ra;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
 * MonoValue. <br/>
 * Exemples: <br/>
 * "birthUncertain": "1901-2000" <br/>
 *
 */
public class BirthUncertainFeature implements RawFeature {

	@Override
	public boolean checkValue(Object value) {
		return value instanceof String && ((String)value).length() <= 9 && ((String)value).contains("-");
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string', 'maxLength': 9, 'pattern': '-'}");
	}

}