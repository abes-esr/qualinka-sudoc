package fr.abes.sudoqual.sudoc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final  class RegexUtils {
	private RegexUtils() {
	}
	
	public static String findLast(Pattern p, String s, int groupToCapture) {
		String res = null;
		Matcher m = p.matcher(s);
		while (m.find()) {
		    res = m.group(groupToCapture);
		} 
		return res;
	}

}
