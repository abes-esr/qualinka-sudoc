/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.sudoc.criterion.ca;

import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Attributs utilisés : <br/>
 * source: pubDate<br/>
 * target: birth, death, birthUncertain<br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * Si on connait la date de mort, mais pas la date de naissance ou inversement,
 * on construit la date manquante : on considère arbitrairement que la personne est 
 * morte à 100 ans (dateBirth = dateDeath – 100 ou inversement dateDeath = dateBirth + 100). <br/>
 * <br/>
 * 
 * Valeurs possibles : <br/>
 * * NOT_COMPARABLE: données insuffisantes, ni la dates de naissances, ni la dates de mort, ni la date de 
 *   naissance uncertaine n'est disponible et non-approximative (format correct: YYYY).<br/>
 * * 1 : si la date de publication de rc correspond au vivant de ra 
 * (supérieure ou égale à la date de naissance +15 et inférieure à la date de mort) basé sur "birth" ou "death". <br/>
 * * neutral : si la date de publication est supérieur à la date de naissancs uncertaine min et inférieur à la date
 * de naissance uncertaine max + 100. <br/>
 * * -1 : si la date de publication de rc est postérieure au vivant de ra. <br/>
 * * never : si la date de publication de rc est antérieure au vivant de ra (inférieure à la date de vie +15). <br/>
 * <br/>
 * <br/>
 *
 */
public class DatePubLifeCACriterion implements Criterion {
	
	private static final Logger logger = LoggerFactory.getLogger(DatePubLifeCACriterion.class);
	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(true, -1, true, 1, false);


    @Override
	public int compare(JSONObject source, JSONObject target) {
    	int datePubRC;
    	try {
    		datePubRC = source.getInt("pubDate");
    	} catch (JSONException e) {
    		return DiscretCompType.NOT_COMPARABLE; 
    	}
    	String birth = null, death = null, birthUncertain = null;
    	if(target.has("birth")) {
    		birth = target.getString("birth");
    	}
    	if(target.has("death")) {
    		death = target.getString("death");    		
    	}
    	if(target.has("birthUncertain")) {
    		birthUncertain = target.getString("birthUncertain");
    	}
    	if (birth == null && death == null && birthUncertain == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }
    	
        Integer dateBirth = null;
        Integer dateDeath = null;
        if (birth != null || death != null) {
            // TODO: traitement du cas des dates approximatives ???
            try {
                if (birth != null) {
                    dateBirth = Integer.parseInt(birth);
                }
            } catch (NumberFormatException nfe) {
                logger.warn("birthdate unknown or approximative: {}",birth);
            }
            try {
                if (death != null) {
                    dateDeath = Integer.parseInt(death);
                }
            } catch (NumberFormatException nfe) {
                logger.warn("Deathdate unknown or approximative: {}", death);
            }
            
            if(dateBirth != null || dateDeath != null) {
            	if (dateBirth == null) {
                    dateBirth = dateDeath - 100;
                } else if (dateDeath == null) {
                    dateDeath = dateBirth + 100;
                }
            	
            	if(dateBirth < 1500 || dateBirth > 2020) {
            		throw new RuntimeException("dateBirth" + dateBirth + target.get("uri"));
            	}
            	
                if (datePubRC < dateBirth + 15) {
                    return DiscretCompType.NEVER; //before life
                } else if (datePubRC >= dateBirth + 15 && datePubRC <= dateDeath) {
                    return 1;
                } else {
                    return -1;	// after life
                }
            }
        } 
        
        if (birthUncertain != null) {
            if (birthUncertain != null) {
            	int dateBirthUncertainBorneInf, dateBirthUncertainBorneSup;
				try {
					String[] split = birthUncertain.split("-");
					if(split.length != 2) {
						return DiscretCompType.NOT_COMPARABLE;
					}
                    dateBirthUncertainBorneInf = Integer.parseInt(split[0]);
                    dateBirthUncertainBorneSup = Integer.parseInt(split[1]);
                } catch (NumberFormatException nfe) {
                	logger.warn("birthUncertain unknown or approximative: {}", birthUncertain);
                    return DiscretCompType.NOT_COMPARABLE;
                }
				
				if(dateBirthUncertainBorneInf < 1500 || dateBirthUncertainBorneInf > 2020) {
            		throw new RuntimeException("dateBirthUncertainBorneInf" + dateBirthUncertainBorneInf + target.get("uri"));
            	}
				
				if(dateBirthUncertainBorneSup < 1500 || dateBirthUncertainBorneSup > 2020) {
            		throw new RuntimeException("dateBirthUncertainBorneSup" + dateBirthUncertainBorneSup + target.get("uri"));
            	}
				
                if (datePubRC < dateBirthUncertainBorneInf) {
                    return DiscretCompType.NEVER;//before life
                }
                if (datePubRC > dateBirthUncertainBorneSup + 100) {
                    return -1;	// after life
                }
                return DiscretCompType.NEUTRAL;
            }

        }
        
        return DiscretCompType.NOT_COMPARABLE;
    }

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("pubDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("birth", "death", "birthUncertain");
	}

	
}
