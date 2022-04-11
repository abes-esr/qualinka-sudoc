package fr.abes.sudoqual.example.criterion;

import java.util.Objects;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.sudoqual.rule_engine.predicate.exception.NotComparableException;


/**
 * Compare les domaines rattachés à la source avec les domaines rattachés à la cible <br/>
 * <br/>
 * Valeurs possibles : <br/>
 * 0 - aucun domaine en commun
 * X - X domaines en commun
 *
 */
public class DomainCriterion implements Criterion {
	
	
	@Override
	public int compare(JSONObject source, JSONObject target) {
		JSONArray sourceComputedDomains = source.optJSONArray("domainComputed");
		JSONArray sourceDomains = source.optJSONArray("domain");
		JSONArray targetComputedDomains = target.optJSONArray("domainComputed");
		JSONArray targetDomains = target.optJSONArray("domain");

		
		if((sourceDomains == null && sourceComputedDomains == null) || (targetDomains == null && targetComputedDomains == null)) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		JSONArray aggregatedSourceDomains = aggregate(sourceComputedDomains, sourceDomains);
		JSONArray aggregatedTargetDomains = aggregate(targetComputedDomains, targetDomains);
		
		int cpt = 0;
		for(Object o : aggregatedSourceDomains) {
			for(Object t: aggregatedTargetDomains) {
				if(Objects.equals(o, t)) {
					++cpt;
				}
			}
		}
		
		return cpt;
	}
	
	private JSONArray aggregate(JSONArray array1, JSONArray array2) {
		if((array1 == null || array2 == null) && (array1 != null || array2 != null)) {
			return (array1 == null)? array2: array1;
		}
		JSONArray res = new JSONArray();
		if(array1 == null && array2 == null) {
			return res;
		}
		// both array are not null
		for(Object o : array1) {
			res.put(o);
		}
		for(Object o : array2) {
			res.put(o);
		}
		return res;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("domainComputed", "domain");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("domainComputed", "domain");
	}

	@Override
	public DiscretCompType getComparisonType() {
		return new DiscretCompTypeImpl(false, -999, true, +999, true);
	}

}
