package fr.abes.qualinka.sudoqual1.filter;

import org.json.JSONObject;

import fr.abes.qualinka.sudoqual1.util.Misc;
import fr.abes.qualinka.sudoqual1.util.adapter.DefaultFilter;
import fr.abes.qualinka.sudoqual1.util.adapter.IReference;

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
