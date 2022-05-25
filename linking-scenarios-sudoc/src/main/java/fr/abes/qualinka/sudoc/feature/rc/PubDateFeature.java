package fr.abes.qualinka.sudoc.feature.rc;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;

/**
 * MonoValue. <br/>
 * Date de publication.
 * <br/>
 * Exemple: <br/> 
 * "pubDate": "1945"
 *
 */
public class PubDateFeature implements PreprocessedFeature<String, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(PubDateFeature.class);

	@Override
	public boolean checkValue(Object value) {
		if((value instanceof String) && ((String)value).length() <= 4) {
			try {
				Integer.parseInt((String)value);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string', 'maxLength': 4, 'pattern': '^[0-9]+$'}");
	}

	@Override
	public Integer buildValue(String rawValue) {
		try {
			return Integer.parseInt(rawValue);
		} catch (NumberFormatException e) {
			if(logger.isWarnEnabled()) {
				logger.warn("try to read '{}' as a year: wrong format", rawValue);
			}
			return null;
		}
	}

}
