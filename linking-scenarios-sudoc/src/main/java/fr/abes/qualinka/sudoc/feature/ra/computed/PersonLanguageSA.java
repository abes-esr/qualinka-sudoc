package fr.abes.qualinka.sudoc.feature.ra.computed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;
import fr.abes.qualinka.sudoc.util.RoleUtils;

/**
 * Aggrégation pondérée et conditionnelle (RoleUtils.langRoleFilter(role)) des "firstExpLanguage" et "expLanguage".
 *
 */
public class PersonLanguageSA implements ComputedFeature<JSONObject> {

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return Set.of("role", "expLanguage", "firstExpLanguage");
	}

	@Override
	public JSONObject compute(Collection<JSONObject> selectedData) {
		int nbUsefulSafeLinks = 0;
		HashMap<String, Integer> map = new HashMap<>();
		
		for(JSONObject json:selectedData)
		{
			if(json.has("role") && !json.getJSONArray("role").isEmpty()) {
				String role = json.getJSONArray("role").getString(0);
				if(RoleUtils.langRoleFilter(role)) {
    				if("author".equals(role) && json.has("firstExpLanguage") && !json.getJSONArray("firstExpLanguage").isEmpty())
    				{
    					nbUsefulSafeLinks++;
    					String lang=json.getJSONArray("firstExpLanguage").getString(0);
    					Integer nb_lang = map.get(lang);
    					if(nb_lang==null) map.put(lang,1);
    					else map.put(lang,nb_lang+1);
    				}  // TODO on pourrait traiter le role traducteur pour mettre les 2 langues
    				else if(json.has("expLanguage") && !json.getJSONArray("expLanguage").isEmpty()) 
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
		
		JSONArray weightedLang = new JSONArray();
		for(String l : map.keySet())
		{
			JSONObject json=new JSONObject();
			json.put("lang",l);
			json.put("weight",(double)map.get(l)/nbUsefulSafeLinks);
			weightedLang.put(json);
		}
		if(nbUsefulSafeLinks>0)
		{
			JSONObject mainJson = new JSONObject();
			mainJson.put("weightedLang",weightedLang);
			mainJson.put("representativeness",nbUsefulSafeLinks);
			return mainJson;
		}
		return null;
	}

}
