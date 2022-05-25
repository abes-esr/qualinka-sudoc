/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.ca;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

// TODO /!\ pas de normalisation du doublement des espaces dans le traitement des titres
// + il faudrait un seuil "titre identique" après normalisation

/**
 * Attributs utilisés : <br/>
 * * source: "title", "pubDate" <br/>
 * * target:  "source"<br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * - on extrait le titre et la date de publication de la source (si c'est possible) <br/>
 * - on compare le titre extrait avec le titre de la rc en appliquant la mesure de Levenhstein <br/>
 * - on test l'égalité entre la date extraite et la date de publication de la rc <br/>
 * <br/>
 * 
 * 
 * Valeurs possibles : <br/>
 * * always: titre proche et date exacte <br/>
 * * 3: titre proche et date proche <br/>
 * * 2: titre proche et pas de date <br/>
 * * 1: titre proche et date éloigné <br/>
 * * neutral: pas de correspondance du titre <br/>
 */
public class SourceCACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 3, true);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("title", "pubDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("source");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("title") || !target.has("source")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		String title = null;
		JSONObject titleJSON  = source.getJSONArray("title").optJSONObject(0);
		if (titleJSON != null) {
			title = titleJSON.getString("normalized");
		}
		Integer pubYear = null;
		if (source.has("pubDate")) {
			pubYear = source.getInt("pubDate");
		}

		JSONArray sourceString = target.optJSONArray("source");

		if (sourceString.isEmpty() || title == null) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		// if the title of the rc contains a ':' then :
		// - the part before ':' is considered as the main title
		// - the part after ':' is considered as the subtitle
		String[] titleParts = title.split(" : ");

		for (Object o : sourceString) {
			JSONObject sourceObject = (JSONObject) o;
			double simTitle = 0.; // comparison with Levenshtein

			String sourceTitle = sourceObject.getString("title");
			simTitle = new Levenshtein().getSimilarity(sourceTitle, title); 

			float simMainTitle;
			if (titleParts.length > 1) {
				// comparing with title of bibliographic record
				simMainTitle = new Levenshtein().getSimilarity(sourceTitle, titleParts[0]); 	                                                                                                 
			} else {
				simMainTitle = 0;
			}

			Integer sourceDate = null;
			if(sourceObject.has("pubYear")) {
				sourceDate = sourceObject.getInt("pubYear");
			}

			if (simTitle > 0.6 || simMainTitle > 0.8) {
				if(sourceDate == null || pubYear == null) {
					return 2; // title but no date
				}
				int delta = Math.abs(sourceDate - pubYear);
				if(delta == 0) {
					return DiscretCompType.ALWAYS; // title and date ok
				} else if (delta <= 3) {
					return 3; // title and near date 
				} else {
					return 1; // title and distant date
				}
			}
		}
		return DiscretCompType.NEUTRAL;
	}
}
