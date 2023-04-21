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
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

public class ThesisTitleCriterionCA extends DefaultCriterion2 {

	public ThesisTitleCriterionCA() {
		super("thesisTitleCriterionCA", new DefaultDiscretCompType(false, -1, true, 2, false), new String[] { "title" },
		      new String[] { "thesisTitleSA" });
	}

	@Override
	public int compare(IReference first, IReference second) {
		// print("entree dans critere thesistitle");
		String titRc = null;
		JSONObject titRcJSONObject = (JSONObject) first.getUniqueValue("title");
		if (titRcJSONObject != null) {
			titRc = (String) titRcJSONObject.get("raw");
		}
		JSONArray titsRa = second.getValue("thesisTitleSA");

		// print("titRc = " + titRc);
		// print("titsRa = " + titsRa);
		if (titRc == null || titsRa.length() == 0) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		// print("titRc : "+ titRc);
		double bestSimTitle = 0.;
		double simTitle;
		// title 1
		for (Object o : titsRa) {
			JSONObject titRaJSON = (JSONObject) o;
			String titRa = (String) titRaJSON.get("raw");
			simTitle = new Levenshtein().getSimilarity(titRc.toLowerCase(), titRa.toLowerCase());
			// print("\t titRa : "+ titRa + " (simTitle = "+simTitle+")");
			if (simTitle > 0.9) {
				return 2;
			}
			if (simTitle > bestSimTitle) {
				bestSimTitle = simTitle;
			}
		}
		if (bestSimTitle > 0.7) {
			return 1;
		}

		return -1;
	}

}
