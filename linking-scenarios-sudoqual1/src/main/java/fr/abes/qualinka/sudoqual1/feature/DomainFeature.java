package fr.abes.qualinka.sudoqual1.feature;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;

/**
 * Exemple:
 * [ "4" ] 
 *
 */
public class DomainFeature implements PreprocessedFeature<JSONArray, JSONArray> {

	private static final Logger logger = LoggerFactory.getLogger(DomainFeature.class);

	public DomainFeature() {
	}

	@Override
	public String getKey() {
		return "domain";
	}

	@Override
	public JSONArray buildValue(JSONArray value) {

		JSONArray res = new JSONArray();
		JSONArray array = (JSONArray) value;
		for (Object o : array) {
			if (o instanceof String) {
				String sdom = (String) o;
				try {
					res.put(process(sdom));

				} catch (NumberFormatException nfe) {
					if (logger.isWarnEnabled()) {
						logger.warn("domain unknown: {}", sdom);
					}
				} catch (Exception nfe) {
					if (logger.isWarnEnabled()) {
						logger.warn("big problem: {}", sdom);
					}
				}
			} else {
				if (logger.isWarnEnabled()) {
					logger.warn("DomainFeature values must be a String, {} found: {}", o.getClass(), o);
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("processed value: '{}' => '{}'", value, res);
		}
		return res;
	}

	public int process(String sdom) {
		int dom = Integer.parseInt(sdom);
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
