package fr.abes.sudoqual.example.feature.preprocessed;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.abes.sudoqual.example.util.StringUtils;
import fr.abes.sudoqual.linking_module.exception.ResourceManagerException;
import fr.abes.sudoqual.linking_module.resources.ResourceManager;
import fr.abes.sudoqual.rule_engine.exception.FeatureConfigurationException;
import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;
import fr.abes.sudoqual.util.ConfigurationProperties;

public class TitleWordsFeature implements PreprocessedFeature<String, JSONArray> {

	private static final Logger logger = LoggerFactory.getLogger(NameFeature.class);
	
	private static final String STOP_WORDS_FILE_KEY = "stopWordsFile";
	private List<String> stopWords = null;

	
	@Override
	public void configure(ConfigurationProperties properties) throws FeatureConfigurationException {
		try {
			this.stopWords = ResourceManager.instance().loadList(properties.getLookupPaths(), properties.get(STOP_WORDS_FILE_KEY));
		} catch (ResourceManagerException e) {
			throw new FeatureConfigurationException("An error occured during feature configuration.", e);
		}
	}
	
	@Override
	public JSONArray buildValue(String value) {
		List<String> words = new LinkedList<String>(Arrays.asList(StringUtils.normalize(value).split(" ")));
		words.removeAll(this.stopWords);
		return new JSONArray(words);
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
