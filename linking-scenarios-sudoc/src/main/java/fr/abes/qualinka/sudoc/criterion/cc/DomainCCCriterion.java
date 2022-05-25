/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.cc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.qualinka.sudoc.old.ISimilarity;
import fr.abes.qualinka.sudoc.util.DeweyRameauMap;
import fr.abes.qualinka.sudoc.util.DomainMap;
import fr.abes.qualinka.sudoc.util.Utilities;

public class DomainCCCriterion implements Criterion {

	static Map<String, Double> domCompMatrix = DomainMap.createDomainMap();
	static Map<String, String> conversion = DeweyRameauMap.getDeweyRameauMap();

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 2, false);

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("domain", "dewey");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("domain", "dewey");
	}

	@Override
    public int compare(JSONObject source, JSONObject target) {
        JSONArray domRc1 = source.optJSONArray("domain");
        JSONArray deweyRc1 = source.optJSONArray("dewey");

        JSONArray domRc2 = target.optJSONArray("domain");
        JSONArray deweyRc2 = target.optJSONArray("dewey");
        
        if (domRc1 == null) {
			domRc1 = new JSONArray();
		}
		if (deweyRc1 == null) {
			deweyRc1 = new JSONArray();
		}
		if (domRc2 == null) {
			domRc2 = new JSONArray();
		}
		if (deweyRc2 == null) {
			deweyRc2 = new JSONArray();
		}

        if ((domRc1.length() == 0 && deweyRc1.length() == 0) || (domRc2.length() == 0 && deweyRc2.length() == 0)) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        // RC 1
        HashMap<Integer, Double> mapdomRc1 = new HashMap<>();
        int nbTotalDom1 = domRc1.length() + deweyRc1.length();
        // enlever les dewey non convertibles du d?compte
        for (Object o : deweyRc1) {
            String dd = (String) o;
            Integer intdd = DeweyRameauMap.convertirDeweyRameau(conversion, dd);
            if (intdd == null) {
                nbTotalDom1 = nbTotalDom1 - 1;
            }
        }

        for (Object o : domRc1) {
            Integer dd = (Integer) o;
            Double nb_domain = mapdomRc1.get(dd);
            if (nb_domain == null) {
                mapdomRc1.put(dd, 1. / nbTotalDom1);
            } else {
                mapdomRc1.put(dd, nb_domain + 1. / nbTotalDom1);
            }
        }

        for (Object o : deweyRc1) {
            String dd = (String) o;
            Integer intdd = DeweyRameauMap.convertirDeweyRameau(conversion, dd);
            if (intdd != null) {
                Double nb_domain = mapdomRc1.get(intdd);
                if (nb_domain == null) {
                    mapdomRc1.put(intdd, 1. / nbTotalDom1);
                } else {
                    mapdomRc1.put(intdd, nb_domain + 1. / nbTotalDom1);
                }
            }
        }

        JSONArray domWRc1 = new JSONArray();
        for (Integer dom : mapdomRc1.keySet()) {
            JSONObject wd = new JSONObject();
            wd.put("domain", dom);
            wd.put("weight", mapdomRc1.get(dom));
            domWRc1.put(wd);
        }

        // RC 2
        HashMap<Integer, Double> mapdomRc2 = new HashMap<>();
        int nbTotalDom2 = domRc2.length() + deweyRc2.length();
        // enlever les dewey non convertibles du d?compte
        for (Object o : deweyRc2) {
            String dd = (String) o;
            Integer intdd = DeweyRameauMap.convertirDeweyRameau(conversion, dd);
            if (intdd == null) {
                nbTotalDom2 = nbTotalDom2 - 1;
            }
        }

        for (Object o : domRc2) {
            Integer dd = (Integer) o;
            Double nb_domain = mapdomRc2.get(dd);
            if (nb_domain == null) {
                mapdomRc2.put(dd, 1. / nbTotalDom2);
            } else {
                mapdomRc2.put(dd, nb_domain + 1. / nbTotalDom2);
            }
        }

        for (Object o : deweyRc2) {
            String dd = (String) o;
            Integer intdd = DeweyRameauMap.convertirDeweyRameau(conversion, dd);
            if (intdd != null) {
                Double nb_domain = mapdomRc2.get(intdd);
                if (nb_domain == null) {
                    mapdomRc2.put(intdd, 1. / nbTotalDom2);
                } else {
                    mapdomRc2.put(intdd, nb_domain + 1. / nbTotalDom2);
                }
            }
        }

        JSONArray domWRc2 = new JSONArray();
        for (Integer dom : mapdomRc2.keySet()) {
            JSONObject wd = new JSONObject();
            wd.put("domain", dom);
            wd.put("weight", mapdomRc2.get(dom));
            domWRc2.put(wd);
        }

        ISimilarity sigma = new ISimilarity() {
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

        if (domWRc1.length() == 0 || domWRc2.length() == 0) {
            return DiscretCompType.NOT_COMPARABLE;
        }
        double sim;
        try {
            sim = Utilities.simplexe(domWRc1, domWRc2, sigma, "domain", "weight");
        } catch (Exception ex) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (sim >= 0.6) {
            return 2;
        } else if (sim >= 0.5) {
            return 1;
        } else {
            return DiscretCompType.NEUTRAL;
        }
    }

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}
}
