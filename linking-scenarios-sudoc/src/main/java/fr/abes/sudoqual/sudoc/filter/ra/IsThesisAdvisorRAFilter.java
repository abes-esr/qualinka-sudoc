package fr.abes.sudoqual.sudoc.filter.ra;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;

/**
 * Attributs utilisés : "roleSA"<br/>
 * <br/>
 * 
 * Valeur : vrai si et seulement si l'attribut "roleSA" contient le role "thesis_advisor" <br/>
 * <br/>
 */
public class IsThesisAdvisorRAFilter implements Filter
{

	@Override
	public Set<String> featureSet() {
		return Set.of("roleSA");
	}
	@Override
	public boolean check(JSONObject data) {
		JSONObject roleRa = data.optJSONObject("roleSA");

		if(roleRa == null) {
     		 return false;
     	}
     	else {
     		JSONArray tableRoleRa = (JSONArray) roleRa.get("weightedValues");
     		for(Object role : tableRoleRa)
			{
				if(((JSONObject) role).get("value").equals("thesis_advisor")) {
				}
     		}
			return false;
   		}
	}
}