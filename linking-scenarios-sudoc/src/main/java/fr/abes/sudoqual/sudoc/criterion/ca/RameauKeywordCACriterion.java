/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.ca;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.sudoqual.sudoc.old.ISimilarity;
import fr.abes.sudoqual.sudoc.util.Utilities;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 * Attributs utilisés : <br/>
 * * source: "keyword", "rameau" <br/>
 * * target: "rameauKeywordSA" <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On considère les libellés rameau comme des mots-clé ; on a donc que des
 * mots-clé. On prend la liste pondérée des mots-clé de la rc et la liste
 * pondérée des mots-clé de la ra. On fait une similarité cosinus sur les poids
 * respectifs des mots-clé de ces deux listes.<br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 2: si proximité >= 0.7
 * * 1: si proximité >= 0.3
 * * neutral: sinon
 */
public class RameauKeywordCACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 2, false);
	private static final Sigma sigma = new Sigma();
	
	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("keyword", "rameau");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("rameauKeywordSA");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if ((!source.has("keyword") && !source.has("rameau")) || !target.has("rameauKeywordSA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		JSONArray keywordRc = source.optJSONArray("keyword");
		JSONArray rameauRc = source.optJSONArray("rameau");

		JSONObject keywordRa = target.getJSONObject("rameauKeywordSA");

		if (((keywordRc == null || keywordRc.isEmpty()) && (rameauRc == null || rameauRc.isEmpty()))) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		if (keywordRc == null) {
			keywordRc = new JSONArray();
		}
		if (rameauRc == null) {
			rameauRc = new JSONArray();
		}

		HashMap<String, Double> mapKeywordRc = new HashMap<>();
		int nbTotal = keywordRc.length() + rameauRc.length();
		for (Object o : keywordRc) {
			String dd = (String) o;
			dd = dd.trim().toLowerCase();
			Double nb_keyword = (Double) mapKeywordRc.get(dd);
			if (nb_keyword == null) {
				mapKeywordRc.put(dd, 1. / nbTotal);
			} else {
				mapKeywordRc.put(dd, nb_keyword + 1. / nbTotal);
			}
		}
		for (Object o : rameauRc) {
			String dd = (String) o;
			int idx = dd.indexOf("--");
			if (idx >= 0) {
				dd = dd.substring(0, idx).trim().toLowerCase();
			}

			Double nb_rameau = (Double) mapKeywordRc.get(dd);
			if (nb_rameau == null) {
				mapKeywordRc.put(dd, 1. / nbTotal);
			} else {
				mapKeywordRc.put(dd, nb_rameau + 1. / nbTotal);
			}
		}

		JSONArray keywordWRc = new JSONArray();
		for (Object dew : mapKeywordRc.keySet()) {
			JSONObject wd = new JSONObject();
			wd.put("keyword", dew);
			wd.put("weight", mapKeywordRc.get(dew));
			keywordWRc.put(wd);
		}

		JSONArray keywordWRa = (JSONArray) keywordRa.get("weightedKeyword");

		// print("keywordRc = " + keywordWRc);
		// print("keywordRa = " + keywordWRa);
		double distance;
		try {
			distance = Utilities.simplexe(keywordWRc, keywordWRa, sigma, "keyword", "weight");
			// print("la distance simplex = " + distance);
		} catch (Exception ex) {
			return DiscretCompType.NOT_COMPARABLE;
		}

//if (distance<0.8 && distance >=0.5) print("1 sur keyword");
		if (distance >= 0.7) {
			return 2;
		} else if (distance >= 0.3) {
			return 1;
		}
		return DiscretCompType.NEUTRAL;
	}
	
	private static class Sigma implements ISimilarity {
		private static final Levenshtein lev = new Levenshtein();
		
		@Override
		public double getSimilarity(Object mot1, Object mot2) {
			return lev.getSimilarity(mot1.toString(), mot2.toString());
		}
	};
}
