package fr.abes.qualinka.these.thesedir.feature;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;
import fr.abes.qualinka.these.util.FirstNameComparison;
import fr.abes.qualinka.these.util.Names;
import org.json.JSONArray;
import org.json.JSONObject;

public class CojuryFeature implements PreprocessedFeature<JSONArray, JSONArray> {
	
	@Override
	public JSONArray buildValue(JSONArray value) {
		JSONArray res = new JSONArray();
		JSONArray array = (JSONArray) value;
		for (Object o : array) {
			if (o instanceof JSONObject) {
				JSONObject json = (JSONObject) o;
				
				String appellation = (String) json.get("cojury");
				JSONObject tmp = Names.processAppellation(appellation);
				String last_name = (String) tmp.get("last_name");
				String first_name = (String) tmp.get("first_name");

				JSONObject jsonFirstName = new JSONObject();
				jsonFirstName.put("raw", first_name);
				jsonFirstName.put("analyzed", FirstNameComparison.analyze(first_name));

				JSONObject jsonLastName = new JSONObject();
				jsonLastName.put("raw", last_name);
				jsonLastName.put("normalized", Names.normalize(last_name));

				JSONObject json2Return = new JSONObject();
				json2Return.put("first_name", jsonFirstName);
				json2Return.put("last_name", jsonLastName);
				
				res.put(json2Return);
			} else {
				// TODO log 
			}
		}		
		return res;
	}

}
