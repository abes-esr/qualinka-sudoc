/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.ca;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: pubDate <br/>
 * * target: datePubSA <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * Ce critère vérifie si la date de publication de la notice contenant la rc est
 * inclut +-X ans dans l'interval de publication de la ra (datePubSA). <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * NOT_COMPARABLE: données insuffisantes ou interval de publication de la ra >
 * 80 ans, <br/>
 * * 2 : si n'est pas NOT_COMPARABLE ET pubDate est inclut dans l'interval
 * datePubSA OU si la taille de l'interval est inférieur à 10 ans ET pubDate est
 * inclut +-3ans. <br/>
 * 
 * * 1: si n'est ni NOT_COMPARABLE ni 2 ET pubDate est inclut dans l'interval
 * datePubSA +-5ans OU si la taille de l'interval est inférieur à 40 ans
 * ET pubDate est inclut +-10ans. <br/>
 * 
 * * neutral: si n'est ni NOT_COMPARABLE, ni 2, ni 1 ET pubDate est inclut
 * +-10ans <br/>
 * 
 * * -1: sinon. <br/>
 */
public class DatePubCACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 2, false);

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("pubDate") || !target.has("datePubSA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		int datePubRC = source.getInt("pubDate");
		JSONObject intPubSA = target.getJSONObject("datePubSA");

		int fdate = intPubSA.getInt("first");
		int ldate = intPubSA.getInt("last");

		int delta = ldate - fdate;
		if (delta > 80) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		if (datePubRC >= fdate && datePubRC <= ldate)
			return 2;
		else if (delta <= 10 && datePubRC >= fdate - 3 && datePubRC <= ldate + 3)
			return 2;
		else if (delta <= 40 && datePubRC >= fdate - 10 && datePubRC <= ldate + 10)
			return 1;		    
		else if (datePubRC >= fdate - 5 && datePubRC <= ldate + 5)
			return 1;
		else if (datePubRC >= fdate - 10 && datePubRC <= ldate + 10)
			return DiscretCompType.NEUTRAL;
		else
			return -1;

	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("pubDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("datePubSA");
	}

}
