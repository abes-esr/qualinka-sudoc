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
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;


public class PublisherCriterionCA extends DefaultCriterion2 {

    public PublisherCriterionCA() {
        super("publisherCriterionCA", new DefaultDiscretCompType(false, 0, true, 2, false), new String[]{"publisher"}, new String[]{"publisherSA"});
    }

    @Override
    public int compare(IReference first, IReference second) {
        JSONArray cbRc = first.getValue("publisher");
        JSONArray cbRa = second.getValue("publisherSA");
        if (cbRc == null || cbRa == null || cbRc.length() == 0 || cbRa.length() == 0) {
            return DiscretCompType.NOT_COMPARABLE;
        }
        int bestComparisonValue = 0;
        for (Object o1 : cbRc) {
            JSONObject cbc = (JSONObject) o1;
            for (Object o2 : cbRa) {
                JSONObject cba = (JSONObject) o2;
                float distance = new Levenshtein().getSimilarity((String) cbc.get("comp_value"), (String) cba.get("comp_value"));
                //print("distance = " + distance);
                if (distance > 0.9) {
                    return 2;
                } else if (distance > 0.7) {
                    bestComparisonValue = 1;
                }
            }
        }
        switch (bestComparisonValue) {
            case 0:
                return DiscretCompType.NEUTRAL;
            case 1:
                return 1;
            default:
                return DiscretCompType.NOT_COMPARABLE;
        }
    }
    /*	public int compare(IReference first,IReference second)
	{
		JSONArray cbRc = first.getValue("publisher");
		JSONArray cbRa = second.getValue("publisherSA");

		if(cbRc==null || cbRa==null || cbRc.size()==0 || cbRa.size()==0) return DefaultDiscretCompType.NOT_COMPARABLE;	
		int bestComparisonValue = 0;
   		for(String cbc : cbRc)
    		{    		
    			for(String cba : cbRa)
    			{
    				distance = new Levenshtein().getSimilarity(cbc,cba);
    				if(distance > 0.9) return 2;
				else if(distance > 0.7) bestComparisonValue = 1;
     		}
    		}
    		switch(bestComparisonValue)
    		{
    			case 0 : return DefaultDiscretCompType.NEUTRAL; 
    			case 1 : return 1;		
     	}
	}
     */
}
