package fr.abes.qualinka.sudoc.feature.rc;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.qualinka.sudoc.util.NameComparison;

/**
 * liste de noms d'éditeur. Multivalué car 1 rc peut avoir plusieurs éditeurs. 
 * Les valeurs du type « s.n. » sont supprimées. <br/>
 * <br/>
 * Exemple : <br/>
 * "publisher": ["[diff. Sofés]", "éd. NordSud"]
 *
 */
public class PublisherFeature extends  PreprocessedArrayFeature<String, JSONObject> {

	private static final String[] EDITOR_LABELS = { "ed", "editeur", "editeurs", "editions", "edition" };

	@Override
	protected JSONObject buildElementValue(String rawPublisher) {
		if (rawPublisher.replaceAll("[ \\[\\].-?]", "").toLowerCase().equals("sn")) {
			// error("a [sn] chain not considered in publisher attribute:
			// "+json.get("publisher"));
			return null;
		}
		JSONObject json2Return = new JSONObject();
		json2Return.put("original", rawPublisher);
		String s = rawPublisher.replaceAll("[ \\[\\].-?]", " ");
		s = NameComparison.normalize(s);
		s = NameComparison.normalize(s);
		for (String label : EDITOR_LABELS) {
			if (s.endsWith(" " + label)) {
				s = s.substring(0, s.length() - 1 - label.length()).trim();
				break;
			}
			if (s.startsWith(label + " ")) {
				s = s.substring(label.length() + 1).trim();
				break;
			}
		}
		json2Return.put("comp_value", s);
		return json2Return;
	}

	@Override
	public JSONObject getElementValidationSchema() {
		return new JSONObject("{'type': 'string'}");
	}


}
