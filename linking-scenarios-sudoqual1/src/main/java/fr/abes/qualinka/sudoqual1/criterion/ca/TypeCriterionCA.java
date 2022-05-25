/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoqual1.criterion.ca;

import org.json.JSONArray;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.qualinka.sudoqual1.old.DefaultDiscretCompType;
import fr.abes.qualinka.sudoqual1.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.sudoqual1.util.adapter.IReference;

public class TypeCriterionCA extends DefaultCriterion2 {
	public TypeCriterionCA() {
		super("TypeCriterionCA", new DefaultDiscretCompType(false, 0, true, 2, false), new String[] { "type" },
		      new String[] { "typeSA" });
	}

	@Override
	public int compare(IReference first, IReference second) {
		String typeRc = (String) first.getUniqueValue("type");
		JSONArray typeRa = second.getValue("typeSA");

		if (typeRc == null || typeRa.length() == 0)
			return DiscretCompType.NOT_COMPARABLE;

		return DiscretCompType.NEUTRAL;

	}
}
