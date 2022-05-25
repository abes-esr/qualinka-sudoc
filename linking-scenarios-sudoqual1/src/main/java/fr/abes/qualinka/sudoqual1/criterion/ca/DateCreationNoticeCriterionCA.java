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
import fr.abes.sudoqual.util.json.JSONArrays;

/**
 * If the RC was initialy linked and the creation date of the RC and RA are the
 * same then return ALWAYS
 */
public class DateCreationNoticeCriterionCA extends DefaultCriterion2 {
	public DateCreationNoticeCriterionCA() {
		super("dateCreationNoticeCriterionCA", new DefaultDiscretCompType(false, 0, true, 0, true),
		      new String[] { "dateCreationNotice" }, new String[] { "dateCreationNotice", "initialLinks" });
	}

	@Override
	public int compare(IReference rc, IReference ra) {
		String dateCreationNoticeRc = (String) rc.getUniqueValue("dateCreationNotice");
		String dateCreationNoticeRa = (String) ra.getUniqueValue("dateCreationNotice");
		JSONArray ppnRc = ra.getValue("initialLinks");

		if (dateCreationNoticeRc == null || dateCreationNoticeRa == null || ppnRc.length() == 0)
			return DiscretCompType.NOT_COMPARABLE;

		if (JSONArrays.contains(ppnRc, rc.getAttributes().getString("uri"))
		    && dateCreationNoticeRc.equals(dateCreationNoticeRa))
			return DiscretCompType.ALWAYS;
		else
			return DiscretCompType.NEUTRAL;
	}
}
