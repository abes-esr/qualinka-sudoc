package fr.abes.qualinka.sudoqual1.criterion.ca;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.qualinka.sudoqual1.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.sudoqual1.util.adapter.IReference;
import fr.abes.qualinka.sudoqual1.feature.CoContributorFeature;
import fr.abes.qualinka.sudoqual1.old.CoContribNameCacheEntry;
import fr.abes.qualinka.sudoqual1.old.DefaultDiscretCompType;
import fr.abes.qualinka.sudoqual1.util.NameComparison;

/**
 * Attributs utilisés : cocontributor (rc), cocontributorSA (ra) <br/>
 * Valeurs retournées : NOT_COMPARABLE, 2, 1, NEUTRAL <br/>
 * Stratégie : <br/>
 * Pour la comparaison de deux appellations dans ce critère on utilise le même
 * algorithme que celui utilisé dans le critère concernant les appellations. On
 * compare chaque appellation de l’attribut cocontributor de la rc avec chaque
 * appellation de l’attribut cocontributor de la ra et on retourne la meilleure
 * valeur. On appelle la fonction nameComparison et : <br/>
 * Renvoie 2 si les appellations sont identiques, <br/>
 * Renvoie 1 si les appellations sont proches, <br/>
 * Renvoie NEUTRAL sinon. <br/>
 */
public class CoContribNameCriterionCA extends DefaultCriterion2 {

	private static final Logger logger = LoggerFactory.getLogger(CoContribNameCriterionCA.class);

	public HashMap<String, CoContribNameCacheEntry> cache = new HashMap<>(2048);

	public CoContribNameCriterionCA() {
		super("cocontribNameCriterionCA", new DefaultDiscretCompType(false, 0, true, 2, false),
		      new String[] { CoContributorFeature.NAME }, new String[] { "cocontributorSA" });
	}

	@Override
	public int compare(IReference first, IReference second) {
		JSONArray ccRc = first.getValue("cocontributor");
		JSONArray ccRa = second.getValue("cocontributorSA");

		CoContribNameCacheEntry cacheEntry = cache.get(Long.toString(first.getId())
		                                               + "_"
		                                               + Long.toString(second.getId()));
		if (cacheEntry == null) {
			cacheEntry = new CoContribNameCacheEntry();
		}
		if (cacheEntry.lastValue == NameComparison.SAME_DENOMINATION) {
			return 2;
		}

		if (ccRc == null || ccRa == null || ccRc.isEmpty() || ccRa.isEmpty()) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		int bestComparisonValue = cacheEntry.lastValue;
		int currentValue;
		for (Object o : ccRc) {
			JSONObject appelRc = (JSONObject) o;
			JSONObject lnameRc = (JSONObject) appelRc.get("last_name");
			JSONObject fnameRc = (JSONObject) appelRc.get("first_name");

			for (int i = cacheEntry.lastEntry + 1; i < ccRa.length(); ++i) {
				JSONObject appelRa = (JSONObject) ccRa.get(i);
				JSONObject lnameRa = (JSONObject) appelRa.get("last_name");
				JSONObject fnameRa = (JSONObject) appelRa.get("first_name");
				currentValue = NameComparison.compareNamesWithPreprocessedEntry(lnameRc, fnameRc, lnameRa, fnameRa);

				if (currentValue == NameComparison.SAME_DENOMINATION) {
					cacheEntry.lastValue = NameComparison.SAME_DENOMINATION;
					cache.put(Long.toString(first.getId()) + "_" + Long.toString(second.getId()), cacheEntry);
					return 2; // i.e. ++
				} else if (bestComparisonValue < currentValue) {
					bestComparisonValue = currentValue;
				}
			}
		}

		cacheEntry.lastValue = bestComparisonValue;
		cacheEntry.lastEntry = ccRa.length() - 1;
		cache.put(Long.toString(first.getId()) + "_" + Long.toString(second.getId()), cacheEntry);

		switch (bestComparisonValue) {
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
				if(logger.isWarnEnabled()) {
					logger.warn("problem of returned value from valCompareNames {}", bestComparisonValue);
				}
				return DiscretCompType.NOT_COMPARABLE;
			}
		}
	}
}
