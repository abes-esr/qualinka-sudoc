/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.these.criterion.ca;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.qualinka.these.old.DefaultDiscretCompType;
import fr.abes.qualinka.these.util.NameComparison;
import fr.abes.qualinka.these.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * RC-RA
 *
 * To use between a contextual reference (first) and an authority reference
 * (second). Attributes : - for rc (first): personName - for ra (second):
 * personName Comparison :
 *
 * Return Values : --, -, +, ++ and NOT_COMPARABLE  *
 *
 */
public class PersonNameCriterionCA extends DefaultCriterion2 {

    public PersonNameCriterionCA() {
        super("personNameCriterionCA", new DefaultDiscretCompType(false, -2, false, 2, false), new String[]{"personName"}, new String[]{"personName"});
    }

    @Override
    public int compare(IReference first, IReference second) {
        JSONObject nameRc = (JSONObject) first.getUniqueValue("personName");
        JSONArray namesRa = second.getValue("personName");

        if (nameRc == null || namesRa.isEmpty()) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        JSONObject lnameRc = (JSONObject) nameRc.get("last_name");
        JSONObject fnameRc = (JSONObject) nameRc.get("first_name");

        if (((String)lnameRc.get("raw")).isEmpty()) {
            System.err.println("Comparison of person name with empty name: " + first);
            return DiscretCompType.NOT_COMPARABLE;
        }

        int bestComparisonValue = NameComparison.DISSIMILAR_DENOMINATION;
        for (Object o : namesRa) {
            JSONObject nameRa = (JSONObject) o;
            JSONObject lnameRa = (JSONObject) nameRa.get("last_name");
            JSONObject fnameRa = (JSONObject) nameRa.get("first_name");
            int currentValue;
            if (((String)lnameRa.get("raw")).isEmpty()) {
                System.err.println("Authority person name with empty name: " + second);
                currentValue = NameComparison.EMPTY_DENOMINATION;
            } else {
                currentValue = NameComparison.compareNamesWithPreprocessedEntry(lnameRc, fnameRc, lnameRa, fnameRa);
            }

            if (currentValue == NameComparison.SAME_DENOMINATION) {
                return 2; //i.e. ++
            } else if (bestComparisonValue < currentValue) {
                bestComparisonValue = currentValue;
            }
        }
        switch (bestComparisonValue) {
            case NameComparison.DISSIMILAR_DENOMINATION:
                return -2; //i.e. --
            case NameComparison.DISTANT_DENOMINATION:
                return -1;	//i.e. -
            case NameComparison.EMPTY_DENOMINATION:
                return DiscretCompType.NOT_COMPARABLE;
            case NameComparison.CLOSE_DENOMINATION:
                return 1;		//i.e. +
            case NameComparison.SAME_DENOMINATION:
                return 2;		//i.e. ++
            default: {
                System.err.println("problem of returned value from valCompareNames" + bestComparisonValue);
                return DiscretCompType.NOT_COMPARABLE;
            }
        }
    }
}
