/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion;

import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: dateCreationNotice, initialLinks <br/>
 * * target: dateCreationNotice <br/>
 * <br/>
 * Stratégie : <br/>
 * Ce critère compare la date de création de la notice porteuse de la RC avec la
 * date de création de la notice porteuse de la RA ainsi que le lien initial de
 * la RC.<br/>
 * <br/>
 * Valeurs possibles : <br/>
 * * NOT_COMPARABLE: données insuffisantes, <br/>
 * * always: si les deux dates sont identiques et que la RC était initialement
 * liée à cette RA, <br/>
 * * neutral: si les deux dates sont différentes ou que la RC n'était pas
 * initialement liée à cette RA. <br/>
 * 
 */
public class DateCreationNoticeCriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 0, true);

	@Override
	public int compare(JSONObject source, JSONObject target) {
		String dateCreationNoticeSource, dateCreationNoticeTarget;
		try {
			dateCreationNoticeSource = source.getString("dateCreationNotice");
			dateCreationNoticeTarget = target.getString("dateCreationNotice");
		} catch(JSONException e) {
			return DiscretCompType.NOT_COMPARABLE;
		}
			
		if (dateCreationNoticeSource.isBlank() || dateCreationNoticeTarget.isBlank()) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		if (dateCreationNoticeSource.equals(dateCreationNoticeTarget)) {
			return DiscretCompType.ALWAYS;
		}
		
		return DiscretCompType.NEUTRAL;
	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("dateCreationNotice");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("dateCreationNotice");
	}
}
