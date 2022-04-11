/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.ca;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * * source: "firstExpLanguage", "expLanguage", "isAuthor" <br/>
 * * target: "allExpLanguageSA", "personLanguageSA" <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * On calcule deux valeurs : valP concernant « PersonLanguage » et valE
 * concernant « AllExpLanguage », la valeur du critère est le max des deux ou -1
 * si la langue de la rc est absente des deux attributs de langue de la ra. La
 * langue de la rc est « FirstExpLanguage » si cet attribut existe et si le rôle
 * de la rc est « author » sinon la langue de la rc est « ExpLanguage ». Calcul
 * de valP et de valE (pour simplifier on les initialise à -1) Si la langue de
 * la rc est dans la valeur de « PersonLanguage » alors si le nombre
 * d’occurrences est >= 5 alors valP=3 sinon si le nombre d’occurrences est <= 2
 * alors valP=2 sinon valP=1 Si la langue de la rc est dans la valeur de
 * « AllExpLanguage » alors si le nombre d’occurrences est >= 10 alors valE=3
 * sinon si le nombre d’occurrences est <= 3 alors valE=2 sinon valE=1.<br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * 3: si la langue de rc est fortement représentée dans les langues de ra<br/>
 * * 2: si la langue de rc est moyennement représentée dans les langues de ra<br/>
 * * 1: si la langue de rc est faiblement représentée dans les langues de ra<br/>
 * * neutral: ? <br/>
 * * -1: si la langue de rc ne correspond à aucune langue de ra.
 */
public class ExpLanguageCACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, true, 3, false);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("firstExpLanguage", "expLanguage", "isAuthor");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("allExpLanguageSA", "personLanguageSA");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		JSONArray langRcTab = source.getJSONArray("expLanguage");
		boolean isAuthor = source.optBoolean("isAuthor");
		JSONArray flangRcTab = source.optJSONArray("firstExpLanguage");

		JSONObject elangSa = target.optJSONObject("allExpLanguageSA");
		JSONObject plangSa = target.optJSONObject("personLanguageSA");

		if (langRcTab == null || langRcTab.length() != 1 || (flangRcTab != null && flangRcTab.length() > 1) // TODO why
																											// does not
																											// manage
																											// length >
																											// 1 as null
				|| (elangSa == null && plangSa == null)) {
			return DiscretCompType.NOT_COMPARABLE;
		}

		String langRc = (String) langRcTab.get(0);
		String flangRc = null;
		if (flangRcTab != null && flangRcTab.length() == 1) {
			flangRc = (String) flangRcTab.get(0);
		}

		if (isAuthor && flangRc != null) {
			langRc = flangRc;
		}

		// If the role does not allow to give an indicator of the language expression of
		// the contextual reference then the criterion value is NEUTRAL
		// Aline 10/07/14 : ajouter toutes les valeurs contenues dans attribut
		// GroupeRole (? cr?er)
		// if(!(roleRc.equals("author") || roleRc.equals("collaborator") ||
		// roleRc.equals("thesis_advisor") || roleRc.equals("editor") ||
		// roleRc.equals("secretary")))
		// return DefaultDiscretCompType.NEUTRAL;
		int valp = 0;
		int vale = 0;
		if (plangSa != null) {
			int representativeness = (int) plangSa.get("representativeness");
			JSONArray wlangRa = (JSONArray) plangSa.get("weightedLang");
			for (Object o : wlangRa) {
				JSONObject wl = (JSONObject) o;
				Object langRa = wl.get("lang");
				Double weight = (Double) wl.get("weight");
				if (langRc.equals(langRa)) {
					if (weight * representativeness >= 5) {
						valp = 3;
					} else if (weight * representativeness <= 2) {
						valp = 1;
					} else {
						valp = 2;
					}
				}
			}
		}
		if (elangSa != null) {
			int representativeness = (int) elangSa.get("representativeness");
			JSONArray wlangRa = (JSONArray) elangSa.get("weightedValues");
			for (Object o : wlangRa) {
				JSONObject wl = (JSONObject) o;
				Object langRa = wl.get("value");
				Double weight = (Double) wl.get("weight");
				if (langRc.equals(langRa)) {
					if (weight * representativeness >= 10) {
						vale = 3;
					} else if (weight * representativeness <= 3) {
						vale = 1;
					} else {
						vale = 2;
					}
				}
			}
		}
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
