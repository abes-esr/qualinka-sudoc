package fr.abes.sudoqual.sudoc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Misc {

	private static final Logger logger = LoggerFactory.getLogger(Misc.class);

	private Misc() {
	}

	public static boolean genreLitteraire(String genre) {
		switch (genre) {
			case "http://www.sudoc.abes.fr/Fiction_roman":
			case "http://www.sudoc.abes.fr/Theatre":
			case "http://www.sudoc.abes.fr/Essais":
			case "http://www.sudoc.abes.fr/Humour_satire":
			case "http://www.sudoc.abes.fr/Lettres":
			case "http://www.sudoc.abes.fr/Nouvelles":
			case "http://www.sudoc.abes.fr/Poesie":
			case "http://www.sudoc.abes.fr/Discours_art_oratoire":
			case "http://www.sudoc.abes.fr/Livret":
			case "http://www.sudoc.abes.fr/Formes_variees_ou_autres_formes_litteraires":
				return true;
			case "http://www.sudoc.abes.fr/Texte_non_litteraire":
				return false;
			default:
				logger.warn("unknown genre : '{}'", genre);
				return false;
		}
	}

	public static boolean estThese(String scientificWork) {
		switch (scientificWork) {
			case "http://www.sudoc.abes.fr/TheseDeDoctorat":
				return true;
			case "http://www.sudoc.abes.fr/AutreTravailUniversitaire":
				return false;
			default:
				logger.warn("unknown scientificWork : {}", scientificWork);
				return false;
		}
	}
	



}
