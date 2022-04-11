package fr.abes.sudoqual.sudoc.filter.rc;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;
import fr.abes.sudoqual.sudoc.util.Misc;

/**
 * Attributs utilisés : "genre" <br/>
 * <br/>
 * 
 * Valeur : vrai si et seulement si le genre est de type "genreLitteraire".<br/>
 * <br/>
 * 
 */
public class IsLiteraryGenreRCFilter implements Filter {

	@Override
	public Set<String> featureSet() {
		return Set.of("genre");
	}

	@Override
	public boolean check(JSONObject data) {
		if(data.has("genre")) {
			return Misc.genreLitteraire(data.getString("genre"));
		}
		return false;
	}
   	
}