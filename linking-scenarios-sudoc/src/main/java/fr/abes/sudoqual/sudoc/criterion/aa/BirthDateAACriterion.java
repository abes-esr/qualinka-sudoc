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
 * * source: birthDate <br/>
 * * target: birthDate <br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * Ce critère vérifie si la date de naissance de la ra source est égale à la date de naissance de la ra target.
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * NOT_COMPARABLE: données insuffisantes <br/>
 *  
 * * 5 : les dates sont identiques YYYYMMDD (année, mois et jour)
 * 
 * * 4 : les 6 premiers YYYYMM sont identiques (année et mois)
 * 
 * * 3 : les 4 premiers YYYY sont identiques (millénaire, siècle, décénnie et année)
 * 
 * * 2 : Les 3 premiers YYY sont identiques (millénaire, siècle et décénnie)
 * 
 * * 1: Les 2 premiers YY sont identiques (millénaire et siècle) <br/>
 * 
 * * -1: sinon. <br/>
 */
public class BirthDateAACriterion implements Criterion {
	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -1, false, 5, false);
	
	@Override
	public int compare(JSONObject source, JSONObject target) {
		String sourceFeature = sourceFeatureSet().iterator().next();
		String targetFeature = targetFeatureSet().iterator().next();
		if(!source.has(sourceFeature) || !target.has(targetFeature)) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		String dateBirthRASource = source.getString(sourceFeature);
		String dateBirthRATarget = target.getString(targetFeature);
		
		if (dateBirthRASource.length()==8 && dateBirthRATarget.length()==8 && dateBirthRASource.compareTo(dateBirthRATarget) == 0)
			return 5;
		else if ( (dateBirthRASource.length()==6 || dateBirthRATarget.length()==6) && (dateBirthRASource.startsWith(dateBirthRATarget) || dateBirthRATarget.startsWith(dateBirthRASource)) )
			return 4;
		else if ( (dateBirthRASource.length()==4 || dateBirthRATarget.length()==4) && (dateBirthRASource.startsWith(dateBirthRATarget) || dateBirthRATarget.startsWith(dateBirthRASource)) )
			return 3;		    
		else if ( (dateBirthRASource.length()==3 || dateBirthRATarget.length()==3) && (dateBirthRASource.startsWith(dateBirthRATarget) || dateBirthRATarget.startsWith(dateBirthRASource)) )
			return 2;
		else if ( (dateBirthRASource.length()==2 || dateBirthRATarget.length()==2) && (dateBirthRASource.startsWith(dateBirthRATarget) || dateBirthRATarget.startsWith(dateBirthRASource)) )
			return 1;
		else
			return -1;
	}

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("birthDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("birthDate");
	}

}
