package fr.abes.qualinka.sudoc.filter.rc;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;
import fr.abes.qualinka.sudoc.util.Misc;

/**
 * Attributs utilisés : "isAuthor","scientificWork" <br/>
 * <br/>
 * 
 * Valeur : vrai si et seulement si la RC est auteur du document représenté et celui-ci
 * est une thèse.<br/>
 * <br/>
 */
public class IsAuthorOfThesisRCFilter implements Filter
{

	@Override
	public Set<String> featureSet() {
		return Set.of("isAuthor","scientificWork");
	}

	@Override
	public boolean check(JSONObject data) {
		boolean isAuthor = data.optBoolean("isAuthor");
		JSONArray array = data.optJSONArray("scientificWork");
		String scientificWork = null;
		if(array!= null && array.length() >= 1) {
			scientificWork = array.getString(0);
		}

     	return isAuthor 
     			&& scientificWork!=null 
     			&& Misc.estThese(scientificWork);
	}	
}
