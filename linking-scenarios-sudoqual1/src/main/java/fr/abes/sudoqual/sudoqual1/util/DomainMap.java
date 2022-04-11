/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoqual1.util;

import java.util.HashMap;
import java.util.Map;

public final class DomainMap {
    
    private DomainMap() {}

    /**
     * Recopier ici le fichier généré par le programme DomainMap.jar à partir du
     * fichier excel des distances entre domaine Commande : java -jar
     * DomainMap.jar FichierExcel.xls > NomFichierSortie Remarque : la
     * symétrisation de la matrice est assurée par la fonction Delta (i.e. on
     * enregistre que la demi-matrice)
*
     */
    /*
* this script has been produced automatically by class fr.lirmm.graphik.cogui.autoritefinder.StoreDomainCompatibility
* from file DistanceEntreDomainesEtendueFinal.xls
     */
    public static Map<String, Double> createDomainMap() {
        Map<String, Double> map = new HashMap();
        map.put("0-0", 1.0); // Savoir et érudition. Musées - Savoir et érudition. Musées
        map.put("0-20", 0.4); // Savoir et érudition. Musées - Sciences de l'information et de la documentation
        map.put("20-20", 1.0); // Sciences de l'information et de la documentation - Sciences de l'information et de la documentation
        map.put("0-100", 0.0); // Savoir et érudition. Musées - Philosophie
        map.put("20-100", 0.0); // Sciences de l'information et de la documentation - Philosophie
        map.put("100-100", 1.0); // Philosophie - Philosophie
        map.put("0-130", 0.2); // Savoir et érudition. Musées - Esotérisme
        map.put("20-130", 0.0); // Sciences de l'information et de la documentation - Esotérisme
        map.put("100-130", 0.1); // Philosophie - Esotérisme
        map.put("130-130", 1.0); // Esotérisme - Esotérisme
        map.put("0-150", 0.0); // Savoir et érudition. Musées - Psychologie
        map.put("20-150", 0.0); // Sciences de l'information et de la documentation - Psychologie
        map.put("100-150", 0.4); // Philosophie - Psychologie
        map.put("130-150", 0.1); // Esotérisme - Psychologie
        map.put("150-150", 1.0); // Psychologie - Psychologie
        map.put("0-200", 0.2); // Savoir et érudition. Musées - Religion
        map.put("20-200", 0.0); // Sciences de l'information et de la documentation - Religion
        map.put("100-200", 0.4); // Philosophie - Religion
        map.put("130-200", 0.2); // Esotérisme - Religion
        map.put("150-200", 0.2); // Psychologie - Religion
        map.put("200-200", 1.0); // Religion - Religion
        map.put("0-300", 0.0); // Savoir et érudition. Musées - Sciences sociales. Sociologie
        map.put("20-300", 0.0); // Sciences de l'information et de la documentation - Sciences sociales. Sociologie
        map.put("100-300", 0.2); // Philosophie - Sciences sociales. Sociologie
        map.put("130-300", 0.2); // Esotérisme - Sciences sociales. Sociologie
        map.put("150-300", 0.2); // Psychologie - Sciences sociales. Sociologie
        map.put("200-300", 0.2); // Religion - Sciences sociales. Sociologie
        map.put("300-300", 1.0); // Sciences sociales. Sociologie - Sciences sociales. Sociologie
        map.put("0-304", 0.0); // Savoir et érudition. Musées - Démographie
        map.put("20-304", 0.0); // Sciences de l'information et de la documentation - Démographie
        map.put("100-304", 0.0); // Philosophie - Démographie
        map.put("130-304", 0.0); // Esotérisme - Démographie
        map.put("150-304", 0.0); // Psychologie - Démographie
        map.put("200-304", 0.0); // Religion - Démographie
        map.put("300-304", 0.6); // Sciences sociales. Sociologie - Démographie
        map.put("304-304", 1.0); // Démographie - Démographie
        map.put("0-305", 0.0); // Savoir et érudition. Musées - Catégories de personnes
        map.put("20-305", 0.0); // Sciences de l'information et de la documentation - Catégories de personnes
        map.put("100-305", 0.0); // Philosophie - Catégories de personnes
        map.put("130-305", 0.0); // Esotérisme - Catégories de personnes
        map.put("150-305", 0.0); // Psychologie - Catégories de personnes
        map.put("200-305", 0.1); // Religion - Catégories de personnes
        map.put("300-305", 0.9); // Sciences sociales. Sociologie - Catégories de personnes
        map.put("304-305", 0.9); // Démographie - Catégories de personnes
        map.put("305-305", 1.0); // Catégories de personnes - Catégories de personnes
        map.put("0-320", 0.0); // Savoir et érudition. Musées - Science politique
        map.put("20-320", 0.0); // Sciences de l'information et de la documentation - Science politique
        map.put("100-320", 0.2); // Philosophie - Science politique
        map.put("130-320", 0.0); // Esotérisme - Science politique
        map.put("150-320", 0.0); // Psychologie - Science politique
        map.put("200-320", 0.0); // Religion - Science politique
        map.put("300-320", 0.6); // Sciences sociales. Sociologie - Science politique
        map.put("304-320", 0.2); // Démographie - Science politique
        map.put("305-320", 0.2); // Catégories de personnes - Science politique
        map.put("320-320", 1.0); // Science politique - Science politique
        map.put("0-330", 0.0); // Savoir et érudition. Musées - Economie politique. Travail
        map.put("20-330", 0.0); // Sciences de l'information et de la documentation - Economie politique. Travail
        map.put("100-330", 0.1); // Philosophie - Economie politique. Travail
        map.put("130-330", 0.0); // Esotérisme - Economie politique. Travail
        map.put("150-330", 0.0); // Psychologie - Economie politique. Travail
        map.put("200-330", 0.0); // Religion - Economie politique. Travail
        map.put("300-330", 0.6); // Sciences sociales. Sociologie - Economie politique. Travail
        map.put("304-330", 0.4); // Démographie - Economie politique. Travail
        map.put("305-330", 0.4); // Catégories de personnes - Economie politique. Travail
        map.put("320-330", 0.9); // Science politique - Economie politique. Travail
        map.put("330-330", 1.0); // Economie politique. Travail - Economie politique. Travail
        map.put("0-340", 0.0); // Savoir et érudition. Musées - Droit
        map.put("20-340", 0.2); // Sciences de l'information et de la documentation - Droit
        map.put("100-340", 0.2); // Philosophie - Droit
        map.put("130-340", 0.0); // Esotérisme - Droit
        map.put("150-340", 0.0); // Psychologie - Droit
        map.put("200-340", 0.1); // Religion - Droit
        map.put("300-340", 0.4); // Sciences sociales. Sociologie - Droit
        map.put("304-340", 0.0); // Démographie - Droit
        map.put("305-340", 0.2); // Catégories de personnes - Droit
        map.put("320-340", 0.4); // Science politique - Droit
        map.put("330-340", 0.4); // Economie politique. Travail - Droit
        map.put("340-340", 1.0); // Droit - Droit
        map.put("0-350", 0.0); // Savoir et érudition. Musées - Administration publique
        map.put("20-350", 0.2); // Sciences de l'information et de la documentation - Administration publique
        map.put("100-350", 0.0); // Philosophie - Administration publique
        map.put("130-350", 0.0); // Esotérisme - Administration publique
        map.put("150-350", 0.0); // Psychologie - Administration publique
        map.put("200-350", 0.0); // Religion - Administration publique
        map.put("300-350", 0.2); // Sciences sociales. Sociologie - Administration publique
        map.put("304-350", 0.2); // Démographie - Administration publique
        map.put("305-350", 0.4); // Catégories de personnes - Administration publique
        map.put("320-350", 0.6); // Science politique - Administration publique
        map.put("330-350", 0.6); // Economie politique. Travail - Administration publique
        map.put("340-350", 0.6); // Droit - Administration publique
        map.put("350-350", 1.0); // Administration publique - Administration publique
        map.put("0-355", 0.2); // Savoir et érudition. Musées - Art et science militaires
        map.put("20-355", 0.0); // Sciences de l'information et de la documentation - Art et science militaires
        map.put("100-355", 0.0); // Philosophie - Art et science militaires
        map.put("130-355", 0.0); // Esotérisme - Art et science militaires
        map.put("150-355", 0.0); // Psychologie - Art et science militaires
        map.put("200-355", 0.0); // Religion - Art et science militaires
        map.put("300-355", 0.2); // Sciences sociales. Sociologie - Art et science militaires
        map.put("304-355", 0.0); // Démographie - Art et science militaires
        map.put("305-355", 0.2); // Catégories de personnes - Art et science militaires
        map.put("320-355", 0.4); // Science politique - Art et science militaires
        map.put("330-355", 0.0); // Economie politique. Travail - Art et science militaires
        map.put("340-355", 0.0); // Droit - Art et science militaires
        map.put("350-355", 0.2); // Administration publique - Art et science militaires
        map.put("355-355", 1.0); // Art et science militaires - Art et science militaires
        map.put("0-360", 0.0); // Savoir et érudition. Musées - Problèmes et services sociaux. Criminologie
        map.put("20-360", 0.0); // Sciences de l'information et de la documentation - Problèmes et services sociaux. Criminologie
        map.put("100-360", 0.0); // Philosophie - Problèmes et services sociaux. Criminologie
        map.put("130-360", 0.1); // Esotérisme - Problèmes et services sociaux. Criminologie
        map.put("150-360", 0.2); // Psychologie - Problèmes et services sociaux. Criminologie
        map.put("200-360", 0.0); // Religion - Problèmes et services sociaux. Criminologie
        map.put("300-360", 0.6); // Sciences sociales. Sociologie - Problèmes et services sociaux. Criminologie
        map.put("304-360", 0.2); // Démographie - Problèmes et services sociaux. Criminologie
        map.put("305-360", 0.2); // Catégories de personnes - Problèmes et services sociaux. Criminologie
        map.put("320-360", 0.4); // Science politique - Problèmes et services sociaux. Criminologie
        map.put("330-360", 0.2); // Economie politique. Travail - Problèmes et services sociaux. Criminologie
        map.put("340-360", 0.4); // Droit - Problèmes et services sociaux. Criminologie
        map.put("350-360", 0.2); // Administration publique - Problèmes et services sociaux. Criminologie
        map.put("355-360", 0.0); // Art et science militaires - Problèmes et services sociaux. Criminologie
        map.put("360-360", 1.0); // Problèmes et services sociaux. Criminologie - Problèmes et services sociaux. Criminologie
        map.put("0-370", 0.2); // Savoir et érudition. Musées - Education
        map.put("20-370", 0.4); // Sciences de l'information et de la documentation - Education
        map.put("100-370", 0.2); // Philosophie - Education
        map.put("130-370", 0.0); // Esotérisme - Education
        map.put("150-370", 0.2); // Psychologie - Education
        map.put("200-370", 0.2); // Religion - Education
        map.put("300-370", 0.4); // Sciences sociales. Sociologie - Education
        map.put("304-370", 0.0); // Démographie - Education
        map.put("305-370", 0.2); // Catégories de personnes - Education
        map.put("320-370", 0.2); // Science politique - Education
        map.put("330-370", 0.0); // Economie politique. Travail - Education
        map.put("340-370", 0.0); // Droit - Education
        map.put("350-370", 0.2); // Administration publique - Education
        map.put("355-370", 0.0); // Art et science militaires - Education
        map.put("360-370", 0.2); // Problèmes et services sociaux. Criminologie - Education
        map.put("370-370", 1.0); // Education - Education
        map.put("0-390", 0.2); // Savoir et érudition. Musées - Ethnonymes
        map.put("20-390", 0.0); // Sciences de l'information et de la documentation - Ethnonymes
        map.put("100-390", 0.0); // Philosophie - Ethnonymes
        map.put("130-390", 0.0); // Esotérisme - Ethnonymes
        map.put("150-390", 0.0); // Psychologie - Ethnonymes
        map.put("200-390", 0.2); // Religion - Ethnonymes
        map.put("300-390", 0.4); // Sciences sociales. Sociologie - Ethnonymes
        map.put("304-390", 0.4); // Démographie - Ethnonymes
        map.put("305-390", 0.2); // Catégories de personnes - Ethnonymes
        map.put("320-390", 0.2); // Science politique - Ethnonymes
        map.put("330-390", 0.1); // Economie politique. Travail - Ethnonymes
        map.put("340-390", 0.0); // Droit - Ethnonymes
        map.put("350-390", 0.0); // Administration publique - Ethnonymes
        map.put("355-390", 0.2); // Art et science militaires - Ethnonymes
        map.put("360-390", 0.2); // Problèmes et services sociaux. Criminologie - Ethnonymes
        map.put("370-390", 0.2); // Education - Ethnonymes
        map.put("390-390", 1.0); // Ethnonymes - Ethnonymes
        map.put("0-391", 0.2); // Savoir et érudition. Musées - Anthropologie. Ethnologie
        map.put("20-391", 0.0); // Sciences de l'information et de la documentation - Anthropologie. Ethnologie
        map.put("100-391", 0.2); // Philosophie - Anthropologie. Ethnologie
        map.put("130-391", 0.0); // Esotérisme - Anthropologie. Ethnologie
        map.put("150-391", 0.0); // Psychologie - Anthropologie. Ethnologie
        map.put("200-391", 0.2); // Religion - Anthropologie. Ethnologie
        map.put("300-391", 0.4); // Sciences sociales. Sociologie - Anthropologie. Ethnologie
        map.put("304-391", 0.2); // Démographie - Anthropologie. Ethnologie
        map.put("305-391", 0.2); // Catégories de personnes - Anthropologie. Ethnologie
        map.put("320-391", 0.1); // Science politique - Anthropologie. Ethnologie
        map.put("330-391", 0.2); // Economie politique. Travail - Anthropologie. Ethnologie
        map.put("340-391", 0.0); // Droit - Anthropologie. Ethnologie
        map.put("350-391", 0.0); // Administration publique - Anthropologie. Ethnologie
        map.put("355-391", 0.1); // Art et science militaires - Anthropologie. Ethnologie
        map.put("360-391", 0.0); // Problèmes et services sociaux. Criminologie - Anthropologie. Ethnologie
        map.put("370-391", 0.0); // Education - Anthropologie. Ethnologie
        map.put("390-391", 0.6); // Ethnonymes - Anthropologie. Ethnologie
        map.put("391-391", 1.0); // Anthropologie. Ethnologie - Anthropologie. Ethnologie
        map.put("0-400", 0.2); // Savoir et érudition. Musées - Langues
        map.put("20-400", 0.4); // Sciences de l'information et de la documentation - Langues
        map.put("100-400", 0.0); // Philosophie - Langues
        map.put("130-400", 0.0); // Esotérisme - Langues
        map.put("150-400", 0.0); // Psychologie - Langues
        map.put("200-400", 0.0); // Religion - Langues
        map.put("300-400", 0.0); // Sciences sociales. Sociologie - Langues
        map.put("304-400", 0.0); // Démographie - Langues
        map.put("305-400", 0.0); // Catégories de personnes - Langues
        map.put("320-400", 0.0); // Science politique - Langues
        map.put("330-400", 0.0); // Economie politique. Travail - Langues
        map.put("340-400", 0.0); // Droit - Langues
        map.put("350-400", 0.0); // Administration publique - Langues
        map.put("355-400", 0.0); // Art et science militaires - Langues
        map.put("360-400", 0.0); // Problèmes et services sociaux. Criminologie - Langues
        map.put("370-400", 0.2); // Education - Langues
        map.put("390-400", 0.2); // Ethnonymes - Langues
        map.put("391-400", 0.2); // Anthropologie. Ethnologie - Langues
        map.put("400-400", 1.0); // Langues - Langues
        map.put("0-401", 0.0); // Savoir et érudition. Musées - Linguistique générale
        map.put("20-401", 0.2); // Sciences de l'information et de la documentation - Linguistique générale
        map.put("100-401", 0.2); // Philosophie - Linguistique générale
        map.put("130-401", 0.0); // Esotérisme - Linguistique générale
        map.put("150-401", 0.1); // Psychologie - Linguistique générale
        map.put("200-401", 0.0); // Religion - Linguistique générale
        map.put("300-401", 0.0); // Sciences sociales. Sociologie - Linguistique générale
        map.put("304-401", 0.0); // Démographie - Linguistique générale
        map.put("305-401", 0.0); // Catégories de personnes - Linguistique générale
        map.put("320-401", 0.0); // Science politique - Linguistique générale
        map.put("330-401", 0.0); // Economie politique. Travail - Linguistique générale
        map.put("340-401", 0.0); // Droit - Linguistique générale
        map.put("350-401", 0.0); // Administration publique - Linguistique générale
        map.put("355-401", 0.0); // Art et science militaires - Linguistique générale
        map.put("360-401", 0.0); // Problèmes et services sociaux. Criminologie - Linguistique générale
        map.put("370-401", 0.2); // Education - Linguistique générale
        map.put("390-401", 0.0); // Ethnonymes - Linguistique générale
        map.put("391-401", 0.0); // Anthropologie. Ethnologie - Linguistique générale
        map.put("400-401", 0.6); // Langues - Linguistique générale
        map.put("401-401", 1.0); // Linguistique générale - Linguistique générale
        map.put("0-500", 0.2); // Savoir et érudition. Musées - Sciences
        map.put("20-500", 0.2); // Sciences de l'information et de la documentation - Sciences
        map.put("100-500", 0.2); // Philosophie - Sciences
        map.put("130-500", 0.0); // Esotérisme - Sciences
        map.put("150-500", 0.2); // Psychologie - Sciences
        map.put("200-500", 0.2); // Religion - Sciences
        map.put("300-500", 0.0); // Sciences sociales. Sociologie - Sciences
        map.put("304-500", 0.2); // Démographie - Sciences
        map.put("305-500", 0.0); // Catégories de personnes - Sciences
        map.put("320-500", 0.0); // Science politique - Sciences
        map.put("330-500", 0.0); // Economie politique. Travail - Sciences
        map.put("340-500", 0.0); // Droit - Sciences
        map.put("350-500", 0.0); // Administration publique - Sciences
        map.put("355-500", 0.0); // Art et science militaires - Sciences
        map.put("360-500", 0.0); // Problèmes et services sociaux. Criminologie - Sciences
        map.put("370-500", 0.2); // Education - Sciences
        map.put("390-500", 0.0); // Ethnonymes - Sciences
        map.put("391-500", 0.0); // Anthropologie. Ethnologie - Sciences
        map.put("400-500", 0.0); // Langues - Sciences
        map.put("401-500", 0.0); // Linguistique générale - Sciences
        map.put("500-500", 1.0); // Sciences - Sciences
        map.put("0-510", 0.0); // Savoir et érudition. Musées - Mathématiques
        map.put("20-510", 0.0); // Sciences de l'information et de la documentation - Mathématiques
        map.put("100-510", 0.0); // Philosophie - Mathématiques
        map.put("130-510", 0.0); // Esotérisme - Mathématiques
        map.put("150-510", 0.0); // Psychologie - Mathématiques
        map.put("200-510", 0.0); // Religion - Mathématiques
        map.put("300-510", 0.0); // Sciences sociales. Sociologie - Mathématiques
        map.put("304-510", 0.0); // Démographie - Mathématiques
        map.put("305-510", 0.0); // Catégories de personnes - Mathématiques
        map.put("320-510", 0.0); // Science politique - Mathématiques
        map.put("330-510", 0.0); // Economie politique. Travail - Mathématiques
        map.put("340-510", 0.0); // Droit - Mathématiques
        map.put("350-510", 0.0); // Administration publique - Mathématiques
        map.put("355-510", 0.0); // Art et science militaires - Mathématiques
        map.put("360-510", 0.0); // Problèmes et services sociaux. Criminologie - Mathématiques
        map.put("370-510", 0.2); // Education - Mathématiques
        map.put("390-510", 0.0); // Ethnonymes - Mathématiques
        map.put("391-510", 0.0); // Anthropologie. Ethnologie - Mathématiques
        map.put("400-510", 0.0); // Langues - Mathématiques
        map.put("401-510", 0.0); // Linguistique générale - Mathématiques
        map.put("500-510", 0.6); // Sciences - Mathématiques
        map.put("510-510", 1.0); // Mathématiques - Mathématiques
        map.put("0-520", 0.2); // Savoir et érudition. Musées - Astronomie
        map.put("20-520", 0.0); // Sciences de l'information et de la documentation - Astronomie
        map.put("100-520", 0.0); // Philosophie - Astronomie
        map.put("130-520", 0.1); // Esotérisme - Astronomie
        map.put("150-520", 0.0); // Psychologie - Astronomie
        map.put("200-520", 0.1); // Religion - Astronomie
        map.put("300-520", 0.0); // Sciences sociales. Sociologie - Astronomie
        map.put("304-520", 0.0); // Démographie - Astronomie
        map.put("305-520", 0.0); // Catégories de personnes - Astronomie
        map.put("320-520", 0.0); // Science politique - Astronomie
        map.put("330-520", 0.0); // Economie politique. Travail - Astronomie
        map.put("340-520", 0.0); // Droit - Astronomie
        map.put("350-520", 0.0); // Administration publique - Astronomie
        map.put("355-520", 0.0); // Art et science militaires - Astronomie
        map.put("360-520", 0.0); // Problèmes et services sociaux. Criminologie - Astronomie
        map.put("370-520", 0.2); // Education - Astronomie
        map.put("390-520", 0.0); // Ethnonymes - Astronomie
        map.put("391-520", 0.0); // Anthropologie. Ethnologie - Astronomie
        map.put("400-520", 0.0); // Langues - Astronomie
        map.put("401-520", 0.0); // Linguistique générale - Astronomie
        map.put("500-520", 0.6); // Sciences - Astronomie
        map.put("510-520", 0.2); // Mathématiques - Astronomie
        map.put("520-520", 1.0); // Astronomie - Astronomie
        map.put("0-530", 0.0); // Savoir et érudition. Musées - Physique
        map.put("20-530", 0.0); // Sciences de l'information et de la documentation - Physique
        map.put("100-530", 0.0); // Philosophie - Physique
        map.put("130-530", 0.0); // Esotérisme - Physique
        map.put("150-530", 0.0); // Psychologie - Physique
        map.put("200-530", 0.0); // Religion - Physique
        map.put("300-530", 0.0); // Sciences sociales. Sociologie - Physique
        map.put("304-530", 0.0); // Démographie - Physique
        map.put("305-530", 0.0); // Catégories de personnes - Physique
        map.put("320-530", 0.0); // Science politique - Physique
        map.put("330-530", 0.0); // Economie politique. Travail - Physique
        map.put("340-530", 0.0); // Droit - Physique
        map.put("350-530", 0.0); // Administration publique - Physique
        map.put("355-530", 0.0); // Art et science militaires - Physique
        map.put("360-530", 0.0); // Problèmes et services sociaux. Criminologie - Physique
        map.put("370-530", 0.2); // Education - Physique
        map.put("390-530", 0.0); // Ethnonymes - Physique
        map.put("391-530", 0.0); // Anthropologie. Ethnologie - Physique
        map.put("400-530", 0.0); // Langues - Physique
        map.put("401-530", 0.0); // Linguistique générale - Physique
        map.put("500-530", 0.6); // Sciences - Physique
        map.put("510-530", 0.2); // Mathématiques - Physique
        map.put("520-530", 0.6); // Astronomie - Physique
        map.put("530-530", 1.0); // Physique - Physique
        map.put("0-540", 0.0); // Savoir et érudition. Musées - Chimie
        map.put("20-540", 0.0); // Sciences de l'information et de la documentation - Chimie
        map.put("100-540", 0.0); // Philosophie - Chimie
        map.put("130-540", 0.0); // Esotérisme - Chimie
        map.put("150-540", 0.0); // Psychologie - Chimie
        map.put("200-540", 0.0); // Religion - Chimie
        map.put("300-540", 0.0); // Sciences sociales. Sociologie - Chimie
        map.put("304-540", 0.0); // Démographie - Chimie
        map.put("305-540", 0.0); // Catégories de personnes - Chimie
        map.put("320-540", 0.0); // Science politique - Chimie
        map.put("330-540", 0.0); // Economie politique. Travail - Chimie
        map.put("340-540", 0.0); // Droit - Chimie
        map.put("350-540", 0.0); // Administration publique - Chimie
        map.put("355-540", 0.0); // Art et science militaires - Chimie
        map.put("360-540", 0.0); // Problèmes et services sociaux. Criminologie - Chimie
        map.put("370-540", 0.2); // Education - Chimie
        map.put("390-540", 0.0); // Ethnonymes - Chimie
        map.put("391-540", 0.0); // Anthropologie. Ethnologie - Chimie
        map.put("400-540", 0.0); // Langues - Chimie
        map.put("401-540", 0.0); // Linguistique générale - Chimie
        map.put("500-540", 0.6); // Sciences - Chimie
        map.put("510-540", 0.0); // Mathématiques - Chimie
        map.put("520-540", 0.0); // Astronomie - Chimie
        map.put("530-540", 0.6); // Physique - Chimie
        map.put("540-540", 1.0); // Chimie - Chimie
        map.put("0-550", 0.0); // Savoir et érudition. Musées - Sciences de la Terre
        map.put("20-550", 0.0); // Sciences de l'information et de la documentation - Sciences de la Terre
        map.put("100-550", 0.0); // Philosophie - Sciences de la Terre
        map.put("130-550", 0.0); // Esotérisme - Sciences de la Terre
        map.put("150-550", 0.0); // Psychologie - Sciences de la Terre
        map.put("200-550", 0.0); // Religion - Sciences de la Terre
        map.put("300-550", 0.0); // Sciences sociales. Sociologie - Sciences de la Terre
        map.put("304-550", 0.0); // Démographie - Sciences de la Terre
        map.put("305-550", 0.0); // Catégories de personnes - Sciences de la Terre
        map.put("320-550", 0.0); // Science politique - Sciences de la Terre
        map.put("330-550", 0.0); // Economie politique. Travail - Sciences de la Terre
        map.put("340-550", 0.0); // Droit - Sciences de la Terre
        map.put("350-550", 0.0); // Administration publique - Sciences de la Terre
        map.put("355-550", 0.0); // Art et science militaires - Sciences de la Terre
        map.put("360-550", 0.0); // Problèmes et services sociaux. Criminologie - Sciences de la Terre
        map.put("370-550", 0.2); // Education - Sciences de la Terre
        map.put("390-550", 0.0); // Ethnonymes - Sciences de la Terre
        map.put("391-550", 0.0); // Anthropologie. Ethnologie - Sciences de la Terre
        map.put("400-550", 0.0); // Langues - Sciences de la Terre
        map.put("401-550", 0.0); // Linguistique générale - Sciences de la Terre
        map.put("500-550", 0.6); // Sciences - Sciences de la Terre
        map.put("510-550", 0.0); // Mathématiques - Sciences de la Terre
        map.put("520-550", 0.4); // Astronomie - Sciences de la Terre
        map.put("530-550", 0.4); // Physique - Sciences de la Terre
        map.put("540-550", 0.4); // Chimie - Sciences de la Terre
        map.put("550-550", 1.0); // Sciences de la Terre - Sciences de la Terre
        map.put("0-560", 0.2); // Savoir et érudition. Musées - Paléontologie
        map.put("20-560", 0.0); // Sciences de l'information et de la documentation - Paléontologie
        map.put("100-560", 0.0); // Philosophie - Paléontologie
        map.put("130-560", 0.0); // Esotérisme - Paléontologie
        map.put("150-560", 0.0); // Psychologie - Paléontologie
        map.put("200-560", 0.0); // Religion - Paléontologie
        map.put("300-560", 0.0); // Sciences sociales. Sociologie - Paléontologie
        map.put("304-560", 0.0); // Démographie - Paléontologie
        map.put("305-560", 0.0); // Catégories de personnes - Paléontologie
        map.put("320-560", 0.0); // Science politique - Paléontologie
        map.put("330-560", 0.0); // Economie politique. Travail - Paléontologie
        map.put("340-560", 0.0); // Droit - Paléontologie
        map.put("350-560", 0.0); // Administration publique - Paléontologie
        map.put("355-560", 0.0); // Art et science militaires - Paléontologie
        map.put("360-560", 0.0); // Problèmes et services sociaux. Criminologie - Paléontologie
        map.put("370-560", 0.2); // Education - Paléontologie
        map.put("390-560", 0.0); // Ethnonymes - Paléontologie
        map.put("391-560", 0.0); // Anthropologie. Ethnologie - Paléontologie
        map.put("400-560", 0.0); // Langues - Paléontologie
        map.put("401-560", 0.0); // Linguistique générale - Paléontologie
        map.put("500-560", 0.4); // Sciences - Paléontologie
        map.put("510-560", 0.0); // Mathématiques - Paléontologie
        map.put("520-560", 0.0); // Astronomie - Paléontologie
        map.put("530-560", 0.0); // Physique - Paléontologie
        map.put("540-560", 0.0); // Chimie - Paléontologie
        map.put("550-560", 0.4); // Sciences de la Terre - Paléontologie
        map.put("560-560", 1.0); // Paléontologie - Paléontologie
        map.put("0-570", 0.0); // Savoir et érudition. Musées - Biologie
        map.put("20-570", 0.0); // Sciences de l'information et de la documentation - Biologie
        map.put("100-570", 0.0); // Philosophie - Biologie
        map.put("130-570", 0.0); // Esotérisme - Biologie
        map.put("150-570", 0.0); // Psychologie - Biologie
        map.put("200-570", 0.0); // Religion - Biologie
        map.put("300-570", 0.0); // Sciences sociales. Sociologie - Biologie
        map.put("304-570", 0.0); // Démographie - Biologie
        map.put("305-570", 0.0); // Catégories de personnes - Biologie
        map.put("320-570", 0.0); // Science politique - Biologie
        map.put("330-570", 0.0); // Economie politique. Travail - Biologie
        map.put("340-570", 0.2); // Droit - Biologie
        map.put("350-570", 0.0); // Administration publique - Biologie
        map.put("355-570", 0.0); // Art et science militaires - Biologie
        map.put("360-570", 0.0); // Problèmes et services sociaux. Criminologie - Biologie
        map.put("370-570", 0.2); // Education - Biologie
        map.put("390-570", 0.0); // Ethnonymes - Biologie
        map.put("391-570", 0.0); // Anthropologie. Ethnologie - Biologie
        map.put("400-570", 0.0); // Langues - Biologie
        map.put("401-570", 0.0); // Linguistique générale - Biologie
        map.put("500-570", 0.6); // Sciences - Biologie
        map.put("510-570", 0.0); // Mathématiques - Biologie
        map.put("520-570", 0.0); // Astronomie - Biologie
        map.put("530-570", 0.2); // Physique - Biologie
        map.put("540-570", 0.2); // Chimie - Biologie
        map.put("550-570", 0.4); // Sciences de la Terre - Biologie
        map.put("560-570", 0.4); // Paléontologie - Biologie
        map.put("570-570", 1.0); // Biologie - Biologie
        map.put("0-577", 0.0); // Savoir et érudition. Musées - Ecologie
        map.put("20-577", 0.0); // Sciences de l'information et de la documentation - Ecologie
        map.put("100-577", 0.0); // Philosophie - Ecologie
        map.put("130-577", 0.0); // Esotérisme - Ecologie
        map.put("150-577", 0.0); // Psychologie - Ecologie
        map.put("200-577", 0.0); // Religion - Ecologie
        map.put("300-577", 0.0); // Sciences sociales. Sociologie - Ecologie
        map.put("304-577", 0.0); // Démographie - Ecologie
        map.put("305-577", 0.0); // Catégories de personnes - Ecologie
        map.put("320-577", 0.0); // Science politique - Ecologie
        map.put("330-577", 0.0); // Economie politique. Travail - Ecologie
        map.put("340-577", 0.2); // Droit - Ecologie
        map.put("350-577", 0.1); // Administration publique - Ecologie
        map.put("355-577", 0.0); // Art et science militaires - Ecologie
        map.put("360-577", 0.0); // Problèmes et services sociaux. Criminologie - Ecologie
        map.put("370-577", 0.2); // Education - Ecologie
        map.put("390-577", 0.0); // Ethnonymes - Ecologie
        map.put("391-577", 0.0); // Anthropologie. Ethnologie - Ecologie
        map.put("400-577", 0.0); // Langues - Ecologie
        map.put("401-577", 0.0); // Linguistique générale - Ecologie
        map.put("500-577", 0.4); // Sciences - Ecologie
        map.put("510-577", 0.0); // Mathématiques - Ecologie
        map.put("520-577", 0.0); // Astronomie - Ecologie
        map.put("530-577", 0.0); // Physique - Ecologie
        map.put("540-577", 0.0); // Chimie - Ecologie
        map.put("550-577", 0.4); // Sciences de la Terre - Ecologie
        map.put("560-577", 0.4); // Paléontologie - Ecologie
        map.put("570-577", 0.4); // Biologie - Ecologie
        map.put("577-577", 1.0); // Ecologie - Ecologie

        map.put("0-579", 0.0); // Savoir et érudition. Musées - Biologie des procaryotes
        map.put("20-579", 0.0); // Sciences de l'information et de la documentation - Biologie des procaryotes
        map.put("100-579", 0.0); // Philosophie - Biologie des procaryotes
        map.put("130-579", 0.0); // Esotérisme - Biologie des procaryotes
        map.put("150-579", 0.0); // Psychologie - Biologie des procaryotes
        map.put("200-579", 0.0); // Religion - Biologie des procaryotes
        map.put("300-579", 0.0); // Sciences sociales. Sociologie - Biologie des procaryotes
        map.put("304-579", 0.0); // Démographie - Biologie des procaryotes
        map.put("305-579", 0.0); // Catégories de personnes - Biologie des procaryotes
        map.put("320-579", 0.0); // Science politique - Biologie des procaryotes
        map.put("330-579", 0.0); // Economie politique. Travail - Biologie des procaryotes
        map.put("340-579", 0.0); // Droit - Biologie des procaryotes
        map.put("350-579", 0.0); // Administration publique - Biologie des procaryotes
        map.put("355-579", 0.0); // Art et science militaires - Biologie des procaryotes
        map.put("360-579", 0.0); // Problèmes et services sociaux. Criminologie - Biologie des procaryotes
        map.put("370-579", 0.0); // Education - Biologie des procaryotes
        map.put("390-579", 0.0); // Ethnonymes - Biologie des procaryotes
        map.put("391-579", 0.0); // Anthropologie. Ethnologie - Biologie des procaryotes
        map.put("400-579", 0.0); // Langues - Biologie des procaryotes
        map.put("401-579", 0.0); // Linguistique générale - Biologie des procaryotes
        map.put("500-579", 0.4); // Sciences - Biologie des procaryotes
        map.put("510-579", 0.0); // Mathématiques - Biologie des procaryotes
        map.put("520-579", 0.0); // Astronomie - Biologie des procaryotes
        map.put("530-579", 0.0); // Physique - Biologie des procaryotes
        map.put("540-579", 0.0); // Chimie - Biologie des procaryotes
        map.put("550-579", 0.4); // Sciences de la Terre - Biologie des procaryotes
        map.put("560-579", 0.4); // Paléontologie - Biologie des procaryotes
        map.put("570-579", 0.9); // Biologie - Biologie des procaryotes
        map.put("577-579", 0.0); // Ecologie - Biologie des procaryotes
        map.put("579-579", 1.0); // Biologie des procaryotes - Biologie des procaryotes
        map.put("0-580", 0.1); // Savoir et érudition. Musées - Botanique
        map.put("20-580", 0.2); // Sciences de l'information et de la documentation - Botanique
        map.put("100-580", 0.0); // Philosophie - Botanique
        map.put("130-580", 0.0); // Esotérisme - Botanique
        map.put("150-580", 0.0); // Psychologie - Botanique
        map.put("200-580", 0.0); // Religion - Botanique
        map.put("300-580", 0.0); // Sciences sociales. Sociologie - Botanique
        map.put("304-580", 0.0); // Démographie - Botanique
        map.put("305-580", 0.0); // Catégories de personnes - Botanique
        map.put("320-580", 0.0); // Science politique - Botanique
        map.put("330-580", 0.0); // Economie politique. Travail - Botanique
        map.put("340-580", 0.0); // Droit - Botanique
        map.put("350-580", 0.0); // Administration publique - Botanique
        map.put("355-580", 0.0); // Art et science militaires - Botanique
        map.put("360-580", 0.0); // Problèmes et services sociaux. Criminologie - Botanique
        map.put("370-580", 0.0); // Education - Botanique
        map.put("390-580", 0.0); // Ethnonymes - Botanique
        map.put("391-580", 0.0); // Anthropologie. Ethnologie - Botanique
        map.put("400-580", 0.0); // Langues - Botanique
        map.put("401-580", 0.0); // Linguistique générale - Botanique
        map.put("500-580", 0.4); // Sciences - Botanique
        map.put("510-580", 0.0); // Mathématiques - Botanique
        map.put("520-580", 0.0); // Astronomie - Botanique
        map.put("530-580", 0.0); // Physique - Botanique
        map.put("540-580", 0.0); // Chimie - Botanique
        map.put("550-580", 0.4); // Sciences de la Terre - Botanique
        map.put("560-580", 0.4); // Paléontologie - Botanique
        map.put("570-580", 0.4); // Biologie - Botanique
        map.put("577-580", 0.6); // Ecologie - Botanique
        map.put("579-580", 0.2); // Biologie des procaryotes - Botanique
        map.put("580-580", 1.0); // Botanique - Botanique
        map.put("0-590", 0.1); // Savoir et érudition. Musées - Zoologie
        map.put("20-590", 0.2); // Sciences de l'information et de la documentation - Zoologie
        map.put("100-590", 0.0); // Philosophie - Zoologie
        map.put("130-590", 0.0); // Esotérisme - Zoologie
        map.put("150-590", 0.0); // Psychologie - Zoologie
        map.put("200-590", 0.0); // Religion - Zoologie
        map.put("300-590", 0.0); // Sciences sociales. Sociologie - Zoologie
        map.put("304-590", 0.0); // Démographie - Zoologie
        map.put("305-590", 0.0); // Catégories de personnes - Zoologie
        map.put("320-590", 0.0); // Science politique - Zoologie
        map.put("330-590", 0.0); // Economie politique. Travail - Zoologie
        map.put("340-590", 0.0); // Droit - Zoologie
        map.put("350-590", 0.0); // Administration publique - Zoologie
        map.put("355-590", 0.0); // Art et science militaires - Zoologie
        map.put("360-590", 0.0); // Problèmes et services sociaux. Criminologie - Zoologie
        map.put("370-590", 0.0); // Education - Zoologie
        map.put("390-590", 0.0); // Ethnonymes - Zoologie
        map.put("391-590", 0.0); // Anthropologie. Ethnologie - Zoologie
        map.put("400-590", 0.0); // Langues - Zoologie
        map.put("401-590", 0.0); // Linguistique générale - Zoologie
        map.put("500-590", 0.4); // Sciences - Zoologie
        map.put("510-590", 0.0); // Mathématiques - Zoologie
        map.put("520-590", 0.0); // Astronomie - Zoologie
        map.put("530-590", 0.0); // Physique - Zoologie
        map.put("540-590", 0.0); // Chimie - Zoologie
        map.put("550-590", 0.4); // Sciences de la Terre - Zoologie
        map.put("560-590", 0.4); // Paléontologie - Zoologie
        map.put("570-590", 0.4); // Biologie - Zoologie
        map.put("577-590", 0.6); // Ecologie - Zoologie
        map.put("579-590", 0.2); // Biologie des procaryotes - Zoologie
        map.put("580-590", 0.0); // Botanique - Zoologie
        map.put("590-590", 1.0); // Zoologie - Zoologie
        map.put("0-600", 0.1); // Savoir et érudition. Musées - Technique
        map.put("20-600", 0.2); // Sciences de l'information et de la documentation - Technique
        map.put("100-600", 0.0); // Philosophie - Technique
        map.put("130-600", 0.0); // Esotérisme - Technique
        map.put("150-600", 0.0); // Psychologie - Technique
        map.put("200-600", 0.0); // Religion - Technique
        map.put("300-600", 0.0); // Sciences sociales. Sociologie - Technique
        map.put("304-600", 0.0); // Démographie - Technique
        map.put("305-600", 0.0); // Catégories de personnes - Technique
        map.put("320-600", 0.0); // Science politique - Technique
        map.put("330-600", 0.0); // Economie politique. Travail - Technique
        map.put("340-600", 0.0); // Droit - Technique
        map.put("350-600", 0.0); // Administration publique - Technique
        map.put("355-600", 0.2); // Art et science militaires - Technique
        map.put("360-600", 0.0); // Problèmes et services sociaux. Criminologie - Technique
        map.put("370-600", 0.2); // Education - Technique
        map.put("390-600", 0.2); // Ethnonymes - Technique
        map.put("391-600", 0.0); // Anthropologie. Ethnologie - Technique
        map.put("400-600", 0.0); // Langues - Technique
        map.put("401-600", 0.0); // Linguistique générale - Technique
        map.put("500-600", 0.2); // Sciences - Technique
        map.put("510-600", 0.2); // Mathématiques - Technique
        map.put("520-600", 0.2); // Astronomie - Technique
        map.put("530-600", 0.2); // Physique - Technique
        map.put("540-600", 0.0); // Chimie - Technique
        map.put("550-600", 0.0); // Sciences de la Terre - Technique
        map.put("560-600", 0.0); // Paléontologie - Technique
        map.put("570-600", 0.0); // Biologie - Technique
        map.put("577-600", 0.2); // Ecologie - Technique
        map.put("579-600", 0.0); // Biologie des procaryotes - Technique
        map.put("580-600", 0.0); // Botanique - Technique
        map.put("590-600", 0.0); // Zoologie - Technique
        map.put("600-600", 1.0); // Technique - Technique
        map.put("0-610", 0.0); // Savoir et érudition. Musées - Médecine
        map.put("20-610", 0.2); // Sciences de l'information et de la documentation - Médecine
        map.put("100-610", 0.0); // Philosophie - Médecine
        map.put("130-610", 0.0); // Esotérisme - Médecine
        map.put("150-610", 0.2); // Psychologie - Médecine
        map.put("200-610", 0.0); // Religion - Médecine
        map.put("300-610", 0.0); // Sciences sociales. Sociologie - Médecine
        map.put("304-610", 0.2); // Démographie - Médecine
        map.put("305-610", 0.0); // Catégories de personnes - Médecine
        map.put("320-610", 0.0); // Science politique - Médecine
        map.put("330-610", 0.2); // Economie politique. Travail - Médecine
        map.put("340-610", 0.2); // Droit - Médecine
        map.put("350-610", 0.2); // Administration publique - Médecine
        map.put("355-610", 0.0); // Art et science militaires - Médecine
        map.put("360-610", 0.2); // Problèmes et services sociaux. Criminologie - Médecine
        map.put("370-610", 0.0); // Education - Médecine
        map.put("390-610", 0.2); // Ethnonymes - Médecine
        map.put("391-610", 0.0); // Anthropologie. Ethnologie - Médecine
        map.put("400-610", 0.0); // Langues - Médecine
        map.put("401-610", 0.0); // Linguistique générale - Médecine
        map.put("500-610", 0.4); // Sciences - Médecine
        map.put("510-610", 0.0); // Mathématiques - Médecine
        map.put("520-610", 0.0); // Astronomie - Médecine
        map.put("530-610", 0.0); // Physique - Médecine
        map.put("540-610", 0.2); // Chimie - Médecine
        map.put("550-610", 0.0); // Sciences de la Terre - Médecine
        map.put("560-610", 0.0); // Paléontologie - Médecine
        map.put("570-610", 0.2); // Biologie - Médecine
        map.put("577-610", 0.0); // Ecologie - Médecine
        map.put("579-610", 0.4); // Biologie des procaryotes - Médecine
        map.put("580-610", 0.2); // Botanique - Médecine
        map.put("590-610", 0.1); // Zoologie - Médecine
        map.put("600-610", 0.2); // Technique - Médecine
        map.put("610-610", 1.0); // Médecine - Médecine
        map.put("0-615", 0.0); // Savoir et érudition. Musées - Pharmacie
        map.put("20-615", 0.2); // Sciences de l'information et de la documentation - Pharmacie
        map.put("100-615", 0.0); // Philosophie - Pharmacie
        map.put("130-615", 0.0); // Esotérisme - Pharmacie
        map.put("150-615", 0.0); // Psychologie - Pharmacie
        map.put("200-615", 0.0); // Religion - Pharmacie
        map.put("300-615", 0.0); // Sciences sociales. Sociologie - Pharmacie
        map.put("304-615", 0.0); // Démographie - Pharmacie
        map.put("305-615", 0.0); // Catégories de personnes - Pharmacie
        map.put("320-615", 0.0); // Science politique - Pharmacie
        map.put("330-615", 0.0); // Economie politique. Travail - Pharmacie
        map.put("340-615", 0.0); // Droit - Pharmacie
        map.put("350-615", 0.0); // Administration publique - Pharmacie
        map.put("355-615", 0.0); // Art et science militaires - Pharmacie
        map.put("360-615", 0.0); // Problèmes et services sociaux. Criminologie - Pharmacie
        map.put("370-615", 0.0); // Education - Pharmacie
        map.put("390-615", 0.0); // Ethnonymes - Pharmacie
        map.put("391-615", 0.0); // Anthropologie. Ethnologie - Pharmacie
        map.put("400-615", 0.0); // Langues - Pharmacie
        map.put("401-615", 0.0); // Linguistique générale - Pharmacie
        map.put("500-615", 0.4); // Sciences - Pharmacie
        map.put("510-615", 0.0); // Mathématiques - Pharmacie
        map.put("520-615", 0.0); // Astronomie - Pharmacie
        map.put("530-615", 0.0); // Physique - Pharmacie
        map.put("540-615", 0.4); // Chimie - Pharmacie
        map.put("550-615", 0.0); // Sciences de la Terre - Pharmacie
        map.put("560-615", 0.0); // Paléontologie - Pharmacie
        map.put("570-615", 0.4); // Biologie - Pharmacie
        map.put("577-615", 0.0); // Ecologie - Pharmacie
        map.put("579-615", 0.4); // Biologie des procaryotes - Pharmacie
        map.put("580-615", 0.6); // Botanique - Pharmacie
        map.put("590-615", 0.0); // Zoologie - Pharmacie
        map.put("600-615", 0.0); // Technique - Pharmacie
        map.put("610-615", 0.6); // Médecine - Pharmacie
        map.put("615-615", 1.0); // Pharmacie - Pharmacie
        map.put("0-620", 0.0); // Savoir et érudition. Musées - Sciences de l'ingénieur
        map.put("20-620", 0.2); // Sciences de l'information et de la documentation - Sciences de l'ingénieur
        map.put("100-620", 0.0); // Philosophie - Sciences de l'ingénieur
        map.put("130-620", 0.0); // Esotérisme - Sciences de l'ingénieur
        map.put("150-620", 0.0); // Psychologie - Sciences de l'ingénieur
        map.put("200-620", 0.0); // Religion - Sciences de l'ingénieur
        map.put("300-620", 0.0); // Sciences sociales. Sociologie - Sciences de l'ingénieur
        map.put("304-620", 0.0); // Démographie - Sciences de l'ingénieur
        map.put("305-620", 0.0); // Catégories de personnes - Sciences de l'ingénieur
        map.put("320-620", 0.0); // Science politique - Sciences de l'ingénieur
        map.put("330-620", 0.0); // Economie politique. Travail - Sciences de l'ingénieur
        map.put("340-620", 0.0); // Droit - Sciences de l'ingénieur
        map.put("350-620", 0.0); // Administration publique - Sciences de l'ingénieur
        map.put("355-620", 0.0); // Art et science militaires - Sciences de l'ingénieur
        map.put("360-620", 0.0); // Problèmes et services sociaux. Criminologie - Sciences de l'ingénieur
        map.put("370-620", 0.2); // Education - Sciences de l'ingénieur
        map.put("390-620", 0.0); // Ethnonymes - Sciences de l'ingénieur
        map.put("391-620", 0.0); // Anthropologie. Ethnologie - Sciences de l'ingénieur
        map.put("400-620", 0.0); // Langues - Sciences de l'ingénieur
        map.put("401-620", 0.0); // Linguistique générale - Sciences de l'ingénieur
        map.put("500-620", 0.6); // Sciences - Sciences de l'ingénieur
        map.put("510-620", 0.4); // Mathématiques - Sciences de l'ingénieur
        map.put("520-620", 0.2); // Astronomie - Sciences de l'ingénieur
        map.put("530-620", 0.4); // Physique - Sciences de l'ingénieur
        map.put("540-620", 0.2); // Chimie - Sciences de l'ingénieur
        map.put("550-620", 0.0); // Sciences de la Terre - Sciences de l'ingénieur
        map.put("560-620", 0.0); // Paléontologie - Sciences de l'ingénieur
        map.put("570-620", 0.0); // Biologie - Sciences de l'ingénieur
        map.put("577-620", 0.0); // Ecologie - Sciences de l'ingénieur
        map.put("579-620", 0.0); // Biologie des procaryotes - Sciences de l'ingénieur
        map.put("580-620", 0.0); // Botanique - Sciences de l'ingénieur
        map.put("590-620", 0.0); // Zoologie - Sciences de l'ingénieur
        map.put("600-620", 0.6); // Technique - Sciences de l'ingénieur
        map.put("610-620", 0.1); // Médecine - Sciences de l'ingénieur
        map.put("615-620", 0.0); // Pharmacie - Sciences de l'ingénieur
        map.put("620-620", 1.0); // Sciences de l'ingénieur - Sciences de l'ingénieur
        map.put("0-621", 0.0); // Savoir et érudition. Musées - Informatique
        map.put("20-621", 0.4); // Sciences de l'information et de la documentation - Informatique
        map.put("100-621", 0.1); // Philosophie - Informatique
        map.put("130-621", 0.0); // Esotérisme - Informatique
        map.put("150-621", 0.0); // Psychologie - Informatique
        map.put("200-621", 0.0); // Religion - Informatique
        map.put("300-621", 0.0); // Sciences sociales. Sociologie - Informatique
        map.put("304-621", 0.0); // Démographie - Informatique
        map.put("305-621", 0.0); // Catégories de personnes - Informatique
        map.put("320-621", 0.0); // Science politique - Informatique
        map.put("330-621", 0.0); // Economie politique. Travail - Informatique
        map.put("340-621", 0.2); // Droit - Informatique
        map.put("350-621", 0.2); // Administration publique - Informatique
        map.put("355-621", 0.0); // Art et science militaires - Informatique
        map.put("360-621", 0.0); // Problèmes et services sociaux. Criminologie - Informatique
        map.put("370-621", 0.2); // Education - Informatique
        map.put("390-621", 0.0); // Ethnonymes - Informatique
        map.put("391-621", 0.0); // Anthropologie. Ethnologie - Informatique
        map.put("400-621", 0.2); // Langues - Informatique
        map.put("401-621", 0.0); // Linguistique générale - Informatique
        map.put("500-621", 0.4); // Sciences - Informatique
        map.put("510-621", 0.4); // Mathématiques - Informatique
        map.put("520-621", 0.0); // Astronomie - Informatique
        map.put("530-621", 0.2); // Physique - Informatique
        map.put("540-621", 0.0); // Chimie - Informatique
        map.put("550-621", 0.0); // Sciences de la Terre - Informatique
        map.put("560-621", 0.0); // Paléontologie - Informatique
        map.put("570-621", 0.0); // Biologie - Informatique
        map.put("577-621", 0.0); // Ecologie - Informatique
        map.put("579-621", 0.0); // Biologie des procaryotes - Informatique
        map.put("580-621", 0.0); // Botanique - Informatique
        map.put("590-621", 0.0); // Zoologie - Informatique
        map.put("600-621", 0.4); // Technique - Informatique
        map.put("610-621", 0.2); // Médecine - Informatique
        map.put("615-621", 0.0); // Pharmacie - Informatique
        map.put("620-621", 0.6); // Sciences de l'ingénieur - Informatique
        map.put("621-621", 1.0); // Informatique - Informatique
        map.put("0-630", 0.0); // Savoir et érudition. Musées - Agriculture
        map.put("20-630", 0.0); // Sciences de l'information et de la documentation - Agriculture
        map.put("100-630", 0.0); // Philosophie - Agriculture
        map.put("130-630", 0.0); // Esotérisme - Agriculture
        map.put("150-630", 0.0); // Psychologie - Agriculture
        map.put("200-630", 0.0); // Religion - Agriculture
        map.put("300-630", 0.0); // Sciences sociales. Sociologie - Agriculture
        map.put("304-630", 0.0); // Démographie - Agriculture
        map.put("305-630", 0.0); // Catégories de personnes - Agriculture
        map.put("320-630", 0.0); // Science politique - Agriculture
        map.put("330-630", 0.2); // Economie politique. Travail - Agriculture
        map.put("340-630", 0.0); // Droit - Agriculture
        map.put("350-630", 0.0); // Administration publique - Agriculture
        map.put("355-630", 0.0); // Art et science militaires - Agriculture
        map.put("360-630", 0.0); // Problèmes et services sociaux. Criminologie - Agriculture
        map.put("370-630", 0.0); // Education - Agriculture
        map.put("390-630", 0.0); // Ethnonymes - Agriculture
        map.put("391-630", 0.0); // Anthropologie. Ethnologie - Agriculture
        map.put("400-630", 0.0); // Langues - Agriculture
        map.put("401-630", 0.0); // Linguistique générale - Agriculture
        map.put("500-630", 0.2); // Sciences - Agriculture
        map.put("510-630", 0.0); // Mathématiques - Agriculture
        map.put("520-630", 0.0); // Astronomie - Agriculture
        map.put("530-630", 0.0); // Physique - Agriculture
        map.put("540-630", 0.2); // Chimie - Agriculture
        map.put("550-630", 0.2); // Sciences de la Terre - Agriculture
        map.put("560-630", 0.0); // Paléontologie - Agriculture
        map.put("570-630", 0.2); // Biologie - Agriculture
        map.put("577-630", 0.2); // Ecologie - Agriculture
        map.put("579-630", 0.2); // Biologie des procaryotes - Agriculture
        map.put("580-630", 0.2); // Botanique - Agriculture
        map.put("590-630", 0.0); // Zoologie - Agriculture
        map.put("600-630", 0.2); // Technique - Agriculture
        map.put("610-630", 0.2); // Médecine - Agriculture
        map.put("615-630", 0.0); // Pharmacie - Agriculture
        map.put("620-630", 0.2); // Sciences de l'ingénieur - Agriculture
        map.put("621-630", 0.0); // Informatique - Agriculture
        map.put("630-630", 1.0); // Agriculture - Agriculture
        map.put("0-640", 0.0); // Savoir et érudition. Musées - Economie domestique. Cuisine
        map.put("20-640", 0.2); // Sciences de l'information et de la documentation - Economie domestique. Cuisine
        map.put("100-640", 0.0); // Philosophie - Economie domestique. Cuisine
        map.put("130-640", 0.0); // Esotérisme - Economie domestique. Cuisine
        map.put("150-640", 0.0); // Psychologie - Economie domestique. Cuisine
        map.put("200-640", 0.0); // Religion - Economie domestique. Cuisine
        map.put("300-640", 0.0); // Sciences sociales. Sociologie - Economie domestique. Cuisine
        map.put("304-640", 0.0); // Démographie - Economie domestique. Cuisine
        map.put("305-640", 0.0); // Catégories de personnes - Economie domestique. Cuisine
        map.put("320-640", 0.0); // Science politique - Economie domestique. Cuisine
        map.put("330-640", 0.1); // Economie politique. Travail - Economie domestique. Cuisine
        map.put("340-640", 0.0); // Droit - Economie domestique. Cuisine
        map.put("350-640", 0.0); // Administration publique - Economie domestique. Cuisine
        map.put("355-640", 0.0); // Art et science militaires - Economie domestique. Cuisine
        map.put("360-640", 0.1); // Problèmes et services sociaux. Criminologie - Economie domestique. Cuisine
        map.put("370-640", 0.0); // Education - Economie domestique. Cuisine
        map.put("390-640", 0.0); // Ethnonymes - Economie domestique. Cuisine
        map.put("391-640", 0.2); // Anthropologie. Ethnologie - Economie domestique. Cuisine
        map.put("400-640", 0.0); // Langues - Economie domestique. Cuisine
        map.put("401-640", 0.0); // Linguistique générale - Economie domestique. Cuisine
        map.put("500-640", 0.0); // Sciences - Economie domestique. Cuisine
        map.put("510-640", 0.0); // Mathématiques - Economie domestique. Cuisine
        map.put("520-640", 0.0); // Astronomie - Economie domestique. Cuisine
        map.put("530-640", 0.0); // Physique - Economie domestique. Cuisine
        map.put("540-640", 0.1); // Chimie - Economie domestique. Cuisine
        map.put("550-640", 0.0); // Sciences de la Terre - Economie domestique. Cuisine
        map.put("560-640", 0.0); // Paléontologie - Economie domestique. Cuisine
        map.put("570-640", 0.0); // Biologie - Economie domestique. Cuisine
        map.put("577-640", 0.0); // Ecologie - Economie domestique. Cuisine
        map.put("579-640", 0.0); // Biologie des procaryotes - Economie domestique. Cuisine
        map.put("580-640", 0.0); // Botanique - Economie domestique. Cuisine
        map.put("590-640", 0.0); // Zoologie - Economie domestique. Cuisine
        map.put("600-640", 0.0); // Technique - Economie domestique. Cuisine
        map.put("610-640", 0.0); // Médecine - Economie domestique. Cuisine
        map.put("615-640", 0.0); // Pharmacie - Economie domestique. Cuisine
        map.put("620-640", 0.0); // Sciences de l'ingénieur - Economie domestique. Cuisine
        map.put("621-640", 0.0); // Informatique - Economie domestique. Cuisine
        map.put("630-640", 0.1); // Agriculture - Economie domestique. Cuisine
        map.put("640-640", 1.0); // Economie domestique. Cuisine - Economie domestique. Cuisine
        map.put("0-650", 0.0); // Savoir et érudition. Musées - Gestion
        map.put("20-650", 0.4); // Sciences de l'information et de la documentation - Gestion
        map.put("100-650", 0.0); // Philosophie - Gestion
        map.put("130-650", 0.0); // Esotérisme - Gestion
        map.put("150-650", 0.0); // Psychologie - Gestion
        map.put("200-650", 0.0); // Religion - Gestion
        map.put("300-650", 0.2); // Sciences sociales. Sociologie - Gestion
        map.put("304-650", 0.2); // Démographie - Gestion
        map.put("305-650", 0.0); // Catégories de personnes - Gestion
        map.put("320-650", 0.0); // Science politique - Gestion
        map.put("330-650", 0.4); // Economie politique. Travail - Gestion
        map.put("340-650", 0.0); // Droit - Gestion
        map.put("350-650", 0.2); // Administration publique - Gestion
        map.put("355-650", 0.0); // Art et science militaires - Gestion
        map.put("360-650", 0.1); // Problèmes et services sociaux. Criminologie - Gestion
        map.put("370-650", 0.2); // Education - Gestion
        map.put("390-650", 0.0); // Ethnonymes - Gestion
        map.put("391-650", 0.0); // Anthropologie. Ethnologie - Gestion
        map.put("400-650", 0.0); // Langues - Gestion
        map.put("401-650", 0.0); // Linguistique générale - Gestion
        map.put("500-650", 0.2); // Sciences - Gestion
        map.put("510-650", 0.0); // Mathématiques - Gestion
        map.put("520-650", 0.0); // Astronomie - Gestion
        map.put("530-650", 0.0); // Physique - Gestion
        map.put("540-650", 0.0); // Chimie - Gestion
        map.put("550-650", 0.0); // Sciences de la Terre - Gestion
        map.put("560-650", 0.0); // Paléontologie - Gestion
        map.put("570-650", 0.0); // Biologie - Gestion
        map.put("577-650", 0.0); // Ecologie - Gestion
        map.put("579-650", 0.0); // Biologie des procaryotes - Gestion
        map.put("580-650", 0.0); // Botanique - Gestion
        map.put("590-650", 0.0); // Zoologie - Gestion
        map.put("600-650", 0.1); // Technique - Gestion
        map.put("610-650", 0.0); // Médecine - Gestion
        map.put("615-650", 0.0); // Pharmacie - Gestion
        map.put("620-650", 0.0); // Sciences de l'ingénieur - Gestion
        map.put("621-650", 0.2); // Informatique - Gestion
        map.put("630-650", 0.0); // Agriculture - Gestion
        map.put("640-650", 0.2); // Economie domestique. Cuisine - Gestion
        map.put("650-650", 1.0); // Gestion - Gestion
        map.put("0-690", 0.0); // Savoir et érudition. Musées - Construction
        map.put("20-690", 0.2); // Sciences de l'information et de la documentation - Construction
        map.put("100-690", 0.0); // Philosophie - Construction
        map.put("130-690", 0.0); // Esotérisme - Construction
        map.put("150-690", 0.0); // Psychologie - Construction
        map.put("200-690", 0.0); // Religion - Construction
        map.put("300-690", 0.0); // Sciences sociales. Sociologie - Construction
        map.put("304-690", 0.0); // Démographie - Construction
        map.put("305-690", 0.0); // Catégories de personnes - Construction
        map.put("320-690", 0.0); // Science politique - Construction
        map.put("330-690", 0.0); // Economie politique. Travail - Construction
        map.put("340-690", 0.2); // Droit - Construction
        map.put("350-690", 0.2); // Administration publique - Construction
        map.put("355-690", 0.2); // Art et science militaires - Construction
        map.put("360-690", 0.0); // Problèmes et services sociaux. Criminologie - Construction
        map.put("370-690", 0.0); // Education - Construction
        map.put("390-690", 0.0); // Ethnonymes - Construction
        map.put("391-690", 0.0); // Anthropologie. Ethnologie - Construction
        map.put("400-690", 0.0); // Langues - Construction
        map.put("401-690", 0.0); // Linguistique générale - Construction
        map.put("500-690", 0.2); // Sciences - Construction
        map.put("510-690", 0.0); // Mathématiques - Construction
        map.put("520-690", 0.0); // Astronomie - Construction
        map.put("530-690", 0.2); // Physique - Construction
        map.put("540-690", 0.0); // Chimie - Construction
        map.put("550-690", 0.0); // Sciences de la Terre - Construction
        map.put("560-690", 0.0); // Paléontologie - Construction
        map.put("570-690", 0.0); // Biologie - Construction
        map.put("577-690", 0.0); // Ecologie - Construction
        map.put("579-690", 0.0); // Biologie des procaryotes - Construction
        map.put("580-690", 0.0); // Botanique - Construction
        map.put("590-690", 0.0); // Zoologie - Construction
        map.put("600-690", 0.4); // Technique - Construction
        map.put("610-690", 0.0); // Médecine - Construction
        map.put("615-690", 0.0); // Pharmacie - Construction
        map.put("620-690", 0.2); // Sciences de l'ingénieur - Construction
        map.put("621-690", 0.0); // Informatique - Construction
        map.put("630-690", 0.0); // Agriculture - Construction
        map.put("640-690", 0.0); // Economie domestique. Cuisine - Construction
        map.put("650-690", 0.0); // Gestion - Construction
        map.put("690-690", 1.0); // Construction - Construction
        map.put("0-700", 0.6); // Savoir et érudition. Musées - Art
        map.put("20-700", 0.0); // Sciences de l'information et de la documentation - Art
        map.put("100-700", 0.0); // Philosophie - Art
        map.put("130-700", 0.0); // Esotérisme - Art
        map.put("150-700", 0.0); // Psychologie - Art
        map.put("200-700", 0.2); // Religion - Art
        map.put("300-700", 0.0); // Sciences sociales. Sociologie - Art
        map.put("304-700", 0.0); // Démographie - Art
        map.put("305-700", 0.0); // Catégories de personnes - Art
        map.put("320-700", 0.0); // Science politique - Art
        map.put("330-700", 0.0); // Economie politique. Travail - Art
        map.put("340-700", 0.0); // Droit - Art
        map.put("350-700", 0.0); // Administration publique - Art
        map.put("355-700", 0.1); // Art et science militaires - Art
        map.put("360-700", 0.0); // Problèmes et services sociaux. Criminologie - Art
        map.put("370-700", 0.2); // Education - Art
        map.put("390-700", 0.2); // Ethnonymes - Art
        map.put("391-700", 0.2); // Anthropologie. Ethnologie - Art
        map.put("400-700", 0.0); // Langues - Art
        map.put("401-700", 0.0); // Linguistique générale - Art
        map.put("500-700", 0.0); // Sciences - Art
        map.put("510-700", 0.0); // Mathématiques - Art
        map.put("520-700", 0.0); // Astronomie - Art
        map.put("530-700", 0.0); // Physique - Art
        map.put("540-700", 0.0); // Chimie - Art
        map.put("550-700", 0.0); // Sciences de la Terre - Art
        map.put("560-700", 0.0); // Paléontologie - Art
        map.put("570-700", 0.0); // Biologie - Art
        map.put("577-700", 0.0); // Ecologie - Art
        map.put("579-700", 0.0); // Biologie des procaryotes - Art
        map.put("580-700", 0.0); // Botanique - Art
        map.put("590-700", 0.0); // Zoologie - Art
        map.put("600-700", 0.2); // Technique - Art
        map.put("610-700", 0.0); // Médecine - Art
        map.put("615-700", 0.0); // Pharmacie - Art
        map.put("620-700", 0.0); // Sciences de l'ingénieur - Art
        map.put("621-700", 0.0); // Informatique - Art
        map.put("630-700", 0.0); // Agriculture - Art
        map.put("640-700", 0.0); // Economie domestique. Cuisine - Art
        map.put("650-700", 0.0); // Gestion - Art
        map.put("690-700", 0.2); // Construction - Art
        map.put("700-700", 1.0); // Art - Art
        map.put("0-710", 0.0); // Savoir et érudition. Musées - Urbanisme. Architecture du paysage
        map.put("20-710", 0.0); // Sciences de l'information et de la documentation - Urbanisme. Architecture du paysage
        map.put("100-710", 0.0); // Philosophie - Urbanisme. Architecture du paysage
        map.put("130-710", 0.0); // Esotérisme - Urbanisme. Architecture du paysage
        map.put("150-710", 0.0); // Psychologie - Urbanisme. Architecture du paysage
        map.put("200-710", 0.0); // Religion - Urbanisme. Architecture du paysage
        map.put("300-710", 0.0); // Sciences sociales. Sociologie - Urbanisme. Architecture du paysage
        map.put("304-710", 0.2); // Démographie - Urbanisme. Architecture du paysage
        map.put("305-710", 0.0); // Catégories de personnes - Urbanisme. Architecture du paysage
        map.put("320-710", 0.0); // Science politique - Urbanisme. Architecture du paysage
        map.put("330-710", 0.0); // Economie politique. Travail - Urbanisme. Architecture du paysage
        map.put("340-710", 0.2); // Droit - Urbanisme. Architecture du paysage
        map.put("350-710", 0.2); // Administration publique - Urbanisme. Architecture du paysage
        map.put("355-710", 0.0); // Art et science militaires - Urbanisme. Architecture du paysage
        map.put("360-710", 0.2); // Problèmes et services sociaux. Criminologie - Urbanisme. Architecture du paysage
        map.put("370-710", 0.0); // Education - Urbanisme. Architecture du paysage
        map.put("390-710", 0.2); // Ethnonymes - Urbanisme. Architecture du paysage
        map.put("391-710", 0.0); // Anthropologie. Ethnologie - Urbanisme. Architecture du paysage
        map.put("400-710", 0.0); // Langues - Urbanisme. Architecture du paysage
        map.put("401-710", 0.0); // Linguistique générale - Urbanisme. Architecture du paysage
        map.put("500-710", 0.0); // Sciences - Urbanisme. Architecture du paysage
        map.put("510-710", 0.0); // Mathématiques - Urbanisme. Architecture du paysage
        map.put("520-710", 0.0); // Astronomie - Urbanisme. Architecture du paysage
        map.put("530-710", 0.0); // Physique - Urbanisme. Architecture du paysage
        map.put("540-710", 0.0); // Chimie - Urbanisme. Architecture du paysage
        map.put("550-710", 0.1); // Sciences de la Terre - Urbanisme. Architecture du paysage
        map.put("560-710", 0.0); // Paléontologie - Urbanisme. Architecture du paysage
        map.put("570-710", 0.0); // Biologie - Urbanisme. Architecture du paysage
        map.put("577-710", 0.4); // Ecologie - Urbanisme. Architecture du paysage
        map.put("579-710", 0.0); // Biologie des procaryotes - Urbanisme. Architecture du paysage
        map.put("580-710", 0.0); // Botanique - Urbanisme. Architecture du paysage
        map.put("590-710", 0.0); // Zoologie - Urbanisme. Architecture du paysage
        map.put("600-710", 0.2); // Technique - Urbanisme. Architecture du paysage
        map.put("610-710", 0.0); // Médecine - Urbanisme. Architecture du paysage
        map.put("615-710", 0.0); // Pharmacie - Urbanisme. Architecture du paysage
        map.put("620-710", 0.0); // Sciences de l'ingénieur - Urbanisme. Architecture du paysage
        map.put("621-710", 0.0); // Informatique - Urbanisme. Architecture du paysage
        map.put("630-710", 0.2); // Agriculture - Urbanisme. Architecture du paysage
        map.put("640-710", 0.0); // Economie domestique. Cuisine - Urbanisme. Architecture du paysage
        map.put("650-710", 0.0); // Gestion - Urbanisme. Architecture du paysage
        map.put("690-710", 0.4); // Construction - Urbanisme. Architecture du paysage
        map.put("700-710", 0.0); // Art - Urbanisme. Architecture du paysage
        map.put("710-710", 1.0); // Urbanisme. Architecture du paysage - Urbanisme. Architecture du paysage
        map.put("0-720", 0.0); // Savoir et érudition. Musées - Architecture
        map.put("20-720", 0.0); // Sciences de l'information et de la documentation - Architecture
        map.put("100-720", 0.0); // Philosophie - Architecture
        map.put("130-720", 0.0); // Esotérisme - Architecture
        map.put("150-720", 0.0); // Psychologie - Architecture
        map.put("200-720", 0.2); // Religion - Architecture
        map.put("300-720", 0.0); // Sciences sociales. Sociologie - Architecture
        map.put("304-720", 0.0); // Démographie - Architecture
        map.put("305-720", 0.0); // Catégories de personnes - Architecture
        map.put("320-720", 0.0); // Science politique - Architecture
        map.put("330-720", 0.0); // Economie politique. Travail - Architecture
        map.put("340-720", 0.0); // Droit - Architecture
        map.put("350-720", 0.0); // Administration publique - Architecture
        map.put("355-720", 0.0); // Art et science militaires - Architecture
        map.put("360-720", 0.0); // Problèmes et services sociaux. Criminologie - Architecture
        map.put("370-720", 0.0); // Education - Architecture
        map.put("390-720", 0.2); // Ethnonymes - Architecture
        map.put("391-720", 0.2); // Anthropologie. Ethnologie - Architecture
        map.put("400-720", 0.0); // Langues - Architecture
        map.put("401-720", 0.0); // Linguistique générale - Architecture
        map.put("500-720", 0.2); // Sciences - Architecture
        map.put("510-720", 0.0); // Mathématiques - Architecture
        map.put("520-720", 0.0); // Astronomie - Architecture
        map.put("530-720", 0.2); // Physique - Architecture
        map.put("540-720", 0.0); // Chimie - Architecture
        map.put("550-720", 0.0); // Sciences de la Terre - Architecture
        map.put("560-720", 0.0); // Paléontologie - Architecture
        map.put("570-720", 0.0); // Biologie - Architecture
        map.put("577-720", 0.0); // Ecologie - Architecture
        map.put("579-720", 0.0); // Biologie des procaryotes - Architecture
        map.put("580-720", 0.0); // Botanique - Architecture
        map.put("590-720", 0.0); // Zoologie - Architecture
        map.put("600-720", 0.4); // Technique - Architecture
        map.put("610-720", 0.0); // Médecine - Architecture
        map.put("615-720", 0.0); // Pharmacie - Architecture
        map.put("620-720", 0.2); // Sciences de l'ingénieur - Architecture
        map.put("621-720", 0.0); // Informatique - Architecture
        map.put("630-720", 0.0); // Agriculture - Architecture
        map.put("640-720", 0.0); // Economie domestique. Cuisine - Architecture
        map.put("650-720", 0.0); // Gestion - Architecture
        map.put("690-720", 0.6); // Construction - Architecture
        map.put("700-720", 0.6); // Art - Architecture
        map.put("710-720", 0.9); // Urbanisme. Architecture du paysage - Architecture
        map.put("720-720", 1.0); // Architecture - Architecture
        map.put("0-730", 0.4); // Savoir et érudition. Musées - Sculpture
        map.put("20-730", 0.0); // Sciences de l'information et de la documentation - Sculpture
        map.put("100-730", 0.0); // Philosophie - Sculpture
        map.put("130-730", 0.0); // Esotérisme - Sculpture
        map.put("150-730", 0.0); // Psychologie - Sculpture
        map.put("200-730", 0.2); // Religion - Sculpture
        map.put("300-730", 0.0); // Sciences sociales. Sociologie - Sculpture
        map.put("304-730", 0.0); // Démographie - Sculpture
        map.put("305-730", 0.0); // Catégories de personnes - Sculpture
        map.put("320-730", 0.0); // Science politique - Sculpture
        map.put("330-730", 0.0); // Economie politique. Travail - Sculpture
        map.put("340-730", 0.0); // Droit - Sculpture
        map.put("350-730", 0.0); // Administration publique - Sculpture
        map.put("355-730", 0.0); // Art et science militaires - Sculpture
        map.put("360-730", 0.0); // Problèmes et services sociaux. Criminologie - Sculpture
        map.put("370-730", 0.0); // Education - Sculpture
        map.put("390-730", 0.2); // Ethnonymes - Sculpture
        map.put("391-730", 0.2); // Anthropologie. Ethnologie - Sculpture
        map.put("400-730", 0.0); // Langues - Sculpture
        map.put("401-730", 0.0); // Linguistique générale - Sculpture
        map.put("500-730", 0.0); // Sciences - Sculpture
        map.put("510-730", 0.0); // Mathématiques - Sculpture
        map.put("520-730", 0.0); // Astronomie - Sculpture
        map.put("530-730", 0.0); // Physique - Sculpture
        map.put("540-730", 0.0); // Chimie - Sculpture
        map.put("550-730", 0.0); // Sciences de la Terre - Sculpture
        map.put("560-730", 0.0); // Paléontologie - Sculpture
        map.put("570-730", 0.0); // Biologie - Sculpture
        map.put("577-730", 0.0); // Ecologie - Sculpture
        map.put("579-730", 0.0); // Biologie des procaryotes - Sculpture
        map.put("580-730", 0.0); // Botanique - Sculpture
        map.put("590-730", 0.0); // Zoologie - Sculpture
        map.put("600-730", 0.2); // Technique - Sculpture
        map.put("610-730", 0.0); // Médecine - Sculpture
        map.put("615-730", 0.0); // Pharmacie - Sculpture
        map.put("620-730", 0.0); // Sciences de l'ingénieur - Sculpture
        map.put("621-730", 0.0); // Informatique - Sculpture
        map.put("630-730", 0.0); // Agriculture - Sculpture
        map.put("640-730", 0.0); // Economie domestique. Cuisine - Sculpture
        map.put("650-730", 0.0); // Gestion - Sculpture
        map.put("690-730", 0.0); // Construction - Sculpture
        map.put("700-730", 0.6); // Art - Sculpture
        map.put("710-730", 0.0); // Urbanisme. Architecture du paysage - Sculpture
        map.put("720-730", 0.2); // Architecture - Sculpture
        map.put("730-730", 1.0); // Sculpture - Sculpture
        map.put("0-740", 0.4); // Savoir et érudition. Musées - Dessin. Arts décoratifs
        map.put("20-740", 0.0); // Sciences de l'information et de la documentation - Dessin. Arts décoratifs
        map.put("100-740", 0.0); // Philosophie - Dessin. Arts décoratifs
        map.put("130-740", 0.0); // Esotérisme - Dessin. Arts décoratifs
        map.put("150-740", 0.0); // Psychologie - Dessin. Arts décoratifs
        map.put("200-740", 0.2); // Religion - Dessin. Arts décoratifs
        map.put("300-740", 0.0); // Sciences sociales. Sociologie - Dessin. Arts décoratifs
        map.put("304-740", 0.0); // Démographie - Dessin. Arts décoratifs
        map.put("305-740", 0.0); // Catégories de personnes - Dessin. Arts décoratifs
        map.put("320-740", 0.0); // Science politique - Dessin. Arts décoratifs
        map.put("330-740", 0.0); // Economie politique. Travail - Dessin. Arts décoratifs
        map.put("340-740", 0.0); // Droit - Dessin. Arts décoratifs
        map.put("350-740", 0.0); // Administration publique - Dessin. Arts décoratifs
        map.put("355-740", 0.0); // Art et science militaires - Dessin. Arts décoratifs
        map.put("360-740", 0.0); // Problèmes et services sociaux. Criminologie - Dessin. Arts décoratifs
        map.put("370-740", 0.2); // Education - Dessin. Arts décoratifs
        map.put("390-740", 0.2); // Ethnonymes - Dessin. Arts décoratifs
        map.put("391-740", 0.2); // Anthropologie. Ethnologie - Dessin. Arts décoratifs
        map.put("400-740", 0.0); // Langues - Dessin. Arts décoratifs
        map.put("401-740", 0.0); // Linguistique générale - Dessin. Arts décoratifs
        map.put("500-740", 0.0); // Sciences - Dessin. Arts décoratifs
        map.put("510-740", 0.0); // Mathématiques - Dessin. Arts décoratifs
        map.put("520-740", 0.0); // Astronomie - Dessin. Arts décoratifs
        map.put("530-740", 0.0); // Physique - Dessin. Arts décoratifs
        map.put("540-740", 0.0); // Chimie - Dessin. Arts décoratifs
        map.put("550-740", 0.0); // Sciences de la Terre - Dessin. Arts décoratifs
        map.put("560-740", 0.0); // Paléontologie - Dessin. Arts décoratifs
        map.put("570-740", 0.0); // Biologie - Dessin. Arts décoratifs
        map.put("577-740", 0.0); // Ecologie - Dessin. Arts décoratifs
        map.put("579-740", 0.0); // Biologie des procaryotes - Dessin. Arts décoratifs
        map.put("580-740", 0.0); // Botanique - Dessin. Arts décoratifs
        map.put("590-740", 0.0); // Zoologie - Dessin. Arts décoratifs
        map.put("600-740", 0.2); // Technique - Dessin. Arts décoratifs
        map.put("610-740", 0.0); // Médecine - Dessin. Arts décoratifs
        map.put("615-740", 0.0); // Pharmacie - Dessin. Arts décoratifs
        map.put("620-740", 0.0); // Sciences de l'ingénieur - Dessin. Arts décoratifs
        map.put("621-740", 0.0); // Informatique - Dessin. Arts décoratifs
        map.put("630-740", 0.0); // Agriculture - Dessin. Arts décoratifs
        map.put("640-740", 0.0); // Economie domestique. Cuisine - Dessin. Arts décoratifs
        map.put("650-740", 0.0); // Gestion - Dessin. Arts décoratifs
        map.put("690-740", 0.0); // Construction - Dessin. Arts décoratifs
        map.put("700-740", 0.6); // Art - Dessin. Arts décoratifs
        map.put("710-740", 0.2); // Urbanisme. Architecture du paysage - Dessin. Arts décoratifs
        map.put("720-740", 0.2); // Architecture - Dessin. Arts décoratifs
        map.put("730-740", 0.4); // Sculpture - Dessin. Arts décoratifs
        map.put("740-740", 1.0); // Dessin. Arts décoratifs - Dessin. Arts décoratifs
        map.put("0-750", 0.4); // Savoir et érudition. Musées - Peinture
        map.put("20-750", 0.0); // Sciences de l'information et de la documentation - Peinture
        map.put("100-750", 0.0); // Philosophie - Peinture
        map.put("130-750", 0.0); // Esotérisme - Peinture
        map.put("150-750", 0.0); // Psychologie - Peinture
        map.put("200-750", 0.2); // Religion - Peinture
        map.put("300-750", 0.0); // Sciences sociales. Sociologie - Peinture
        map.put("304-750", 0.0); // Démographie - Peinture
        map.put("305-750", 0.0); // Catégories de personnes - Peinture
        map.put("320-750", 0.0); // Science politique - Peinture
        map.put("330-750", 0.0); // Economie politique. Travail - Peinture
        map.put("340-750", 0.0); // Droit - Peinture
        map.put("350-750", 0.0); // Administration publique - Peinture
        map.put("355-750", 0.0); // Art et science militaires - Peinture
        map.put("360-750", 0.0); // Problèmes et services sociaux. Criminologie - Peinture
        map.put("370-750", 0.2); // Education - Peinture
        map.put("390-750", 0.2); // Ethnonymes - Peinture
        map.put("391-750", 0.2); // Anthropologie. Ethnologie - Peinture
        map.put("400-750", 0.0); // Langues - Peinture
        map.put("401-750", 0.0); // Linguistique générale - Peinture
        map.put("500-750", 0.0); // Sciences - Peinture
        map.put("510-750", 0.0); // Mathématiques - Peinture
        map.put("520-750", 0.0); // Astronomie - Peinture
        map.put("530-750", 0.0); // Physique - Peinture

        map.put("540-750", 0.0); // Chimie - Peinture
        map.put("550-750", 0.0); // Sciences de la Terre - Peinture
        map.put("560-750", 0.0); // Paléontologie - Peinture
        map.put("570-750", 0.0); // Biologie - Peinture
        map.put("577-750", 0.0); // Ecologie - Peinture
        map.put("579-750", 0.0); // Biologie des procaryotes - Peinture
        map.put("580-750", 0.0); // Botanique - Peinture
        map.put("590-750", 0.0); // Zoologie - Peinture
        map.put("600-750", 0.2); // Technique - Peinture
        map.put("610-750", 0.0); // Médecine - Peinture
        map.put("615-750", 0.0); // Pharmacie - Peinture
        map.put("620-750", 0.0); // Sciences de l'ingénieur - Peinture
        map.put("621-750", 0.0); // Informatique - Peinture
        map.put("630-750", 0.0); // Agriculture - Peinture
        map.put("640-750", 0.0); // Economie domestique. Cuisine - Peinture
        map.put("650-750", 0.0); // Gestion - Peinture
        map.put("690-750", 0.0); // Construction - Peinture
        map.put("700-750", 0.6); // Art - Peinture
        map.put("710-750", 0.0); // Urbanisme. Architecture du paysage - Peinture
        map.put("720-750", 0.0); // Architecture - Peinture
        map.put("730-750", 0.2); // Sculpture - Peinture
        map.put("740-750", 0.9); // Dessin. Arts décoratifs - Peinture
        map.put("750-750", 1.0); // Peinture - Peinture
        map.put("0-760", 0.2); // Savoir et érudition. Musées - Arts graphiques
        map.put("20-760", 0.0); // Sciences de l'information et de la documentation - Arts graphiques
        map.put("100-760", 0.0); // Philosophie - Arts graphiques
        map.put("130-760", 0.0); // Esotérisme - Arts graphiques
        map.put("150-760", 0.0); // Psychologie - Arts graphiques
        map.put("200-760", 0.2); // Religion - Arts graphiques
        map.put("300-760", 0.0); // Sciences sociales. Sociologie - Arts graphiques
        map.put("304-760", 0.0); // Démographie - Arts graphiques
        map.put("305-760", 0.0); // Catégories de personnes - Arts graphiques
        map.put("320-760", 0.0); // Science politique - Arts graphiques
        map.put("330-760", 0.0); // Economie politique. Travail - Arts graphiques
        map.put("340-760", 0.0); // Droit - Arts graphiques
        map.put("350-760", 0.0); // Administration publique - Arts graphiques
        map.put("355-760", 0.0); // Art et science militaires - Arts graphiques
        map.put("360-760", 0.0); // Problèmes et services sociaux. Criminologie - Arts graphiques
        map.put("370-760", 0.0); // Education - Arts graphiques
        map.put("390-760", 0.2); // Ethnonymes - Arts graphiques
        map.put("391-760", 0.0); // Anthropologie. Ethnologie - Arts graphiques
        map.put("400-760", 0.0); // Langues - Arts graphiques
        map.put("401-760", 0.0); // Linguistique générale - Arts graphiques
        map.put("500-760", 0.0); // Sciences - Arts graphiques
        map.put("510-760", 0.0); // Mathématiques - Arts graphiques
        map.put("520-760", 0.0); // Astronomie - Arts graphiques
        map.put("530-760", 0.0); // Physique - Arts graphiques
        map.put("540-760", 0.0); // Chimie - Arts graphiques
        map.put("550-760", 0.0); // Sciences de la Terre - Arts graphiques
        map.put("560-760", 0.0); // Paléontologie - Arts graphiques
        map.put("570-760", 0.0); // Biologie - Arts graphiques
        map.put("577-760", 0.0); // Ecologie - Arts graphiques
        map.put("579-760", 0.0); // Biologie des procaryotes - Arts graphiques
        map.put("580-760", 0.0); // Botanique - Arts graphiques
        map.put("590-760", 0.0); // Zoologie - Arts graphiques
        map.put("600-760", 0.2); // Technique - Arts graphiques
        map.put("610-760", 0.0); // Médecine - Arts graphiques
        map.put("615-760", 0.0); // Pharmacie - Arts graphiques
        map.put("620-760", 0.0); // Sciences de l'ingénieur - Arts graphiques
        map.put("621-760", 0.0); // Informatique - Arts graphiques
        map.put("630-760", 0.0); // Agriculture - Arts graphiques
        map.put("640-760", 0.0); // Economie domestique. Cuisine - Arts graphiques
        map.put("650-760", 0.0); // Gestion - Arts graphiques
        map.put("690-760", 0.0); // Construction - Arts graphiques
        map.put("700-760", 0.6); // Art - Arts graphiques
        map.put("710-760", 0.0); // Urbanisme. Architecture du paysage - Arts graphiques
        map.put("720-760", 0.2); // Architecture - Arts graphiques
        map.put("730-760", 0.2); // Sculpture - Arts graphiques
        map.put("740-760", 0.9); // Dessin. Arts décoratifs - Arts graphiques
        map.put("750-760", 0.6); // Peinture - Arts graphiques
        map.put("760-760", 1.0); // Arts graphiques - Arts graphiques
        map.put("0-770", 0.2); // Savoir et érudition. Musées - Photographie
        map.put("20-770", 0.0); // Sciences de l'information et de la documentation - Photographie
        map.put("100-770", 0.0); // Philosophie - Photographie
        map.put("130-770", 0.0); // Esotérisme - Photographie
        map.put("150-770", 0.0); // Psychologie - Photographie
        map.put("200-770", 0.0); // Religion - Photographie
        map.put("300-770", 0.0); // Sciences sociales. Sociologie - Photographie
        map.put("304-770", 0.0); // Démographie - Photographie
        map.put("305-770", 0.0); // Catégories de personnes - Photographie
        map.put("320-770", 0.0); // Science politique - Photographie
        map.put("330-770", 0.0); // Economie politique. Travail - Photographie
        map.put("340-770", 0.0); // Droit - Photographie
        map.put("350-770", 0.0); // Administration publique - Photographie
        map.put("355-770", 0.0); // Art et science militaires - Photographie
        map.put("360-770", 0.0); // Problèmes et services sociaux. Criminologie - Photographie
        map.put("370-770", 0.0); // Education - Photographie
        map.put("390-770", 0.0); // Ethnonymes - Photographie
        map.put("391-770", 0.0); // Anthropologie. Ethnologie - Photographie
        map.put("400-770", 0.0); // Langues - Photographie
        map.put("401-770", 0.0); // Linguistique générale - Photographie
        map.put("500-770", 0.2); // Sciences - Photographie
        map.put("510-770", 0.0); // Mathématiques - Photographie
        map.put("520-770", 0.0); // Astronomie - Photographie
        map.put("530-770", 0.0); // Physique - Photographie
        map.put("540-770", 0.0); // Chimie - Photographie
        map.put("550-770", 0.0); // Sciences de la Terre - Photographie
        map.put("560-770", 0.0); // Paléontologie - Photographie
        map.put("570-770", 0.0); // Biologie - Photographie
        map.put("577-770", 0.0); // Ecologie - Photographie
        map.put("579-770", 0.0); // Biologie des procaryotes - Photographie
        map.put("580-770", 0.0); // Botanique - Photographie
        map.put("590-770", 0.0); // Zoologie - Photographie
        map.put("600-770", 0.4); // Technique - Photographie
        map.put("610-770", 0.0); // Médecine - Photographie
        map.put("615-770", 0.0); // Pharmacie - Photographie
        map.put("620-770", 0.0); // Sciences de l'ingénieur - Photographie
        map.put("621-770", 0.0); // Informatique - Photographie
        map.put("630-770", 0.0); // Agriculture - Photographie
        map.put("640-770", 0.0); // Economie domestique. Cuisine - Photographie
        map.put("650-770", 0.0); // Gestion - Photographie
        map.put("690-770", 0.0); // Construction - Photographie
        map.put("700-770", 0.6); // Art - Photographie
        map.put("710-770", 0.0); // Urbanisme. Architecture du paysage - Photographie
        map.put("720-770", 0.0); // Architecture - Photographie
        map.put("730-770", 0.0); // Sculpture - Photographie
        map.put("740-770", 0.4); // Dessin. Arts décoratifs - Photographie
        map.put("750-770", 0.0); // Peinture - Photographie
        map.put("760-770", 0.6); // Arts graphiques - Photographie
        map.put("770-770", 1.0); // Photographie - Photographie
        map.put("0-780", 0.0); // Savoir et érudition. Musées - Musique
        map.put("20-780", 0.0); // Sciences de l'information et de la documentation - Musique
        map.put("100-780", 0.0); // Philosophie - Musique
        map.put("130-780", 0.0); // Esotérisme - Musique
        map.put("150-780", 0.0); // Psychologie - Musique
        map.put("200-780", 0.2); // Religion - Musique
        map.put("300-780", 0.0); // Sciences sociales. Sociologie - Musique
        map.put("304-780", 0.0); // Démographie - Musique
        map.put("305-780", 0.0); // Catégories de personnes - Musique
        map.put("320-780", 0.0); // Science politique - Musique
        map.put("330-780", 0.0); // Economie politique. Travail - Musique
        map.put("340-780", 0.0); // Droit - Musique
        map.put("350-780", 0.0); // Administration publique - Musique
        map.put("355-780", 0.0); // Art et science militaires - Musique
        map.put("360-780", 0.0); // Problèmes et services sociaux. Criminologie - Musique
        map.put("370-780", 0.2); // Education - Musique
        map.put("390-780", 0.2); // Ethnonymes - Musique
        map.put("391-780", 0.2); // Anthropologie. Ethnologie - Musique
        map.put("400-780", 0.0); // Langues - Musique
        map.put("401-780", 0.0); // Linguistique générale - Musique
        map.put("500-780", 0.0); // Sciences - Musique
        map.put("510-780", 0.0); // Mathématiques - Musique
        map.put("520-780", 0.0); // Astronomie - Musique
        map.put("530-780", 0.0); // Physique - Musique
        map.put("540-780", 0.0); // Chimie - Musique
        map.put("550-780", 0.0); // Sciences de la Terre - Musique
        map.put("560-780", 0.0); // Paléontologie - Musique
        map.put("570-780", 0.0); // Biologie - Musique
        map.put("577-780", 0.0); // Ecologie - Musique
        map.put("579-780", 0.0); // Biologie des procaryotes - Musique
        map.put("580-780", 0.0); // Botanique - Musique
        map.put("590-780", 0.0); // Zoologie - Musique
        map.put("600-780", 0.0); // Technique - Musique
        map.put("610-780", 0.0); // Médecine - Musique
        map.put("615-780", 0.0); // Pharmacie - Musique
        map.put("620-780", 0.0); // Sciences de l'ingénieur - Musique
        map.put("621-780", 0.0); // Informatique - Musique
        map.put("630-780", 0.0); // Agriculture - Musique
        map.put("640-780", 0.0); // Economie domestique. Cuisine - Musique
        map.put("650-780", 0.0); // Gestion - Musique
        map.put("690-780", 0.0); // Construction - Musique
        map.put("700-780", 0.6); // Art - Musique
        map.put("710-780", 0.0); // Urbanisme. Architecture du paysage - Musique
        map.put("720-780", 0.0); // Architecture - Musique
        map.put("730-780", 0.0); // Sculpture - Musique
        map.put("740-780", 0.0); // Dessin. Arts décoratifs - Musique
        map.put("750-780", 0.0); // Peinture - Musique
        map.put("760-780", 0.0); // Arts graphiques - Musique
        map.put("770-780", 0.0); // Photographie - Musique
        map.put("780-780", 1.0); // Musique - Musique
        map.put("0-790", 0.0); // Savoir et érudition. Musées - Arts du spectacle
        map.put("20-790", 0.0); // Sciences de l'information et de la documentation - Arts du spectacle
        map.put("100-790", 0.0); // Philosophie - Arts du spectacle
        map.put("130-790", 0.0); // Esotérisme - Arts du spectacle
        map.put("150-790", 0.0); // Psychologie - Arts du spectacle
        map.put("200-790", 0.0); // Religion - Arts du spectacle
        map.put("300-790", 0.0); // Sciences sociales. Sociologie - Arts du spectacle
        map.put("304-790", 0.0); // Démographie - Arts du spectacle
        map.put("305-790", 0.0); // Catégories de personnes - Arts du spectacle
        map.put("320-790", 0.0); // Science politique - Arts du spectacle
        map.put("330-790", 0.0); // Economie politique. Travail - Arts du spectacle
        map.put("340-790", 0.0); // Droit - Arts du spectacle
        map.put("350-790", 0.0); // Administration publique - Arts du spectacle
        map.put("355-790", 0.0); // Art et science militaires - Arts du spectacle
        map.put("360-790", 0.0); // Problèmes et services sociaux. Criminologie - Arts du spectacle
        map.put("370-790", 0.0); // Education - Arts du spectacle
        map.put("390-790", 0.2); // Ethnonymes - Arts du spectacle
        map.put("391-790", 0.0); // Anthropologie. Ethnologie - Arts du spectacle
        map.put("400-790", 0.0); // Langues - Arts du spectacle
        map.put("401-790", 0.0); // Linguistique générale - Arts du spectacle
        map.put("500-790", 0.0); // Sciences - Arts du spectacle
        map.put("510-790", 0.0); // Mathématiques - Arts du spectacle
        map.put("520-790", 0.0); // Astronomie - Arts du spectacle
        map.put("530-790", 0.0); // Physique - Arts du spectacle
        map.put("540-790", 0.0); // Chimie - Arts du spectacle
        map.put("550-790", 0.0); // Sciences de la Terre - Arts du spectacle
        map.put("560-790", 0.0); // Paléontologie - Arts du spectacle
        map.put("570-790", 0.0); // Biologie - Arts du spectacle
        map.put("577-790", 0.0); // Ecologie - Arts du spectacle
        map.put("579-790", 0.0); // Biologie des procaryotes - Arts du spectacle
        map.put("580-790", 0.0); // Botanique - Arts du spectacle
        map.put("590-790", 0.0); // Zoologie - Arts du spectacle
        map.put("600-790", 0.0); // Technique - Arts du spectacle
        map.put("610-790", 0.0); // Médecine - Arts du spectacle
        map.put("615-790", 0.0); // Pharmacie - Arts du spectacle
        map.put("620-790", 0.0); // Sciences de l'ingénieur - Arts du spectacle
        map.put("621-790", 0.0); // Informatique - Arts du spectacle
        map.put("630-790", 0.0); // Agriculture - Arts du spectacle
        map.put("640-790", 0.0); // Economie domestique. Cuisine - Arts du spectacle
        map.put("650-790", 0.0); // Gestion - Arts du spectacle
        map.put("690-790", 0.0); // Construction - Arts du spectacle
        map.put("700-790", 0.6); // Art - Arts du spectacle
        map.put("710-790", 0.0); // Urbanisme. Architecture du paysage - Arts du spectacle
        map.put("720-790", 0.0); // Architecture - Arts du spectacle
        map.put("730-790", 0.0); // Sculpture - Arts du spectacle
        map.put("740-790", 0.0); // Dessin. Arts décoratifs - Arts du spectacle
        map.put("750-790", 0.0); // Peinture - Arts du spectacle
        map.put("760-790", 0.2); // Arts graphiques - Arts du spectacle
        map.put("770-790", 0.0); // Photographie - Arts du spectacle
        map.put("780-790", 0.4); // Musique - Arts du spectacle
        map.put("790-790", 1.0); // Arts du spectacle - Arts du spectacle
        map.put("0-791", 0.0); // Savoir et érudition. Musées - Audiovisuel
        map.put("20-791", 0.4); // Sciences de l'information et de la documentation - Audiovisuel
        map.put("100-791", 0.0); // Philosophie - Audiovisuel
        map.put("130-791", 0.0); // Esotérisme - Audiovisuel
        map.put("150-791", 0.0); // Psychologie - Audiovisuel
        map.put("200-791", 0.0); // Religion - Audiovisuel
        map.put("300-791", 0.0); // Sciences sociales. Sociologie - Audiovisuel
        map.put("304-791", 0.0); // Démographie - Audiovisuel
        map.put("305-791", 0.0); // Catégories de personnes - Audiovisuel
        map.put("320-791", 0.0); // Science politique - Audiovisuel
        map.put("330-791", 0.0); // Economie politique. Travail - Audiovisuel
        map.put("340-791", 0.0); // Droit - Audiovisuel
        map.put("350-791", 0.0); // Administration publique - Audiovisuel
        map.put("355-791", 0.0); // Art et science militaires - Audiovisuel
        map.put("360-791", 0.0); // Problèmes et services sociaux. Criminologie - Audiovisuel
        map.put("370-791", 0.0); // Education - Audiovisuel
        map.put("390-791", 0.0); // Ethnonymes - Audiovisuel
        map.put("391-791", 0.0); // Anthropologie. Ethnologie - Audiovisuel
        map.put("400-791", 0.0); // Langues - Audiovisuel
        map.put("401-791", 0.0); // Linguistique générale - Audiovisuel
        map.put("500-791", 0.2); // Sciences - Audiovisuel
        map.put("510-791", 0.0); // Mathématiques - Audiovisuel
        map.put("520-791", 0.0); // Astronomie - Audiovisuel
        map.put("530-791", 0.0); // Physique - Audiovisuel
        map.put("540-791", 0.0); // Chimie - Audiovisuel
        map.put("550-791", 0.0); // Sciences de la Terre - Audiovisuel
        map.put("560-791", 0.0); // Paléontologie - Audiovisuel
        map.put("570-791", 0.0); // Biologie - Audiovisuel
        map.put("577-791", 0.0); // Ecologie - Audiovisuel
        map.put("579-791", 0.0); // Biologie des procaryotes - Audiovisuel
        map.put("580-791", 0.0); // Botanique - Audiovisuel
        map.put("590-791", 0.0); // Zoologie - Audiovisuel
        map.put("600-791", 0.4); // Technique - Audiovisuel
        map.put("610-791", 0.0); // Médecine - Audiovisuel
        map.put("615-791", 0.0); // Pharmacie - Audiovisuel
        map.put("620-791", 0.0); // Sciences de l'ingénieur - Audiovisuel
        map.put("621-791", 0.0); // Informatique - Audiovisuel
        map.put("630-791", 0.0); // Agriculture - Audiovisuel
        map.put("640-791", 0.0); // Economie domestique. Cuisine - Audiovisuel
        map.put("650-791", 0.0); // Gestion - Audiovisuel
        map.put("690-791", 0.0); // Construction - Audiovisuel
        map.put("700-791", 0.4); // Art - Audiovisuel
        map.put("710-791", 0.0); // Urbanisme. Architecture du paysage - Audiovisuel
        map.put("720-791", 0.0); // Architecture - Audiovisuel
        map.put("730-791", 0.0); // Sculpture - Audiovisuel
        map.put("740-791", 0.0); // Dessin. Arts décoratifs - Audiovisuel
        map.put("750-791", 0.0); // Peinture - Audiovisuel
        map.put("760-791", 0.2); // Arts graphiques - Audiovisuel
        map.put("770-791", 0.4); // Photographie - Audiovisuel
        map.put("780-791", 0.4); // Musique - Audiovisuel
        map.put("790-791", 0.4); // Arts du spectacle - Audiovisuel
        map.put("791-791", 1.0); // Audiovisuel - Audiovisuel
        map.put("0-793", 0.0); // Savoir et érudition. Musées - Sports et jeux
        map.put("20-793", 0.0); // Sciences de l'information et de la documentation - Sports et jeux
        map.put("100-793", 0.0); // Philosophie - Sports et jeux
        map.put("130-793", 0.0); // Esotérisme - Sports et jeux
        map.put("150-793", 0.2); // Psychologie - Sports et jeux
        map.put("200-793", 0.0); // Religion - Sports et jeux
        map.put("300-793", 0.0); // Sciences sociales. Sociologie - Sports et jeux
        map.put("304-793", 0.0); // Démographie - Sports et jeux
        map.put("305-793", 0.0); // Catégories de personnes - Sports et jeux
        map.put("320-793", 0.0); // Science politique - Sports et jeux
        map.put("330-793", 0.0); // Economie politique. Travail - Sports et jeux
        map.put("340-793", 0.0); // Droit - Sports et jeux
        map.put("350-793", 0.0); // Administration publique - Sports et jeux
        map.put("355-793", 0.0); // Art et science militaires - Sports et jeux
        map.put("360-793", 0.0); // Problèmes et services sociaux. Criminologie - Sports et jeux
        map.put("370-793", 0.2); // Education - Sports et jeux
        map.put("390-793", 0.0); // Ethnonymes - Sports et jeux
        map.put("391-793", 0.0); // Anthropologie. Ethnologie - Sports et jeux
        map.put("400-793", 0.0); // Langues - Sports et jeux
        map.put("401-793", 0.0); // Linguistique générale - Sports et jeux
        map.put("500-793", 0.0); // Sciences - Sports et jeux
        map.put("510-793", 0.0); // Mathématiques - Sports et jeux
        map.put("520-793", 0.0); // Astronomie - Sports et jeux
        map.put("530-793", 0.0); // Physique - Sports et jeux
        map.put("540-793", 0.0); // Chimie - Sports et jeux
        map.put("550-793", 0.0); // Sciences de la Terre - Sports et jeux
        map.put("560-793", 0.0); // Paléontologie - Sports et jeux
        map.put("570-793", 0.2); // Biologie - Sports et jeux
        map.put("577-793", 0.0); // Ecologie - Sports et jeux
        map.put("579-793", 0.0); // Biologie des procaryotes - Sports et jeux
        map.put("580-793", 0.0); // Botanique - Sports et jeux
        map.put("590-793", 0.0); // Zoologie - Sports et jeux
        map.put("600-793", 0.0); // Technique - Sports et jeux
        map.put("610-793", 0.2); // Médecine - Sports et jeux
        map.put("615-793", 0.1); // Pharmacie - Sports et jeux
        map.put("620-793", 0.0); // Sciences de l'ingénieur - Sports et jeux
        map.put("621-793", 0.0); // Informatique - Sports et jeux
        map.put("630-793", 0.0); // Agriculture - Sports et jeux
        map.put("640-793", 0.0); // Economie domestique. Cuisine - Sports et jeux
        map.put("650-793", 0.0); // Gestion - Sports et jeux
        map.put("690-793", 0.0); // Construction - Sports et jeux
        map.put("700-793", 0.0); // Art - Sports et jeux
        map.put("710-793", 0.0); // Urbanisme. Architecture du paysage - Sports et jeux
        map.put("720-793", 0.0); // Architecture - Sports et jeux
        map.put("730-793", 0.0); // Sculpture - Sports et jeux
        map.put("740-793", 0.0); // Dessin. Arts décoratifs - Sports et jeux
        map.put("750-793", 0.0); // Peinture - Sports et jeux
        map.put("760-793", 0.0); // Arts graphiques - Sports et jeux
        map.put("770-793", 0.0); // Photographie - Sports et jeux
        map.put("780-793", 0.0); // Musique - Sports et jeux
        map.put("790-793", 0.2); // Arts du spectacle - Sports et jeux
        map.put("791-793", 0.2); // Audiovisuel - Sports et jeux
        map.put("793-793", 1.0); // Sports et jeux - Sports et jeux
        map.put("0-800", 0.0); // Savoir et érudition. Musées - Littératures

        map.put("20-800", 0.0); // Sciences de l'information et de la documentation - Littératures
        map.put("100-800", 0.0); // Philosophie - Littératures
        map.put("130-800", 0.1); // Esotérisme - Littératures
        map.put("150-800", 0.1); // Psychologie - Littératures
        map.put("200-800", 0.2); // Religion - Littératures
        map.put("300-800", 0.0); // Sciences sociales. Sociologie - Littératures
        map.put("304-800", 0.0); // Démographie - Littératures
        map.put("305-800", 0.0); // Catégories de personnes - Littératures
        map.put("320-800", 0.0); // Science politique - Littératures
        map.put("330-800", 0.0); // Economie politique. Travail - Littératures
        map.put("340-800", 0.0); // Droit - Littératures
        map.put("350-800", 0.0); // Administration publique - Littératures
        map.put("355-800", 0.0); // Art et science militaires - Littératures
        map.put("360-800", 0.0); // Problèmes et services sociaux. Criminologie - Littératures
        map.put("370-800", 0.2); // Education - Littératures
        map.put("390-800", 0.2); // Ethnonymes - Littératures
        map.put("391-800", 0.0); // Anthropologie. Ethnologie - Littératures
        map.put("400-800", 0.2); // Langues - Littératures
        map.put("401-800", 0.2); // Linguistique générale - Littératures
        map.put("500-800", 0.0); // Sciences - Littératures
        map.put("510-800", 0.0); // Mathématiques - Littératures
        map.put("520-800", 0.0); // Astronomie - Littératures
        map.put("530-800", 0.0); // Physique - Littératures
        map.put("540-800", 0.0); // Chimie - Littératures
        map.put("550-800", 0.0); // Sciences de la Terre - Littératures
        map.put("560-800", 0.0); // Paléontologie - Littératures
        map.put("570-800", 0.0); // Biologie - Littératures
        map.put("577-800", 0.0); // Ecologie - Littératures
        map.put("579-800", 0.0); // Biologie des procaryotes - Littératures
        map.put("580-800", 0.0); // Botanique - Littératures
        map.put("590-800", 0.0); // Zoologie - Littératures
        map.put("600-800", 0.0); // Technique - Littératures
        map.put("610-800", 0.0); // Médecine - Littératures
        map.put("615-800", 0.0); // Pharmacie - Littératures
        map.put("620-800", 0.0); // Sciences de l'ingénieur - Littératures
        map.put("621-800", 0.0); // Informatique - Littératures
        map.put("630-800", 0.0); // Agriculture - Littératures
        map.put("640-800", 0.0); // Economie domestique. Cuisine - Littératures
        map.put("650-800", 0.0); // Gestion - Littératures
        map.put("690-800", 0.0); // Construction - Littératures
        map.put("700-800", 0.4); // Art - Littératures
        map.put("710-800", 0.0); // Urbanisme. Architecture du paysage - Littératures
        map.put("720-800", 0.0); // Architecture - Littératures
        map.put("730-800", 0.0); // Sculpture - Littératures
        map.put("740-800", 0.0); // Dessin. Arts décoratifs - Littératures
        map.put("750-800", 0.0); // Peinture - Littératures
        map.put("760-800", 0.0); // Arts graphiques - Littératures
        map.put("770-800", 0.0); // Photographie - Littératures
        map.put("780-800", 0.0); // Musique - Littératures
        map.put("790-800", 0.0); // Arts du spectacle - Littératures
        map.put("791-800", 0.0); // Audiovisuel - Littératures
        map.put("793-800", 0.0); // Sports et jeux - Littératures
        map.put("800-800", 1.0); // Littératures - Littératures
        map.put("0-801", 0.0); // Savoir et érudition. Musées - Littérature générale
        map.put("20-801", 0.0); // Sciences de l'information et de la documentation - Littérature générale
        map.put("100-801", 0.2); // Philosophie - Littérature générale
        map.put("130-801", 0.0); // Esotérisme - Littérature générale
        map.put("150-801", 0.0); // Psychologie - Littérature générale
        map.put("200-801", 0.0); // Religion - Littérature générale
        map.put("300-801", 0.0); // Sciences sociales. Sociologie - Littérature générale
        map.put("304-801", 0.0); // Démographie - Littérature générale
        map.put("305-801", 0.0); // Catégories de personnes - Littérature générale
        map.put("320-801", 0.0); // Science politique - Littérature générale
        map.put("330-801", 0.0); // Economie politique. Travail - Littérature générale
        map.put("340-801", 0.0); // Droit - Littérature générale
        map.put("350-801", 0.0); // Administration publique - Littérature générale
        map.put("355-801", 0.0); // Art et science militaires - Littérature générale
        map.put("360-801", 0.0); // Problèmes et services sociaux. Criminologie - Littérature générale
        map.put("370-801", 0.2); // Education - Littérature générale
        map.put("390-801", 0.0); // Ethnonymes - Littérature générale
        map.put("391-801", 0.0); // Anthropologie. Ethnologie - Littérature générale
        map.put("400-801", 0.0); // Langues - Littérature générale
        map.put("401-801", 0.4); // Linguistique générale - Littérature générale
        map.put("500-801", 0.0); // Sciences - Littérature générale
        map.put("510-801", 0.0); // Mathématiques - Littérature générale
        map.put("520-801", 0.0); // Astronomie - Littérature générale
        map.put("530-801", 0.0); // Physique - Littérature générale
        map.put("540-801", 0.0); // Chimie - Littérature générale
        map.put("550-801", 0.0); // Sciences de la Terre - Littérature générale
        map.put("560-801", 0.0); // Paléontologie - Littérature générale
        map.put("570-801", 0.0); // Biologie - Littérature générale
        map.put("577-801", 0.0); // Ecologie - Littérature générale
        map.put("579-801", 0.0); // Biologie des procaryotes - Littérature générale
        map.put("580-801", 0.0); // Botanique - Littérature générale
        map.put("590-801", 0.0); // Zoologie - Littérature générale
        map.put("600-801", 0.0); // Technique - Littérature générale
        map.put("610-801", 0.0); // Médecine - Littérature générale
        map.put("615-801", 0.0); // Pharmacie - Littérature générale
        map.put("620-801", 0.0); // Sciences de l'ingénieur - Littérature générale
        map.put("621-801", 0.0); // Informatique - Littérature générale
        map.put("630-801", 0.0); // Agriculture - Littérature générale
        map.put("640-801", 0.0); // Economie domestique. Cuisine - Littérature générale
        map.put("650-801", 0.0); // Gestion - Littérature générale
        map.put("690-801", 0.0); // Construction - Littérature générale
        map.put("700-801", 0.4); // Art - Littérature générale
        map.put("710-801", 0.0); // Urbanisme. Architecture du paysage - Littérature générale
        map.put("720-801", 0.0); // Architecture - Littérature générale
        map.put("730-801", 0.0); // Sculpture - Littérature générale
        map.put("740-801", 0.0); // Dessin. Arts décoratifs - Littérature générale
        map.put("750-801", 0.0); // Peinture - Littérature générale
        map.put("760-801", 0.0); // Arts graphiques - Littérature générale
        map.put("770-801", 0.0); // Photographie - Littérature générale
        map.put("780-801", 0.0); // Musique - Littérature générale
        map.put("790-801", 0.0); // Arts du spectacle - Littérature générale
        map.put("791-801", 0.0); // Audiovisuel - Littérature générale
        map.put("793-801", 0.0); // Sports et jeux - Littérature générale
        map.put("800-801", 0.9); // Littératures - Littérature générale
        map.put("801-801", 1.0); // Littérature générale - Littérature générale
        map.put("0-900", 0.4); // Savoir et érudition. Musées - Histoire
        map.put("20-900", 0.0); // Sciences de l'information et de la documentation - Histoire
        map.put("100-900", 0.1); // Philosophie - Histoire
        map.put("130-900", 0.0); // Esotérisme - Histoire
        map.put("150-900", 0.0); // Psychologie - Histoire
        map.put("200-900", 0.2); // Religion - Histoire
        map.put("300-900", 0.1); // Sciences sociales. Sociologie - Histoire
        map.put("304-900", 0.1); // Démographie - Histoire
        map.put("305-900", 0.2); // Catégories de personnes - Histoire
        map.put("320-900", 0.2); // Science politique - Histoire
        map.put("330-900", 0.1); // Economie politique. Travail - Histoire
        map.put("340-900", 0.1); // Droit - Histoire
        map.put("350-900", 0.1); // Administration publique - Histoire
        map.put("355-900", 0.4); // Art et science militaires - Histoire
        map.put("360-900", 0.1); // Problèmes et services sociaux. Criminologie - Histoire
        map.put("370-900", 0.2); // Education - Histoire
        map.put("390-900", 0.2); // Ethnonymes - Histoire
        map.put("391-900", 0.2); // Anthropologie. Ethnologie - Histoire
        map.put("400-900", 0.1); // Langues - Histoire
        map.put("401-900", 0.0); // Linguistique générale - Histoire
        map.put("500-900", 0.2); // Sciences - Histoire
        map.put("510-900", 0.0); // Mathématiques - Histoire
        map.put("520-900", 0.0); // Astronomie - Histoire
        map.put("530-900", 0.0); // Physique - Histoire
        map.put("540-900", 0.0); // Chimie - Histoire
        map.put("550-900", 0.0); // Sciences de la Terre - Histoire
        map.put("560-900", 0.2); // Paléontologie - Histoire
        map.put("570-900", 0.0); // Biologie - Histoire
        map.put("577-900", 0.0); // Ecologie - Histoire
        map.put("579-900", 0.0); // Biologie des procaryotes - Histoire
        map.put("580-900", 0.0); // Botanique - Histoire
        map.put("590-900", 0.0); // Zoologie - Histoire
        map.put("600-900", 0.2); // Technique - Histoire
        map.put("610-900", 0.0); // Médecine - Histoire
        map.put("615-900", 0.0); // Pharmacie - Histoire
        map.put("620-900", 0.0); // Sciences de l'ingénieur - Histoire
        map.put("621-900", 0.0); // Informatique - Histoire
        map.put("630-900", 0.0); // Agriculture - Histoire
        map.put("640-900", 0.0); // Economie domestique. Cuisine - Histoire
        map.put("650-900", 0.0); // Gestion - Histoire
        map.put("690-900", 0.0); // Construction - Histoire
        map.put("700-900", 0.2); // Art - Histoire
        map.put("710-900", 0.2); // Urbanisme. Architecture du paysage - Histoire
        map.put("720-900", 0.2); // Architecture - Histoire
        map.put("730-900", 0.2); // Sculpture - Histoire
        map.put("740-900", 0.1); // Dessin. Arts décoratifs - Histoire
        map.put("750-900", 0.2); // Peinture - Histoire
        map.put("760-900", 0.0); // Arts graphiques - Histoire
        map.put("770-900", 0.0); // Photographie - Histoire
        map.put("780-900", 0.1); // Musique - Histoire
        map.put("790-900", 0.0); // Arts du spectacle - Histoire
        map.put("791-900", 0.0); // Audiovisuel - Histoire
        map.put("793-900", 0.0); // Sports et jeux - Histoire
        map.put("800-900", 0.0); // Littératures - Histoire
        map.put("801-900", 0.0); // Littérature générale - Histoire
        map.put("900-900", 1.0); // Histoire - Histoire
        map.put("0-910", 0.0); // Savoir et érudition. Musées - Géographie
        map.put("20-910", 0.2); // Sciences de l'information et de la documentation - Géographie
        map.put("100-910", 0.0); // Philosophie - Géographie
        map.put("130-910", 0.0); // Esotérisme - Géographie
        map.put("150-910", 0.0); // Psychologie - Géographie
        map.put("200-910", 0.0); // Religion - Géographie
        map.put("300-910", 0.1); // Sciences sociales. Sociologie - Géographie
        map.put("304-910", 0.4); // Démographie - Géographie
        map.put("305-910", 0.2); // Catégories de personnes - Géographie
        map.put("320-910", 0.1); // Science politique - Géographie
        map.put("330-910", 0.2); // Economie politique. Travail - Géographie
        map.put("340-910", 0.0); // Droit - Géographie
        map.put("350-910", 0.0); // Administration publique - Géographie
        map.put("355-910", 0.2); // Art et science militaires - Géographie
        map.put("360-910", 0.0); // Problèmes et services sociaux. Criminologie - Géographie
        map.put("370-910", 0.2); // Education - Géographie
        map.put("390-910", 0.2); // Ethnonymes - Géographie
        map.put("391-910", 0.2); // Anthropologie. Ethnologie - Géographie
        map.put("400-910", 0.1); // Langues - Géographie
        map.put("401-910", 0.0); // Linguistique générale - Géographie
        map.put("500-910", 0.0); // Sciences - Géographie
        map.put("510-910", 0.0); // Mathématiques - Géographie
        map.put("520-910", 0.0); // Astronomie - Géographie
        map.put("530-910", 0.0); // Physique - Géographie
        map.put("540-910", 0.0); // Chimie - Géographie
        map.put("550-910", 0.2); // Sciences de la Terre - Géographie
        map.put("560-910", 0.2); // Paléontologie - Géographie
        map.put("570-910", 0.0); // Biologie - Géographie
        map.put("577-910", 0.2); // Ecologie - Géographie
        map.put("579-910", 0.0); // Biologie des procaryotes - Géographie
        map.put("580-910", 0.2); // Botanique - Géographie
        map.put("590-910", 0.2); // Zoologie - Géographie
        map.put("600-910", 0.0); // Technique - Géographie
        map.put("610-910", 0.0); // Médecine - Géographie
        map.put("615-910", 0.0); // Pharmacie - Géographie
        map.put("620-910", 0.0); // Sciences de l'ingénieur - Géographie
        map.put("621-910", 0.0); // Informatique - Géographie
        map.put("630-910", 0.2); // Agriculture - Géographie
        map.put("640-910", 0.0); // Economie domestique. Cuisine - Géographie
        map.put("650-910", 0.0); // Gestion - Géographie
        map.put("690-910", 0.0); // Construction - Géographie
        map.put("700-910", 0.0); // Art - Géographie
        map.put("710-910", 0.1); // Urbanisme. Architecture du paysage - Géographie
        map.put("720-910", 0.2); // Architecture - Géographie
        map.put("730-910", 0.0); // Sculpture - Géographie
        map.put("740-910", 0.0); // Dessin. Arts décoratifs - Géographie
        map.put("750-910", 0.0); // Peinture - Géographie
        map.put("760-910", 0.0); // Arts graphiques - Géographie
        map.put("770-910", 0.0); // Photographie - Géographie
        map.put("780-910", 0.0); // Musique - Géographie
        map.put("790-910", 0.0); // Arts du spectacle - Géographie
        map.put("791-910", 0.0); // Audiovisuel - Géographie
        map.put("793-910", 0.0); // Sports et jeux - Géographie
        map.put("800-910", 0.0); // Littératures - Géographie
        map.put("801-910", 0.0); // Littérature générale - Géographie
        map.put("900-910", 0.1); // Histoire - Géographie
        map.put("910-910", 1.0); // Géographie - Géographie
        map.put("0-912", 0.0); // Savoir et érudition. Musées - Géographie de la France
        map.put("20-912", 0.2); // Sciences de l'information et de la documentation - Géographie de la France
        map.put("100-912", 0.0); // Philosophie - Géographie de la France
        map.put("130-912", 0.0); // Esotérisme - Géographie de la France
        map.put("150-912", 0.0); // Psychologie - Géographie de la France
        map.put("200-912", 0.0); // Religion - Géographie de la France
        map.put("300-912", 0.1); // Sciences sociales. Sociologie - Géographie de la France
        map.put("304-912", 0.4); // Démographie - Géographie de la France
        map.put("305-912", 0.2); // Catégories de personnes - Géographie de la France
        map.put("320-912", 0.1); // Science politique - Géographie de la France
        map.put("330-912", 0.2); // Economie politique. Travail - Géographie de la France
        map.put("340-912", 0.0); // Droit - Géographie de la France
        map.put("350-912", 0.0); // Administration publique - Géographie de la France
        map.put("355-912", 0.2); // Art et science militaires - Géographie de la France
        map.put("360-912", 0.0); // Problèmes et services sociaux. Criminologie - Géographie de la France
        map.put("370-912", 0.2); // Education - Géographie de la France
        map.put("390-912", 0.2); // Ethnonymes - Géographie de la France
        map.put("391-912", 0.2); // Anthropologie. Ethnologie - Géographie de la France
        map.put("400-912", 0.1); // Langues - Géographie de la France
        map.put("401-912", 0.0); // Linguistique générale - Géographie de la France
        map.put("500-912", 0.0); // Sciences - Géographie de la France
        map.put("510-912", 0.0); // Mathématiques - Géographie de la France
        map.put("520-912", 0.0); // Astronomie - Géographie de la France
        map.put("530-912", 0.0); // Physique - Géographie de la France
        map.put("540-912", 0.0); // Chimie - Géographie de la France
        map.put("550-912", 0.2); // Sciences de la Terre - Géographie de la France
        map.put("560-912", 0.1); // Paléontologie - Géographie de la France
        map.put("570-912", 0.0); // Biologie - Géographie de la France
        map.put("577-912", 0.2); // Ecologie - Géographie de la France
        map.put("579-912", 0.0); // Biologie des procaryotes - Géographie de la France
        map.put("580-912", 0.2); // Botanique - Géographie de la France
        map.put("590-912", 0.2); // Zoologie - Géographie de la France
        map.put("600-912", 0.0); // Technique - Géographie de la France
        map.put("610-912", 0.0); // Médecine - Géographie de la France
        map.put("615-912", 0.0); // Pharmacie - Géographie de la France
        map.put("620-912", 0.0); // Sciences de l'ingénieur - Géographie de la France
        map.put("621-912", 0.0); // Informatique - Géographie de la France
        map.put("630-912", 0.2); // Agriculture - Géographie de la France
        map.put("640-912", 0.0); // Economie domestique. Cuisine - Géographie de la France
        map.put("650-912", 0.0); // Gestion - Géographie de la France
        map.put("690-912", 0.0); // Construction - Géographie de la France
        map.put("700-912", 0.0); // Art - Géographie de la France
        map.put("710-912", 0.1); // Urbanisme. Architecture du paysage - Géographie de la France
        map.put("720-912", 0.2); // Architecture - Géographie de la France
        map.put("730-912", 0.0); // Sculpture - Géographie de la France
        map.put("740-912", 0.0); // Dessin. Arts décoratifs - Géographie de la France
        map.put("750-912", 0.0); // Peinture - Géographie de la France
        map.put("760-912", 0.0); // Arts graphiques - Géographie de la France
        map.put("770-912", 0.0); // Photographie - Géographie de la France
        map.put("780-912", 0.0); // Musique - Géographie de la France
        map.put("790-912", 0.0); // Arts du spectacle - Géographie de la France
        map.put("791-912", 0.0); // Audiovisuel - Géographie de la France
        map.put("793-912", 0.0); // Sports et jeux - Géographie de la France
        map.put("800-912", 0.0); // Littératures - Géographie de la France
        map.put("801-912", 0.0); // Littérature générale - Géographie de la France
        map.put("900-912", 0.1); // Histoire - Géographie de la France
        map.put("910-912", 0.9); // Géographie - Géographie de la France
        map.put("912-912", 1.0); // Géographie de la France - Géographie de la France
        map.put("0-914", 0.0); // Savoir et érudition. Musées - Géographie de l'Europe
        map.put("20-914", 0.2); // Sciences de l'information et de la documentation - Géographie de l'Europe
        map.put("100-914", 0.0); // Philosophie - Géographie de l'Europe
        map.put("130-914", 0.0); // Esotérisme - Géographie de l'Europe
        map.put("150-914", 0.0); // Psychologie - Géographie de l'Europe
        map.put("200-914", 0.0); // Religion - Géographie de l'Europe
        map.put("300-914", 0.1); // Sciences sociales. Sociologie - Géographie de l'Europe
        map.put("304-914", 0.4); // Démographie - Géographie de l'Europe
        map.put("305-914", 0.2); // Catégories de personnes - Géographie de l'Europe
        map.put("320-914", 0.1); // Science politique - Géographie de l'Europe
        map.put("330-914", 0.2); // Economie politique. Travail - Géographie de l'Europe
        map.put("340-914", 0.0); // Droit - Géographie de l'Europe
        map.put("350-914", 0.0); // Administration publique - Géographie de l'Europe
        map.put("355-914", 0.2); // Art et science militaires - Géographie de l'Europe
        map.put("360-914", 0.0); // Problèmes et services sociaux. Criminologie - Géographie de l'Europe
        map.put("370-914", 0.2); // Education - Géographie de l'Europe
        map.put("390-914", 0.2); // Ethnonymes - Géographie de l'Europe
        map.put("391-914", 0.2); // Anthropologie. Ethnologie - Géographie de l'Europe
        map.put("400-914", 0.1); // Langues - Géographie de l'Europe
        map.put("401-914", 0.0); // Linguistique générale - Géographie de l'Europe
        map.put("500-914", 0.0); // Sciences - Géographie de l'Europe
        map.put("510-914", 0.0); // Mathématiques - Géographie de l'Europe
        map.put("520-914", 0.0); // Astronomie - Géographie de l'Europe
        map.put("530-914", 0.0); // Physique - Géographie de l'Europe
        map.put("540-914", 0.0); // Chimie - Géographie de l'Europe
        map.put("550-914", 0.2); // Sciences de la Terre - Géographie de l'Europe
        map.put("560-914", 0.1); // Paléontologie - Géographie de l'Europe
        map.put("570-914", 0.0); // Biologie - Géographie de l'Europe
        map.put("577-914", 0.2); // Ecologie - Géographie de l'Europe
        map.put("579-914", 0.0); // Biologie des procaryotes - Géographie de l'Europe
        map.put("580-914", 0.2); // Botanique - Géographie de l'Europe
        map.put("590-914", 0.2); // Zoologie - Géographie de l'Europe
        map.put("600-914", 0.0); // Technique - Géographie de l'Europe
        map.put("610-914", 0.0); // Médecine - Géographie de l'Europe
        map.put("615-914", 0.0); // Pharmacie - Géographie de l'Europe
        map.put("620-914", 0.0); // Sciences de l'ingénieur - Géographie de l'Europe
        map.put("621-914", 0.0); // Informatique - Géographie de l'Europe
        map.put("630-914", 0.2); // Agriculture - Géographie de l'Europe
        map.put("640-914", 0.0); // Economie domestique. Cuisine - Géographie de l'Europe
        map.put("650-914", 0.0); // Gestion - Géographie de l'Europe
        map.put("690-914", 0.0); // Construction - Géographie de l'Europe
        map.put("700-914", 0.0); // Art - Géographie de l'Europe
        map.put("710-914", 0.1); // Urbanisme. Architecture du paysage - Géographie de l'Europe
        map.put("720-914", 0.2); // Architecture - Géographie de l'Europe
        map.put("730-914", 0.0); // Sculpture - Géographie de l'Europe
        map.put("740-914", 0.0); // Dessin. Arts décoratifs - Géographie de l'Europe
        map.put("750-914", 0.0); // Peinture - Géographie de l'Europe
        map.put("760-914", 0.0); // Arts graphiques - Géographie de l'Europe
        map.put("770-914", 0.0); // Photographie - Géographie de l'Europe
        map.put("780-914", 0.0); // Musique - Géographie de l'Europe
        map.put("790-914", 0.0); // Arts du spectacle - Géographie de l'Europe
        map.put("791-914", 0.0); // Audiovisuel - Géographie de l'Europe
        map.put("793-914", 0.0); // Sports et jeux - Géographie de l'Europe
        map.put("800-914", 0.0); // Littératures - Géographie de l'Europe
        map.put("801-914", 0.0); // Littérature générale - Géographie de l'Europe
        map.put("900-914", 0.1); // Histoire - Géographie de l'Europe
        map.put("910-914", 0.9); // Géographie - Géographie de l'Europe
        map.put("912-914", 0.6); // Géographie de la France - Géographie de l'Europe
        map.put("914-914", 1.0); // Géographie de l'Europe - Géographie de l'Europe
        map.put("0-915", 0.0); // Savoir et érudition. Musées - Géographie du reste du monde
        map.put("20-915", 0.2); // Sciences de l'information et de la documentation - Géographie du reste du monde
        map.put("100-915", 0.0); // Philosophie - Géographie du reste du monde
        map.put("130-915", 0.0); // Esotérisme - Géographie du reste du monde
        map.put("150-915", 0.0); // Psychologie - Géographie du reste du monde
        map.put("200-915", 0.0); // Religion - Géographie du reste du monde
        map.put("300-915", 0.1); // Sciences sociales. Sociologie - Géographie du reste du monde
        map.put("304-915", 0.4); // Démographie - Géographie du reste du monde
        map.put("305-915", 0.2); // Catégories de personnes - Géographie du reste du monde
        map.put("320-915", 0.1); // Science politique - Géographie du reste du monde
        map.put("330-915", 0.2); // Economie politique. Travail - Géographie du reste du monde
        map.put("340-915", 0.0); // Droit - Géographie du reste du monde
        map.put("350-915", 0.0); // Administration publique - Géographie du reste du monde
        map.put("355-915", 0.2); // Art et science militaires - Géographie du reste du monde
        map.put("360-915", 0.0); // Problèmes et services sociaux. Criminologie - Géographie du reste du monde
        map.put("370-915", 0.2); // Education - Géographie du reste du monde
        map.put("390-915", 0.2); // Ethnonymes - Géographie du reste du monde
        map.put("391-915", 0.2); // Anthropologie. Ethnologie - Géographie du reste du monde
        map.put("400-915", 0.1); // Langues - Géographie du reste du monde
        map.put("401-915", 0.0); // Linguistique générale - Géographie du reste du monde
        map.put("500-915", 0.0); // Sciences - Géographie du reste du monde
        map.put("510-915", 0.0); // Mathématiques - Géographie du reste du monde
        map.put("520-915", 0.0); // Astronomie - Géographie du reste du monde
        map.put("530-915", 0.0); // Physique - Géographie du reste du monde
        map.put("540-915", 0.0); // Chimie - Géographie du reste du monde
        map.put("550-915", 0.2); // Sciences de la Terre - Géographie du reste du monde
        map.put("560-915", 0.1); // Paléontologie - Géographie du reste du monde
        map.put("570-915", 0.0); // Biologie - Géographie du reste du monde
        map.put("577-915", 0.2); // Ecologie - Géographie du reste du monde
        map.put("579-915", 0.0); // Biologie des procaryotes - Géographie du reste du monde
        map.put("580-915", 0.2); // Botanique - Géographie du reste du monde
        map.put("590-915", 0.2); // Zoologie - Géographie du reste du monde
        map.put("600-915", 0.0); // Technique - Géographie du reste du monde
        map.put("610-915", 0.0); // Médecine - Géographie du reste du monde
        map.put("615-915", 0.0); // Pharmacie - Géographie du reste du monde
        map.put("620-915", 0.0); // Sciences de l'ingénieur - Géographie du reste du monde
        map.put("621-915", 0.0); // Informatique - Géographie du reste du monde
        map.put("630-915", 0.2); // Agriculture - Géographie du reste du monde
        map.put("640-915", 0.0); // Economie domestique. Cuisine - Géographie du reste du monde
        map.put("650-915", 0.0); // Gestion - Géographie du reste du monde
        map.put("690-915", 0.0); // Construction - Géographie du reste du monde
        map.put("700-915", 0.0); // Art - Géographie du reste du monde
        map.put("710-915", 0.1); // Urbanisme. Architecture du paysage - Géographie du reste du monde
        map.put("720-915", 0.2); // Architecture - Géographie du reste du monde
        map.put("730-915", 0.0); // Sculpture - Géographie du reste du monde
        map.put("740-915", 0.0); // Dessin. Arts décoratifs - Géographie du reste du monde
        map.put("750-915", 0.0); // Peinture - Géographie du reste du monde
        map.put("760-915", 0.0); // Arts graphiques - Géographie du reste du monde

        map.put("770-915", 0.0); // Photographie - Géographie du reste du monde
        map.put("780-915", 0.0); // Musique - Géographie du reste du monde
        map.put("790-915", 0.0); // Arts du spectacle - Géographie du reste du monde
        map.put("791-915", 0.0); // Audiovisuel - Géographie du reste du monde
        map.put("793-915", 0.0); // Sports et jeux - Géographie du reste du monde
        map.put("800-915", 0.0); // Littératures - Géographie du reste du monde
        map.put("801-915", 0.0); // Littérature générale - Géographie du reste du monde
        map.put("900-915", 0.1); // Histoire - Géographie du reste du monde
        map.put("910-915", 0.9); // Géographie - Géographie du reste du monde
        map.put("912-915", 0.4); // Géographie de la France - Géographie du reste du monde
        map.put("914-915", 0.4); // Géographie de l'Europe - Géographie du reste du monde
        map.put("915-915", 1.0); // Géographie du reste du monde - Géographie du reste du monde
        map.put("0-930", 0.6); // Savoir et érudition. Musées - Archéologie Préhistoire Histoire ancienne
        map.put("20-930", 0.0); // Sciences de l'information et de la documentation - Archéologie Préhistoire Histoire ancienne
        map.put("100-930", 0.0); // Philosophie - Archéologie Préhistoire Histoire ancienne
        map.put("130-930", 0.0); // Esotérisme - Archéologie Préhistoire Histoire ancienne
        map.put("150-930", 0.0); // Psychologie - Archéologie Préhistoire Histoire ancienne
        map.put("200-930", 0.2); // Religion - Archéologie Préhistoire Histoire ancienne
        map.put("300-930", 0.0); // Sciences sociales. Sociologie - Archéologie Préhistoire Histoire ancienne
        map.put("304-930", 0.0); // Démographie - Archéologie Préhistoire Histoire ancienne
        map.put("305-930", 0.0); // Catégories de personnes - Archéologie Préhistoire Histoire ancienne
        map.put("320-930", 0.0); // Science politique - Archéologie Préhistoire Histoire ancienne
        map.put("330-930", 0.2); // Economie politique. Travail - Archéologie Préhistoire Histoire ancienne
        map.put("340-930", 0.0); // Droit - Archéologie Préhistoire Histoire ancienne
        map.put("350-930", 0.0); // Administration publique - Archéologie Préhistoire Histoire ancienne
        map.put("355-930", 0.0); // Art et science militaires - Archéologie Préhistoire Histoire ancienne
        map.put("360-930", 0.0); // Problèmes et services sociaux. Criminologie - Archéologie Préhistoire Histoire ancienne
        map.put("370-930", 0.0); // Education - Archéologie Préhistoire Histoire ancienne
        map.put("390-930", 0.2); // Ethnonymes - Archéologie Préhistoire Histoire ancienne
        map.put("391-930", 0.4); // Anthropologie. Ethnologie - Archéologie Préhistoire Histoire ancienne
        map.put("400-930", 0.0); // Langues - Archéologie Préhistoire Histoire ancienne
        map.put("401-930", 0.0); // Linguistique générale - Archéologie Préhistoire Histoire ancienne
        map.put("500-930", 0.0); // Sciences - Archéologie Préhistoire Histoire ancienne
        map.put("510-930", 0.0); // Mathématiques - Archéologie Préhistoire Histoire ancienne
        map.put("520-930", 0.0); // Astronomie - Archéologie Préhistoire Histoire ancienne
        map.put("530-930", 0.0); // Physique - Archéologie Préhistoire Histoire ancienne
        map.put("540-930", 0.0); // Chimie - Archéologie Préhistoire Histoire ancienne
        map.put("550-930", 0.0); // Sciences de la Terre - Archéologie Préhistoire Histoire ancienne
        map.put("560-930", 0.4); // Paléontologie - Archéologie Préhistoire Histoire ancienne
        map.put("570-930", 0.0); // Biologie - Archéologie Préhistoire Histoire ancienne
        map.put("577-930", 0.0); // Ecologie - Archéologie Préhistoire Histoire ancienne
        map.put("579-930", 0.0); // Biologie des procaryotes - Archéologie Préhistoire Histoire ancienne
        map.put("580-930", 0.0); // Botanique - Archéologie Préhistoire Histoire ancienne
        map.put("590-930", 0.0); // Zoologie - Archéologie Préhistoire Histoire ancienne
        map.put("600-930", 0.0); // Technique - Archéologie Préhistoire Histoire ancienne
        map.put("610-930", 0.0); // Médecine - Archéologie Préhistoire Histoire ancienne
        map.put("615-930", 0.0); // Pharmacie - Archéologie Préhistoire Histoire ancienne
        map.put("620-930", 0.0); // Sciences de l'ingénieur - Archéologie Préhistoire Histoire ancienne
        map.put("621-930", 0.0); // Informatique - Archéologie Préhistoire Histoire ancienne
        map.put("630-930", 0.0); // Agriculture - Archéologie Préhistoire Histoire ancienne
        map.put("640-930", 0.0); // Economie domestique. Cuisine - Archéologie Préhistoire Histoire ancienne
        map.put("650-930", 0.0); // Gestion - Archéologie Préhistoire Histoire ancienne
        map.put("690-930", 0.0); // Construction - Archéologie Préhistoire Histoire ancienne
        map.put("700-930", 0.2); // Art - Archéologie Préhistoire Histoire ancienne
        map.put("710-930", 0.1); // Urbanisme. Architecture du paysage - Archéologie Préhistoire Histoire ancienne
        map.put("720-930", 0.2); // Architecture - Archéologie Préhistoire Histoire ancienne
        map.put("730-930", 0.1); // Sculpture - Archéologie Préhistoire Histoire ancienne
        map.put("740-930", 0.1); // Dessin. Arts décoratifs - Archéologie Préhistoire Histoire ancienne
        map.put("750-930", 0.2); // Peinture - Archéologie Préhistoire Histoire ancienne
        map.put("760-930", 0.1); // Arts graphiques - Archéologie Préhistoire Histoire ancienne
        map.put("770-930", 0.0); // Photographie - Archéologie Préhistoire Histoire ancienne
        map.put("780-930", 0.0); // Musique - Archéologie Préhistoire Histoire ancienne
        map.put("790-930", 0.0); // Arts du spectacle - Archéologie Préhistoire Histoire ancienne
        map.put("791-930", 0.0); // Audiovisuel - Archéologie Préhistoire Histoire ancienne
        map.put("793-930", 0.0); // Sports et jeux - Archéologie Préhistoire Histoire ancienne
        map.put("800-930", 0.0); // Littératures - Archéologie Préhistoire Histoire ancienne
        map.put("801-930", 0.0); // Littérature générale - Archéologie Préhistoire Histoire ancienne
        map.put("900-930", 0.6); // Histoire - Archéologie Préhistoire Histoire ancienne
        map.put("910-930", 0.1); // Géographie - Archéologie Préhistoire Histoire ancienne
        map.put("912-930", 0.1); // Géographie de la France - Archéologie Préhistoire Histoire ancienne
        map.put("914-930", 0.1); // Géographie de l'Europe - Archéologie Préhistoire Histoire ancienne
        map.put("915-930", 0.1); // Géographie du reste du monde - Archéologie Préhistoire Histoire ancienne
        map.put("930-930", 1.0); // Archéologie Préhistoire Histoire ancienne - Archéologie Préhistoire Histoire ancienne
        map.put("0-940", 0.4); // Savoir et érudition. Musées - Histoire de l'Europe
        map.put("20-940", 0.0); // Sciences de l'information et de la documentation - Histoire de l'Europe
        map.put("100-940", 0.0); // Philosophie - Histoire de l'Europe
        map.put("130-940", 0.0); // Esotérisme - Histoire de l'Europe
        map.put("150-940", 0.0); // Psychologie - Histoire de l'Europe
        map.put("200-940", 0.2); // Religion - Histoire de l'Europe
        map.put("300-940", 0.1); // Sciences sociales. Sociologie - Histoire de l'Europe
        map.put("304-940", 0.1); // Démographie - Histoire de l'Europe
        map.put("305-940", 0.2); // Catégories de personnes - Histoire de l'Europe
        map.put("320-940", 0.2); // Science politique - Histoire de l'Europe
        map.put("330-940", 0.2); // Economie politique. Travail - Histoire de l'Europe
        map.put("340-940", 0.1); // Droit - Histoire de l'Europe
        map.put("350-940", 0.1); // Administration publique - Histoire de l'Europe
        map.put("355-940", 0.2); // Art et science militaires - Histoire de l'Europe
        map.put("360-940", 0.1); // Problèmes et services sociaux. Criminologie - Histoire de l'Europe
        map.put("370-940", 0.2); // Education - Histoire de l'Europe
        map.put("390-940", 0.2); // Ethnonymes - Histoire de l'Europe
        map.put("391-940", 0.2); // Anthropologie. Ethnologie - Histoire de l'Europe
        map.put("400-940", 0.1); // Langues - Histoire de l'Europe
        map.put("401-940", 0.0); // Linguistique générale - Histoire de l'Europe
        map.put("500-940", 0.0); // Sciences - Histoire de l'Europe
        map.put("510-940", 0.0); // Mathématiques - Histoire de l'Europe
        map.put("520-940", 0.0); // Astronomie - Histoire de l'Europe
        map.put("530-940", 0.0); // Physique - Histoire de l'Europe
        map.put("540-940", 0.0); // Chimie - Histoire de l'Europe
        map.put("550-940", 0.0); // Sciences de la Terre - Histoire de l'Europe
        map.put("560-940", 0.0); // Paléontologie - Histoire de l'Europe
        map.put("570-940", 0.0); // Biologie - Histoire de l'Europe
        map.put("577-940", 0.0); // Ecologie - Histoire de l'Europe
        map.put("579-940", 0.0); // Biologie des procaryotes - Histoire de l'Europe
        map.put("580-940", 0.0); // Botanique - Histoire de l'Europe
        map.put("590-940", 0.0); // Zoologie - Histoire de l'Europe
        map.put("600-940", 0.0); // Technique - Histoire de l'Europe
        map.put("610-940", 0.0); // Médecine - Histoire de l'Europe
        map.put("615-940", 0.0); // Pharmacie - Histoire de l'Europe
        map.put("620-940", 0.0); // Sciences de l'ingénieur - Histoire de l'Europe
        map.put("621-940", 0.0); // Informatique - Histoire de l'Europe
        map.put("630-940", 0.0); // Agriculture - Histoire de l'Europe
        map.put("640-940", 0.0); // Economie domestique. Cuisine - Histoire de l'Europe
        map.put("650-940", 0.0); // Gestion - Histoire de l'Europe
        map.put("690-940", 0.0); // Construction - Histoire de l'Europe
        map.put("700-940", 0.2); // Art - Histoire de l'Europe
        map.put("710-940", 0.1); // Urbanisme. Architecture du paysage - Histoire de l'Europe
        map.put("720-940", 0.2); // Architecture - Histoire de l'Europe
        map.put("730-940", 0.1); // Sculpture - Histoire de l'Europe
        map.put("740-940", 0.0); // Dessin. Arts décoratifs - Histoire de l'Europe
        map.put("750-940", 0.2); // Peinture - Histoire de l'Europe
        map.put("760-940", 0.0); // Arts graphiques - Histoire de l'Europe
        map.put("770-940", 0.0); // Photographie - Histoire de l'Europe
        map.put("780-940", 0.1); // Musique - Histoire de l'Europe
        map.put("790-940", 0.0); // Arts du spectacle - Histoire de l'Europe
        map.put("791-940", 0.0); // Audiovisuel - Histoire de l'Europe
        map.put("793-940", 0.0); // Sports et jeux - Histoire de l'Europe
        map.put("800-940", 0.0); // Littératures - Histoire de l'Europe
        map.put("801-940", 0.0); // Littérature générale - Histoire de l'Europe
        map.put("900-940", 0.9); // Histoire - Histoire de l'Europe
        map.put("910-940", 0.1); // Géographie - Histoire de l'Europe
        map.put("912-940", 0.0); // Géographie de la France - Histoire de l'Europe
        map.put("914-940", 0.2); // Géographie de l'Europe - Histoire de l'Europe
        map.put("915-940", 0.0); // Géographie du reste du monde - Histoire de l'Europe
        map.put("930-940", 0.2); // Archéologie Préhistoire Histoire ancienne - Histoire de l'Europe
        map.put("940-940", 1.0); // Histoire de l'Europe - Histoire de l'Europe
        map.put("0-944", 0.4); // Savoir et érudition. Musées - Histoire de la France
        map.put("20-944", 0.0); // Sciences de l'information et de la documentation - Histoire de la France
        map.put("100-944", 0.0); // Philosophie - Histoire de la France
        map.put("130-944", 0.0); // Esotérisme - Histoire de la France
        map.put("150-944", 0.0); // Psychologie - Histoire de la France
        map.put("200-944", 0.2); // Religion - Histoire de la France
        map.put("300-944", 0.1); // Sciences sociales. Sociologie - Histoire de la France
        map.put("304-944", 0.1); // Démographie - Histoire de la France
        map.put("305-944", 0.2); // Catégories de personnes - Histoire de la France
        map.put("320-944", 0.2); // Science politique - Histoire de la France
        map.put("330-944", 0.2); // Economie politique. Travail - Histoire de la France
        map.put("340-944", 0.1); // Droit - Histoire de la France
        map.put("350-944", 0.1); // Administration publique - Histoire de la France
        map.put("355-944", 0.2); // Art et science militaires - Histoire de la France
        map.put("360-944", 0.1); // Problèmes et services sociaux. Criminologie - Histoire de la France
        map.put("370-944", 0.2); // Education - Histoire de la France
        map.put("390-944", 0.2); // Ethnonymes - Histoire de la France
        map.put("391-944", 0.2); // Anthropologie. Ethnologie - Histoire de la France
        map.put("400-944", 0.1); // Langues - Histoire de la France
        map.put("401-944", 0.0); // Linguistique générale - Histoire de la France
        map.put("500-944", 0.0); // Sciences - Histoire de la France
        map.put("510-944", 0.0); // Mathématiques - Histoire de la France
        map.put("520-944", 0.0); // Astronomie - Histoire de la France
        map.put("530-944", 0.0); // Physique - Histoire de la France
        map.put("540-944", 0.0); // Chimie - Histoire de la France
        map.put("550-944", 0.0); // Sciences de la Terre - Histoire de la France
        map.put("560-944", 0.0); // Paléontologie - Histoire de la France
        map.put("570-944", 0.0); // Biologie - Histoire de la France
        map.put("577-944", 0.0); // Ecologie - Histoire de la France
        map.put("579-944", 0.0); // Biologie des procaryotes - Histoire de la France
        map.put("580-944", 0.0); // Botanique - Histoire de la France
        map.put("590-944", 0.0); // Zoologie - Histoire de la France
        map.put("600-944", 0.0); // Technique - Histoire de la France
        map.put("610-944", 0.0); // Médecine - Histoire de la France
        map.put("615-944", 0.0); // Pharmacie - Histoire de la France
        map.put("620-944", 0.0); // Sciences de l'ingénieur - Histoire de la France
        map.put("621-944", 0.0); // Informatique - Histoire de la France
        map.put("630-944", 0.0); // Agriculture - Histoire de la France
        map.put("640-944", 0.0); // Economie domestique. Cuisine - Histoire de la France
        map.put("650-944", 0.0); // Gestion - Histoire de la France
        map.put("690-944", 0.0); // Construction - Histoire de la France
        map.put("700-944", 0.2); // Art - Histoire de la France
        map.put("710-944", 0.1); // Urbanisme. Architecture du paysage - Histoire de la France
        map.put("720-944", 0.2); // Architecture - Histoire de la France
        map.put("730-944", 0.1); // Sculpture - Histoire de la France
        map.put("740-944", 0.0); // Dessin. Arts décoratifs - Histoire de la France
        map.put("750-944", 0.2); // Peinture - Histoire de la France
        map.put("760-944", 0.0); // Arts graphiques - Histoire de la France
        map.put("770-944", 0.0); // Photographie - Histoire de la France
        map.put("780-944", 0.1); // Musique - Histoire de la France
        map.put("790-944", 0.0); // Arts du spectacle - Histoire de la France
        map.put("791-944", 0.0); // Audiovisuel - Histoire de la France
        map.put("793-944", 0.0); // Sports et jeux - Histoire de la France
        map.put("800-944", 0.0); // Littératures - Histoire de la France
        map.put("801-944", 0.0); // Littérature générale - Histoire de la France
        map.put("900-944", 0.9); // Histoire - Histoire de la France
        map.put("910-944", 0.1); // Géographie - Histoire de la France
        map.put("912-944", 0.2); // Géographie de la France - Histoire de la France
        map.put("914-944", 0.0); // Géographie de l'Europe - Histoire de la France
        map.put("915-944", 0.0); // Géographie du reste du monde - Histoire de la France
        map.put("930-944", 0.2); // Archéologie Préhistoire Histoire ancienne - Histoire de la France
        map.put("940-944", 0.4); // Histoire de l'Europe - Histoire de la France
        map.put("944-944", 1.0); // Histoire de la France - Histoire de la France
        map.put("0-950", 0.4); // Savoir et érudition. Musées - Histoire du reste du monde
        map.put("20-950", 0.0); // Sciences de l'information et de la documentation - Histoire du reste du monde
        map.put("100-950", 0.0); // Philosophie - Histoire du reste du monde
        map.put("130-950", 0.0); // Esotérisme - Histoire du reste du monde
        map.put("150-950", 0.0); // Psychologie - Histoire du reste du monde
        map.put("200-950", 0.2); // Religion - Histoire du reste du monde
        map.put("300-950", 0.1); // Sciences sociales. Sociologie - Histoire du reste du monde
        map.put("304-950", 0.1); // Démographie - Histoire du reste du monde
        map.put("305-950", 0.2); // Catégories de personnes - Histoire du reste du monde
        map.put("320-950", 0.2); // Science politique - Histoire du reste du monde
        map.put("330-950", 0.2); // Economie politique. Travail - Histoire du reste du monde
        map.put("340-950", 0.1); // Droit - Histoire du reste du monde
        map.put("350-950", 0.1); // Administration publique - Histoire du reste du monde
        map.put("355-950", 0.2); // Art et science militaires - Histoire du reste du monde
        map.put("360-950", 0.1); // Problèmes et services sociaux. Criminologie - Histoire du reste du monde
        map.put("370-950", 0.2); // Education - Histoire du reste du monde
        map.put("390-950", 0.2); // Ethnonymes - Histoire du reste du monde
        map.put("391-950", 0.2); // Anthropologie. Ethnologie - Histoire du reste du monde
        map.put("400-950", 0.1); // Langues - Histoire du reste du monde
        map.put("401-950", 0.0); // Linguistique générale - Histoire du reste du monde
        map.put("500-950", 0.0); // Sciences - Histoire du reste du monde
        map.put("510-950", 0.0); // Mathématiques - Histoire du reste du monde
        map.put("520-950", 0.0); // Astronomie - Histoire du reste du monde
        map.put("530-950", 0.0); // Physique - Histoire du reste du monde
        map.put("540-950", 0.0); // Chimie - Histoire du reste du monde
        map.put("550-950", 0.0); // Sciences de la Terre - Histoire du reste du monde
        map.put("560-950", 0.0); // Paléontologie - Histoire du reste du monde
        map.put("570-950", 0.0); // Biologie - Histoire du reste du monde
        map.put("577-950", 0.0); // Ecologie - Histoire du reste du monde
        map.put("579-950", 0.0); // Biologie des procaryotes - Histoire du reste du monde
        map.put("580-950", 0.0); // Botanique - Histoire du reste du monde
        map.put("590-950", 0.0); // Zoologie - Histoire du reste du monde
        map.put("600-950", 0.0); // Technique - Histoire du reste du monde
        map.put("610-950", 0.0); // Médecine - Histoire du reste du monde
        map.put("615-950", 0.0); // Pharmacie - Histoire du reste du monde
        map.put("620-950", 0.0); // Sciences de l'ingénieur - Histoire du reste du monde
        map.put("621-950", 0.0); // Informatique - Histoire du reste du monde
        map.put("630-950", 0.0); // Agriculture - Histoire du reste du monde
        map.put("640-950", 0.0); // Economie domestique. Cuisine - Histoire du reste du monde
        map.put("650-950", 0.0); // Gestion - Histoire du reste du monde
        map.put("690-950", 0.0); // Construction - Histoire du reste du monde
        map.put("700-950", 0.2); // Art - Histoire du reste du monde
        map.put("710-950", 0.1); // Urbanisme. Architecture du paysage - Histoire du reste du monde
        map.put("720-950", 0.2); // Architecture - Histoire du reste du monde
        map.put("730-950", 0.1); // Sculpture - Histoire du reste du monde
        map.put("740-950", 0.0); // Dessin. Arts décoratifs - Histoire du reste du monde
        map.put("750-950", 0.2); // Peinture - Histoire du reste du monde
        map.put("760-950", 0.0); // Arts graphiques - Histoire du reste du monde
        map.put("770-950", 0.0); // Photographie - Histoire du reste du monde
        map.put("780-950", 0.1); // Musique - Histoire du reste du monde
        map.put("790-950", 0.0); // Arts du spectacle - Histoire du reste du monde
        map.put("791-950", 0.0); // Audiovisuel - Histoire du reste du monde
        map.put("793-950", 0.0); // Sports et jeux - Histoire du reste du monde
        map.put("800-950", 0.0); // Littératures - Histoire du reste du monde
        map.put("801-950", 0.0); // Littérature générale - Histoire du reste du monde
        map.put("900-950", 0.9); // Histoire - Histoire du reste du monde
        map.put("910-950", 0.1); // Géographie - Histoire du reste du monde
        map.put("912-950", 0.0); // Géographie de la France - Histoire du reste du monde
        map.put("914-950", 0.0); // Géographie de l'Europe - Histoire du reste du monde
        map.put("915-950", 0.2); // Géographie du reste du monde - Histoire du reste du monde
        map.put("930-950", 0.2); // Archéologie Préhistoire Histoire ancienne - Histoire du reste du monde
        map.put("940-950", 0.4); // Histoire de l'Europe - Histoire du reste du monde
        map.put("944-950", 0.4); // Histoire de la France - Histoire du reste du monde
        map.put("950-950", 1.0); // Histoire du reste du monde - Histoire du reste du monde
        return map;
    }

    public static double delta(int domain1, int domain2, Map<String, Double> domainMap) {
        String key;
        if (domain1 > domain2) {
            key = "" + domain2 + "-" + domain1;
        } else {
            key = "" + domain1 + "-" + domain2;
        }
        Double ret = domainMap.get(key);
        return ret == null ? 0. : ret;
    }

}
