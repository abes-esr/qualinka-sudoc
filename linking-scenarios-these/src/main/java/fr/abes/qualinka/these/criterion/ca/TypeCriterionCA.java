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

public class TypeCriterionCA extends DefaultCriterion2 {
	public TypeCriterionCA() {
		super("TypeCriterionCA", new DiscretCompTypeImpl(false, 0, true, 2, false), new String[] { "type" },
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
