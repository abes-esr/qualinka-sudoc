/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import fr.abes.sudoqual.sudoc.util.DeweyRameauMap;
import fr.abes.sudoqual.sudoc.util.DomainMap;
import fr.abes.sudoqual.sudoc.util.Utilities;

/**
 * Attributs utilisés : <br/>
 * * source: domain, dewey <br/>
 * * target: domainDeweySA <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * A l’aide d’un mapping entre les codes domaines et les indices dewey, on
 * transforme les indices dewey. Ces derniers sont intégrés dans ce critère de
 * la même façon que les codes domaine. On imagine une liste de domaines avec
 * des poids dont la somme des poids vaut 1. Soit une liste (non pondérée au
 * départ, mais pondérée avant la comparaison) avec répétition de domaines d'une
 * rc et une liste pondérée de domaines d'une ra (la somme des poids valant 1).
 * Puis les deux listes pondérées rc et ra (poids de chaque domaine relatif aux
 * autres domaines soit de la rc, soit de la ra) sont envoyées à l’algo simplex
 * accompagnées d’une matrice indiquant les proximités entre domaines.<br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 2: proximité des dewey très forte<br/>
 * * 1: proximité des dewey assez forte<br/>
 * * neutral: proximité des dewey moyenne<br/>
 * * 0: proximité des dewey très faible<br/>
 * 
 */
public class DomainCACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 2, false);

	static Map<String, Double> domCompMatrix = DomainMap.createDomainMap();
	static Map<String, String> conversion = DeweyRameauMap.getDeweyRameauMap();

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if ((!source.has("domain") && !source.has("dewey")) || !target.has("domainDeweySA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		JSONArray domRc = source.optJSONArray("domain");
		JSONArray deweyRc = source.optJSONArray("dewey");
		JSONObject domSa = target.getJSONObject("domainDeweySA");

		if (domRc == null) {
			domRc = new JSONArray();
		}
		if (deweyRc == null) {
			deweyRc = new JSONArray();
		}

		if (domRc.isEmpty() && deweyRc.isEmpty()) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		// soit une liste (non pond?r?e) avec r?p?tition de domaines d'une rc
		// et une liste pond?r?e de domaines d'une ra (la somme des poids valant 1)
		// int representativeness = domSa.get("representativeness");
		// if(representativeness < 2) return DefaultDiscretCompType.NEUTRAL;
		JSONArray domRa = (JSONArray) domSa.get("weightedDomain");

		// enlever les dewey non convertibles du d?compte
		HashMap<Integer, Double> mapDomRc = new HashMap<>();
		int nbTotalDom = domRc.length() + deweyRc.length();

		// enlever les dewey non convertibles du d?compte
		for (Object o : deweyRc) {
			String dd = (String) o;
			Integer intdd = DeweyRameauMap.convertirDeweyRameau(conversion, dd);

			if (intdd == null) {
				nbTotalDom = nbTotalDom - 1;
			}
		}

		for (Object o : domRc) {
			Integer dd = (Integer) o;
			Double nb_domain = (Double) mapDomRc.get(dd);
			if (nb_domain == null) {
				mapDomRc.put(dd, 1. / nbTotalDom);
			} else {
				mapDomRc.put(dd, nb_domain + 1. / nbTotalDom);
			}
		}

		for (Object o : deweyRc) {
			String dd = (String) o;
			Integer intdd = DeweyRameauMap.convertirDeweyRameau(conversion, dd);
			if (intdd != null) {
				Double nb_domain = (Double) mapDomRc.get(intdd);
				if (nb_domain == null) {
					mapDomRc.put(intdd, 1. / nbTotalDom);
				} else {
					mapDomRc.put(intdd, nb_domain + 1. / nbTotalDom);
				}
			}
		}

		JSONArray domWRc = new JSONArray();
		for (Object dom : mapDomRc.keySet()) {
			JSONObject wd = new JSONObject();
			wd.put("domain", dom);
			wd.put("weight", mapDomRc.get(dom));
			domWRc.put(wd);
		}

		ISimilarity sigma = new ISimilarity() {
			@Override
			public double getSimilarity(Object o1, Object o2) {
				String key;
				Integer d1 = (Integer) o1;
				Integer d2 = (Integer) o2;
				if (d1.intValue() > d2.intValue()) {
					key = "" + d2 + "-" + d1;
				} else {
					key = "" + d1 + "-" + d2;
				}
				Double ret = domCompMatrix.get(key);
				return ret == null ? 0 : ret;
			}
		};

		if (domWRc.isEmpty() || domRa.isEmpty()) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		double sim;
		try {
			sim = Utilities.simplexe(domWRc, domRa, sigma, "domain", "weight");
		} catch (Exception ex) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		if (sim >= 0.6) {
			return 2;
		}
		if (sim >= 0.3) {
			return 1;
		}
		if (sim >= 0.2) {
			return DiscretCompType.NEUTRAL;
		}
		return -1;

	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("domain", "dewey");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("domainDeweySA");
	}

}
