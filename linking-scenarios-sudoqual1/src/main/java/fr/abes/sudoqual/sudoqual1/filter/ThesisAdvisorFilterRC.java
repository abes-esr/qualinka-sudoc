package fr.abes.sudoqual.sudoqual1.filter;

import org.json.JSONObject;

import fr.abes.sudoqual.sudoqual1.util.adapter.DefaultFilter;
import fr.abes.sudoqual.sudoqual1.util.adapter.IReference;

public class ThesisAdvisorFilterRC extends DefaultFilter 
{
	public ThesisAdvisorFilterRC()
	{
		super("thesisAdvisorFilterRC",new String[]{"role"});//,"scientificWork"});
	}
	
	@Override
	public boolean check(IReference ref)
	{
     	JSONObject role = (JSONObject) ref.getUniqueValue("role");
     	//String scientificWork = ref.getUniqueValue("scientificWork");
     	

     	return role!=null //&& scientificWork!=null
     	       && role.get("role").equals("thesis_advisor") ;//&& estThese(ref.getUniqueValue("scientificWork"));
   	}
   	
}