package fr.abes.qualinka.sudoc.feature.rc.derived;

import org.json.JSONArray;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;
import fr.abes.qualinka.sudoc.feature.rc.RoleFeature;
import fr.abes.qualinka.sudoc.util.RoleUtils;

/**
 * Attribut booléen construit à partir de l'attribut role. Cette attribut vaut
 * "true" si et seulement si la rc possède un rôle de type "auteur".
 * 
 * @see RoleFeature
 */
public class isAuthorFeature implements PreprocessedFeature<JSONArray, Boolean> {
	
	@Override
	public Boolean buildValue(JSONArray rawValue) {
		if(rawValue.isEmpty()) {
			return null;
		}
		for(Object o: rawValue) {
			if(RoleUtils.isAuthor((String)o)) {
				return true;
			}
		}
		return false;
	}
	
	public String getRelatedRawFeature() {
		return "role";
	}

	

}
