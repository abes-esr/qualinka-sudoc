/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.ca;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.qualinka.sudoc.criterion.abstracts.BestArrayElementComparisonCriterion;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 * Attributs utilisés : <br/>
 * * source: publisher <br/>
 * * target: publisherSA <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On compare chacune des chaînes représentant un éditeur dans la rc avec chaque
 * chaîne représentant un éditeur dans la ra avec la mesure de Levenshtein. On
 * garde la meilleure similarité. <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 2: la meilleur similarité est supérieure à 0.9<br/>
 * * 1: la meilleur similarité est supérieure à 0.7<br/>
 * * neutral: sinon
 */
public class PublisherCACriterion extends BestArrayElementComparisonCriterion<JSONObject, JSONObject, Float> {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 2, false);
	
	public PublisherCACriterion() {
		super(false, 0.9f);
	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	protected Float compareElement(JSONObject elementSource, JSONObject elementTarget) {
		return new Levenshtein().getSimilarity(elementSource.getString("comp_value"), elementTarget.getString("comp_value"));
	}

	@Override
	protected int mapComparisonValueToCriterionValue(Float cmpValue) {
		if (cmpValue > 0.9f) {
			return 2;
		} else if (cmpValue > 0.7f) {
			return 1;
		} else {
			return DiscretCompType.NEUTRAL;
		}
	}

	@Override
	public String sourceFeature() {
		return "publisher";
	}

	@Override
	public String targetFeature() {
		return "publisherSA";
	}
}
