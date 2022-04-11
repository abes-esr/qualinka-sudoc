package fr.abes.sudoqual.sudoc.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
* comparison of two last names
**/
public final class LastNameComparison {
	
	private static final Logger logger = LoggerFactory.getLogger(LastNameComparison.class);


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
        
    public static int compareRawlName(String n1, String n2) {
    	if(n1.length()==0 || n2.length()==0) {
			logger.warn("Empty last name: compare({}, {})", n1, n2);
			return EMPTY_LNAME; 
		}
		if(n1.equals(n2)) {
			return IDENTICAL_LNAME;
		}
		return 0;
    }


	public static int comparelName(String n1, String n2)
	{
		int res = compareRawlName(n1, n2);
		if(res != 0) {
			return res;
		}
		n1=NameComparison.normalize(n1);
		n2=NameComparison.normalize(n2);
		return compareNormalizedLastName(n1, n2);
	}

	/**
	 *
	 * String n1 a normalized lastname
	 * String n2 an other normalized lastname
	 */
	public static int compareNormalizedLastName(String n1, String n2) {
		n1=neutraliser(n1);
		n2=neutraliser(n2);
		if(n1.equals(n2)) {
			return STRONGLY_COMPATIBLE_LNAME;
		} 

		int valContains12 = contains(n1,n2);
		if(valContains12 == 1) {
			return COMPATIBLE_LNAME;
		}
			
		int valContains21 = contains(n2, n1);
		if(valContains21 == 1) {
			return COMPATIBLE_LNAME;
		}
		
		if(valContains12 == 0 || valContains21 == 0 || new Levenshtein().getSimilarity(n1,n2) > 0.8) {
			return DISTANT_LNAME;
		}
		else {
			return DIFFERENT_LNAME;
		}
		
	}
	
	/**
	 * Returns 1 if contained as a separate word, 0 if contained, -1 if not contained.
	 * @param container
	 * @param stringToSearch
	 * @return
	 */
	private static int contains(String container, String stringToSearch) {
		int i12=container.indexOf(stringToSearch);
		if(i12>=0)
		{
			int deb=i12-1;
			int fin=i12+stringToSearch.length();
			if((deb<0 || Character.isWhitespace(container.charAt(deb))) 
					&& (fin>=container.length() || Character.isWhitespace(container.charAt(fin)))) {
				return 1;
			}
			return 0;
		}
		return -1;
	}

	/**
	 * neutraliser(Cha?ne ch) = Soit une cha?ne ch normalis?e, on retourne une copie de ch 
	 * dans laquelle on a remplac? les ? (apostrophes), les ? (traits d?union) et les . (points) 
	 * par des espaces. Plus on vire les espaces ? gauche et ? droite et on remplace une succession 
	 * d?espaces par un seul.
     *
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