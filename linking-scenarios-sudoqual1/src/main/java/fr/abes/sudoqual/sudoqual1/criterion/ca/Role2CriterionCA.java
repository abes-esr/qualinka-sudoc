/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoqual1.criterion.ca;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.sudoqual1.old.DefaultDiscretCompType;
import fr.abes.sudoqual.sudoqual1.util.adapter.DefaultCriterion2;
import fr.abes.sudoqual.sudoqual1.util.adapter.IReference;


public class Role2CriterionCA extends DefaultCriterion2 {

    public Role2CriterionCA() {
        super("role2CriterionCA", new DefaultDiscretCompType(false, -2, true, 2, false), new String[]{"role"}, new String[]{"roleSA", "grpRoleSA"});
        // PVV
        //	ajouter l'attribut rc sur la ref 2   "initialLinks"});
    }

    @Override
    public int compare(IReference first, IReference second) {

        JSONObject roleRc = (JSONObject) first.getUniqueValue("role");
        JSONObject roleRa = second.getAttributes().optJSONObject("roleSA");
        JSONObject grpRoleRa = second.getAttributes().optJSONObject("grpRoleSA");
// LIES A LA PROPORTION DE VALEURS VIDES
//		JSONArray rcRa = second.getValue("initialLinks");

        //print("--->" + roleRc.get("group"));
        //print("roleRc = " + roleRc);
        //print("roleRa = " + roleRa);
        //print("grpRoleRa = " + grpRoleRa);
        if (roleRc == null || roleRa == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (roleRc.get("group").equals("unspecific_intellectual_contribution") || roleRc.get("group").equals("neutral") || roleRc.get("group").equals("research")) {
            return DiscretCompType.NEUTRAL;
        }

        String leRoleRc = (String) roleRc.get("role");
        String grpRc = (String) roleRc.get("group");

        int representativeness = (int) roleRa.get("representativeness");
//   REVOIR LA NOTION DE PROPORTION DE VALEURS VIDES 
//		double proportionValeursVides;
//		if (rcRa.size()==0) proportionValeursVides = 0;
//		else proportionValeursVides = (new Double(rcRa.size() - representativeness))/rcRa.size();
//print("pVV "+ proportionValeursVides+ " "+ rcRa.size()+"-"+representativeness+"/"+rcRa.size());

        JSONArray tabgrpRa = new JSONArray();
        if(grpRoleRa != null && grpRoleRa.has("weightedValues")) {
        	tabgrpRa = (JSONArray) grpRoleRa.get("weightedValues");
        }
        JSONArray tableRoleRa = (roleRa.has("weightedValues"))? (JSONArray) roleRa.get("weightedValues"): new JSONArray();

        //print("AAA" + leRoleRc + " " + grpRc + " "+tabgrpRa+" "+tableRoleRa);
        for (Object o : tableRoleRa) {
            JSONObject wleRoleRa = (JSONObject) o;
            Object ga = wleRoleRa.get("value");
            //Object wa = wleRoleRa.get("weight");

//print("roleRc "+leRoleRc+" role ra :  "+ga+ " weight "+ wa);			
            if (leRoleRc.equals(ga)) {
                return 2;
            }
        }
        for (Object o : tabgrpRa) {
            JSONObject wgrpRa = (JSONObject) o;
            Object gaa = wgrpRa.get("value");
            //Object wa = wgrpRa.get("weight");
//print("roleRc "+roleRc+" roleRa "+ra+ " weight "+ wa);	
//print("roleRc "+roleRc+" groupe ra :  "+ga+ " weight "+ wa);			
            if (grpRc.equals(gaa)) {
                return 1;
            }
        }
// ON NE RENVOIE DU NEGATIF QUE SI LE SUPERATTRIBUT ROLE EST SUFFISAMMENT REPRESENTATIF
        if (representativeness < 3) {
            return DiscretCompType.NEUTRAL;
        } else if (representativeness < 5) {
            return -1;
        } else {
            return -2;
        }
    }
}
