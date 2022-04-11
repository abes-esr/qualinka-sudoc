/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.aa;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.sudoc.criterion.abstracts.BestArrayElementComparisonCriterion;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 * Attributs utilisés : <br/>
 * * source: corporateBodySA <br/>
 * * target: corporateBodySA <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * Le critère compare chacune des chaînes représentant une collectivité dans la
 * ra source avec chaque chaîne représentant une collectivité dans la ra target avec la mesure
 * de Levenshtein. On garde la meilleure similarité (Levenshtein). <br/>
 * <br/>
 * Valeurs possibles : <br/>
 * * 2 : il existe un nom de collectivités très semblable
 * <br/>
 * * 1 : il existe un nom de collectivités semblable <br/>
 * * NEUTRAL: aucun nom de collectivités semblable<br/>
 *
 */
public class CorporateBodyAACriterion extends BestArrayElementComparisonCriterion<String, String, Float> {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 2, false);
	
	public CorporateBodyAACriterion() {
		super(true, 0.9f);
	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	protected Float compareElement(String elementSource, String elementTarget) {
		return new Levenshtein().getSimilarity(elementSource, elementTarget);
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
		return "corporateBodySA";
	}

	@Override
	public String targetFeature() {
		return "corporateBodySA";
	}
}
