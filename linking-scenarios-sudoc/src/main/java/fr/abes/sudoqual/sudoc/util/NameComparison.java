package fr.abes.sudoqual.sudoc.util;

import java.text.Normalizer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NameComparison {
	private static final Logger logger = LoggerFactory.getLogger(NameComparison.class);

	/*
	 * Comparison of a pair of last and first names
	 * return values : DISSIMILAR, DISTANT, CLOSE, SAME
	 */

	public static final int SAME_DENOMINATION = 2;
	public static final int CLOSE_DENOMINATION = 1;
	public static final int DISTANT_DENOMINATION = -1;
	public static final int EMPTY_DENOMINATION = -2;   // when a last name is empty
	public static final int DISSIMILAR_DENOMINATION = -3;
        public static final int ERROR_DENOMINATION = Integer.MIN_VALUE;

	public static int compareNames(String n1, String p1, String n2, String p2)
	{
		//compute comparison values for last and first names
		int lnameCompValue = LastNameComparison.comparelName(n1,n2);
		if(lnameCompValue==LastNameComparison.EMPTY_LNAME) return EMPTY_DENOMINATION;
		if(lnameCompValue > LastNameComparison.IDENTICAL_LNAME || lnameCompValue < LastNameComparison.DIFFERENT_LNAME) {
			logger.warn("unknown value returned by compareLastName");
			logger.warn("n1="+n1+" p1="+p1+" n2="+n2+" p2="+p2);
			return 0;
		}
		int fnameCompValue = FirstNameComparison.comparefName(p1,p2);
		if(fnameCompValue > FirstNameComparison.IDENTICAL_FNAME || fnameCompValue < FirstNameComparison.DIFFERENT_FNAME) {
			logger.warn("unknown value returned by compareFirstName");
			return 0;
		}

		return diagnosticCompareNames(lnameCompValue, fnameCompValue);
	}

	public static int compareNamesWithPreprocessedEntry(JSONObject n1, JSONObject p1, JSONObject n2, JSONObject p2)
	{
		//compute comparison values for last and first names
		int lnameCompValue = LastNameComparison.ERROR_LNAME;
		if(n1.getString("raw").isBlank() || n2.getString("raw").isBlank()) {
			logger.warn("Empty last name: compare({}, {})", n1, n2);
			lnameCompValue = LastNameComparison.EMPTY_LNAME;
		} else if(n1.getString("raw").equals(n2.getString("raw"))) {
			lnameCompValue = LastNameComparison.IDENTICAL_LNAME;
		} else {
			lnameCompValue = LastNameComparison.compareNormalizedLastName((String)n1.get("normalized"),(String)n2.get("normalized"));
		}
		if(lnameCompValue==LastNameComparison.EMPTY_LNAME) {
			return EMPTY_DENOMINATION;
		}
		if(lnameCompValue > LastNameComparison.IDENTICAL_LNAME || lnameCompValue < LastNameComparison.DIFFERENT_LNAME) {
			logger.warn("unknown value returned by compareLastName");
			return 0;
		}
		
		int fnameCompValue = FirstNameComparison.ERROR_FNAME;
		if(p1.getString("raw").isBlank() || p2.getString("raw").isBlank()) {
			if(p1.getString("raw").isBlank() && p2.getString("raw").isBlank()) {
				fnameCompValue = FirstNameComparison.TWO_EMPTY_FNAME;
			} else {
				fnameCompValue = FirstNameComparison.ONE_EMPTY_FNAME;
			}
		} else if(p1.getString("raw").equals(p2.getString("raw"))) {
			fnameCompValue = FirstNameComparison.IDENTICAL_FNAME;
		} else {
			fnameCompValue = FirstNameComparison.compareAnalyzedFirstName((JSONArray)p1.get("analyzed"),(JSONArray)p2.get("analyzed"));
		}
		if(fnameCompValue > FirstNameComparison.IDENTICAL_FNAME || fnameCompValue < FirstNameComparison.DIFFERENT_FNAME) {
			logger.warn("unknown value returned by compareFirstName");
			return 0;
		}

		return diagnosticCompareNames(lnameCompValue, fnameCompValue);
	}

	static int diagnosticCompareNames(int lnameCompValue, int fnameCompValue) {
		// agregate last_name and first_name comparison values
		switch(lnameCompValue) {
			case LastNameComparison.IDENTICAL_LNAME :
				if(fnameCompValue >= FirstNameComparison.COMPATIBLE_FNAME || fnameCompValue == FirstNameComparison.TWO_EMPTY_FNAME) return SAME_DENOMINATION;
				else if(fnameCompValue == FirstNameComparison.ONE_EMPTY_FNAME || fnameCompValue == FirstNameComparison.WEAKLY_COMPATIBLE_FNAME) return CLOSE_DENOMINATION;
				else return DISTANT_DENOMINATION;
			case LastNameComparison.STRONGLY_COMPATIBLE_LNAME :
				if(fnameCompValue >= FirstNameComparison.STRONGLY_COMPATIBLE_FNAME) return SAME_DENOMINATION;
				else if(fnameCompValue >= FirstNameComparison.TWO_EMPTY_FNAME) return CLOSE_DENOMINATION;
				else return DISTANT_DENOMINATION;
			case LastNameComparison.COMPATIBLE_LNAME :	
				if(fnameCompValue >= FirstNameComparison.WEAKLY_COMPATIBLE_FNAME) return CLOSE_DENOMINATION;
				else if(fnameCompValue >= FirstNameComparison.DISTANT_FNAME) return DISTANT_DENOMINATION;
				else return DISSIMILAR_DENOMINATION;		
			case LastNameComparison.DISTANT_LNAME :
				if(fnameCompValue >= FirstNameComparison.DISTANT_FNAME) return DISTANT_DENOMINATION;
				else return DISSIMILAR_DENOMINATION;					
			case LastNameComparison.DIFFERENT_LNAME :
				return DISSIMILAR_DENOMINATION;
			case LastNameComparison.EMPTY_LNAME :
				return EMPTY_DENOMINATION;
			default:
                            logger.warn("valeur inconnue="+lnameCompValue);
                            return ERROR_DENOMINATION;
		}
	}
	
	 /** recovering the last_name and first_name from the appellation
	    * last_name : the part before the first comma 
	    * first_name : the part after the fist comma but before the first parenthesis
	    * normally there is a space after the comma and before the parenthesis
	    */
	    public static JSONObject processAppellation(String appellation) {

	            String firstname = "";
	            String lastname = "";
	            int com = appellation.indexOf(",");
	            int end = appellation.indexOf("(") - 1;

	            if ( end>-1 && end<com ) 
	                    com = -1; // comma after parenthesis is not significant
	            if ( end < 0 )
	                    end = appellation.length(); // end of appellation is either a parenthesis or the end of the string

	            if ( com!=-1 )
	            {
	                    lastname = appellation.substring(0, com);
	                    firstname = appellation.substring(com+2, end); 
	            }
	            else
	            {
	                    lastname = appellation.substring(0, end);
	            }
	            JSONObject json2return = new JSONObject();
	            json2return.put("first_name", firstname);
	            json2return.put("last_name", lastname);
	            return json2return;
	    }
	    
	    public static String normalize(String s)
	    {
	       String temp = Normalizer.normalize(s.trim(),Normalizer.Form.NFD);
	       return temp.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
	    }

}

