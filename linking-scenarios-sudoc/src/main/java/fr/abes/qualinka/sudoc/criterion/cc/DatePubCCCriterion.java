/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.cc;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: pubDate <br/>
 * * target: pubDate <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * 
 */
public class DatePubCCCriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 2, false);

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("pubDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("pubDate");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("pubDate") || !target.has("pubDate")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		int datePubRC1 = source.getInt("pubDate");
		int datePubRC2 = target.getInt("pubDate");

		int sup, inf;
		if (datePubRC1 > datePubRC2) {
			sup = datePubRC1;
			inf = datePubRC2;
		} else {
			sup = datePubRC2;
			inf = datePubRC1;
		}
		int ecart = sup - inf;
		if (ecart < 21) {
			return 2;
		} else if (ecart < 41) {
			return 1;
		} else if (ecart < 81) {
			return DiscretCompType.NEUTRAL;
		}
		return -1;
	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

}
