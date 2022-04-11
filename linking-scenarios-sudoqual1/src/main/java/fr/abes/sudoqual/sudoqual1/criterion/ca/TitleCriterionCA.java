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
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

public class TitleCriterionCA extends DefaultCriterion2 {

	final static int SEUIL1 = 4000;
	final static int SEUIL2 = 6000;

	public TitleCriterionCA() {
		super("titleCriterionCA", new DefaultDiscretCompType(false, 0, true, 3, false), new String[] { "title" },
		      new String[] { "titleSA" });
	}

	@Override
	public int compare(IReference first, IReference second) {
		JSONObject jsonTitRc = (JSONObject) first.getUniqueValue("title");
		String titRc = null;
		if (jsonTitRc != null) {
			titRc = (String) jsonTitRc.get("raw");
		}
		JSONArray titsRa = second.getValue("titleSA");

		if (titRc == null || titsRa == null || titsRa.isEmpty()) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		double bestSimTitle = 0.;
		double simTitle;

		for (Object o : titsRa) {
			JSONObject jsonTitRa = (JSONObject) o;
			String titRa = (String) jsonTitRa.get("raw");
			simTitle = new Levenshtein().getSimilarity(titRc.toLowerCase(), titRa.toLowerCase());
			if (simTitle > 0.9) {
				return 3;
			}
			if (simTitle > bestSimTitle) {
				bestSimTitle = simTitle;
			}
		}

		if (bestSimTitle > 0.7) {
			return 2;
		}

		// title 2
		bestSimTitle = 0.;
		JSONObject mapTitRC = jsonTitRc.getJSONObject("uncommonWords");

		for (Object o : titsRa) {
			JSONObject jsonTitRa = (JSONObject) o;
			JSONObject mapTitRA = jsonTitRa.getJSONObject("uncommonWords");
			simTitle = 0.;
			for (String motTitRA : mapTitRA.keySet()) {
				for (String motTitRC : mapTitRC.keySet()) {
					if (new Levenshtein().getSimilarity(motTitRC, motTitRA) > 0.9) {
						simTitle = simTitle + (mapTitRC.getDouble(motTitRC) + mapTitRA.getDouble(motTitRA)) / 2.;
					}
				}
			}
			if (simTitle >= 1. / SEUIL1) {
				return 2;
			} else if (simTitle >= 1. / SEUIL2) {
				bestSimTitle = 1.;
			}
		}
		if (bestSimTitle == 1.) {
			return 1;
		}
		return DiscretCompType.NEUTRAL;
	}

}
