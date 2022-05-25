/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoqual1.criterion.ca;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.qualinka.sudoqual1.old.DefaultDiscretCompType;
import fr.abes.qualinka.sudoqual1.old.ISimilarity;
import fr.abes.qualinka.sudoqual1.util.Utilities;
import fr.abes.qualinka.sudoqual1.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.sudoqual1.util.adapter.IReference;


public class DeweyCriterionCA extends DefaultCriterion2 {

    //static Map deweyCompMatrix = createDeweyMap();
    public DeweyCriterionCA() {
        super("deweyCriterionCA", new DefaultDiscretCompType(false, -1, true, 2, false), new String[]{"dewey", "role"}, new String[]{"deweySA"});
    }

    @Override
    public int compare(IReference first, IReference second) {
        JSONArray deweyRc = first.getValue("dewey");
        JSONObject deweyRaIn = (JSONObject) second.getUniqueValue("deweySA");
        JSONObject roleRc1 = (JSONObject) first.getUniqueValue("role");

        if (deweyRc == null || deweyRc.length() == 0 || deweyRaIn == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (!(roleRc1.get("group").equals("unspecific_intellectual_contribution") || roleRc1.get("group").equals("research"))) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        //int representativeness = deweyRaIn.get("representativeness");
        //if(representativeness < 2) return DefaultDiscretCompType.NEUTRAL;
        JSONArray deweyRa = (JSONArray) deweyRaIn.get("weightedDewey");

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
        for (Object dew  : mapDeweyRc.keySet()) {
            JSONObject wd = new JSONObject();
            wd.put("dewey", dew);
            wd.put("weight", mapDeweyRc.get(dew));
            deweyWRc.put(wd);
        }

        ISimilarity sigma = new ISimilarity() {
            @Override
			public double getSimilarity(Object o1, Object o2) {
                /*print("d1 = " + d1);
	      	print("d2 = " + d2);*/
 /*String key;
		      if(d1.intValue()>d2.intValue()) key=""+d2+"-"+d1;
		      else key=""+d1+"-"+d2;
		      Double ret= deweyCompMatrix.get(key);
		      return ret==null ? 0: ret;*/
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

        /*print("test sigma vaut = " + sigma.getSimilarity("001","001"));
        print("test sigma vaut = " + sigma.getSimilarity("051","052"));
        print("test sigma vaut = " + sigma.getSimilarity("012","026"));
        print("test sigma vaut = " + sigma.getSimilarity("145","001"));*/
        if (deweyWRc.length() < 1 || deweyRa.length() < 1) {
            return DiscretCompType.NOT_COMPARABLE;
        }
        double distance;
        try {
            distance = Utilities.simplexe(deweyWRc, deweyRa, sigma, "dewey", "weight");
        } catch (Exception ex) {
            return DiscretCompType.NOT_COMPARABLE;
        }
        //print("la distance = " + distance);
//if (distance<0.2) print("-1 sur Dewey");
//if (distance<0.8 && distance >=0.5) print("1 sur Dewey");
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
}
