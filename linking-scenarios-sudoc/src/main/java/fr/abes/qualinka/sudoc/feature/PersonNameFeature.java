package fr.abes.qualinka.sudoc.feature;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.qualinka.sudoc.util.FirstNameComparison;
import fr.abes.qualinka.sudoc.util.NameComparison;

/**
 * Liste ordonnée de (nom, prénom) => forme retenue en 
 * premier puis les formes rejetées et les formes parallèles. <br/>
 * <br/>
 * Exemples: <br/>
 * "personName": [ "Vian, Boris", "Sullivan, Vernon" ] <br/>
 * OR <br/>
 * "personName": [ {"last_name": "Vian", "first_name": "Boris"}, {"last_name": "Sullivan", "first_name": "Vernon"} ] <br/>
 */
public class PersonNameFeature extends PreprocessedArrayFeature<Object, JSONObject>  {
	
	private static String LASTNAME_KEY = "last_name";
	private static String FIRSTNAME_KEY = "first_name";
	private static String APPELLATION_KEY = "appellation";
	
	@Override
	protected boolean checkElementValue(Object object) {
		boolean res = false;
		if (object instanceof String) {
			res = true;
		} else if(object instanceof JSONObject) {
			JSONObject json = (JSONObject) object;
			res = json.has(LASTNAME_KEY);
			res = res || json.has(APPELLATION_KEY);
		}
		return res;
	}

	@Override
	protected JSONObject getElementValidationSchema() {
		return new JSONObject("{\"oneOf\" : [\n" +
				"    {\"type\": \"string\"},\n" +
				"    {\"type\": \"object\", \"required\" : [ \"last_name\" ]},\n" +
				"    {\"type\": \"object\", \"required\" : [ \"appellation\" ]}\n" +
				"  ]}");
	}

	@Override
	protected JSONObject buildElementValue(Object object) {
		String last_name = null;
		String first_name = null;
		
		if (object instanceof String){
			object = NameComparison.processAppellation((String)object);
		}

		if(object instanceof JSONObject) {
			JSONObject json = (JSONObject) object;
			if(json.has(APPELLATION_KEY)) {
				json = NameComparison.processAppellation(json.optString(APPELLATION_KEY));
			}
    		last_name = json.optString(LASTNAME_KEY);
    		first_name = json.optString(FIRSTNAME_KEY);
		} else {
			throw new IllegalArgumentException("Input object must be either a String either a JSONObject");
		}

		JSONObject jsonFirstName = new JSONObject();
		jsonFirstName.put("raw", first_name);
		jsonFirstName.put("analyzed", FirstNameComparison.analyze(first_name));
		
		JSONObject jsonLastName = new JSONObject();
		jsonLastName.put("raw", last_name);
		jsonLastName.put("normalized", NameComparison.normalize(last_name));

		JSONObject json2Return = new JSONObject();
		json2Return.put(FIRSTNAME_KEY, jsonFirstName);
		json2Return.put(LASTNAME_KEY, jsonLastName);
		
		return json2Return;
	}

}
