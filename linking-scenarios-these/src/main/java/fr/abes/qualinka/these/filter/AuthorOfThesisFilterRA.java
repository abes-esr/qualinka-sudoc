package fr.abes.qualinka.these.filter;

import fr.abes.qualinka.these.util.adapter.DefaultFilter;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONArray;

public class AuthorOfThesisFilterRA extends DefaultFilter 
{
	public AuthorOfThesisFilterRA()
	{
		super("authorOfThesisFilterRA",new String[]{"thesisTitleSA"});
	}
	@Override
	public boolean check(IReference ref)
	{
     	JSONArray tit = ref.getValue("thesisTitleSA");
/*if(tit.size()>0) print("auteur de la these "+tit);
else print("pas auteur de thèse");*/
     	return tit != null && tit.length()>0;
   	}	
}