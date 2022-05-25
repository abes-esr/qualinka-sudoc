package fr.abes.qualinka.sudoc.util;

import java.text.Normalizer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

public final class FirstNameComparison {
    	/*
	 * Comparison of two elements of first name
	 */
	private static final Logger logger = LoggerFactory.getLogger(FirstNameComparison.class);

	// Return values for comparison of two first names   			
	public static final int IDENTICAL_FNAME = 5;	
	public static final int STRONGLY_COMPATIBLE_FNAME = 4;		
	public static final int COMPATIBLE_FNAME = 3;	
	public static final int WEAKLY_COMPATIBLE_FNAME = 2;
	public static final int ONE_EMPTY_FNAME = 1;
	public static final int TWO_EMPTY_FNAME = 0;
	public static final int DISTANT_FNAME = -1;
	public static final int DIFFERENT_FNAME = -2;
        public static final int ERROR_FNAME = Integer.MIN_VALUE;


	// Return values for comparison of two first name elements   			
	static final int compPlus = 3;	// identical and complete
	static final int comp = 2;		// compatible (case of matching initials) 
	static final int compMoins = 1;	// incomplete matching
	static final int incomp = 0;	// different
	static final float threshold = 0.7f;	// threshold used for Levenshtein comparison (case of slight difference in the comparison of complete first name) 

	private FirstNameComparison() {}

	/*
	* Analyze of a first name. Return a JSON array composed of JSON Objects with :
	*  	- type of the element : (S)imple or (C)omposed (case of composed first name)
	*  	- form of the element : (F)ull or (A)bbreviated (case of initiales)
	*  	- value : val for S  or (fst,snd) for C
	*/

	public static JSONArray analyze(String s)
	{
	   JSONArray typedFname= new JSONArray();
	   String[] strTab = Normalizer.normalize(s,Normalizer.Form.NFD).replaceAll(","," ").replaceAll("[^a-zA-Z- .']", "").trim().split(" +");
	   /* strTab est un tableau de chaine ne contenant que des lettres, tiret, apostrophe et point */
	   for(String fname : strTab) {
		int posTiret = fname.indexOf('-');
		if(posTiret==-1 || posTiret==0 || posTiret==fname.length()-1 || posTiret!=fname.lastIndexOf('-')) {
			// type = S
			// cas d'une prenom simple (ou mal compose, i.e. tiret en debut ou en fin ou plusieurs tirets)
			String[] sfnameTab=fname.split("-+");
			for(String sfname : sfnameTab) {
				if(sfname.contains(".")) {
					// forme = A car il s'agit d'un ou plusieurs prenoms simples abrevies
					String[] asfnameTab=fname.split("\\.+");
					for(String asfname : asfnameTab)
						if(asfname.length()!=0)
							typedFname.put(createSFnameElt('A',asfname));
				} else if(sfname.length()==1) {
						// forme = A car il s'agit d'une seule lettre
						typedFname.put(createSFnameElt('A',sfname));
				} else if(sfname.length()==2 && isConsonant(sfname.charAt(0)) && isConsonant(sfname.charAt(1))) {
					if(isUpper(sfname.charAt(0)) && isUpper(sfname.charAt(1))) {
						// 2 fois forme = A car cas de deux consonnes majuscules
						typedFname.put(createSFnameElt('A',sfname.substring(0,1)));
						typedFname.put(createSFnameElt('A',sfname.substring(1,2)));
					} else {
						// 1 seule fois forme = A car cas des Ch, Ph...
							typedFname.put(createSFnameElt('A',sfname));

					}
				} else if(sfname.length()!=0) {
					// forme = E car plus de 2 lettres ou au moins 1 voyelle
					typedFname.put(createSFnameElt('F',sfname));
				}
			}
		} else {
			// type = C sauf si chaine vide a droite ou gauche du tiret
			if(fname.indexOf(".") != -1) {
				// forme A car presence d'un point
				String fst = fname.substring(0,posTiret).replace(".","");
				String snd = fname.substring(posTiret+1).replace(".","");
				if(fst.length()==0 && snd.length()!=0)
					typedFname.put(createSFnameElt('A',snd));
				else if(fst.length()!=0 && snd.length()==0)
					typedFname.put(createSFnameElt('A',fst));
				else if(fst.length()!=0 && snd.length()!=0) 
					typedFname.put(createCFnameElt('A',fst,snd));
			} else {
				String fst = fname.substring(0,posTiret);
				String snd = fname.substring(posTiret+1);
				if(fst.length()==0 && snd.length()!=0)
					typedFname.put(createSFnameElt(snd.length()==1?'A':'F',snd));
				else if(fst.length()!=0 && snd.length()==0)
					typedFname.put(createSFnameElt(fst.length()==1?'A':'F',fst));
				else if(fst.length()!=0 && snd.length()!=0) 
					typedFname.put(createCFnameElt((fst.length()==1||snd.length()==1)?'A':'F',fst,snd));

			}
		}
	   }
	   return typedFname;
	}

	
	public static int comparefName(String fn1, String fn2) {
            JSONArray tabfn1 = FirstNameComparison.analyze(fn1);
            JSONArray tabfn2 = FirstNameComparison.analyze(fn2);
            return compareAnalyzedFirstName(tabfn1, tabfn2);
	}

	public static int compareAnalyzedFirstName(JSONArray tabfn1, JSONArray tabfn2)
	{
	   int l1 = tabfn1.length();
	   int l2 = tabfn2.length();
	   if(l1==0 && l2==0) return TWO_EMPTY_FNAME;
	   if(l1==0 || l2==0) return ONE_EMPTY_FNAME;
           
           JSONArray shortTab = null;
           JSONArray longTab = null;
           int shortSize = -1;
           int longSize = -1;
	   if(l1<=l2) {
		shortTab = tabfn1;
		shortSize = l1;
		longTab  = tabfn2;
		longSize = l2;
	   } else {
		shortTab = tabfn2;
		shortSize = l2; 
		longTab  = tabfn1;
		longSize = l1;
	   }
	   int s = 0;
	   int l = 0;
	   int val = compPlus;
	   boolean unCompPlus = false;
	   boolean premComp = false;
	   boolean omission = false;

	   // case where first element of a list is C and two first elements of the second list are S
	   if(((Character)((JSONObject)shortTab.get(0)).get("type"))=='C' && longSize>1 && ((Character)((JSONObject)longTab.get(0)).get("type"))=='S' && ((Character)((JSONObject)longTab.get(1)).get("type"))=='S') {
		JSONObject recomposedFname = new JSONObject();
		char recomposedform = (((Character)((JSONObject)longTab.get(0)).get("form"))=='A' || ((Character)((JSONObject)longTab.get(1)).get("form"))=='A')?'A':'F';
		recomposedFname.put("type",Character.valueOf('C'));
		recomposedFname.put("form",Character.valueOf(recomposedform));
		recomposedFname.put("fst",(String)((JSONObject)longTab.get(0)).get("val"));
		recomposedFname.put("snd",(String)((JSONObject)longTab.get(1)).get("val"));
		/// Attention, ici on tient compte du codage entier de la valeur de comparaison de deux elements de prenom
		int valr = fnameEltComparison((JSONObject)shortTab.get(0),recomposedFname);
	//print("valR = "+valr+" pour "+recomposedFname+" et "+shortTab.get(0));
		if(valr==comp || valr==compPlus) {
			val = comp;
			premComp = true;
			s++;
			l = l+2;
			if(longSize==shortSize) {
                            // cas ou la longue devient la courte a cause de la composition
                            // ex. J-P Martin et Jean Pierrre
                            JSONArray tempTab = shortTab;
                            shortTab = longTab;
                            longTab = tempTab;
                            int temp = s;
                            s = l;
                            l = temp;
			}   
		}
	   } else if(((Character)((JSONObject)longTab.get(0)).get("type"))=='C' && shortSize>1 && ((Character)((JSONObject)shortTab.get(0)).get("type"))=='S' && ((Character)((JSONObject)shortTab.get(1)).get("type"))=='S') {
		JSONObject recomposedFname = new JSONObject();
		char recomposedform = (((Character)((JSONObject)shortTab.get(0)).get("form"))=='A' || ((Character)((JSONObject)shortTab.get(1)).get("form"))=='A')?'A':'F';
		recomposedFname.put("type",Character.valueOf('C'));
		recomposedFname.put("form",Character.valueOf(recomposedform));
		recomposedFname.put("fst",(String)((JSONObject)shortTab.get(0)).get("val"));
		recomposedFname.put("snd",(String)((JSONObject)shortTab.get(1)).get("val"));
		/// Attention, ici on tient compte du codage entier de la valeur de comparaison de deux elements de prenom
		int valr = fnameEltComparison((JSONObject)longTab.get(0),recomposedFname);
	//print("valR = "+valr+" pour "+recomposedFname+" et "+longTab.get(0));
		if(valr==comp || valr==compPlus) {
			val = comp;
			premComp = true;
			s = s+2;
			l++;	  
		}
	   }

	   while(s<shortSize && l<longSize) {
		int currentVal = fnameEltComparison((JSONObject)shortTab.get(s),(JSONObject)longTab.get(l));
	//print("val = "+currentVal+" pour "+shortTab.get(s)+" et "+longTab.get(l));
		 switch(currentVal) {
			case compPlus :
				unCompPlus = true;
				s++;
				break;
			case comp :
				if(val==compPlus) val = comp;
				if(s==0 && l==0) premComp=true;
				s++;
				break;
			case compMoins :
				if(val==comp || val==compPlus) {
					s++;
					val = compMoins;
				}
				else omission = true;
				break;
			case incomp :
				omission = true;
				break;
		}
		l++;
	   }
	   if(s<shortSize) {
		if(unCompPlus) return DISTANT_FNAME;
		else return testeUnCompPlus(tabfn1,tabfn2);
	   }
	   else if (!omission) {
		if(l==longSize && val==compPlus) return IDENTICAL_FNAME;
		else if(val==compPlus || (val==comp && unCompPlus)) return STRONGLY_COMPATIBLE_FNAME;
		 else if(val==comp) return COMPATIBLE_FNAME;
		 else if(val==compMoins) return DISTANT_FNAME;
                 else 
                 { 
                     logger.warn("valCompareFname : pas d'omission et valeur inconnue");
                     return ERROR_FNAME;
                 }
	   }
	   else { //omission
		 if((val==comp || val==compPlus) && (unCompPlus || premComp)) return WEAKLY_COMPATIBLE_FNAME;
		 else if(val==comp) return DISTANT_FNAME;
		 else if(val==compMoins && premComp) return DISTANT_FNAME;
		 else if(unCompPlus) return DISTANT_FNAME;
		 else return testeUnCompPlus(tabfn1,tabfn2);
	   }
	}

	////////////////////////////////////////////////////////////////////////////
	//	NOT PUBLIC
	////////////////////////////////////////////////////////////////////////////

	static int fnameEltComparison(JSONObject p1, JSONObject p2) {
		char t1 = (char) p1.get("type");
		char f1 = (char) p1.get("form");
		char t2 = (char) p2.get("type");
		char f2 = (char) p2.get("form");

		if(t1=='S' && t2=='S') {
			String v1 = (String) p1.get("val");
			String v2 = (String) p2.get("val");
			if (f1=='F' && f2=='F') {
				if(v1.equals(v2)) return compPlus;
				else if(new Levenshtein().getSimilarity(v1,v2) >= threshold) return compMoins;
				else return incomp;
			}
			else if(f1=='A' && f2=='A') {
				if(v1.indexOf(v2)==0 || v2.indexOf(v1)==0) return comp;
				else return incomp;
			}
			else if((f1=='A' && v2.indexOf(v1)==0) || (f2=='A' && v1.indexOf(v2)==0)) return comp ;
			else return incomp;
		} else if(t1=='C' && t2=='C') {
			String fst1 = (String) p1.get("fst");
			String snd1 = (String) p1.get("snd");
			String fst2 = (String) p2.get("fst");
			String snd2 = (String) p2.get("snd");
			if (f1=='F' && f2=='F') {
				if((fst1+"-"+snd1).equals(fst2+"-"+snd2)) return compPlus;
				else if(new Levenshtein().getSimilarity(fst1+"-"+snd1,fst2+"-"+snd2) >= threshold) return compMoins;
				else return incomp;
			}
			else if(f1=='A' && f2=='A') {
				if((fst1.indexOf(fst2)==0 && snd1.indexOf(snd2)==0) || (fst2.indexOf(fst1)==0 && snd2.indexOf(snd1)==0)) return comp;
				else return incomp;
			}
			else if((f1=='A' && fst2.indexOf(fst1)==0 && snd2.indexOf(snd1)==0) || (f2=='A' && fst1.indexOf(fst2)==0 && snd1.indexOf(snd2)==0)) return comp ;
			else return incomp;
		} else { // 1 type S et 1 type C
                        String vS = null;
                        char fS = 0;
                        String fstC = null;
                        String sndC = null;
			if(t1=='S') {
                            vS = (String) p1.get("val");
                            fS = f1;
                            fstC = (String) p2.get("fst");
                            sndC = (String) p2.get("snd");
			} else {
                            vS = (String) p2.get("val");
                            fS = f2;
                            fstC = (String) p1.get("fst");
                            sndC = (String) p1.get("snd");
			}
			if (f1=='F' && f2=='F') {
				if(vS.equals(fstC)) return compMoins;
				else if(vS.equals(sndC)) return compMoins;
				else return incomp;
			}
			else if(f1=='A' && f2=='A') {
				if(vS.indexOf(fstC)==0 || fstC.indexOf(vS)==0) return compMoins;
				else if(vS.indexOf(sndC)==0 || sndC.indexOf(vS)==0) return compMoins;
				else return incomp;
			}
			else if(fS=='A') {  // et fC = F
				if(fstC.indexOf(vS)==0) return compMoins;
				else if(sndC.indexOf(vS)==0) return compMoins;
				else return incomp;
			}
			else { //fS = A et fC = F
				if(vS.indexOf(fstC)==0) return compMoins;
				else if(vS.indexOf(sndC)==0) return compMoins;
				else return incomp;			
			}
		}
	}



	/* 
	 *   comparison of two first names
	 */



	static int testeUnCompPlus(JSONArray t1, JSONArray t2) {
		for(Object fn1 : t1) 
			for (Object fn2 : t2)
				if(fnameEltComparison((JSONObject)fn1,(JSONObject)fn2)==compPlus) 
                                    return DISTANT_FNAME;
		return DIFFERENT_FNAME;
	}

	
	static JSONObject createSFnameElt(char f, String v){
		// creation d'un element de prenom simple v avec sa forme (F ou A)
		JSONObject fnameElt = new JSONObject();
		fnameElt.put("type",Character.valueOf('S'));
		fnameElt.put("form",Character.valueOf(f));
		fnameElt.put("val",v.toLowerCase());
		return fnameElt;
	}

	static JSONObject createCFnameElt(char f, String v1, String v2){
		// creation d'un element de prenom compos? v1-v2 avec sa forme (F ou A)
		JSONObject fnameElt = new JSONObject();
		fnameElt.put("type",Character.valueOf('C'));
		fnameElt.put("form",Character.valueOf(f));
		fnameElt.put("fst",v1.toLowerCase());
		fnameElt.put("snd",v2.toLowerCase());
		return fnameElt;
	}

        static boolean isConsonant(char c) {
		switch(c) {
			case 'a': case 'e': case 'i': case 'o': case 'u': case 'y': return false;
			default : return true;
		}
	}

        static boolean isUpper(char c) {
		return (c>='A' && c<='Z');
	}

}
