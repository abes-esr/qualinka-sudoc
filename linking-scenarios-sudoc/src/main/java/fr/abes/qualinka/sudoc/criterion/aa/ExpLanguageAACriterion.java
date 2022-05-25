/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.aa;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: "allExpLanguageSA", "personLanguageSA" <br/>
 * * target: "allExpLanguageSA", "personLanguageSA" <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On calcule deux valeurs : valP concernant « PersonLanguage » et valE
 * concernant « AllExpLanguage », la valeur du critère est le max des deux ou -1
 * si la langue de la ra source est absente des deux attributs de langue de la ra target. 
 * Calcul de valP et de valE (pour simplifier on les initialise à -1) Si la langue de
 * la ra source est dans la valeur de « PersonLanguage » alors si le nombre
 * d’occurrences est >= 5 alors valP=3 sinon si le nombre d’occurrences est <= 2
 * alors valP=2 sinon valP=1 Si la langue de la ra source est dans la valeur de
 * « AllExpLanguage » alors si le nombre d’occurrences est >= 10 alors valE=3
 * sinon si le nombre d’occurrences est <= 3 alors valE=2 sinon valE=1.<br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 3: si les langues de ra source sont fortement représentées dans les langues de ra target<br/>
 * * 2: si les langues de ra source sont moyennement représentées dans les langues de ra target<br/>
 * * 1: si les langues de ra source sont faiblement représentées dans les langues de ra target<br/>
 * * neutral: ? <br/>
 * * -1: si les langues de ra source ne correspondent à aucune langue de ra target.
 */
public class ExpLanguageAACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 3, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("allExpLanguageSA", "personLanguageSA");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("allExpLanguageSA", "personLanguageSA");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		JSONObject elangSa1 = source.optJSONObject("allExpLanguageSA");
		JSONObject plangSa1 = source.optJSONObject("personLanguageSA");
		
		JSONObject elangSa2 = target.optJSONObject("allExpLanguageSA");
		JSONObject plangSa2 = target.optJSONObject("personLanguageSA");

		if ((elangSa1 == null && plangSa1 == null) 
				|| (elangSa2 == null && plangSa2 == null)) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		int valp = 0;
		int cptValp = 0;
		int vale = 0;
		int cptVale = 0;
		if (plangSa2 != null) {
			int representativeness1 = (int) plangSa1.get("representativeness");
			JSONArray wlangRa1 = (JSONArray) plangSa1.get("weightedLang");
			
			int representativeness2 = (int) plangSa2.get("representativeness");
			JSONArray wlangRa2 = (JSONArray) plangSa2.get("weightedLang");
			
			for (Object o1 : wlangRa1) {
				JSONObject wl1 = (JSONObject) o1;
				Object langRa1 = wl1.get("lang");
				//Double weight1 = (Double) wl1.get("weight"); //not used ?
				
				for (Object o2 : wlangRa2) {
					JSONObject wl2 = (JSONObject) o2;
					Object langRa2 = wl2.get("lang");
					Double weight2 = (Double) wl2.get("weight");
				
					if (langRa1.equals(langRa2)) {
						if (weight2 * representativeness2 >= 5) {
							valp = 3;
						} else if (weight2 * representativeness2 <= 2) {
							valp = 1;
						} else {
							valp = 2;
						}
					}
					cptValp++;
				}
			}
		}
		valp = valp/cptValp; //average of valp
		
		if (elangSa2 != null) {
			int representativeness1 = (int) elangSa1.get("representativeness");
			JSONArray wlangRa1 = (JSONArray) elangSa1.get("weightedValues");
			
			int representativeness2 = (int) elangSa2.get("representativeness");
			JSONArray wlangRa2 = (JSONArray) elangSa2.get("weightedValues");
			
			for (Object o1 : wlangRa1) {
				JSONObject wl1 = (JSONObject) o1;
				Object langRa1 = wl1.get("value");
				//Double weight1 = (Double) wl1.get("weight"); //not used ?
				
				for (Object o2 : wlangRa2) {
					JSONObject wl2 = (JSONObject) o2;
					Object langRa2 = wl2.get("value");
					Double weight2 = (Double) wl2.get("weight");
					if (langRa1.equals(langRa2)) {
						if (weight2 * representativeness2 >= 10) {
							vale = 3;
						} else if (weight2 * representativeness2 <= 3) {
							vale = 1;
						} else {
							vale = 2;
						}
					}
					cptVale++;
				}
			}
		}
		vale = vale/cptVale; //average of vale
		
		if (vale != 0) {
			if (valp > vale) {
				return valp;
			} else {
				return vale;
			}
		} else if (valp != 0) {
			return valp;
		} else {
			return -1;
		}
	}
}
