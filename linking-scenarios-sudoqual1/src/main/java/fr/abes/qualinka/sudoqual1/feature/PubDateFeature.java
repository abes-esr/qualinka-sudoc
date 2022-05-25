package fr.abes.qualinka.sudoqual1.feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.qualinka.sudoqual1.util.adapter.AbstractSudoQual1Feature;

public class PubDateFeature extends AbstractSudoQual1Feature<String> {

	private static final Logger logger = LoggerFactory.getLogger(PubDateFeature.class);

	public PubDateFeature() {
		super(true);
	}
	
	@Override
	public String getKey() {
		return "pubDate";
	}

	@Override
	protected Object process(String value) {
		try {
			return Integer.decode(value);
		} catch (NumberFormatException e) {
			if(logger.isWarnEnabled()) {
				logger.warn("try to decode '{}' as a year: wrong format", value);
			}
			return null;
		}
	}

}
