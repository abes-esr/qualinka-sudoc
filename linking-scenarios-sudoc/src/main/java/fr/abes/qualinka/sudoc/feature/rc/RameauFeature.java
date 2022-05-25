package fr.abes.qualinka.sudoc.feature.rc;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
 * Liste de mot clées Rameau. <br/>
 * 
 * Exemple: <br/>
 * "rameau":  ["Robotique", "Temps réel (informatique)", "Architecture des ordinateurs"] <br/>
 *
 */
public class RameauFeature implements RawFeature {
	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'array', 'items': {'type': 'string'}}");
	}
}
