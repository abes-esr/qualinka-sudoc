/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.cc;

import fr.abes.sudoqual.sudoc.criterion.ca.CocontribNameCACriterion;



public class CocontribNameCCCriterion extends CocontribNameCACriterion {

	@Override
	public String targetFeature() {
		return sourceFeature();
	}
}
