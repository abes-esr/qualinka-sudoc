package fr.abes.qualinka.these.feature;

import fr.abes.sudoqual.linking_module.exception.ResourceManagerException;
import fr.abes.sudoqual.linking_module.resources.ResourceManager;
import fr.abes.sudoqual.rule_engine.exception.FeatureConfigurationException;
import fr.abes.qualinka.these.util.Titles;
import fr.abes.qualinka.these.util.adapter.AbstractSudoQual1Feature;
import fr.abes.sudoqual.util.ConfigurationProperties;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.Map;

/**
 * Exemple:
 * [ "Ceci n'est pas un titre" ] 
 *
 */
public class TitleFeature extends AbstractSudoQual1Feature<String> {
	
	private static final String DICO_KEY = "sudocWordFreqDico";
	private Map<String,Integer> dico = null;

	public TitleFeature() {
		super(false);
	}
	
	@Override
	public void configure(ConfigurationProperties properties) throws FeatureConfigurationException {
		try {
			this.dico = ResourceManager.instance().loadMap(properties.getLookupPaths(), properties.get(DICO_KEY));
		} catch (ResourceManagerException e) {
			throw new FeatureConfigurationException("An error occured during feature configuration.", e);
		}
	}
	
	@Override
	public String getKey() {
		return "title";
	}

	@Override
	protected Object process(String title) {
		JSONObject json2Return=new JSONObject();
		json2Return.put("raw",title);
		json2Return.put("normalized", StringUtils.normalizeSpace(StringUtils.stripAccents(title.toLowerCase())));
		json2Return.put("uncommonWords",Titles.process(title, this.dico));
		return json2Return;
	}

}