/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.these.criterion.ca;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.qualinka.these.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONArray;
import org.json.JSONObject;


public class ScientificWorkCriterionCA extends DefaultCriterion2 {

    public ScientificWorkCriterionCA() {
        super("ScientificWorkCriterionCA", new DiscretCompTypeImpl(false, -2, true, 3, false), new String[]{"scientificWork"}, new String[]{"initialLinks", "scientificWorkSA"});
        // PVV
        //	ajouter l'attribut rc sur la ref 2   "initialLinks"});
    }

    @Override
    public int compare(IReference first, IReference second) {

        JSONArray roleRc = first.getValue("scientificWork");
        JSONObject roleRa = (JSONObject) second.getUniqueValue("scientificWorkSA");
// LIES A LA PROPORTION DE VALEURS VIDES
//		JSONArray rcRa = second.getValue("initialLinks");

        if (roleRc.length() == 0 || roleRa == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        double representativeness = (double) roleRa.get("representativeness");
        double nbrc = second.getValue("initialLinks").length();

        // a ce stade, on sait que la rc est soit http://www.sudoc.abes.fr/TheseDeDoctorat, soit http://www.sudoc.abes.fr/AutreTravailuniversitaire
        // et que la ra est une liste de soit http://www.sudoc.abes.fr/TheseDeDoctorat, soit http://www.sudoc.abes.fr/TheseDeDoctorat
        // on veut traiter les deux valeurs comme travail universitaire sans distinction
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
// ON NE RENVOIE DU NEGATIF QUE SI LE SUPERATTRIBUT ROLE EST SUFFISAMMENT REPRESENTATIF
        /* Commented because this following code block is unreachable
        if (representativeness < 3) {
            return DiscretCompTypeImpl.NEUTRAL;
        } else if (representativeness < 5) {
            return -1;
        } else {
            return -2;
        }*/
    }
}
