package fr.abes.qualinka.sudoc.feature.ra;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;
import org.json.JSONObject;

/**
 * MonoValue. A minima : YY et au plus : YYYYMMDD<br/>
 * Exemples: <br/>
 * "birthDate":  "1956 " renvoie "1956"<br/>
 * "birthDate":  "195" renvoie "195"<br/>
 * "birthDate":  "195. " renvoie "195"<br/>
 * "birthDate":  "195? " renvoie "195"<br/>
 * "birthDate":  "195..30 " renvoie "195"<br/>
 *
 */
public class BirthDateFeature implements PreprocessedFeature<String, String> {	
	private static final Pattern p = Pattern.compile("^[0-2][0-9][^a-zA-Z]*$");
	private static final Pattern p2 = Pattern.compile("^\\s*([0-9]+).*$");
	
	@Override
	public boolean checkValue(Object value) {
		if (value instanceof String) {
			String valueTrim= ((String)value).trim();
			return valueTrim.length() >= 2 && valueTrim.length() <= 8 && 
					valueTrim.length() != 5 && valueTrim.length() != 7 && p.matcher(valueTrim).matches();
		}
		else 
			return false; 		
	}

	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'string', 'pattern': '^\\\\s*([0-9]{2}|[0-9]{4}|[0-9]{6}|[0-9]{8})\\\\s*$'}");
	}

	@Override
	public String buildValue(String value) {
		Matcher m= p2.matcher(value);
		return m.matches() ? m.group(1) : null;
	}

}
