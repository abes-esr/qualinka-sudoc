package fr.abes.qualinka.these.filter;

import fr.abes.qualinka.these.util.Misc;
import fr.abes.qualinka.these.util.adapter.DefaultFilter;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONObject;

public class AuthorOfThesisFilterRC extends DefaultFilter
{
	public AuthorOfThesisFilterRC()
	{
		super("authorOfThesisFilterRC",new String[]{"role","scientificWork"});
	}
	
	@Override
	public boolean check(IReference ref)
	{
     	JSONObject role = (JSONObject) ref.getUniqueValue("role");
     	String scientificWork = (String) ref.getUniqueValue("scientificWork");
     	

     	return role!=null 
     			&& scientificWork!=null 
     			&& role.get("role").equals("author") 
     			&& Misc.estThese((String) ref.getUniqueValue("scientificWork"));
   	}	
}
