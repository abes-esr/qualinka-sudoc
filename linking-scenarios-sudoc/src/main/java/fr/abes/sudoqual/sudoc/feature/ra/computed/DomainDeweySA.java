package fr.abes.sudoqual.sudoc.feature.ra.computed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;
import fr.abes.sudoqual.sudoc.util.DeweyRameauMap;

/**
 * Aggrégation pondérée des "domain" et "dewey"
 *
 */
public class DomainDeweySA implements ComputedFeature<JSONObject> {

	static Map<String,String> conversion = DeweyRameauMap.getDeweyRameauMap();

	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return Set.of("domain", "dewey");
	}

	@Override
	public JSONObject compute(Collection<JSONObject> selectedData) {
	
		HashMap<Integer, Double> result=new HashMap<>();
		int nbUsefulSafeLinks = 0;
		
		for(JSONObject json:selectedData)
		{		
			
			JSONArray domainArray = (json.has("domain"))? json.getJSONArray("domain") : new JSONArray();
			JSONArray deweyArray = (json.has("dewey"))? json.getJSONArray("dewey"): new JSONArray();
			
			HashMap<Integer, Double> map=new HashMap<>();
			if(domainArray.length()!=0 || deweyArray.length()!=0) {
				nbUsefulSafeLinks++;
			}
			for(Object domainObj:domainArray)
			{
				Integer domain = (Integer) domainObj;
				Double nb_domain=map.get(domain);
				if(nb_domain==null) map.put(domain,1.);
				else map.put(domain,nb_domain+1.);
			}

			for(Object domainObj:deweyArray)
			{
				String domain = (String) domainObj;
				Integer intdomain = DeweyRameauMap.convertirDeweyRameau(conversion,domain);
				if (intdomain != null)
				{
					Double nb_domain=map.get(intdomain);
					if(nb_domain==null) map.put(intdomain,1.);
					else map.put(intdomain,nb_domain+1.);
				}
			}
			int taille = deweyArray.length() + domainArray.length();
		
			// enlever les dewey non convertibles du d?compte
	     	for (Object ddObj : deweyArray)
	     	{
	     		String dd = (String) ddObj;
	     		Integer intdomain = DeweyRameauMap.convertirDeweyRameau(conversion,dd);
				if (intdomain == null)
				{
					taille = taille -1;
				}
	     	}
	     	
			for(Integer d:map.keySet())
			{
				Double val=result.get(d);
				if(val==null) result.put(d,(1.0*map.get(d))/taille);
				else result.put(d,val+(1.0*map.get(d))/taille);
			}
		}
	
		JSONArray weightedDomain = new JSONArray();
		for(Integer d:result.keySet())
		{
			JSONObject json=new JSONObject();
			json.put("domain",d);
			json.put("weight",result.get(d)/nbUsefulSafeLinks);
			weightedDomain.put(json);
		}
		if(nbUsefulSafeLinks>0)
		{
			JSONObject mainJson = new JSONObject();
			mainJson.put("weightedDomain",weightedDomain);
			mainJson.put("representativeness",nbUsefulSafeLinks);
			return mainJson;
		}
		return null;
	}

}
