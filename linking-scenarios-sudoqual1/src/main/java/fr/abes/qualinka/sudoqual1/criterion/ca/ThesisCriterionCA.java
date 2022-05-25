/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoqual1.criterion.ca;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.qualinka.sudoqual1.old.DefaultDiscretCompType;
import fr.abes.qualinka.sudoqual1.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.sudoqual1.util.adapter.IReference;

// NOT USED
public class ThesisCriterionCA extends DefaultCriterion2 {

    public ThesisCriterionCA() {
        super("ThesisCriterionCA", new DefaultDiscretCompType(false, -2, true, 3, false), new String[]{"scientificWork", "role"}, new String[]{"initialLinks", "scientificWorkSA", "roleSA"});
        // PVV
        //	ajouter l'attribut rc sur la ref 2   "initialLinks"});
    }

    @Override
    public int compare(IReference first, IReference second) {

        JSONArray scientificWorkRc = first.getValue("scientificWork");
        JSONObject scientificWorkRa = (JSONObject) second.getUniqueValue("scientificWorkSA");
// LIES A LA PROPORTION DE VALEURS VIDES
//		JSONArray rcRa = second.getValue("initialLinks");

        // ci dessous, on ne veut traiter que le cas de la th?se, pas le cas plus large du http://www.sudoc.abes.fr/AutreTravailUniversitaire
        if (scientificWorkRc.length() == 0 || scientificWorkRc.get(0) == "http://www.sudoc.abes.fr/AutreTravailUniversitaire" || scientificWorkRa == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        // ici scientificWorkRc.get(0) vaut forc?ment http://www.sudoc.abes.fr/TheseDeDoctorat
        //print("le role pour these = " + first.getValue("role"));
        //print("le role pour these = " + second.getValue("roleSA"));
        //int representativeness = scientificWorkRa.get("representativeness");
        JSONArray tabgrpRa = (JSONArray) scientificWorkRa.get("weightedScientificWork");
        double nbrc = second.getValue("initialLinks").length();

// on calcule la representativeness r?elle, cad sans AutreTravailUniversitaire qui est aussi r?cup. par la query
        double representativeness = 0.0;
        for (Object o : tabgrpRa) {
            JSONObject wgrpRa = (JSONObject) o;
            Object ga = wgrpRa.get("scientificWork");
            if (ga.equals("http://www.sudoc.abes.fr/TheseDeDoctorat")) {
                representativeness = representativeness + 1.0;
            }
        }

        double wa = representativeness / nbrc;
        //print("representativeness = " + representativeness);
        //print("nbrc = " + nbrc);
        //print("wa = " + wa);

        if (wa < 1. / 3.) {
            return 1;
        } else if (wa < 2. / 3.) {
// PVV
//					if (proportionValeursVides < 1./3.)
            return 2;
//					else return 1;
        } else {
// PVV					if (proportionValeursVides < 1./3.)
            return 3;
//					else if (proportionValeursVides < 2./3.) return 2;
//					else return 1;
        }

// ON NE RENVOIE DU NEGATIF QUE SI LE SUPERATTRIBUT scientificWork EST SUFFISAMMENT REPRESENTATIF
        /* commented because the following code block is unreachable 

        if (representativeness < 3) {
            return DefaultDiscretCompType.NEUTRAL;
        } else if (representativeness < 5) {
            return -1;
        } else {
            return -2;
        }*/
    }
}
