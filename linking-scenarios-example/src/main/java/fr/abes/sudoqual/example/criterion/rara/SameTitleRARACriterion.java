package fr.abes.sudoqual.example.criterion.rara;

import java.util.Objects;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.sudoqual.rule_engine.predicate.exception.NotComparableException;

/**
 * always - il existe un document lié à la source qui possède exactement le même titre qu'un document lié à la cible <br/>
 * 0 - dans tous les autres cas.<br/>
 */
public class SameTitleRARACriterion implements Criterion {

	@Override
	public int compare(JSONObject source, JSONObject target) {
		JSONArray sourceWords = source.optJSONArray("titleComputed");
		JSONArray targetWords = target.optJSONArray("titleComputed");
		
		if(sourceWords == null || targetWords == null) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		for(Object o : sourceWords) {
			for(Object t: targetWords) {
				if(Objects.equals(o, t)) {
					return DiscretCompType.ALWAYS;
				}
			}
		}
		
		return 0;
	}
	
	@Override
	public DiscretCompType getComparisonType() {
		return new DiscretCompTypeImpl(false, 0, true, 0, true);
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("titleComputed");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("titleComputed");
	}

}
