package fr.abes.qualinka.sudoqual1.feature.computed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.util.CollectionUtils;
import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;

public class KeywordSA implements ComputedFeature<JSONArray> {

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return CollectionUtils.setFrom("keyword");
	}

	@Override
	public JSONArray compute(Collection<JSONObject> selectedData) {
		JSONArray ret_array=new JSONArray();
		JSONObject mainJson = new JSONObject();
	
		HashMap<String, Double> result=new HashMap<>();
		int nbUsefulSafeLinks = 0;
		for(JSONObject json:selectedData)
		{		
			if(json.has("keyword")) {
    			HashMap<String, Double> map=new HashMap();
    			if(json.getJSONArray("keyword").length()!=0) {
    				nbUsefulSafeLinks++;
    			}
    			
    			for(Object keywordObj:json.getJSONArray("keyword"))
    			{
    				String keyword = (String) keywordObj;
    				Double nb_keyword=map.get(keyword);
    				if(nb_keyword==null) map.put(keyword,1.);
    				else map.put(keyword,nb_keyword+1.);
    			}
    			for(String d:map.keySet())
    			{
    				Double val=result.get(d);
    				if(val==null) result.put(d,(1.0*map.get(d))/json.getJSONArray("keyword").length());
    				else result.put(d,val+(1.0*map.get(d))/json.getJSONArray("keyword").length());
    			}
			}
		}
		JSONArray weightedKeyword = new JSONArray();
		for(String d:result.keySet())
		{
			JSONObject json=new JSONObject();
			json.put("keyword",d);
			json.put("weight",result.get(d)/nbUsefulSafeLinks);
			weightedKeyword.put(json);
		}
		if(nbUsefulSafeLinks!=0)
		{
			mainJson.put("weightedKeyword",weightedKeyword);
			mainJson.put("representativeness",nbUsefulSafeLinks);
			ret_array.put(mainJson);
		}
		
		return ret_array;
	}

}
