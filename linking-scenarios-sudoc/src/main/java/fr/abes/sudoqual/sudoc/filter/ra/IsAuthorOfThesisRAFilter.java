package fr.abes.sudoqual.sudoc.filter.ra;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;

/**
 * Attributs utilisés : "thesisTitleSA"<br/>
 * <br/>
 * 
 * Valeur : vrai si l'attribut  thesisTitleSA contient au moins un titre de thèse.<br/>
 * <br/>
 */
public class IsAuthorOfThesisRAFilter implements Filter
{

	@Override
	public Set<String> featureSet() {
		return Set.of("thesisTitleSA");
	}
	
	@Override
	public boolean check(JSONObject data) {
		JSONArray tit = data.optJSONArray("thesisTitleSA");

		return tit != null && tit.length()>0;
	}	
}