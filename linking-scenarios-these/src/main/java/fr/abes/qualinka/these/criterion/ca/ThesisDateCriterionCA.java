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

/**
 * On compare la date de publication de RC (pubDate) et la date de soutenance de
 * la th?se de RA (thesisDefenseDate).
 *
 * Si pubDate < thesisDefenseDate, return -1, * return 1.
 *
 * ThesisDateCriterionCA
 *
 */
public class ThesisDateCriterionCA extends DefaultCriterion2 {

    public ThesisDateCriterionCA() {
        super("thesisDateCriterionCA", new DefaultDiscretCompType(false, -1, true, 1, false), new String[]{"pubDate"}, new String[]{"thesisDateSA"});
    }

    @Override
    public int compare(IReference first, IReference second) {
        //print("entree dans ThesisDateCriterionCA");
        Integer datePubRC = (Integer) first.getUniqueValue("pubDate");
        JSONArray intThesisSA = second.getValue("thesisDateSA");

        //print("datePubRC = " + datePubRC);
        //print("intThesisSA = " + intThesisSA);
        //print("intThesisSA.get(0) = " + intThesisSA.get(0).get("pubDate").get(0));
        if (datePubRC == null || intThesisSA.length() == 0 || intThesisSA.get(0) == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }
        
        Integer thesisDateRA = (Integer) intThesisSA.get(0);

        //if (datePubRC < intThesisSA.get(0).get("pubDate").get(0)) print("la date est inf");
        //if (datePubRC > intThesisSA.get(0).get("pubDate").get(0)) print("la date est sup");
//		print("datePubRC="+datePubRC+" pubDate="+intThesisSA.get(0).get("pubDate").get(0));
        if (datePubRC < thesisDateRA) {
            return -1;
        }
        if (datePubRC > thesisDateRA) {
            return 1;
        }

        return DiscretCompType.NEUTRAL;
    }
}
