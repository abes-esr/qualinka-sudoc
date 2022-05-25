package fr.abes.qualinka.sudoc.feature.rc;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.qualinka.sudoc.util.StringUtils;

/**
 * Liste des collectivités associées. <br/>
 * 
 * Exemple : <br/>
 * "corporateBody": ["Université de Grenoble","Université de Toulouse"]  
 *
 */
public class CorporateBodyFeature extends PreprocessedArrayFeature<String, String> {

	@Override
	protected String buildElementValue(String rawElementValue) {
		return StringUtils.normalize(rawElementValue);
	}

}
