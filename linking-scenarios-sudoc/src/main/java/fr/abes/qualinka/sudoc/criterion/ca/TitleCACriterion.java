/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.ca;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 * Attributs utilisés : <br/>
 * * source: "title" <br/>
 * * target: "titleSA" <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On compare chaque titre de ra avec le titre de la rc et on applique la mesure
 * de Levenshtein. Renvoie 3 si un des titres de ra est quasiment le même que le
 * titre de rc (Levenshtein > 0,9), Renvoie 2 si un des titres de ra a une
 * combinaison commune rare de mots avec le titre de rc (Levenshtein > 0,7) ; Si
 * les titres sont différents (Levenshtein < 0,7), on transforme le titre de la
 * RC en enlevant les mots trop fréquents (en utilisant le dictionnaire des mots
 * fréquents des titres du sudoc). La limite est un seuil fixé actuellement à
 * 3000. Si le mot n’est pas présent dans le dictionnaire, on le laisse. Les
 * mots sont mis en minuscules. Pour chaque titre de la RA, on effectue la même
 * transformation que pour le titre de la RC, puis on passe les 2 titres au soft
 * cosinus avec une fonction de similarité qui renvoie un levenshtein entre deux
 * mots. A chaque titre testé de la RA, on garde la meilleure similarité. <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 3: si deux titres sont très similaires.<br/>
 * * 2: si deux titres sont similaire ou ont des « perles rares » en commun (seuil 3000).<br/>
 * * 1: si deux titres ont des « perles rares » en commun (seuil 6000).<br/>
 * * neutral: sinon<br/>
 */
public class TitleCACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 3, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("title");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("titleSA");
	}

	final static int SEUIL1 = 3000;
	final static int SEUIL2 = 6000;

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if (!source.has("title") || !target.has("titleSA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		JSONObject jsonTitRc = source.getJSONArray("title").optJSONObject(0);
		String titRc = jsonTitRc.getString("normalized");
		JSONArray titsRa = target.getJSONArray("titleSA");

		if (titsRa.isEmpty()) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		double bestSimTitle = 0.;
		double simTitle;

		for (Object o : titsRa) {
			JSONObject jsonTitRa = (JSONObject) o;
			String titRa = (String) jsonTitRa.get("normalized");
			simTitle = new Levenshtein().getSimilarity(titRc.toLowerCase(), titRa.toLowerCase());
			if (simTitle > 0.9) {
				return 3;
			}
			if (simTitle > bestSimTitle) {
				bestSimTitle = simTitle;
			}
		}

		if (bestSimTitle > 0.7) {
			return 2;
		}

		// title 2
		bestSimTitle = 0.;
		JSONObject mapTitRC = jsonTitRc.getJSONObject("uncommonWords");

		for (Object o : titsRa) {
			JSONObject jsonTitRa = (JSONObject) o;
			JSONObject mapTitRA = jsonTitRa.getJSONObject("uncommonWords");
			List<String> listMotTitRA = new LinkedList<>(mapTitRA.keySet());
			simTitle = 0.;
			for (String motTitRC : mapTitRC.keySet()) {
				String matchFound = null;
				for (String motTitRA : listMotTitRA) {
					if (new Levenshtein().getSimilarity(motTitRC, motTitRA) > 0.9) {
						simTitle = simTitle + (mapTitRC.getDouble(motTitRC) + mapTitRA.getDouble(motTitRA)) / 2.;
						matchFound = motTitRA;
						break; // does not count same word twice
					}
				}
				// does not count same word twice
				if (matchFound != null) {
					listMotTitRA.remove(matchFound);
				}
			}
			if (simTitle >= 1. / SEUIL1) {
				return 2;
			} else if (simTitle >= 1. / SEUIL2) {
				bestSimTitle = 1.;
			}
		}
		if (bestSimTitle == 1.) {
			return 1;
		}
		return DiscretCompType.NEUTRAL;
	}

}
