/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.aa;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: datePubSA <br/>
 * * target: datePubSA <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * Ce critère vérifie si l'interval de publication de la ra source (datePubSA) est
 * inclut +-X ans dans l'interval de publication de la ra target (datePubSA). <br/>
 * Et inversement : si l'interval de ra target est inclus dans l'interval de ra source <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * NOT_COMPARABLE: données insuffisantes ou interval de publication de la ra source/target >
 * 80 ans, <br/>
 * * 2 : si n'est pas NOT_COMPARABLE ET datePubSA source est inclut dans l'interval
 * datePubSA target OU si la taille de l'interval target est inférieur à 10 ans ET l'interval source est
 * inclut +-3ans. Et inversement.<br/>
 * 
 * * 1: si n'est ni NOT_COMPARABLE ni 2 ET datePubSA source est inclut dans l'interval
 * datePubSA target +-5ans OU si la taille de l'interval target est inférieur à 40 ans
 * ET datePubSA source est inclut +-10ans. Et inversement.<br/>
 * 
 * * neutral: si n'est ni NOT_COMPARABLE, ni 2, ni 1 ET datePubSA source est inclut
 * +-10ans. Et inversement. <br/>
 * 
 * * -1: sinon. <br/>
 */
public class DatePubAACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 2, false);

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("datePubSA") || !target.has("datePubSA")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		JSONObject intPubSA1 = source.getJSONObject("datePubSA");
		JSONObject intPubSA2 = target.getJSONObject("datePubSA");

		int fdate1 = intPubSA1.getInt("first");
		int ldate1 = intPubSA1.getInt("last");
		
		int fdate2 = intPubSA2.getInt("first");
		int ldate2 = intPubSA2.getInt("last");

		int delta1 = ldate1 - fdate1;
		int delta2 = ldate2 - fdate2;
		if (delta1 > 80 || delta2 > 80) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		if ((fdate1 >= fdate2 && ldate1 <= ldate2) || (fdate2 >= fdate1 && ldate2 <= ldate1)) //date1 into date2 or the inverse
			return 2;		
		else if ( (delta2 <= 10 && fdate1 >= fdate2 - 3 && ldate1 <= ldate2 + 3) ||  (delta1 <= 10 && fdate2 >= fdate1 - 3 && ldate2 <= ldate1 + 3) ) //mini delta2 and date1 more or less included into date2  or the inverse
			return 2;
		else if ( (delta2 <= 40 && fdate1 >= fdate2 - 10 && ldate1 <= ldate2 + 10) || (delta1 <= 40 && fdate2 >= fdate1 - 10 && ldate2 <= ldate1 + 10) )  //medium delta2 and date1 included into date2 +/- 10 years  or the inverse
			return 1;		    
		else if ( (fdate1 >= fdate2 - 5 && ldate1 <= ldate2 + 5) || (fdate2 >= fdate1 - 5 && ldate2 <= ldate1 + 5) ) //date1 into date2 +/- 5 years  or the inverse
			return 1;
		else if ( (fdate1 >= fdate2 - 10 && ldate1 <= ldate2 + 10) || (fdate2 >= fdate1 - 10 && ldate2 <= ldate1 + 10) ) //date1 into date2 +/- 10 years  or the inverse
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
		return Set.of("datePubSA");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("datePubSA");
	}

}
