package fr.abes.qualinka.example.feature.preprocessed;

import fr.abes.qualinka.example.util.StringUtils;
import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;
import org.json.JSONObject;

public class TitleFeature implements PreprocessedFeature<String, String> {
	
	@Override
	public String buildValue(String value) {
		return StringUtils.normalize(value);
	}
	
	@Override
	public  String getRelatedRawFeature() {
		return "title";
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string'}");
	}
}
