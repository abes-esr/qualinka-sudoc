/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.cc;

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

public class DeweyCCCriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 2, false);

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("dewey");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("dewey");
	}
	
    @Override
    public int compare(JSONObject source, JSONObject target) {
    	if(!source.has("dewey") || !target.has("dewey")) {
            return DiscretCompType.NOT_COMPARABLE;
    	}
        JSONArray deweyRc1 = source.getJSONArray("dewey");
        JSONArray deweyRc2 = target.getJSONArray("dewey");

        if (deweyRc1.length() == 0 || deweyRc2.length() == 0) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        // pour la rc1
        Map<String, Double> mapdeweyRc1 = new HashMap<>();
        int nbTotalDewey1 = deweyRc1.length();
        for (Object o : deweyRc1) {
            String dd = (String) o;
            if (dd.length() > 2) {
                Double nb_dewey1 = mapdeweyRc1.get(dd);
                if (nb_dewey1 == null) {
                    mapdeweyRc1.put(dd, 1. / nbTotalDewey1);
                } else {
                    mapdeweyRc1.put(dd, nb_dewey1 + 1. / nbTotalDewey1);
                }
            }
        }

        JSONArray deweyWRc1 = new JSONArray();
        for (Object dew   : mapdeweyRc1.keySet()) {
            JSONObject wd = new JSONObject();
            wd.put("dewey", dew);
            wd.put("weight", mapdeweyRc1.get(dew));
            deweyWRc1.put(wd);
        }

        // pour la rc 2
        Map<String, Double> mapdeweyRc2 = new HashMap<>();
        int nbTotalDewey2 = deweyRc2.length();
        for (Object o : deweyRc2) {
            String dd = (String) o;
            if (dd.length() > 2) {
                Double nb_dewey2 = mapdeweyRc2.get(dd);
                if (nb_dewey2 == null) {
                    mapdeweyRc2.put(dd, 1. / nbTotalDewey2);
                } else {
                    mapdeweyRc2.put(dd, nb_dewey2 + 1. / nbTotalDewey2);
                }
            }
        }

        JSONArray deweyWRc2 = new JSONArray();
        for (Object dew   : mapdeweyRc2.keySet()) {
            JSONObject wd = new JSONObject();
            wd.put("dewey", dew);
            wd.put("weight", mapdeweyRc2.get(dew));
            deweyWRc2.put(wd);
        }

        ISimilarity sigma = new ISimilarity() {
            @Override
            public double getSimilarity(Object o1, Object o2) {
                String d1 = (String) o1;
                String d2 = (String) o2;
                if (d1.substring(0, 3).equals(d2.substring(0, 3))) {
                    return 1.0;
                }
                if (d1.substring(0, 2).equals(d2.substring(0, 2))) {
                    return 0.6;
                }
                if (d1.substring(0, 1).equals(d2.substring(0, 1))) {
                    return 0.3;
                }
                return 0.0;
            }
        };
        if (deweyWRc1.length() < 1 || deweyWRc2.length() < 1) {
            return DiscretCompType.NOT_COMPARABLE;
        }
        double distance;
        try {
            distance = Utilities.simplexe(deweyWRc1, deweyWRc2, sigma, "dewey", "weight");
        } catch (Exception ex) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (distance >= 0.8) {
            return 2;
        } else if (distance >= 0.5) {
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
}
