package fr.abes.qualinka.these.filter;

import fr.abes.qualinka.these.util.Misc;
import fr.abes.qualinka.these.util.adapter.DefaultFilter;
import fr.abes.qualinka.these.util.adapter.IReference;

/**
 *  Filtre genre pour une RC
 *
 */
public class LiteraryGenreFilterRC extends DefaultFilter 
{
	public LiteraryGenreFilterRC()
	{
		super("literaryGenreFilterRC",new String[]{"genre"});
	}

	@Override
	public boolean check(IReference ref)
	{
     	String genre = (String) ref.getUniqueValue("genre");
     	return genre!=null && Misc.genreLitteraire(genre);
   	}
   	
}