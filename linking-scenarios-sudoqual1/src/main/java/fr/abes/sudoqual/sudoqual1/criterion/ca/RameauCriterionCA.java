/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoqual1.criterion.ca;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.sudoqual1.old.DefaultDiscretCompType;
import fr.abes.sudoqual.sudoqual1.old.ISimilarity;
import fr.abes.sudoqual.sudoqual1.util.Utilities;
import fr.abes.sudoqual.sudoqual1.util.adapter.DefaultCriterion2;
import fr.abes.sudoqual.sudoqual1.util.adapter.IReference;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;


public class RameauCriterionCA extends DefaultCriterion2 {

    public RameauCriterionCA() {
        super("rameauCriterionCA", new DefaultDiscretCompType(false, 0, true, 2, false), new String[]{"rameau", "role"}, new String[]{"rameauSA"});
    }

    @Override
    public int compare(IReference first, IReference second) {

        JSONArray rameauRc = first.getValue("rameau");
        JSONObject rameauRa = (JSONObject) second.getUniqueValue("rameauSA");
        JSONObject roleRc1 = (JSONObject) first.getUniqueValue("role");

        if (rameauRc.length() == 0 || rameauRa == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (!(roleRc1.get("group").equals("unspecific_intellectual_contribution") || roleRc1.get("group").equals("research"))) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        HashMap<String, Double> mapRameauRc = new HashMap<>();
        int nbTotalRameau = rameauRc.length();
        for (Object o : rameauRc) {
            String dd = (String) o;
            Double nb_rameau = mapRameauRc.get(dd);
            if (nb_rameau == null) {
                mapRameauRc.put(dd, 1. / nbTotalRameau);
            } else {
                mapRameauRc.put(dd, nb_rameau + 1. / nbTotalRameau);
            }
        }

        JSONArray rameauWRc = new JSONArray();
        for (String dew : mapRameauRc.keySet()) {
            JSONObject wd = new JSONObject();
            wd.put("rameau", dew);
            wd.put("weight", mapRameauRc.get(dew));
            rameauWRc.put(wd);
        }

        JSONArray rameauWRa = (JSONArray) rameauRa.get("weightedRameau");

        for (Object o : rameauWRa) {
            JSONObject wgrpRa = (JSONObject) o;
            String ga = (String) wgrpRa.get("rameau");
            //print(ga);
            int pos = ga.indexOf(" --");
            if (pos != -1) {
                ga = ga.substring(0, pos);

                wgrpRa.put("rameau", ga.substring(0, pos));
                //print(wgrpRa.get("rameau"));
            }
        }
        for (Object o : rameauWRc) {
            JSONObject wgrpRa = (JSONObject) o;
            String ga = (String) wgrpRa.get("rameau");
            //print(ga);
            int pos = ga.indexOf(" --");
            if (pos != -1) {
                ga = ga.substring(0, pos);

                wgrpRa.put("rameau", ga.substring(0, pos));
                //print(wgrpRa.get("rameau"));
            }
        }

        ISimilarity sigma = new ISimilarity() {
            @Override
            public double getSimilarity(Object mot1, Object mot2) {
                return new Levenshtein().getSimilarity((String)mot1, (String)mot2);
            }
        };

        double distance;
        try {
            distance = Utilities.softCosine(rameauWRc, rameauWRa, sigma, "rameau", "weight");
        } catch (Exception ex) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (distance >= 0.8) {
            return 2;
        } else if (distance >= 0.5) {
            return 1;
        }
        return DiscretCompType.NEUTRAL;
    }

}
