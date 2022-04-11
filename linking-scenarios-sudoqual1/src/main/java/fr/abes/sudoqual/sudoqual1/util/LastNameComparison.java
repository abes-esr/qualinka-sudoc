package fr.abes.sudoqual.sudoqual1.util;

import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
* comparison of two last names
**/
public final class LastNameComparison {

	// Return values for comparison of two last names   			
	public static final int IDENTICAL_LNAME = 2;	
	public static final int STRONGLY_COMPATIBLE_LNAME = 1;		
	public static final int COMPATIBLE_LNAME = 0;	
	public static final int DISTANT_LNAME = -1;
	public static final int DIFFERENT_LNAME = -2;
	public static final int EMPTY_LNAME = -3 ;
        public static final int ERROR_LNAME = Integer.MIN_VALUE ;
        
        private LastNameComparison() {
            
        }

	public static int comparelName(String n1, String n2)
	{
		n1=Names.normalize(n1);
		n2=Names.normalize(n2);
		return compareNormalizedLastName(n1, n2);
	}

	/**
	 *
	 * String n1 a normalized lastname
	 * String n2 an other normalized lastname
	 */
	public static int compareNormalizedLastName(String n1, String n2) {
		if(neutraliser(n1).length()==0 || neutraliser(n2).length()==0) {
			System.err.println("Empty last name in comparelNAme");
			return EMPTY_LNAME; 
		}
		if(n1.equals(n2)) return IDENTICAL_LNAME;
		else 
		{
				n1=neutraliser(n1);
				n2=neutraliser(n2);
				if(n1.equals(n2)) return STRONGLY_COMPATIBLE_LNAME;
			else
				{
					int i12=n1.indexOf(n2);
					if(i12>=0)
					{
						int deb=i12-1;
						int fin=i12+n2.length();
						if((deb<0 || n1.charAt(deb)==' ') && (fin>=n1.length() || n1.charAt(fin)==' ')) return COMPATIBLE_LNAME;
						else return DISTANT_LNAME;
					}
					else
					{
						int i21=n2.indexOf(n1);
						if(i21>=0)
					{
							int deb=i21-1;
							int fin=i21+n1.length();
							if((deb<0 || n2.charAt(deb)==' ') && (fin>=n2.length() || n2.charAt(fin)==' ')) return COMPATIBLE_LNAME;
							else return DISTANT_LNAME;
						}
					}
			}
				if((new Levenshtein().getSimilarity(n1,n2)) > 0.8) return DISTANT_LNAME;
				else return DIFFERENT_LNAME;
		}
	}

	/*
		 neutraliser(Cha?ne ch) = Soit une cha?ne ch normalis?e, on retourne une copie de ch 
		 dans laquelle on a remplac? les ? (apostrophes), les ? (traits d?union) et les . (points) 
		 par des espaces. Plus on vire les espaces ? gauche et ? droite et on remplace une succession 
		 d?espaces par un seul.

	 */
	static String neutraliser(String s)
	{
            s=s.replaceAll("[,.;:_-]"," ");
            String[] tokens = s.split(" +");
            String result=null;
            for(String token : tokens) {
                    if(result==null) 
                        result=token;
                    else 
                        result+=" "+token;
            }
            return result==null ? "":result;
	}


}