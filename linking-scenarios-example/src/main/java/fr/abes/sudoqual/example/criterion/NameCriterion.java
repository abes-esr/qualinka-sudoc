package fr.abes.sudoqual.example.criterion;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Compare le nom de la source avec le nom de la cible. <br/>
 * <br/>
 * Valeurs possibles : <br/>
 * 1 - les noms sont identiques <br/>
 * 0 - les noms sont différents <br/>
 *
 */
public class NameCriterion implements Criterion {
	

	@Override
	public int compare(JSONObject source, JSONObject target) {
		String sourceName = source.optString("name");
		String targetName = target.optString("name");
		
		if(sourceName == null || targetName == null) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		if(targetName.equals(sourceName)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public DiscretCompType getComparisonType() {
		return new DiscretCompTypeImpl(false, 0, true, 1, false);
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("name");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("name");
	}

}
