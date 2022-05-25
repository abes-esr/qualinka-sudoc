package fr.abes.qualinka.sudoc.feature.rc;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
 * URI désignant différents types de travail scientifique.
 * 
 * Exemple: <br/>
 * 
 * "scientificWork": ["http://www.sudoc.abes.fr/These"]
 *
 */
public class ScientificWorkFeature implements RawFeature {
	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'array', 'items': {'type': 'string'}}");
	}
}
