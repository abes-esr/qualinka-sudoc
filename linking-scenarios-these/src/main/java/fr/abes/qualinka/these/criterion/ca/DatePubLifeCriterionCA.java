/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.qualinka.these.criterion.ca;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.qualinka.these.util.adapter.DefaultCriterion2;
import fr.abes.qualinka.these.util.adapter.IReference;


public class DatePubLifeCriterionCA extends DefaultCriterion2 {

    public DatePubLifeCriterionCA() {
        super("datePubLifeCriterionCA", new DiscretCompTypeImpl(true, -1, true, 1, false), new String[]{"pubDate"}, new String[]{"birth", "death", "birthUncertain", "deathUncertain"});
    }

    @Override
    public int compare(IReference first, IReference second) {
        Integer datePubRC = (Integer) first.getUniqueValue("pubDate");
        String birth = (String) second.getUniqueValue("birth");
        String death = (String) second.getUniqueValue("death");
        String birthUncertain = (String) second.getUniqueValue("birthUncertain");
        //String deathUncertain = (String) second.getUniqueValue("deathUncertain");
        int dateBirth = -1;
        int dateDeath = -1;

        if (datePubRC == null || (birth == null && death == null && birthUncertain == null)) {
            return DiscretCompType.NOT_COMPARABLE;
        }

//print("*** Birth = "+birth+"   Death = "+death);
        if (birth != null || death != null) {
            // TODO: traitement du cas des dates approximatives ???
            try {
                if (birth != null) {
                    dateBirth = Integer.decode(birth);
                }
            } catch (NumberFormatException nfe) {
                System.err.println("birthdate unknown or approximative: " + birth);
                return DiscretCompType.NEUTRAL;
            }
            try {
                if (death != null) {
                    dateDeath = Integer.decode(death);
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Deathdate unknown or approximative: " + death);
                return DiscretCompType.NEUTRAL;
            }

            if (birth == null) {
                dateBirth = dateDeath - 100;
            }
            if (death == null) {
                dateDeath = dateBirth + 100; // TODO check, I think we can get 99 by mistake
            }
            if (datePubRC < dateBirth + 15) {
                return DiscretCompType.NEVER;//before life
            } else if (datePubRC >= dateBirth + 15 && datePubRC <= dateDeath) {
                return 1;
            } else {
                return -1;	// after life
            }
        } else if (birthUncertain != null) {

            try {
                if (birthUncertain != null) {
                    Integer dateBirthUncertainBorneInf = Integer.decode(birthUncertain.substring(0, 4));
                    Integer dateBirthUncertainBorneSup = Integer.decode(birthUncertain.substring(5, 9));
                    if (datePubRC < dateBirthUncertainBorneInf) {
                        return DiscretCompType.NEVER;//before life
                    }
                    if (datePubRC > dateBirthUncertainBorneSup + 200) {
                        return -1;	// after life
                    }
                }
            } catch (NumberFormatException nfe) {
                System.err.println("birthUncertain unknown or approximative: " + birthUncertain);
                return DiscretCompType.NEUTRAL;
            }

            return DiscretCompType.NEUTRAL;

        } else {
            return DiscretCompType.NOT_COMPARABLE;
        }
    }
}
