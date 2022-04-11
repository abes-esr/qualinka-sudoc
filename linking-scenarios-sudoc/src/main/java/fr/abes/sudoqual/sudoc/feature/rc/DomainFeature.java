package fr.abes.sudoqual.sudoc.feature.rc;



import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import org.json.JSONObject;

/**
 * Liste de codes domaine. On a une liste de codes erroné pour lesquels on 
 * switch avec le code à jour correspondant. <br/>
 * <br/>
 * Exemple: <br/>
 * "domain": [ "4", "600" ] 
 *
 */
public class DomainFeature extends PreprocessedArrayFeature<String, Integer> {

	
	@Override
	protected boolean checkElementValue(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	@Override
	public JSONObject getElementValidationSchema() {
		return new JSONObject("{'type': 'string', 'pattern': '^[0-9]+$'}");
	}
	
	@Override
	protected Integer buildElementValue(String value) {
		try {
			return map(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private int map(int dom) {
		/*
		 * Suite à notre discussion de ce matin, voici ci-dessous la liste des codes non
		 * répertoriés dans la table d’équivalence. La présence de ces codes est
		 * probablement due à un problème de mise à jour (j'investigue). Pour
		 * parapsychologie, je pencherais plutôt vers 200, car c'est considéré comme une
		 * para-science. Les '?' correspondent à des erreurs. A priori, ce sont les
		 * seuls codes qui ne sont pas dans la table d’équivalence, mais si vous en
		 * trouvez d'autres, indiquez-les moi pour que je vérifie.
		 * 
		 * Valeur Nombre d’occurrences libellé Code "synonyme" 004 1813 Informatique
		 * 621-Informatique 010 492 Bibliographie 000-Généralités 070 489 Médias
		 * d'information 020-Sciences de l'information et de la documentation 090 1 ?
		 * 130 295 Parapsychologie 200-Religion ou 150-Psychologie ? 380 2 ? 388 1 ? 620
		 * 3753 Ingénierie 600-Technique. Sciences de l'ingénieur 634 1 ? 641 782
		 * Cuisine 640-Economie domestique. Cuisine 702 1 ? 78 1 ? 822 1 ? 91 81 ? 960 1
		 * ? 999 2 ? Géographie de la France 2 ?
		 */
		switch (dom) {
			case 4:
				return 621;
			case 10:
				return 0;
			case 70:
				return 20;
			case 130:
				return 150;
			case 620:
				return 600;
			case 641:
				return 640;
			default:
				return dom;
		}

	}

}
