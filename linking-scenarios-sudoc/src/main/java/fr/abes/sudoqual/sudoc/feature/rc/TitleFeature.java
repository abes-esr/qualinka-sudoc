package fr.abes.sudoqual.sudoc.feature.rc;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.exception.ResourceManagerException;
import fr.abes.sudoqual.linking_module.resources.ResourceManager;
import fr.abes.sudoqual.rule_engine.exception.FeatureConfigurationException;
import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.sudoqual.sudoc.util.Titles;
import fr.abes.sudoqual.util.ConfigurationProperties;

/**
 * Liste des titres du document.
 * 
 * Exemple: <br/>
 * ["Co-design approaches for dependable networked control systems"]
 *
 */
public class TitleFeature extends PreprocessedArrayFeature<String, JSONObject>{
	
	private static final String DICO_KEY = "sudocWordFreqDico";
	private Map<String,Integer> dico = null;
	
	@Override
	public void configure(ConfigurationProperties properties) throws FeatureConfigurationException {
		try {
			this.dico = ResourceManager.instance().loadMap(properties.getLookupPaths(), properties.get(DICO_KEY));
		} catch (ResourceManagerException e) {
			throw new FeatureConfigurationException("An error occured during feature configuration.", e);
		}
	}

	@Override
	protected JSONObject buildElementValue(String rawElementValue) {
		JSONObject json2Return=new JSONObject();
		json2Return.put("raw",rawElementValue);
		json2Return.put("normalized", StringUtils.normalizeSpace(StringUtils.stripAccents(rawElementValue.toLowerCase())));
		json2Return.put("uncommonWords",Titles.process(rawElementValue, this.dico));
		return json2Return;
	}

	@Override
	public JSONObject getElementValidationSchema() {
		return new JSONObject("{'type': 'string', 'minLength': 1}");
	}

}
