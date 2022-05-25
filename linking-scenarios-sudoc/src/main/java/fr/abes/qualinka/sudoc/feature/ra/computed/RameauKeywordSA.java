package fr.abes.qualinka.sudoc.feature.ra.computed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;

/**
 * Aggrégation pondéré des mots clées "rameau" et "keyword".
 *
 */
public class RameauKeywordSA implements ComputedFeature<JSONObject> {

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return Set.of("rameau", "keyword");
	}

	@Override
	public JSONObject compute(Collection<JSONObject> selectedData) {
	
		HashMap<String, Double> result=new HashMap<>();
		int nbUsefulSafeLinks = 0;
		for(JSONObject json:selectedData)
		{		
			HashMap<String, Double> map=new HashMap<>();
			JSONArray keywordArray = json.has("keyword")? json.getJSONArray("keyword"): new JSONArray();
			JSONArray rameauArray = json.has("rameau")? json.getJSONArray("rameau"): new JSONArray();
			if(keywordArray.length()!=0 || rameauArray.length()!=0) {
				nbUsefulSafeLinks++;
			}
			
			for(Object keywordObj:keywordArray)
			{
				String keyword = (String) keywordObj;
				keyword = keyword.trim().toLowerCase();
				Double nb_keyword=map.get(keyword);
				if(nb_keyword==null) map.put(keyword,1.);
				else map.put(keyword,nb_keyword+1.);
			}
		
			for(Object rameauObj:rameauArray)
			{
				String rameau = (String) rameauObj;
				if (rameau.indexOf("--") != -1)
					rameau = rameau.substring(0, rameau.indexOf("--")).trim().toLowerCase();
					
				//print("rameau = " + rameau);
				Double nb_rameau=map.get(rameau);
				if(nb_rameau==null) map.put(rameau,1.);
				else map.put(rameau,nb_rameau+1.);
			}
			int taille = rameauArray.length() + keywordArray.length();
			for(String d:map.keySet())
			{
				Double val=result.get(d);
				if(val==null) result.put(d,(1.0*map.get(d))/taille);
				else result.put(d,val+(1.0*map.get(d))/taille);
			}
		}
		JSONArray weightedDomain = new JSONArray();
		for(String d:result.keySet())
		{
			JSONObject json=new JSONObject();
			json.put("keyword",d);
			json.put("weight",result.get(d)/nbUsefulSafeLinks);
			weightedDomain.put(json);
		}
		if(nbUsefulSafeLinks>0)
		{
			JSONObject mainJson = new JSONObject();
			mainJson.put("weightedKeyword",weightedDomain);
			mainJson.put("representativeness",nbUsefulSafeLinks);
			return mainJson;
		}
		return null;
	}

}
