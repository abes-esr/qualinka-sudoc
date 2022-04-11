package fr.abes.sudoqual.sudoc.util;

public class StringUtils {

	private StringUtils() {
	}
	
	public static String normalizeSpace(String s) {
		StringBuilder sb = new StringBuilder();
		boolean hasPreviousSpace = false;
		for(char c : s.trim().toCharArray()) {
			if(Character.isWhitespace(c)) {
				if(!hasPreviousSpace) {
					hasPreviousSpace = true;
					sb.append(' ');
				}
			} else {
				hasPreviousSpace = false;
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static String normalize(String s) {
		StringBuilder sb = new StringBuilder();
		for(char c : normalizeSpace(s).toLowerCase().toCharArray()) {
			if((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
				sb.append(c);
			} else if (Character.isWhitespace(c)) {
				sb.append(' ');
			} else {
				switch(c) {
				case 'á':
				case 'à': 
				case 'â':
				case 'ä': 
					sb.append('a');
					break;
				case 'é':
				case 'è':
				case 'ê':
				case 'ë':
					sb.append('e');
					break;
				case 'í':
				case 'ì':
				case 'î':
				case 'ï':
					sb.append('i');
					break;
				case 'ó':
				case 'ò':
				case 'ô':
				case 'ö':
					sb.append('o');
					break;	
				case 'ú':
				case 'ù':
				case 'û':
				case 'ü':
					sb.append('u');
					break;	
				
				case 'ç':
					sb.append('c');
					break;
				case 'œ':
					sb.append("oe");
					break;
				case 'æ':
					sb.append("ae");
					break;
				case '\'':
				case '’':
				case '"':
					sb.append('\'');
					break;
				case '-':
				case '_':
				case '–':
				case '—':
					sb.append('-');
					break;
				default:
					sb.append(c);
				}
			}
		}
		return sb.toString().toUpperCase(); // FIXME
	}
	
}
