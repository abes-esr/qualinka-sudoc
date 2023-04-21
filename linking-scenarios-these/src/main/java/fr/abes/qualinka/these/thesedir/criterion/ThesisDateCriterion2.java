package fr.abes.qualinka.these.thesedir.criterion;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ThesisDateCriterion2 implements Criterion {

	private static final Logger logger = LoggerFactory.getLogger(ThesisDateCriterion2.class);
	
	@Override
	public DiscretCompType getComparisonType() {
		// TODO Auto-generated method stub
		return new DiscretCompTypeImpl(false, -1, false, 1, false);
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("pubDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("thesisDefenseDate");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		JSONArray ccRc = source.optJSONArray("pubDate");
		Integer thesisDefenseDate = target.optInt("thesisDefenseDate");
		Integer datePub = null;
		
		if (ccRc == null || thesisDefenseDate == null || ccRc.isEmpty()) {
			return DiscretCompType.NOT_COMPARABLE;
		}				
		
		try {
			datePub = ccRc.getInt(0);					
		}
		catch (NumberFormatException e) {
			logger.warn("datePub n'est pas un entier", e);
			return DiscretCompType.NOT_COMPARABLE;
		}				
		
		if (datePub > thesisDefenseDate) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
