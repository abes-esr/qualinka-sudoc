package fr.abes.qualinka.sudoc.feature.rc;

import fr.abes.sudoqual.rule_engine.feature.RawFeature;
import org.json.JSONObject;

/**
* Pour chaque code de rôle présent dans une rc, on récupère le libellé en anglais. 
* On associe au rôle un group, qui peut avoir la valeur "" (i.e. la chaîne vide représente: n'appartient à aucun type).   
* <br/>
* Exemples :<br/> 
* "role" : ["director", "founder"] <br/> 
* "role" : ["composer"]  <br/> 
*
*/
public class RoleFeature implements RawFeature {
	@Override
	public JSONObject getValidationSchema() {
		return new JSONObject("{'type': 'array', 'items': {'type': 'string'}}");
	}
}
