package fr.abes.sudoqual.example.feature.preprocessed;

import fr.abes.sudoqual.example.util.StringUtils;
import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;
import org.json.JSONObject;

public class SourceFeature implements PreprocessedFeature<String, String> {
	
	@Override
	public String buildValue(String value) {
		return StringUtils.normalize(value);
	}
	
	@Override
	public  String getRelatedRawFeature() {
		return "source";
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string'}");
	}
}