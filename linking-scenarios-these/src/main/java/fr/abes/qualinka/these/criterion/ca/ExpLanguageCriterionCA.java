/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.these.criterion.ca;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.qualinka.these.old.DefaultDiscretCompType;
import fr.abes.qualinka.these.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExpLanguageCriterionCA extends DefaultCriterion2 {

    public ExpLanguageCriterionCA() {
        super("expLanguageCriterionCA", new DefaultDiscretCompType(false, -1, true, 3, false), new String[]{"firstExpLanguage", "expLanguage", "role"}, new String[]{"allExpLanguageSA", "personLanguageSA"});
    }

    @Override
    public int compare(IReference first, IReference second) {
        JSONArray langRcTab = first.getValue("expLanguage");
        JSONArray flangRcTab = first.getValue("firstExpLanguage");
        JSONObject roleRc1 = (JSONObject) first.getUniqueValue("role");

        JSONObject elangSa = second.getAttributes().optJSONObject("allExpLanguageSA");
        JSONObject plangSa = (JSONObject) second.getUniqueValue("personLanguageSA");
        //print("langRcTab = " + langRcTab);
        //print("flangRcTab = " + flangRcTab);
        //print("langRcTab(1] = " + langRcTab.get(0));

        if (langRcTab == null || langRcTab.length() == 0 || langRcTab.length() > 1 || 
        		(flangRcTab != null && flangRcTab.length() > 1) || roleRc1 == null || 
        		(elangSa == null && plangSa == null)) {
            return DiscretCompType.NOT_COMPARABLE;
        }
        String roleRc = (String) roleRc1.get("role");

        String langRc = (String) langRcTab.get(0);
        String flangRc = null;
        if (flangRcTab != null && flangRcTab.length() == 1) {
            flangRc = (String) flangRcTab.get(0);
        }

        if (roleRc.equals("author") && flangRc != null) {
            langRc = flangRc;
        }

        // If the role does not allow to give an indicator of the language expression of the contextual reference then the criterion value is NEUTRAL
        // Aline 10/07/14 : ajouter toutes les valeurs contenues dans attribut GroupeRole (? cr?er)
        //	if(!(roleRc.equals("author") || roleRc.equals("collaborator") || roleRc.equals("thesis_advisor") || roleRc.equals("editor") || roleRc.equals("secretary")))
        //		return DefaultDiscretCompType.NEUTRAL;
        int valp = 0;
        int vale = 0;
        if (plangSa != null) {
            int representativeness = (int) plangSa.get("representativeness");
            JSONArray wlangRa = (JSONArray) plangSa.get("weightedLang");
            for (Object o : wlangRa) {
                JSONObject wl = (JSONObject)o;
                Object langRa = wl.get("lang");
                Double weight = (Double) wl.get("weight");
                if (langRc.equals(langRa)) {
                    if (weight * representativeness >= 5) {
                        valp = 3;
                    } else if (weight * representativeness <= 2) {
                        valp = 1;
                    } else {
                        valp = 2;
                    }
                }
            }
        }
        if (elangSa != null) {
            int representativeness = (int) elangSa.get("representativeness");
            JSONArray wlangRa = (JSONArray) elangSa.get("weightedValues");
            for (Object o : wlangRa) {
                JSONObject wl = (JSONObject) o;
                Object langRa = wl.get("value");
                Double weight = (Double) wl.get("weight");
                if (langRc.equals(langRa)) {
                    if (weight * representativeness >= 10) {
                        vale = 3;
                    } else if (weight * representativeness <= 3) {
                        vale = 1;
                    } else {
                        vale = 2;
                    }
                }
            }
        }
        if (vale != 0) {
            if (valp > vale) {
                return valp;
            } else {
                return vale;
            }
        } else if (valp != 0) {
            return valp;
        } else {
            return -1;
        }
    }
}
