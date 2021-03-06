package fr.abes.qualinka.sudoqual1.feature;

import fr.abes.qualinka.sudoqual1.util.adapter.AbstractSudoQual1Feature;

public class ExpLanguageFeature extends AbstractSudoQual1Feature<String> {

	//private static final Logger logger = LoggerFactory.getLogger(ExpLanguageFeature.class);

	public ExpLanguageFeature() {
		super(false);
	}

	@Override
	public String getKey() {
		return "expLanguage";
	}

	@Override
	protected Object process(String lang) {
		return ("und".equals(lang))? null : lang;
	}


}
