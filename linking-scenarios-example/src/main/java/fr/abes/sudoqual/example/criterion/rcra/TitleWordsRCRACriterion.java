package fr.abes.sudoqual.example.criterion.rcra;

import java.util.Objects;
import java.util.Set;

import javax.management.NotCompliantMBeanException;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.sudoqual.rule_engine.predicate.exception.NotComparableException;

/**
 * Ce critère compare l'attribut "titleWords" de la source avec l'attribut "titleWordsComputed" de la cible. <br/>
 * <br/>
 * Valeurs possibles:<br/>
 * - 0 si aucun mot en commun entre les titres des document liés à la source et les titres des documents liés à la cible<br/>
 * - X si il existe X mots en commun <br/>
 * - always, si il existe plus de 999 en commun <br/>.
 *
 */
public class TitleWordsRCRACriterion implements Criterion {
	
	private static final int MAX_WORDS = 999;
	
	@Override
	public int compare(JSONObject source, JSONObject target) {
		JSONArray sourceWords = source.optJSONArray("titleWords");
		JSONArray targetWords = target.optJSONArray("titleWordsComputed");
		
		if(sourceWords == null || targetWords == null) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		int cpt = 0;
		for(Object o : sourceWords) {
			for(Object t: targetWords) {
				if(Objects.equals(o, t)) {
					if(++cpt > MAX_WORDS) {
						return DiscretCompType.ALWAYS;
					}
				}
			}
		}
		
		return cpt;
	}
	
	@Override
	public DiscretCompType getComparisonType() {
		return new DiscretCompTypeImpl(false, 0, true, MAX_WORDS, true);
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("titleWords");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("titleWordsComputed");
	}

}
