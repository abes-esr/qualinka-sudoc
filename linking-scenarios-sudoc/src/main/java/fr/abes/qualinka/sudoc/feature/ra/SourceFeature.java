package fr.abes.qualinka.sudoc.feature.ra;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.qualinka.sudoc.util.RegexUtils;

/**
 * Liste des sources de la RA. <br/>
 * 
 * Exemple:
 * "source": [
 *			"Construction d\u2019un Web sémantique multi-points de vue / par Than Le Bach ; sous la direction de Rose Dieng-Kuntz. Paris, 2006",
 *			"Représentation et apprentissage de concepts et d'ontologies pour le web sémantique / par Alexandre Delteil ; sous la dir. de Rose Dieng-Kuntz. - 2002"
 *		]
 */
public class SourceFeature extends PreprocessedArrayFeature<String, JSONObject> {
	
	private static final Pattern SOURCE_DATE_PATTERN = Pattern.compile(",\\s*([0-9]{4})([^0-9]|$)");


	@Override
	protected JSONObject buildElementValue(String value) {
		if(value.isBlank()) {
			return null;
		}
		
		JSONObject res = new JSONObject();
		res.put("raw", value);
		
		// recovering the year portion of the source : the four first non blank chars
		// after the last comma (if it exists) if there are digits
		String sourceYearString = RegexUtils.findLast(SOURCE_DATE_PATTERN, value, 1);
		try {
			res.put("pubYear", Integer.parseInt(sourceYearString));
		} catch (NumberFormatException e) {
			// do nothing
		}
		
		// recovering the title portion of the source :
		// - the substring before the first slash when there is a slash
		// - or the entire string if there is no slash
		// cf. http://documentation.abes.fr/sudoc/formats/unma/zones/810.htm
		String title = (value.indexOf(" / ") >= 0)? value.split(" / ")[0] : value.split("/")[0];
		title = StringUtils.normalizeSpace(title.toLowerCase());
		title = StringUtils.stripAccents(title);
		res.put("title", title);
		
		return res;
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'array', 'items': {'type': 'string'}}");
	}
	
}
