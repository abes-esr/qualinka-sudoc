package fr.abes.sudoqual.sudoqual1.feature;

import org.json.JSONObject;

import fr.abes.sudoqual.sudoqual1.util.FirstNameComparison;
import fr.abes.sudoqual.sudoqual1.util.Names;
import fr.abes.sudoqual.sudoqual1.util.adapter.AbstractSudoQual1Feature;

/**
 * 
 * Example: <br/>
 * [
 *   { 'cocontributor': 'will be, ignored', 'person': 'a',  'idcocontrib': 'a' }, 
 *   { 'cocontributor': 'Mugnier, Marie-Laure', 'person': 'a',  'idcocontrib': 'b' }, 
 *   { 'cocontributor': 'Chein, Michel',  'person': 'a', 'idcocontrib': 'c'},
 * ]
 */
public class CoContributorFeature extends AbstractSudoQual1Feature<JSONObject>{

	//private static final Logger logger = LoggerFactory.getLogger(CoContributorFeature.class);
	public static final String NAME = "cocontributor";
	
	public CoContributorFeature() {
		super(false);
	}
	
	@Override
	public String getKey() {
		return NAME;
	}

	@Override
	protected JSONObject process(JSONObject json) {
		Object person = json.get("person");
		if (person != null && !person.equals(json.get("idcocontrib"))) 
		{
			String appellation = (String) json.get("cocontributor");
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
		
			return json2Return;
		}
		return null;
	}


}
