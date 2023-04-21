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
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 * CorporateBodyCriterionCA doc
 *
 */
public class CorporateBodyCriterionCA extends DefaultCriterion2 {

	public CorporateBodyCriterionCA() {
		super("corporateBodyCriterionCA", new DefaultDiscretCompType(false, 0, true, 2, false),
		      new String[] { "corporateBody" }, new String[] { "corporateBodySA" });
	}

	@Override
	public int compare(IReference first, IReference second) {
		JSONArray cbRc = first.getValue("corporateBody");
		JSONArray cbRa = second.getValue("corporateBodySA");

		if (cbRc == null || cbRa == null || cbRc.length() == 0 || cbRa.length() == 0) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		int bestComparisonValue = 0;
		for (Object o1 : cbRc) {
			String cbc = (String) o1;
			for (Object o2 : cbRa) {
				String cba = (String) o2;
				float distance = new Levenshtein().getSimilarity(cbc, cba);
				if (distance > 0.9) {
					return 2;
				} else if (distance > 0.7) {
					bestComparisonValue = 1;
				}
			}
		}
		switch (bestComparisonValue) {
			case 0:
				return DiscretCompType.NEUTRAL;
			case 1:
				return 1;
			case 2:
				return 2;
			default:
				return DiscretCompType.NOT_COMPARABLE;
		}
	}
}
