package fr.abes.qualinka.sudoc.util;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.qualinka.sudoc.old.ISimilarity;



public final class Utilities {
	
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

}
