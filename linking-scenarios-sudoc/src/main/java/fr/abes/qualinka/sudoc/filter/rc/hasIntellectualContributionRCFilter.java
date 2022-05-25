package fr.abes.qualinka.sudoc.filter.rc;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;

/**
 * Attributs utilisés : hasIntellectualContribution<br/>
 * <br/>
 * 
 * Valeur : vrai si et seulement si l'attribut hasIntellectualContribution est vrai.<br/>
 * <br/>
 */
public class hasIntellectualContributionRCFilter implements Filter {

	@Override
	public Set<String> featureSet() {
		return Set.of("hasIntellectualContribution");
	}

	@Override
	public boolean check(JSONObject data) {
		return data.optBoolean("hasIntellectualContribution");
	}

}
