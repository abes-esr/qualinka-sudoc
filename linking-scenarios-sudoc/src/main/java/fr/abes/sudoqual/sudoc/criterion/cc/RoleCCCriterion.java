/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.cc;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;


public class RoleCCCriterion implements Criterion {
    
	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, -2, true, 2, false);

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("filteredRole","roleGroup");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("filteredRole","roleGroup");
	}
	
	@Override
    public int compare(JSONObject source, JSONObject target) {
		if(!source.has("filteredRole") || !target.has("filteredRole")) {
            return DiscretCompType.NOT_COMPARABLE;
		}
    	String roleRc1 = (String) source.getJSONArray("filteredRole").optString(0);
    	String roleRc2 = (String) target.getJSONArray("filteredRole").optString(0);

        if (roleRc1.isBlank() || roleRc2.isBlank()) {
            return DiscretCompType.NOT_COMPARABLE;
        }

        if (roleRc1.equals(roleRc2)) {
            return 2;
        }
        
        if(source.has("roleGroup") && target.has("roleGroup")) {
	        String roleGrpRc1 = (String) source.getJSONArray("roleGroup").optString(0);
	        String roleGrpRc2 = (String) target.getJSONArray("roleGroup").optString(0);
	
	        if (!roleGrpRc1.isBlank() && !roleGrpRc2.isBlank()
		        && roleGrpRc1.equals(roleGrpRc2)) {
		        return 1;
		    }
        }

        return DiscretCompType.NEUTRAL;
    }
    
    @Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

}
