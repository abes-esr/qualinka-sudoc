package fr.abes.qualinka.these.filter;

import fr.abes.qualinka.these.util.adapter.DefaultFilter;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONObject;

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