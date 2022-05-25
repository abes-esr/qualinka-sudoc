/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.aa;

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
 * * source: "titleSA" <br/>
 * * target: "titleSA" <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On compare chaque titre de ra target avec chaque titre de la ra source et on applique la mesure
 * de Levenshtein. 
 * Renvoie 3 si un des titres de ra target est quasiment le même qu'un des
 * titres de la ra source (Levenshtein > 0,9), 
 * Renvoie 2 si un des titres de ra target a une
 * combinaison commune rare de mots avec un des titres de ra source (Levenshtein > 0,7) ; Si
 * les titres sont différents (Levenshtein < 0,7), on transforme les titres de la
 * RA source en enlevant les mots trop fréquents (en utilisant le dictionnaire des mots
 * fréquents des titres du sudoc). La limite est un seuil fixé actuellement à
 * 3000. Si le mot n’est pas présent dans le dictionnaire, on le laisse. Les
 * mots sont mis en minuscules. Pour chaque titre de la RA target, on effectue la même
 * transformation que pour les titres de la RA source, puis on passe les 2 titres au soft
 * cosinus avec une fonction de similarité qui renvoie un levenshtein entre deux
 * mots. A chaque titre testé de la RA target, on garde la meilleure similarité. <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 3: si deux titres sont très similaires.<br/>
 * * 2: si deux titres sont similaire ou ont des « perles rares » en commun (seuil 3000).<br/>
 * * 1: si deux titres ont des « perles rares » en commun (seuil 6000).<br/>
 * * neutral: sinon<br/>
 */
public class TitleAACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 3, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("titleSA");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("titleSA");
	}

	final static int SEUIL1 = 3000;
	final static int SEUIL2 = 6000;

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if (!source.has("titleSA") || !target.has("titleSA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		JSONArray titsRa1 = source.getJSONArray("titleSA");
		JSONArray titsRa2 = target.getJSONArray("titleSA");

		if (titsRa2.isEmpty()) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		double bestSimTitle = 0.;
		double simTitle;

		for (Object o1 : titsRa1) {
			JSONObject jsonTitRa1 = (JSONObject) o1;
			String titRa1 = (String) jsonTitRa1.get("normalized");
			for (Object o2 : titsRa2) {
				JSONObject jsonTitRa2 = (JSONObject) o2;
				String titRa2 = (String) jsonTitRa2.get("normalized");
				
				simTitle = new Levenshtein().getSimilarity(titRa1.toLowerCase(), titRa2.toLowerCase());
				if (simTitle > 0.9) {
					return 3;
				}
				if (simTitle > bestSimTitle) {
					bestSimTitle = simTitle;
				}
				
			}
		}

		if (bestSimTitle > 0.7) {
			return 2;
		}

		// title 2
		bestSimTitle = 0.;
		
		for (Object o1 : source.getJSONArray("titleSA")) {
			JSONObject jsonTitRa1Unco = (JSONObject) o1;
			JSONObject mapTitRa1 = jsonTitRa1Unco.getJSONObject("uncommonWords");
	
			for (Object o2 : titsRa2) {
				JSONObject jsonTitRa2 = (JSONObject) o2;
				JSONObject mapTitRa2 = jsonTitRa2.getJSONObject("uncommonWords");
				List<String> listMotTitRa2 = new LinkedList<>(mapTitRa2.keySet());
				simTitle = 0.;
				for (String motTitRa1 : mapTitRa1.keySet()) {
					String matchFound = null;
					for (String motTitRa2 : listMotTitRa2) {
						if (new Levenshtein().getSimilarity(motTitRa1, motTitRa2) > 0.9) {
							simTitle = simTitle + (mapTitRa1.getDouble(motTitRa1) + mapTitRa2.getDouble(motTitRa2)) / 2.;
							matchFound = motTitRa2;
							break; // does not count same word twice
						}
					}
					// does not count same word twice
					if (matchFound != null) {
						listMotTitRa2.remove(matchFound);
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
		}
		
		
		return DiscretCompType.NEUTRAL;
	}

}
