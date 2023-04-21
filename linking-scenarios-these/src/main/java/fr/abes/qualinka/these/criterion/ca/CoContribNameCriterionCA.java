package fr.abes.qualinka.these.criterion.ca;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.qualinka.these.feature.CoContributorFeature;
import fr.abes.qualinka.these.old.CoContribNameCacheEntry;
import fr.abes.qualinka.these.old.DefaultDiscretCompType;
import fr.abes.qualinka.these.util.NameComparison;
import fr.abes.qualinka.these.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * 
 *
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
