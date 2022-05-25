package fr.abes.qualinka.sudoc.feature;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
 * MonoValue <br/>
 * Date de création de la notice contenant la rc ou contenant la ra, au format YYYYMMDD.<br/>
 * 
 * Exemple : <br/>
 * "dateCreationNotice" : "20140212"
 *
 */
public class DateCreationNoticeFeature implements RawFeature {
	
	@Override
	public boolean checkValue(Object value) {
		return (value instanceof String) && ((String)value).length() == 8;
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string', 'pattern': '^[0-9]{8}$' }");
	}
}
