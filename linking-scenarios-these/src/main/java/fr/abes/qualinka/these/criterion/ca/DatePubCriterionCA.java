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
import org.json.JSONObject;

/**
 * DatePubCriterionCA
 *
 */
public class DatePubCriterionCA extends DefaultCriterion2 {
	public DatePubCriterionCA() {
		super("datePubCriterionCA", new DiscretCompTypeImpl(false, -2, true, 2, false), new String[] { "pubDate" },
		      new String[] { "datePubSA" });
	}

	@Override
	public int compare(IReference first, IReference second) {
		Integer datePubRC = (Integer) first.getUniqueValue("pubDate");
		JSONObject intPubSA = (JSONObject) second.getUniqueValue("datePubSA");

		if (datePubRC == null || intPubSA == null)
			return DiscretCompType.NOT_COMPARABLE;

		//int representativeness = (int) intPubSA.get("representativeness");
		int fdate = (int) intPubSA.get("first");
		int ldate = (int) intPubSA.get("last");

		//int per = ldate - fdate;
		if ((ldate - fdate) <= 80 && datePubRC >= fdate && datePubRC <= ldate)
			return 2;
		else if ((ldate - fdate) <= 10 && datePubRC >= fdate - 3 && datePubRC <= ldate + 3)
			return 2;
		else if ((ldate - fdate) <= 40 && datePubRC >= fdate - 10 && datePubRC <= ldate + 10)
			return 1;
		else if ((ldate - fdate) <= 80 && datePubRC >= fdate - 5 && datePubRC <= ldate + 5)
			return 1;
		else if ((ldate - fdate) <= 80 && datePubRC >= fdate - 10 && datePubRC <= ldate + 10)
			return DiscretCompType.NEUTRAL;
		else if ((ldate - fdate) <= 80)
			return -1;
		else if ((ldate - fdate) <= 80 && (ldate - fdate) > 40)
			return -2;
		else
			return DiscretCompType.NEUTRAL;

		/*
		 * if (per>80) return DiscretCompTypeImpl.NEUTRAL; // cas de r??dition else
		 * if (datePubRC >= fdate && datePubRC <= ldate) return 2; else if (per<=10 &&
		 * datePubRC >= fdate-3 && datePubRC <= ldate+3) return 2; else if (datePubRC >=
		 * ldate-80 && datePubRC <= fdate+80) return 1; else return -1;
		 */
	}
}
