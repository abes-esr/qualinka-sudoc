/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.these.criterion.ca;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.qualinka.these.old.DefaultDiscretCompType;
import fr.abes.qualinka.these.old.ISimilarity;
import fr.abes.qualinka.these.util.Utilities;
import fr.abes.qualinka.these.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONArray;
import org.json.JSONObject;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

import java.util.HashMap;

public class RameauKeywordCriterionCA extends DefaultCriterion2 {

    public RameauKeywordCriterionCA() {
        super("rameauKeywordCriterionCA", new DefaultDiscretCompType(false, 0, true, 2, false), new String[]{"keyword", "role", "rameau"}, new String[]{"rameauKeywordSA"});
    }

    @Override
    public int compare(IReference first, IReference second) {
        JSONArray keywordRc = first.getValue("keyword");
        JSONArray rameauRc = first.getValue("rameau");
        JSONObject keywordRa = (JSONObject) second.getUniqueValue("rameauKeywordSA");
        JSONObject roleRc1 = (JSONObject) first.getUniqueValue("role");

        if ((keywordRc == null || rameauRc == null || (keywordRc.isEmpty() && rameauRc.isEmpty())) || keywordRa == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (roleRc1 == null || !(roleRc1.get("group").equals("unspecific_intellectual_contribution") || roleRc1.get("group").equals("research"))) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        HashMap<String, Double> mapKeywordRc = new HashMap<>();
        int nbTotal = keywordRc.length() + rameauRc.length();
        for (Object o  : keywordRc) {
            String dd = (String) o;
            dd = dd.trim().toLowerCase();
            Double nb_keyword = (Double) mapKeywordRc.get(dd);
            if (nb_keyword == null) {
                mapKeywordRc.put(dd, 1. / nbTotal);
            } else {
                mapKeywordRc.put(dd, nb_keyword + 1. / nbTotal);
            }
        }
        for (Object o : rameauRc) {
            String dd = (String) o;
            int idx = dd.indexOf("--");
            if (idx >= 0) {
                dd = dd.substring(0, idx).trim().toLowerCase();
            }

            Double nb_rameau = (Double) mapKeywordRc.get(dd);
            if (nb_rameau == null) {
                mapKeywordRc.put(dd, 1. / nbTotal);
            } else {
                mapKeywordRc.put(dd, nb_rameau + 1. / nbTotal);
            }
        }

        JSONArray keywordWRc = new JSONArray();
        for (Object dew  : mapKeywordRc.keySet()) {
            JSONObject wd = new JSONObject();
            wd.put("keyword", dew);
            wd.put("weight", mapKeywordRc.get(dew));
            keywordWRc.put(wd);
        }

        JSONArray keywordWRa = (JSONArray) keywordRa.get("weightedKeyword");

        ISimilarity sigma = new ISimilarity() {
            @Override
            public double getSimilarity(Object mot1, Object mot2) {
                return new Levenshtein().getSimilarity(mot1.toString(), mot2.toString());
            }
        };

        //print("keywordRc = " + keywordWRc);
        //print("keywordRa = " + keywordWRa);
        double distance;
        try {
            distance = Utilities.simplexe(keywordWRc, keywordWRa, sigma, "keyword", "weight");
            //print("la distance simplex = " + distance);
        } catch (Exception ex) {
            return DiscretCompType.NOT_COMPARABLE;
        }

//if (distance<0.8 && distance >=0.5) print("1 sur keyword");
        if (distance >= 0.7) {
            return 2;
        } else if (distance >= 0.3) {
            return 1;
        }
        return DiscretCompType.NEUTRAL;
    }
}
