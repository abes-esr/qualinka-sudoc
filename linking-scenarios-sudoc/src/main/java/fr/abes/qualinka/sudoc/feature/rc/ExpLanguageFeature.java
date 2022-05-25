package fr.abes.qualinka.sudoc.feature.rc;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import org.json.JSONObject;

/**
 * Code de langue. La valeur est la langue de la notice 
 * de la rc. Si la valeur d'un code est « und », ce code est supprimé. <br/>
 * <br/>
 * Exemple : <br/>
 * "expLanguage": [ "fre", "eng" ]
 *
 */
public class ExpLanguageFeature extends PreprocessedArrayFeature<String, String> {

	@Override
	protected String buildElementValue(String rawElementValue) {
		return ("und".equals(rawElementValue))? null : rawElementValue;
	}

	@Override
	public JSONObject getElementValidationSchema() {
		return new JSONObject("{'type': 'string'}");
	}

}
