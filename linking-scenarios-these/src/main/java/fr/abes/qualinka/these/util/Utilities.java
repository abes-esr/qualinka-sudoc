package fr.abes.qualinka.these.util;

import fr.abes.qualinka.these.old.ISimilarity;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;



public final class Utilities {
	
	private static final Logger logger = LoggerFactory.getLogger(Utilities.class);

	public static long uri2ppn(String sppn)
	{
		sppn=sppn.trim();
		boolean beg1=true;
		boolean beg2=false;
		boolean neg=false;
		String ppn2parse1="";
		String ppn2parse2="";
		for(int i=0;i<sppn.length();i++)
		{
			char c=sppn.charAt(i);
			if(Character.isDigit(c))
			{
				if(beg1)	ppn2parse1+=c;
				else if(beg2) ppn2parse2+=c;
			}
			else if(beg1 && ppn2parse1.length()>0)
				{
					if(c=='X') neg=true;
					else if(c=='-') {
						beg1=false;
						beg2=true;
					}
					else break;
				}
			else if(beg2) break;
		}
		if(ppn2parse1.length()==0) return -1L;
		long ppnLong1=Long.parseLong(ppn2parse1)*1000;
		long ppnLong2=(beg2 && ppn2parse2.length()>0)? Long.parseLong(ppn2parse2):0;
		ppnLong1+=ppnLong2;
		return neg? -ppnLong1:ppnLong1;
	}
	public static String ppn2uri(long ppn){
		return ppn2uri(ppn,false);
	}
	public static String ppn2uri(long lppn,boolean force_biblio)
	{
		boolean neg=lppn<0;
		String sppn;		
		if(neg) 
			{
			lppn=-lppn;
			sppn=String.format("%08d",lppn/1000)+"X";
			}
		else sppn=String.format("%09d",lppn/1000);
		long subppn=lppn%1000;
		if(subppn>100)
		{
			logger.debug("subppn={} sppn={} lppn={}", subppn, sppn, lppn);
		}
		if(subppn==0 && !force_biblio) return "http://www.idref.fr/"+sppn+"/id";
		else return "http://www.sudoc.abes.fr/"+sppn+"-"+String.format("%d",subppn);
	}
        
        public static String ppn2sppn(long lppn) {
            boolean neg=lppn<0;
            String sppn;		
            if(neg) {
                    lppn=-lppn;
                    sppn=String.format("%08d",lppn/1000)+"X";
            } else {
                sppn=String.format("%09d",lppn/1000);
            }
            return sppn;
        }
        
	
	protected static DirectedWeightedMultigraph<String, DefaultWeightedEdge> domainGraph=null;
	public static DirectedWeightedMultigraph<String, DefaultWeightedEdge> getDomainGraph(Map<String,Double> map) 
	{
		if(domainGraph==null){
		domainGraph=new DirectedWeightedMultigraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		HashSet<Integer> domains=new HashSet<Integer>();
		for(String key:map.keySet())
		{
			int i=key.indexOf('-');
			if(map.get(key)==0.0) continue; // nothing to do with zero weighted edges
			Integer d1=Integer.decode(key.substring(0,i));
			Integer d2=Integer.decode(key.substring(i+1));
			domains.add(d1);
			domainGraph.addVertex("s"+d1.intValue());
			domainGraph.addVertex("s"+d2.intValue());
			domainGraph.addVertex("t"+d1.intValue());
			domainGraph.addVertex("t"+d2.intValue());
			domainGraph.setEdgeWeight(domainGraph.addEdge("s"+d1, "t"+d2), map.get(key));
			domainGraph.setEdgeWeight(domainGraph.addEdge("s"+d2, "t"+d1), map.get(key));
		}
		for(Integer d:domains)
			domainGraph.setEdgeWeight(domainGraph.addEdge("s"+d, "t"+d), 1.0);
		}
		return domainGraph;
	}

	// similarity algo with Simplex 	
	public static double simplexe(JSONArray array1,JSONArray array2,ISimilarity comparator,String attrName) throws Exception{
		return simplexe(array1,array2,comparator,attrName,"weight");
	}
	public static double simplexe(JSONArray array1,JSONArray array2,ISimilarity comparator,String attrName,String weightAttrName) throws Exception
	{
		int nbDomS=array1.length();
		int nbDomT=array2.length();
		if(nbDomS==0 || nbDomT==0) throw new Exception("simplexe ne peut �tre calcul�. Liste vide.");
		
		double[][] A=new double[nbDomS+nbDomT][nbDomS*nbDomT];
		double[] b=new double[nbDomS+nbDomT];
		double[] c=new double[nbDomS*nbDomT];
		// build constraint array
		for(int i=0;i<nbDomS;i++)
		{
			JSONObject json=(JSONObject)array1.get(i);
			b[i]=(Double)json.get(weightAttrName); 
		}
		for(int i=0;i<nbDomT;i++)
		{
			JSONObject json=(JSONObject)array2.get(i);
			b[i+nbDomS]=(Double)json.get(weightAttrName); 
		}
		// build domain distance array
		for(int i=0;i<nbDomS;i++)
		{
			JSONObject json1=(JSONObject)array1.get(i);
			Object object1=json1.get(attrName);
			for(int j=0;j<nbDomT;j++)
			{	
				JSONObject json2=(JSONObject)array2.get(j);
				Object object2=json2.get(attrName);
				double res=comparator.getSimilarity(object1,object2);
				int idx = i*nbDomT + j;
				c[idx]=res;
				A[i][idx]=res==0?0.:1.;
				A[nbDomS+j][idx]=res==0?0.:1.;
			}
		}
		Simplexe lp=new Simplexe(A,b,c);
		
		return lp.value();
	}
	
	public static double compareDomainsCosine(JSONArray domainRef1,JSONArray domainRef2) throws Exception
	{
		int n1=domainRef1.length();
		int n2=domainRef2.length();
		if(n1==0 || n2==0) throw new Exception("cosine ne peut �tre calcul�. Liste vide");
		double dividende=0;
		for(int i=0;i<n1;i++)
		{
				Double pi=null;
				Double pj=null;
			
				Number di=(Number)((JSONObject)domainRef1.get(i)).get("domain");
				for(int j=0;j<n2;j++)
				{
						Number dj=(Number)((JSONObject)domainRef2.get(j)).get("domain");
						if(di.equals(dj))
						{
							pi=(Double)((JSONObject)domainRef1.get(i)).get("weight");
							pj=(Double)((JSONObject)domainRef2.get(j)).get("weight");
							break;
						}
				}
				if(pj!=null)
					dividende+=pi*pj;
					
		}
		double diviseur1=0;
		for(int i=0;i<n1;i++)
			{
				double pi=(Double)((JSONObject)domainRef1.get(i)).get("weight");
				diviseur1+=pi*pi;
			}
		diviseur1=Math.sqrt(diviseur1);
		double diviseur2=0;
		for(int j=0;j<n2;j++)
			{
				double pj=(Double)((JSONObject)domainRef2.get(j)).get("weight");
				diviseur2+=pj*pj;
			}
		diviseur2=Math.sqrt(diviseur2);
		double diviseur=diviseur1*diviseur2;
		return dividende/diviseur;
	}
	
	public static double compareDomainsSoftCosine(JSONArray domainRef1,JSONArray domainRef2,ISimilarity sigma) throws Exception
	{
		return softCosine(domainRef1,domainRef2,sigma,"domain","weight");
	}
	public static double softCosine(JSONArray array1,JSONArray array2,ISimilarity comparator,String attrName) throws Exception{
		return simplexe(array1,array2,comparator,attrName,"weight");
	}
	public static double softCosine(JSONArray array1,JSONArray array2,ISimilarity comparator,String attrName,String weightAttrName) throws Exception
	{
		int n1=array1.length();
		int n2=array2.length();
		if(n1==0 || n2==0) throw new Exception("soft cosine ne peut �tre calcul�. Liste vide");
		double dividende=0;
		for(int i=0;i<n1;i++)
		{
			for(int j=0;j<n2;j++)
			{
				double pi=(Double)((JSONObject)array1.get(i)).get(weightAttrName);
				double pj=(Double)((JSONObject)array2.get(j)).get(weightAttrName);
				Object di=((JSONObject)array1.get(i)).get(attrName);
				Object dj=((JSONObject)array2.get(j)).get(attrName);
				
				dividende+=pi*pj*comparator.getSimilarity(di,dj);
			}
		}
		double diviseur1=0;
		for(int i=0;i<n1;i++)
			{
			for(int j=0;j<n1;j++)
			{
				double pi=(Double)((JSONObject)array1.get(i)).get(weightAttrName);
				double pj=(Double)((JSONObject)array1.get(j)).get(weightAttrName);
				Object di=((JSONObject)array1.get(i)).get(attrName);
				Object dj=((JSONObject)array1.get(j)).get(attrName);
				diviseur1+=pi*pj*comparator.getSimilarity(di,dj);
			}
			}
		diviseur1=Math.sqrt(diviseur1);
		double diviseur2=0;
		for(int i=0;i<n2;i++)
			{
			for(int j=0;j<n2;j++)
			{
				double pi=(Double)((JSONObject)array2.get(i)).get(weightAttrName);
				double pj=(Double)((JSONObject)array2.get(j)).get(weightAttrName);
				Object di=((JSONObject)array2.get(i)).get(attrName);
				Object dj=((JSONObject)array2.get(j)).get(attrName);
				diviseur2+=pi*pj*comparator.getSimilarity(di,dj);
			}
			}
		diviseur2=Math.sqrt(diviseur2);
		double diviseur=diviseur1*diviseur2;
		return dividende/diviseur;
	}
        
	public static int[] primeNumbers(int nbPrimeNumbers)
	{
		int[] result=new int[nbPrimeNumbers];
		result[0]=2; // c'est bien connu (si on oublie 1)
	      int status = 1;
	      int num = 3;
	      for ( int i = 2 ; i <=nbPrimeNumbers ;  )
	      {
	         for ( int j = 2 ; j <= Math.sqrt(num) ; j++ )
	         {
	            if ( num%j == 0 )
	            {
	               status = 0;
	               break;
	            }
	         }
	         if ( status != 0 )
	         {
	        	 result[i-1]=num;
	            i++;
	         }
	         status = 1;
	         num++;
	      }  
	      return result;
	   }
/*	public static void main(String[] args)
	{
		if(args.length<1) System.err.println("placer le nom du fichier contenant les objets � comparer");
		else {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			String line;
			while ((line = br.readLine()) != null) {
			   // process the line.
			}
			br.close();
			
		}
	}
*/	

}
