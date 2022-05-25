package fr.abes.qualinka.example.criterion.rcrc;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Compare deux dates de publication (attribut "publicationDate"). <br/>
 * <br/>
 * Valeurs possibles : <br/>
 * always - les deux dates sont les mêmes <br/>
 * 3 - la différence entre les deux dates est < 5 ans <br/>
 * 2 - la différence entre les deux dates est  < 10 ans <br/>
 * 1 - la différence entre les deux dates est  < 20 ans <br/>
 * 0 - la différence entre les deux dates est  comprise entre 20 et 40 ans inclus <br/>
 * -1 - la différence entre les deux dates est  > 40 ans <br/>
 * -2 - la différence entre les deux dates est > 50 ans <br/>
 * -3 - la différence entre les deux dates est  > 60 ans <br/> 
 * never - la différence entre les deux dates est > 100 ans <br/>
 */
public class PublicationDateRCRCCriterion implements Criterion {

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("publicationDate") || !target.has("publicationDate")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		int pubDateSource = source.optInt("publicationDate");
		int pubDateTarget = target.optInt("publicationDate");
		
		int delta = Math.abs(pubDateSource - pubDateTarget);
		if(delta == 0) {
			return DiscretCompType.ALWAYS;
		}else if(delta < 5) {
			return 3;
		} else if (delta < 10) {
			return 2;
		} else if (delta < 20) {
			return 1;
		} else if (delta > 100) {
			return DiscretCompType.NEVER;
		} else if (delta > 60) {
			return -3;
		} else if (delta > 50) {
			return -2;
		} else if (delta > 40) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public DiscretCompType getComparisonType() {
		return new DiscretCompTypeImpl(true, -3, true, 3, true);
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("publicationDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("publicationDate");
	}

}
