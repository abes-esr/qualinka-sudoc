/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.util;

import java.util.HashMap;
import java.util.Map;

public final class Titles {
	
	//private static final Logger logger = LoggerFactory.getLogger(Titles.class);

	private final static int SEUIL = 10000;	

	// ///////////////////////////////////////////////////////////////////
	//  METHODS
	// ///////////////////////////////////////////////////////////////////

	public static HashMap<String, Double> process(String titre, Map<String, Integer> dico) {
		String[] mots = titre.split("[/\\s.,;!?:()'\"\\[\\]]+");
		HashMap<String, Double> mapTitre = new HashMap<>();

		for (String mot : mots) {
			mot = mot.toLowerCase();
			Integer freq = dico.get(mot);
			if (freq == null) {
				mapTitre.put(mot, 1.);
			} else if (freq <= SEUIL) {
				mapTitre.put(mot, 1. / freq);
			}
		}
		return mapTitre;
	}

	
}
