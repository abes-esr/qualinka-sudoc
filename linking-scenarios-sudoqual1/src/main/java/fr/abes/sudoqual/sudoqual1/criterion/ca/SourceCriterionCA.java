/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoqual1.criterion.ca;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.sudoqual1.old.DefaultDiscretCompType;
import fr.abes.sudoqual.sudoqual1.util.adapter.DefaultCriterion2;
import fr.abes.sudoqual.sudoqual1.util.adapter.IReference;
import fr.abes.sudoqual.util.json.JSONArrays;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 * RC/RA To use between a contextual reference (first) and an authority
 * reference (second). Attributes : - for rc (first): title, date_pub - for ra
 * (second): source, rc (or alternatively ra of first) possibly empty Comparison
 * : (1) the authority ref must have the contextual ref in its rc attribute
 * (alternatively, one could check that the authority reference is the unique
 * value of the contextual reference ra attribute) - we extract a title part and
 * a publication year from the source (if it is possible) - we compare by
 * Levenhstein measure this extracted title with the title of bib. record - we
 * test equality of the extracted year with the publication year of the bib.
 * record Return Values : NEUTRAL, ALWAYS, MATCH, and NOT_COMPARABLE we return
 * NOT_COMPARABLE if required attributes are missing, ALWAYS if (1) is satisfied
 * and if the Levenhstein measure is greater than 0.6 and the years (when they
 * are known) are not different, MATCH (i.e. +1) if no asserted link is
 * established from the contextual reference to the authority reference but the
 * Levenhstein measure is greater than 0.6 and the years (when they are known)
 * are not different, and NEUTRAL otherwise.
*
 */

public class SourceCriterionCA extends DefaultCriterion2 {

    private static final String URI_KEY = "uri";

	public SourceCriterionCA() {
        super("sourceCriterionCA", new DefaultDiscretCompType(false, 0, true, 1, true), new String[]{"title", "pubDate"}, new String[]{"source", "initialLinks"});
    }

    @Override
    public int compare(IReference first, IReference second) {
        String title = null;
        JSONObject titleJSON = (JSONObject) first.getUniqueValue("title");
        if (titleJSON != null) {
            title = (String) titleJSON.get("raw");
        }
        Integer pubYear = (Integer) first.getUniqueValue("pubDate");
        JSONArray sourcesString = second.getValue("source");
        JSONArray ppnRc = second.getValue("initialLinks");
        
        
        if (sourcesString.isEmpty() || title == null || pubYear == null) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        // Comparison for either an Always value, or a positive value depending on rc 
        int retValue;
        if (JSONArrays.contains(ppnRc, first.getAttributes().getString(URI_KEY))) {
            retValue = DiscretCompType.ALWAYS;
        } else {
            retValue = 1;
        }

        // if the title of the rc contains a ':' then :
        //  - the part before ':' is considered as the main title  
        //  - the part after ':' is considered as the subtitle
        String mainTitle = null;
        //String subTitle = null;
        int indSubTitle = title.indexOf(" : ");
        if (indSubTitle != -1) {
            mainTitle = title.substring(0, indSubTitle - 1);
            //subTitle = title.substring(indSubTitle + 3);
        }

        for (Object o  : sourcesString) {
            String aSourceTitle = (String) o;
            double simTitle = 0.; // comparison with Levenshtein
            int eqYear = 0; // three values : -1=different years, 0=lack of information, 1=same years

            // recovering the title portion of the source : the substring before the first slash when there is a slash
            //  - from char 0 to char deb-1 because I assume a space is always inserted before the slash
            //  - or the entire string if there is no slash
            // cf. http://documentation.abes.fr/sudoc/formats/unma/zones/810.htm
            String sourceTitle;
            int deb = aSourceTitle.indexOf(" / ");
            if (deb != -1) {
                sourceTitle = aSourceTitle.substring(0, deb - 1);
            } else {
                sourceTitle = aSourceTitle;
            }

            simTitle = new Levenshtein().getSimilarity(sourceTitle.toLowerCase(), title.toLowerCase()); //comparing with title of bibliographic record
            float simMainTitle;
            if (mainTitle != null) {
                simMainTitle = new Levenshtein().getSimilarity(sourceTitle.toLowerCase(), mainTitle.toLowerCase()); //comparing with title of bibliographic record
            } else {
                simMainTitle = 0;
            }

            // recovering the year portion of the source : the four first non blank chars after the last comma (if it exists) if there are digits
            deb = aSourceTitle.lastIndexOf(",");
            try {
                if (deb != -1) {
                    String sourceYear = aSourceTitle.substring(deb + 1); // FIXME strange behavior on "Architecture pour les réseaux de capteurs mobiles : applications au déploiement de capteur mobile / par Milan Erdelj ; sous la direction de David Simplot-Ryl et la co-direction de Tahiry Razafindralambo, 2013 [thèse, Lille 1]"
                    int pos = 1;
                    while (sourceYear.charAt(pos) == ' ') {
                        pos++;
                    }
                    sourceYear = sourceYear.substring(pos, pos + 4);
                    if (Integer.parseInt(sourceYear) == pubYear) {
                        eqYear = 1;
                    } else {
                        eqYear = -1;
                    }
                } else {
                    eqYear = 0;
                }
            } catch (Exception e) {
                eqYear = 0;
            }
            // TO DEBUG
            //print(sourceTitle + " "+title);
            //print("SimTitle="+simTitle+" & eqYear="+eqYear);

            if ((simTitle > 0.6 || simMainTitle > 0.8) && eqYear != -1) {
                return retValue;
            }
        }
        return DiscretCompType.NEUTRAL;
    }
}
