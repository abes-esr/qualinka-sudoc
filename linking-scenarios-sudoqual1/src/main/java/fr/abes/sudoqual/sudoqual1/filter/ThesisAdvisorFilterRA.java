package fr.abes.sudoqual.sudoqual1.filter;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.sudoqual1.util.adapter.DefaultFilter;
import fr.abes.sudoqual.sudoqual1.util.adapter.IReference;

public class ThesisAdvisorFilterRA extends DefaultFilter 
{
	public ThesisAdvisorFilterRA()
	{
		super("thesisAdvisorFilterRA",new String[]{"roleSA"});//,"scientificWork"});
	}
	public boolean check(IReference ref)
	{
     	JSONObject roleRa = ref.getAttributes().optJSONObject("roleSA");
     	//String scientificWork = ref.getUniqueValue("scientificWork");
     	if(roleRa == null) {
                 // ce n'est pas un directeur de thèse
     		 return false;
     	}
     	else {
     		JSONArray tableRoleRa = (JSONArray) roleRa.get("weightedValues");
     		for(Object role : tableRoleRa)
			{
				if(((JSONObject) role).get("value").equals("thesis_advisor")) {
	                                // c'est un directeur de thèse
					return true;
				}
     		}
                        // c'est pas un directeur de thèse
			return false;
   		}
	}
}