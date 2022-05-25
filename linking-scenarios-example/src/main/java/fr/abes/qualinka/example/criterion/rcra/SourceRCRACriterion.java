package fr.abes.qualinka.example.criterion.rcra;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Ce critère compare l'attribut "title" de la source avec l'attribut "source" de la cible. <br/>
 * <br/>
 * Valeurs possibles : <br/>
 * 1 - le titre rattaché à la source est identique au contenu de l'attribut "source", <br/>
 * 0 - dans tous les autres cas. <br/>
 *
 */
public class SourceRCRACriterion implements Criterion {
	

	@Override
	public int compare(JSONObject source, JSONObject target) {
		String title = source.optString("title");
		String sourceTitle = target.optString("source");
		
		if(sourceTitle == null || title == null || sourceTitle.isBlank() || title.isBlank()) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		if(title.equals(sourceTitle)) {
			return +1;
		} else {
			return -1;
		}
	}
	
	@Override
	public DiscretCompType getComparisonType() {
		return new DiscretCompTypeImpl(false, -1, false, 1, false);
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("title");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("source");
	}

}
