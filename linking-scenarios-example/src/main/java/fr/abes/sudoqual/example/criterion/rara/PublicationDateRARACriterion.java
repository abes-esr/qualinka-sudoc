package fr.abes.sudoqual.example.criterion.rara;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Compare deux intervals de dates de publication. <br/>
 * <br/>
 * Valeurs possibles : <br/>
 * always - il existe une intersection non-vide entre les deux intervals <br/>
 * 3 - l'écart entre les deux intervals est < 5 ans <br/>
 * 2 - l'écart entre les deux intervals est < 10 ans <br/>
 * 1 - l'écart entre les deux intervals est < 20 ans <br/>
 * 0 - l'écart entre les deux intervals est compris entre 20 et 40 ans inclus <br/>
 * -1 - l'écart entre les deux intervals est > 40 ans <br/>
 * -2 - l'écart entre les deux intervals est > 50 ans <br/>
 * -3 - l'écart entre les deux intervals est > 60 ans <br/> 
 * never - l'écart entre les deux intervals est > 100 ans <br/>
 */
public class PublicationDateRARACriterion implements Criterion {

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("publicationDateComputed") || !target.has("publicationDateComputed")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		JSONObject intervalSource = source.optJSONObject("publicationDateComputed");
		JSONObject intervalTarget = target.optJSONObject("publicationDateComputed");
		
		if(!intervalSource.has("min") || !intervalSource.has("max")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		if(!intervalTarget.has("min") || !intervalTarget.has("max")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		int minSource = intervalSource.optInt("min");
		int minTarget = intervalTarget.optInt("min");
		int maxSource = intervalSource.optInt("max");
		int maxTarget = intervalTarget.optInt("max");
		
		if(minTarget < minSource) {
			// we swap source and target
			// so source always start before target
			int minTmp = minSource;
			int maxTmp = maxSource;
			minSource = minTarget;
			maxSource = maxTarget;
			minTarget = minTmp;
			maxTarget = maxTmp;
		}
		
		// check if source overlaps or contains target
		if(maxSource >= minTarget) {
			return DiscretCompType.ALWAYS;
		}
		 
		int delta = minTarget - maxSource;
		if (delta < 5) {
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
		return Set.of("publicationDateComputed");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("publicationDateComputed");
	}

}
