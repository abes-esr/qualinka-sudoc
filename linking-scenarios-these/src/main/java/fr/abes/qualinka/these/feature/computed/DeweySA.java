package fr.abes.qualinka.these.feature.computed;

import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class DeweySA implements ComputedFeature<JSONArray> {

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return Collections.singleton("dewey");
	}

	@Override
	public JSONArray compute(Collection<JSONObject> selectedData) {
		JSONArray ret_array=new JSONArray();
		JSONObject mainJson = new JSONObject();
	
		HashMap<String, Double> result=new HashMap<>();
		int nbUsefulSafeLinks = 0;
		for(JSONObject json:selectedData)
		{	
			if(json.has("dewey")) {
    			JSONArray deweyArray = json.getJSONArray("dewey");
    			HashMap<String, Double> map=new HashMap<>();
    			if(deweyArray.length()!=0) nbUsefulSafeLinks++;
    			
    			for(Object domainObj:deweyArray)
    			{
    				String domain = (String) domainObj;
    				if(domain.length()>2)
    				{
    					Double nb_domain=map.get(domain);
    					if(nb_domain==null) map.put(domain,1.);
    					else map.put(domain,nb_domain+1.);
    				}
    			}
    			for(String d:map.keySet())
    			{
    				Double val=result.get(d);
    				if(val==null) result.put(d,(1.0*map.get(d))/deweyArray.length());
    				else result.put(d,val+(1.0*map.get(d))/deweyArray.length());
    			}
			}
		}
		JSONArray weightedDomain = new JSONArray();
		for(String d:result.keySet())
		{
			JSONObject json=new JSONObject();
			json.put("dewey",d);
			json.put("weight",result.get(d)/nbUsefulSafeLinks);
			weightedDomain.put(json);
		}
		if(nbUsefulSafeLinks!=0)
		{
			mainJson.put("weightedDewey",weightedDomain);
			mainJson.put("representativeness",nbUsefulSafeLinks);
			ret_array.put(mainJson);
		}
		
		return ret_array;
	}

}
