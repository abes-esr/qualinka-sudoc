/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoqual1.criterion.ca;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.sudoqual1.old.DefaultDiscretCompType;
import fr.abes.sudoqual.sudoqual1.old.ISimilarity;
import fr.abes.sudoqual.sudoqual1.util.DeweyRameauMap;
import fr.abes.sudoqual.sudoqual1.util.DomainMap;
import fr.abes.sudoqual.sudoqual1.util.Utilities;
import fr.abes.sudoqual.sudoqual1.util.adapter.DefaultCriterion2;
import fr.abes.sudoqual.sudoqual1.util.adapter.IReference;

public class DomainCriterionCA extends DefaultCriterion2 {
	
	private static final Logger logger = LoggerFactory.getLogger(DomainCriterionCA.class);

    static Map<String, Double> domCompMatrix = DomainMap.createDomainMap();
    static Map<String, String> conversion = DeweyRameauMap.getDeweyRameauMap();

    public DomainCriterionCA() {
        super("domainCriterionCA", new DefaultDiscretCompType(false, -1, true, 2, false), new String[]{"domain", "role", "dewey"}, new String[]{"domainDeweySA"});
    }

    @Override
    public int compare(IReference first, IReference second) {

        JSONArray domRc = first.getValue("domain");
        JSONObject roleRc1 = (JSONObject) first.getUniqueValue("role");
        JSONArray deweyRc = first.getValue("dewey");
        JSONObject domSa = (JSONObject) second.getUniqueValue("domainDeweySA");
        
        if(logger.isInfoEnabled()) {
        	logger.info("compare({}, {}): [{}, {}, {}] // [{}])", first.getAttributes().getString("uri"), second.getAttributes().getString("uri"), domRc, roleRc1, deweyRc, domSa);
        }

        //print("dewey = " + deweyRc);
        //print("domRc = " + domRc);
        if (domRc == null || deweyRc == null || (domRc.isEmpty() && deweyRc.isEmpty()) || roleRc1 == null || domSa == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (!(roleRc1.get("group").equals("unspecific_intellectual_contribution") || roleRc1.get("group").equals("research"))) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        // soit une liste (non pond?r?e) avec r?p?tition de domaines d'une rc
        // et une liste pond?r?e de domaines d'une ra (la somme des poids valant 1)
        //int representativeness = domSa.get("representativeness");
        //if(representativeness < 2) return DefaultDiscretCompType.NEUTRAL;
        JSONArray domRa = (JSONArray) domSa.get("weightedDomain");

        // enlever les dewey non convertibles du d?compte
        HashMap<Integer, Double> mapDomRc = new HashMap<>();
        int nbTotalDom = domRc.length() + deweyRc.length();

        // enlever les dewey non convertibles du d?compte
        for (Object o  : deweyRc) {
            String dd = (String) o;
            Integer intdd = DeweyRameauMap.convertirDeweyRameau(conversion, dd);

            if (intdd == null) {
                nbTotalDom = nbTotalDom - 1;
            }
        }

        for (Object o  : domRc) {
            Integer dd = (Integer) o;
            Double nb_domain = (Double) mapDomRc.get(dd);
            if (nb_domain == null) {
                mapDomRc.put(dd, 1. / nbTotalDom);
            } else {
                mapDomRc.put(dd, nb_domain + 1. / nbTotalDom);
            }
        }

        for (Object o  : deweyRc) {
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
        for (Object dom  : mapDomRc.keySet()) {
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

//print("sim = " + sim);
//if (sim<0.2) print("-1 sur domain");
//if (sim<0.6 && sim >=0.5) print("1 sur domain");
        if (sim >= 0.6) {
        	  if(logger.isInfoEnabled()) {
              	logger.info("return 2");
              }
            return 2;
        }
        if (sim >= 0.3) {
        	 if(logger.isInfoEnabled()) {
               	logger.info("return 1");
               }
            return 1;
        }
        if (sim >= 0.2) {
        	 if(logger.isInfoEnabled()) {
                	logger.info("return 0");
                }
            return DiscretCompType.NEUTRAL;
        }
        return -1;

    }

}
