package fr.abes.qualinka.sudoqual1.feature.computed;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;

public class DatePubSA implements ComputedFeature<JSONArray> {

	private static final Logger logger = LoggerFactory.getLogger(DatePubSA.class);

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return Collections.singleton("pubDate");
	}

	@Override
	public JSONArray compute(Collection<JSONObject> selectedData) {
		int nbUsefulSafeLinks = 0;
		int date;
		int fdate = -1;
		int ldate = -1;
		for(JSONObject json: selectedData)
		{		
			if(json.has("pubDate")) {
    			int nbDate=json.getJSONArray("pubDate").length();
    			if(nbDate>1) 
    			{
    				if(logger.isWarnEnabled()) {
    					logger.warn("Several publication dates for a contextual reference");
    				}
    			}
    			else if (nbDate==1)
    			{
    				
    				date = json.getJSONArray("pubDate").getInt(0);
    				if(nbUsefulSafeLinks!=0)
    				{
    					if(date<fdate) fdate = date;
    					else if(date>ldate) ldate = date;
    				}
    				else
    				{
    					fdate = date;
    					ldate =date;
    				}
    				nbUsefulSafeLinks++;
    			}
			}
		}		
		
		JSONArray ret_array=new JSONArray();
		if(nbUsefulSafeLinks!=0)
		{
			JSONObject dateMap = new JSONObject();
			dateMap.put("first",fdate);
			dateMap.put("last",ldate);
			dateMap.put("representativeness",nbUsefulSafeLinks);
			ret_array.put(dateMap);
		}
		return ret_array;
	}

}
