/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.sudoc.criterion.abstracts.BestArrayElementComparisonCriterion;
import fr.abes.sudoqual.sudoc.util.NameComparison;

/**
 * Attributs utilisés : <br/>
 * * source: personName <br/>
 * * target: personName <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On compare chaque appellations de la rc avec chaque appellations de la ra. On
 * garde la meilleur comparaison. <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 2: deux appellations sont identiques<br/>
 * * 1: deux appellations sont semblables<br/>
 * * -1: toutes les appellations sont disemblables<br/>
 * * -2: toutes les appellations sont très disemblables<br/>
 */
public class PersonNameCriterion extends BestArrayElementComparisonCriterion<JSONObject, JSONObject, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(PersonNameCriterion.class);

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -2, false, 2, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	public PersonNameCriterion() {
		super(false, 2);
	}

	@Override
	public String sourceFeature() {
		return "personName";
	}

	@Override
	public String targetFeature() {
		return "personName";
	}

	@Override
	protected Integer compareElement(JSONObject elementSource, JSONObject elementTarget) {
		JSONObject lnameSource = elementSource.getJSONObject("last_name");
		JSONObject fnameSource = elementSource.getJSONObject("first_name");

		JSONObject lnameTarget = elementTarget.getJSONObject("last_name");
		JSONObject fnameTarget = elementTarget.getJSONObject("first_name");
		
		return NameComparison.compareNamesWithPreprocessedEntry(lnameSource, fnameSource, lnameTarget, fnameTarget);
	}

	@Override
	protected int mapComparisonValueToCriterionValue(Integer cmpValue) {
		switch (cmpValue) {
		case NameComparison.DISSIMILAR_DENOMINATION:
			return -2; // i.e. --
		case NameComparison.DISTANT_DENOMINATION:
			return -1; // i.e. -
		case NameComparison.EMPTY_DENOMINATION:
			return DiscretCompType.NOT_COMPARABLE;
		case NameComparison.CLOSE_DENOMINATION:
			return 1; // i.e. +
		case NameComparison.SAME_DENOMINATION:
			return 2; // i.e. ++
		default: {
			logger.warn("problem of returned value from valCompareNames" + cmpValue);
			return DiscretCompType.NOT_COMPARABLE;
		}
		}
	}
}
