package fr.abes.qualinka.sudoc.feature.rc.derived;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.qualinka.sudoc.feature.rc.RoleFeature;
import fr.abes.qualinka.sudoc.util.RoleUtils;

/**
 * Liste des groupes associés aux rôles de la RC. <br/>
 * 
 * Attribut construit à partir de l'attribut role. 
 * 
 * @see RoleFeature
 */
public class RoleGroupFeature extends PreprocessedArrayFeature<String, String> {

	
	@Override
	protected String buildElementValue(String role) {
		String grp = RoleUtils.group(role);
		if(grp == null || grp.equals("unspecific_intellectual_contribution") || grp.equals("neutral") || grp.equals("none")) {
			return null;
		}
		return grp;
	}
	
	public String getRelatedRawFeature() {
		return "role";
	}

}
