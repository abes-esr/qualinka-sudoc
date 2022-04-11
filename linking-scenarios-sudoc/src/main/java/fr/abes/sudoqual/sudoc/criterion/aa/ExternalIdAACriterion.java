/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.aa;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.sudoqual.sudoc.criterion.abstracts.BestArrayElementComparisonCriterion;
import fr.abes.sudoqual.sudoc.util.NameComparison;

/**
 * Attributs utilisés : <br/>
 * * source: externalId <br/>
 * * target: externalId <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On cherche un identifiant identique entre source et target. <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 1: au moins un identifiant externe est identique<br/>
 * * 0: aucun identifiant externe identique n'est trouvé<br/>
 */
public class ExternalIdAACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 1, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		
		if (!source.has("externalId") || !target.has("externalId")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		else {
			JSONArray externalIdRASource = source.getJSONArray("externalId");
			JSONArray externalIdRATarget = target.getJSONArray("externalId");
			for (Object s : externalIdRASource) {
				String externalIdRASource1 = (String) s;
				for (Object t : externalIdRATarget) {
					String externalIdRATarget1 = (String) t;
					if (externalIdRASource1.compareTo(externalIdRATarget1)==0) {
						return 1;
					}
				}
			}
			return DiscretCompType.NEUTRAL;
		}		
	}

	@Override
	public Set<String> sourceFeatureSet() {		
		return Set.of("externalId");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("externalId");
	}

}
