package fr.abes.sudoqual.sudoqual1.filter;

import org.json.JSONArray;

import fr.abes.sudoqual.sudoqual1.util.adapter.DefaultFilter;
import fr.abes.sudoqual.sudoqual1.util.adapter.IReference;

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