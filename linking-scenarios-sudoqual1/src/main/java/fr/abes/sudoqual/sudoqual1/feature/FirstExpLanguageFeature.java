package fr.abes.sudoqual.sudoqual1.feature;

import fr.abes.sudoqual.sudoqual1.util.adapter.AbstractSudoQual1Feature;

public class FirstExpLanguageFeature extends AbstractSudoQual1Feature<String> {

	//private static final Logger logger = LoggerFactory.getLogger(FirstExpLanguageFeature.class);

	public FirstExpLanguageFeature() {
		super(false);
	}

	@Override
	public String getKey() {
		return "firstExpLanguage";
	}

	@Override
	protected Object process(String lang) {
		return ("und".equals(lang))? null : lang;
	}


}
