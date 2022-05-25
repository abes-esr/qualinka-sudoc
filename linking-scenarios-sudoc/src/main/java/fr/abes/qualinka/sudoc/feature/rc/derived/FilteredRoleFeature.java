package fr.abes.qualinka.sudoc.feature.rc.derived;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedArrayFeature;
import fr.abes.qualinka.sudoc.feature.rc.RoleFeature;
import fr.abes.qualinka.sudoc.util.RoleUtils;

/**
 * Liste de role filtrée, construit à partir de l'attribut role. 
 *
 * @see RoleFeature
 */
public class FilteredRoleFeature extends PreprocessedArrayFeature<String, String> {

	
	@Override
	protected String buildElementValue(String role) {
		String grp = RoleUtils.group(role);
		if(grp != null && (grp.equals("research") || grp.equals("unspecific_intellectual_contribution") || grp.equals("neutral"))) {
			return null;
		}
		return role;
	}
	
	@Override
	public String getRelatedRawFeature() {
		return "role";
	}

}
