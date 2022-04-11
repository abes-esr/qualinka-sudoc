/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoqual1.util;

import java.text.Normalizer;

import org.json.JSONObject;

public final class Names {
    
    private Names() {
    }

    /*
            normaliser(Cha?ne ch) = Soit une cha?ne ch, on retourne une copie de ch 
            dans laquelle les ? accents ? (aigu, grave, circonflexe, tr?ma, tilde?) 
            sont supprim?s et les majuscules transform?es en minuscule. Cela n?cessite 
            s?rement quelques hypoth?ses sur les caract?res autoris?s dans l?alphabet 
            d?entr?e (iso_latin_ ???) et doit engendr? des cha?nes n?utilisant que les 26 
            lettres minuscules de l?alphabet latin + {apostrophe, tiret,., espace}. 
            A priori aucun autre caract?re ne devrait appara?tre ! 
            Si d?autres caract?res apparaissent alors il faudrait les neutraliser en les 
            transformant en espace, en rempla?ant les successions d?espaces par un seul 
            espace et en ?liminant les espace ? gauche et ? droite.
    */
    public static String normalize(String s)
    {
       String temp = Normalizer.normalize(s.trim(),Normalizer.Form.NFD);
       return temp.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
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

    ////////////////////////////////////////////////////////////////////////////
    //	NOT PUBLIC
    ////////////////////////////////////////////////////////////////////////////



}
