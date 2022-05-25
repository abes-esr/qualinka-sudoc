package fr.abes.qualinka.sudoc.feature.rc;

import org.json.JSONObject;

import fr.abes.qualinka.sudoc.feature.PersonNameFeature;


/**
 * Liste des appellations des cocontributeurs de rc (dans la notice de la rc et sauf rc).
 *  
 * Example: <br/>
 * "cocontributor" : [ "Mugnier, Marie-Laure", "Chein, Michel", "Leclere, Michel" ] <br/>
 */
public class CocontributorFeature extends PersonNameFeature {

	@Override
	protected boolean checkElementValue(Object object) {
		if(object instanceof JSONObject) {
			JSONObject json = (JSONObject) object;
			if(json.has("cocontributor")) {
				return super.checkElementValue(json.get("cocontributor"));
			}
		} else if (object instanceof String) {
			return super.checkElementValue(object);
		}
		return false;
	}

	@Override
	protected JSONObject getElementValidationSchema() {
		return new JSONObject("{\"oneOf\" : [\n" +
				"    {\"type\": \"string\"},\n" +
				"    {\"type\": \"object\", \"required\" : [ \"cocontributor\" ], " +
				"      'properties': { 'cocontributor': " + super.getElementValidationSchema() + '}' +
				"    },\n" +
				"  ]}");
	}
	
	@Override
	protected JSONObject buildElementValue(Object object) {
		if(object instanceof JSONObject) {
			JSONObject json = (JSONObject) object;
			if(json.has("idcocontrib") && json.has("person") 
					&& (json.get("idcocontrib") == null || json.get("idcocontrib").equals(json.get("person")))) {
				return null;
			}
			object = json.get("cocontributor");
		}
		return super.buildElementValue(object);
		
	}
}
