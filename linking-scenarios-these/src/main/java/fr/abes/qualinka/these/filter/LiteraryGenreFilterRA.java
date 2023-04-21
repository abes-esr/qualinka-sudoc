package fr.abes.qualinka.these.filter;

import fr.abes.qualinka.these.util.Misc;
import fr.abes.qualinka.these.util.adapter.DefaultFilter;
import fr.abes.qualinka.these.util.adapter.IReference;
import org.json.JSONArray;
import org.json.JSONObject;

// Filtre genre pour une RA
public class LiteraryGenreFilterRA extends DefaultFilter 
{
	public LiteraryGenreFilterRA()
	{
		super("literaryGenreFilterRA",new String[]{"genreSA"});
	}

	@Override
	public boolean check(IReference ref)
	{

		JSONObject genreSA = ref.getAttributes().optJSONObject("genreSA");

		if(genreSA==null) return false;	
		
		//int representativeness = genreSA.get("representativeness");
		JSONArray genres = (JSONArray) genreSA.get("weightedValues");
		for(Object wgenre : genres)
		{
			String genre = (String) ((JSONObject) wgenre).get("value");
			//weight=wgenre.get("weight");
               if(Misc.genreLitteraire(genre)) return true; 
		}
		return false ; 
	}
}