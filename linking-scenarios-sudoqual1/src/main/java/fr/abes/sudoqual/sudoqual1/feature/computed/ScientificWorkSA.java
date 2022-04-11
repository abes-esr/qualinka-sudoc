package fr.abes.sudoqual.sudoqual1.feature.computed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.util.CollectionUtils;
import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;

public class ScientificWorkSA implements ComputedFeature<JSONArray> {

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}
	
	@Override
	public Set<String> getRelatedFeatures() {
		return CollectionUtils.setFrom("scientificWork");
	}

	@Override
	public JSONArray compute(Collection<JSONObject> selectedData) {
		JSONArray ret_array=new JSONArray();
		JSONObject mainJson = new JSONObject();
	
		HashMap<String,Double>  result=new HashMap<>();
		int nbUsefulSafeLinks = 0;
		for(JSONObject json:selectedData)
		{		
			if(json.has("scientificWork")) {
    			HashMap<String, Double>map=new HashMap<>();
    			if(json.getJSONArray("scientificWork").length()!=0) {
    				nbUsefulSafeLinks++;
    			}
    			
    			for(Object scientificWorkObj:json.getJSONArray("scientificWork"))
    			{
    				String scientificWork = (String) scientificWorkObj;
    				Double nb_scientificWork=map.get(scientificWork);
    				if(nb_scientificWork==null) map.put(scientificWork,1.);
    				else map.put(scientificWork,nb_scientificWork+1.);
    			}
    			for(String d:map.keySet())
    			{
    				Double val=result.get(d);
    				if(val==null) result.put(d,(1.0*map.get(d))/json.getJSONArray("scientificWork").length());
    				else result.put(d,val+(1.0*map.get(d))/json.getJSONArray("scientificWork").length());
    			}
			}
		}
		JSONArray weightedSW = new JSONArray();
		for(String d:result.keySet())
		{
			JSONObject json=new JSONObject();
			json.put("scientificWork",d);
			json.put("weight",result.get(d)/nbUsefulSafeLinks);
			weightedSW.put(json);
		}
		if(nbUsefulSafeLinks!=0)
		{
			mainJson.put("weightedScientificWork",weightedSW);
			mainJson.put("representativeness",nbUsefulSafeLinks);
			ret_array.put(mainJson);
		}
		return ret_array;
	}

}
