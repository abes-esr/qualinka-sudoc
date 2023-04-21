package fr.abes.qualinka.these.feature;

import fr.abes.qualinka.these.util.Names;
import fr.abes.qualinka.these.util.adapter.AbstractSudoQual1Feature;
import org.json.JSONObject;

public class PublisherFeature extends AbstractSudoQual1Feature<String> {

	private static final String[] EDITOR_LABELS = { "ed", "editeur", "editeurs", "editions", "edition" };

	public PublisherFeature() {
		super(false);
	}

	@Override
	public String getKey() {
		return "publisher";
	}

	@Override
	protected Object process(String publisher) {
		if (publisher.replaceAll("[ \\[\\].-?]", "").toLowerCase().equals("sn")) {
			// error("a [sn] chain not considered in publisher attribute:
			// "+json.get("publisher"));
			return null;
		}
		JSONObject json2Return = new JSONObject();
		json2Return.put("original", publisher);
		String s = publisher.replaceAll("[ \\[\\].-?]", " ");
		s = Names.normalize(s);
		s = Names.normalize(s);
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

}
