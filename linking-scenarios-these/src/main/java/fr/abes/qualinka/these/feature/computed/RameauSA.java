package fr.abes.qualinka.these.feature.computed;

import fr.abes.sudoqual.linking_module.util.CollectionUtils;
import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class RameauSA implements ComputedFeature<JSONArray> {

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return CollectionUtils.setFrom("rameau");
	}

	@Override
	public JSONArray compute(Collection<JSONObject> selectedData) {
		JSONArray ret_array=new JSONArray();
		JSONObject mainJson = new JSONObject();
	
		HashMap<String, Double> result=new HashMap<>();
		int nbUsefulSafeLinks = 0;
		for(JSONObject json:selectedData)
		{	
			if(json.has("rameau")) {
    			HashMap<String, Double> map=new HashMap<>();
    			if(json.getJSONArray("rameau").length()!=0) nbUsefulSafeLinks++;
    			
    			for(Object domainObj:json.getJSONArray("rameau"))
    			{
    				String domain = (String) domainObj;
    				Double nb_domain=map.get(domain);
    				if(nb_domain==null) map.put(domain,1.);
    				else map.put(domain,nb_domain+1.);
    			}
    			for(String d:map.keySet())
    			{
    				Double val=result.get(d);
    				if(val==null) result.put(d,(1.0*map.get(d))/json.getJSONArray("rameau").length());
    				else result.put(d,val+(1.0*map.get(d))/json.getJSONArray("rameau").length());
    			}
			}
		}
		JSONArray weightedDomain = new JSONArray();
		for(String d:result.keySet())
		{
			JSONObject json=new JSONObject();
			json.put("rameau",d);
			json.put("weight",result.get(d)/nbUsefulSafeLinks);
			weightedDomain.put(json);
		}
		if(nbUsefulSafeLinks!=0)
		{
			mainJson.put("weightedRameau",weightedDomain);
			mainJson.put("representativeness",nbUsefulSafeLinks);
			ret_array.put(mainJson);
		}
		return ret_array;
	}

}
