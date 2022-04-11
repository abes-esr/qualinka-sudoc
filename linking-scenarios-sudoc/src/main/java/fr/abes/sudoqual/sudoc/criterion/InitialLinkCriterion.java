/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.sudoqual.rule_engine.predicate.exception.InconsistentException;
import fr.abes.sudoqual.rule_engine.predicate.exception.NotComparableException;
import fr.abes.sudoqual.util.json.JSONArrays;

/**
 * Attributs utilisés : <br/>
 * * source: initialLinks <br/>
 * * target: uri <br/>
 * <br/>
 * Stratégie : <br/>
 * Ce critère vérifie que l'identifiant (uri) de la cible (target) soit contenu dans le tableau des liens « sameAs » initiaux
 * de la source.<br/>
 * <br/>
 * Valeurs possibles : <br/>
 * * NOT_COMPARABLE: données insuffisantes ; <br/>
 * * always: l'uri de la cible est contenu dans le tableau des liens « sameAs » initiaux
 * de la source ; <br/>
 * * neutral: sinon. <br/>
 * 
 */
public class InitialLinkCriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 0, true);

	@Override
	public int compare(JSONObject source, JSONObject target) {
		JSONArray listeURI = source.optJSONArray("initialLinks");

		if (listeURI == null || listeURI.length() == 0)
			return DiscretCompType.NOT_COMPARABLE;

		if (JSONArrays.contains(listeURI, target.getString("uri")))
			return DiscretCompType.ALWAYS;
		else
			return DiscretCompType.NEUTRAL;
	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("initialLinks");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("uri");
	}
}
