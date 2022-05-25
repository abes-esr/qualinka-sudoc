package fr.abes.qualinka.sudoc.feature.ra.computed;

import java.util.Collection;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;


import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;

/**
 * Aggrégation pondérée des dates de publications sous forme d'interval: <br/>
 * {<br/>
 *   "first": 1986,<br/>
 *   "last": 2001<br/>
 *   "representativeness": 5
 * }<br/>
 * 
 * La propriété "representativeness" correspond au nombre de dates aggrégées.
 *
 */
public class DatePubSA implements ComputedFeature<JSONObject> {

	private static final String PUB_DATE_KEY = "pubDate";

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return Set.of(PUB_DATE_KEY);
	}

	@Override
	public JSONObject compute(Collection<JSONObject> selectedData) {
		int nbUsefulSafeLinks = 0;
		int firstDate = -1;
		int lastDate = -1;
		for(JSONObject json: selectedData)
		{		
			if(json.has(PUB_DATE_KEY)) {
    			int date = json.getInt(PUB_DATE_KEY);
				if(nbUsefulSafeLinks > 0)
				{
					if(date < firstDate) {
						firstDate = date;
					} else if (date > lastDate) {
						lastDate = date;
					}
				}
				else
				{
					firstDate = date;
					lastDate = date;
				}
				nbUsefulSafeLinks++;
			}
		}		
		
		JSONArray ret_array=new JSONArray();
		if(nbUsefulSafeLinks>0)
		{
			JSONObject dateMap = new JSONObject();
			dateMap.put("first",firstDate);
			dateMap.put("last",lastDate);
			dateMap.put("representativeness",nbUsefulSafeLinks);
			return dateMap;
		}
		return null;
	}

}
