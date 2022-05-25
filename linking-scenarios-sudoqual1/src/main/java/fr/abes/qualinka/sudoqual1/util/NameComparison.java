package fr.abes.qualinka.sudoqual1.util;

import org.json.JSONArray;
import org.json.JSONObject;

public final class NameComparison {
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
			System.err.println("unknown value returned by compareLastName");
			System.err.println("n1="+n1+" p1="+p1+" n2="+n2+" p2="+p2);
			return 0;
		}
		int fnameCompValue = FirstNameComparison.comparefName(p1,p2);
		if(fnameCompValue > FirstNameComparison.IDENTICAL_FNAME || fnameCompValue < FirstNameComparison.DIFFERENT_FNAME) {
			System.err.println("unknown value returned by compareFirstName");
			return 0;
		}

		return diagnosticCompareNames(lnameCompValue, fnameCompValue);
	}

	public static int compareNamesWithPreprocessedEntry(JSONObject n1, JSONObject p1, JSONObject n2, JSONObject p2)
	{
		//compute comparison values for last and first names
		int lnameCompValue = LastNameComparison.compareNormalizedLastName((String)n1.get("normalized"),(String)n2.get("normalized"));
		if(lnameCompValue==LastNameComparison.EMPTY_LNAME) return EMPTY_DENOMINATION;
		if(lnameCompValue > LastNameComparison.IDENTICAL_LNAME || lnameCompValue < LastNameComparison.DIFFERENT_LNAME) {
			System.err.println("unknown value returned by compareLastName");
			return 0;
		}
		int fnameCompValue = FirstNameComparison.compareAnalyzedFirstName((JSONArray)p1.get("analyzed"),(JSONArray)p2.get("analyzed"));
		if(fnameCompValue > FirstNameComparison.IDENTICAL_FNAME || fnameCompValue < FirstNameComparison.DIFFERENT_FNAME) {
			System.err.println("unknown value returned by compareFirstName");
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
                            System.err.println("valeur inconnue="+lnameCompValue);
                            return ERROR_DENOMINATION;
		}
	}

}

