package fr.abes.sudoqual.sudoc.criterion.ca;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.sudoqual.sudoc.old.ISimilarity;
import fr.abes.sudoqual.sudoc.util.Utilities;

/**
 * Attributs utilisés : <br/>
 * * source: dewey <br/>
 * * target: deweySA <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On imagine une liste de dewey avec des poids dont la somme des poids vaut 1.
 * <br/>
 * Soit une liste (non pondérée au départ, mais pondérée avant la comparaison)
 * avec répétition de dewey d'une rc et une liste pondérée de dewey d'une ra (la
 * somme des poids valant 1). les deux listes pondérées rc et ra (poids de
 * chaque dewey relatif aux autres dewey soit de la rc, soit de la ra) sont
 * envoyées à l’algo simplex accompagnées d’une distance entre les
 * dewey calculée ainsi : On regarde en partant de la gauche (on va du concept
 * le plus spécifique au plus générique) Si les 3 caractères dewey sont
 * identiques, la similarité vaut 1 Si 2 caractères dewey sont identiques, la
 * similarité vaut 0.6 Si le premier caractère dewey est identique, la
 * similarité vaut 0.3 Sinon la similarité vaut 0. Puis on regarde le résultat 
 * de proximité renvoyé par le simplexe.<br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 2: proximité des dewey très forte<br/>
 * * 1: proximité des dewey assez forte<br/>
 * * neutral: proximité des dewey moyenne<br/>
 * * 0: proximité des dewey très faible<br/>
 */
public class DeweyCACriterion implements Criterion {
	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 2, false);

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if (!source.has("dewey") || !target.has("deweySA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		JSONArray deweyRc = source.getJSONArray("dewey");
		JSONObject deweySARa = target.getJSONObject("deweySA");

		if (deweyRc.isEmpty()) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		JSONArray deweyRa = (JSONArray) deweySARa.get("weightedDewey");

		Map<String, Double> mapDeweyRc = new HashMap<>();
		int nbTotalDewey = deweyRc.length();
		for (Object o : deweyRc) {
			String dd = (String) o;
			if (dd.length() > 2) {
				Double nb_dewey = mapDeweyRc.get(dd);
				if (nb_dewey == null) {
					mapDeweyRc.put(dd, 1. / nbTotalDewey);
				} else {
					mapDeweyRc.put(dd, nb_dewey + 1. / nbTotalDewey);
				}
			}
		}

		JSONArray deweyWRc = new JSONArray();
		for (Object dew : mapDeweyRc.keySet()) {
			JSONObject wd = new JSONObject();
			wd.put("dewey", dew);
			wd.put("weight", mapDeweyRc.get(dew));
			deweyWRc.put(wd);
		}

		if (deweyWRc.length() < 1 || deweyRa.length() < 1) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		double distance;
		try {
			distance = Utilities.simplexe(deweyWRc, deweyRa, new Sigma(), "dewey", "weight");
		} catch (Exception ex) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		if (distance >= 0.6) {
			return 2;
		} else if (distance >= 0.3) {
			return 1;
		} else if (distance >= 0.2) {
			return DiscretCompType.NEUTRAL;
		} else {
			return -1;
		}

	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("dewey");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("deweySA");
	}

	private static class Sigma implements ISimilarity {
		@Override
		public double getSimilarity(Object o1, Object o2) {
			String d1 = (String) o1;
			String d2 = (String) o2;
			if (d1.substring(0, 1).equals(d2.substring(0, 1))) {
				if (d1.substring(0, 2).equals(d2.substring(0, 2))) {
					if (d1.substring(0, 3).equals(d2.substring(0, 3))) {
						return 1.0;
					} else {
						return 0.6;
					}
				} else {
					return 0.3;
				}
			} else {
				return 0.0;
			}

		}
	};

}
