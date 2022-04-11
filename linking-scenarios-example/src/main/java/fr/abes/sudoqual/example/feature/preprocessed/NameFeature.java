package fr.abes.sudoqual.example.feature.preprocessed;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.abes.sudoqual.example.util.StringUtils;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;

public class NameFeature implements PreprocessedFeature<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(NameFeature.class);

	@Override
	public String getKey() {
		return "name";
	}
	
	@Override
	public String buildValue(String value) {
		return StringUtils.normalize(value);
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string'}");
	}
}
