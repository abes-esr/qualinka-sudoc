package fr.abes.qualinka.these.thesedir.criterion;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.qualinka.these.old.CoContribNameCacheEntry;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.qualinka.these.util.NameComparison;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Set;

public class CojuryNameCriterion implements Criterion {

		private static final Logger logger = LoggerFactory.getLogger(CojuryNameCriterion.class);

		public HashMap<String, CoContribNameCacheEntry> cache = new HashMap<>(2048);

		@Override
		public DiscretCompType getComparisonType() {
			return new DiscretCompTypeImpl(false, 0, true, 2, false);
		}

		@Override
		public Set<String> sourceFeatureSet() {			
			return Set.of("cojury");
		}

		@Override
		public Set<String> targetFeatureSet() {
			return Set.of("cojurySA");
		}

		@Override
		public int compare(JSONObject source, JSONObject target) {
			JSONArray ccRc = source.optJSONArray("cojury");
			JSONArray ccRa = target.optJSONArray("cojurySA");

			CoContribNameCacheEntry cacheEntry = cache.get(source.getString("uri")
			                                               + "_"
			                                               + target.getString("uri"));
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
						cache.put(source.getString("uri") + "_" + target.getString("uri"), cacheEntry);
						return 2; // i.e. ++
					} else if (bestComparisonValue < currentValue) {
						bestComparisonValue = currentValue;
					}
				}
			}

			cacheEntry.lastValue = bestComparisonValue;
			cacheEntry.lastEntry = ccRa.length() - 1;
			cache.put(source.getString("uri") + "_" + target.getString("uri"), cacheEntry);

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

