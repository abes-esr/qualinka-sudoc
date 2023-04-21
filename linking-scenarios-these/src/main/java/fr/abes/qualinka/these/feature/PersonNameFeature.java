package fr.abes.qualinka.these.feature;

import fr.abes.qualinka.these.util.FirstNameComparison;
import fr.abes.qualinka.these.util.Names;
import fr.abes.qualinka.these.util.adapter.AbstractSudoQual1Feature;
import org.json.JSONObject;

public class PersonNameFeature extends AbstractSudoQual1Feature<JSONObject> {

	//private static final Logger logger = LoggerFactory.getLogger(PersonNameFeature.class);

	public PersonNameFeature() {
		super(false);
	}
	
	@Override
	public String getKey() {
		return "personName";
	}
	
	@Override
	protected JSONObject process(JSONObject json)
	{
		String last_name = null;
		String first_name = null;
		if(json.has("last_name")) {
    		last_name = json.getString("last_name");
    		first_name = json.getString("first_name");
		}

		if(last_name == null || last_name.isEmpty()) {
			JSONObject tmp = Names.processAppellation(json.getString("appellation"));
			last_name = tmp.getString ("last_name");
			first_name = tmp.getString("first_name");
		} 

		JSONObject jsonFirstName = new JSONObject();
		jsonFirstName.put("raw", first_name);
		jsonFirstName.put("analyzed", FirstNameComparison.analyze(first_name));
		
		JSONObject jsonLastName = new JSONObject();
		jsonLastName.put("raw", last_name);
		jsonLastName.put("normalized", Names.normalize(last_name));

		JSONObject json2Return = new JSONObject();
		json2Return.put("first_name", jsonFirstName);
		json2Return.put("last_name", jsonLastName);
		
		return json2Return;
	}

}
