package fr.abes.sudoqual.sudoc.criterion.aa;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.sudoc.criterion.abstracts.BestArrayElementComparisonCriterion;
import fr.abes.sudoqual.sudoc.util.NameComparison;

/**
 * Attributs utilisés : <br/>
 * source: cocontributorSA<br/>
 * target: cocontributorSA<br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * Ce critère compare chaque appellation de la liste "cocontributorSA (ra)" avec
 * chaque appellation de la liste "cocontributorSA (ra)". La comparaison de deux
 * appellations utilise la méthode
 * {@link NameComparison#compareNamesWithPreprocessedEntry}. On utilise ensuite
 * la meilleur valeur trouvée. <br/>
 * <br/>
 * Valeurs possibles : <br/>
 * * 2: il existe une appellation identique, <br/>
 * * 1: il existe une appellation proche, <br/>
 * * neutral: aucune appellation proche.
 * <br/>
 * <br/>
 *
 */
public class CocontribNameAACriterion extends BestArrayElementComparisonCriterion<JSONObject, JSONObject, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(CocontribNameAACriterion.class);
	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 2, false);

	public CocontribNameAACriterion() {
		super(true, 2);
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
				return DiscretCompType.NEUTRAL;
			case NameComparison.DISTANT_DENOMINATION:
				return DiscretCompType.NEUTRAL;
			case NameComparison.EMPTY_DENOMINATION:
				return DiscretCompType.NEUTRAL;
			case NameComparison.CLOSE_DENOMINATION:
				return 1; // i.e. +
			case NameComparison.SAME_DENOMINATION:
				return 2; // i.e. ++
			default: {
				if (logger.isWarnEnabled()) {
					logger.warn("problem of returned value from valCompareNames {}", cmpValue);
				}
				return DiscretCompType.ERROR;
			}
		}
	}

	@Override
	public String sourceFeature() {
		return "cocontributorSA";
	}

	@Override
	public String targetFeature() {
		return "cocontributorSA";
	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}
}
