package fr.abes.sudoqual.sudoc.filter.rc;
import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;

/**
 * Attributs utilisés : "role" <br/>
 * <br/>
 * 
 * Valeur : vrai si et seulement si le premier role de l'attribut role est "thesis_advisor". <br/>
 * <br/>
 */
public class IsThesisAdvisorRCFilter implements Filter 
{

	@Override
	public Set<String> featureSet() {
		return Set.of("role");
	}

	@Override
	public boolean check(JSONObject data) {
		if(data.has("role") && data.getJSONArray("role").length() >= 1) {
			return "thesis_advisor".equals(data.getJSONArray("role").getString(0));
		}
		return false;
	}
   	
}