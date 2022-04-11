package fr.abes.sudoqual.sudoc.filter.ra;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;
import fr.abes.sudoqual.sudoc.util.Misc;

/**
 * Attributs utilisés : genreSA<br/>
 * <br/>
 * 
 * Valeur : vrai si et seulement si l'attribut "genreSA" contient un genre de type littéraire. <br/>
 * <br/>
 */
public class HasLiteraryGenreRAFilter implements Filter
{

	@Override
	public Set<String> featureSet() {
		return Set.of("genreSA");
	}

	@Override
	public boolean check(JSONObject data) {
		if(data.has("genreSA")) {
			JSONObject genreSA = data.getJSONObject("genreSA");
				
			JSONArray genres = (JSONArray) genreSA.get("weightedValues");
			for(Object wgenre : genres)
			{
				String genre = (String) ((JSONObject) wgenre).get("value");
	               if(Misc.genreLitteraire(genre)) {
	            	   return true; 
	               }
			}
		}
		return false ; 
	}
}