package fr.abes.sudoqual.sudoqual1.feature.computed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.linking_module.util.CollectionUtils;
import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;
import fr.abes.sudoqual.sudoqual1.util.Misc;

public class PersonLanguageSA implements ComputedFeature<JSONArray> {

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return CollectionUtils.setFrom("role", "expLanguage", "firstExpLanguage", "author");
	}

	@Override
	public JSONArray compute(Collection<JSONObject> selectedData) {
		int nbUsefulSafeLinks = 0;
		HashMap<String, Integer> map = new HashMap<>();
		
		for(JSONObject json:selectedData)
		{
			if(json.has("role")) {
				JSONArray roleArray = json.getJSONArray("role");
    			if(roleArray.length()!=0 && Misc.langRoleFilter(roleArray.getJSONObject(0).getString("role"))) {
    				if(roleArray.getJSONObject(0).getString("role").equals("author") && json.has("firstExpLanguage") && json.getJSONArray("firstExpLanguage").length()!=0)
    				{
    					nbUsefulSafeLinks++;
    					String lang=json.getJSONArray("firstExpLanguage").getString(0);
    					Integer nb_lang = map.get(lang);
    					if(nb_lang==null) map.put(lang,1);
    					else map.put(lang,nb_lang+1);
    				}  // on pourrait traiter le role traducteur pour mettre les 2 langues
    				else if(json.getJSONArray("expLanguage").length()!=0) 
    				{
    					nbUsefulSafeLinks++;
    					String lang=json.getJSONArray("expLanguage").getString(0);
    					Integer nb_lang = map.get(lang);
    					if(nb_lang==null) map.put(lang,1);
    					else map.put(lang,nb_lang+1);
    				}
    			}
			}
		}
		
		JSONArray ret_array=new JSONArray();

		JSONArray weightedLang = new JSONArray();
		for(String l : map.keySet())
		{
			JSONObject json=new JSONObject();
			json.put("lang",l);
			json.put("weight",(double)map.get(l)/nbUsefulSafeLinks);
			weightedLang.put(json);
		}
		if(nbUsefulSafeLinks!=0)
		{
			JSONObject mainJson = new JSONObject();
			mainJson.put("weightedLang",weightedLang);
			mainJson.put("representativeness",nbUsefulSafeLinks);
			ret_array.put(mainJson);
		}
		return ret_array;
	}

}
