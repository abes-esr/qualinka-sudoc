/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.cc;

import fr.abes.qualinka.sudoc.criterion.ca.PublisherCACriterion;


public class PublisherCCCriterion extends PublisherCACriterion {

	@Override
	public String targetFeature() {
		return sourceFeature();
	}

}
