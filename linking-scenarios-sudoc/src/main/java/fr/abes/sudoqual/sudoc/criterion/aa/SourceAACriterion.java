/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.abes.sudoqual.sudoc.criterion.aa;

import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;
import fr.abes.sudoqual.sudoc.util.RegexUtils;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 * Attributs utilisés : <br/>
 * * source: "source"<br/>
 * * target: "source"<br/>
 * <br/>
 * 
 * Stratégie : <br/>
 * - on extrait le titre et la date de publication des deux sources (si c'est possible) <br/>
 * - on compare les titres extraits avec Levenhstein <br/>
 * - on test l'égalité entre les dates extraites <br/>
 * <br/>
 * 
 * 
 * Valeurs possibles : <br/>
 * * always: titre proche et date exacte <br/>
 * * 3: titre proche et date proche <br/>
 * * 2: titre proche et pas de date <br/>
 * * 1: titre proche et date éloigné <br/>
 * * neutral: pas de correspondance du titre <br/>
 */
public class SourceAACriterion implements Criterion {

	private static final DiscretCompType COMP_TYPE = new DiscretCompTypeImpl(false, 0, true, 3, true);

	@Override
	public DiscretCompType getComparisonType() {
		return COMP_TYPE;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("source");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("source");
	}

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("source") || !target.has("source")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		JSONArray sourceSourceString = source.optJSONArray("source");
		JSONArray sourceTargetString = target.optJSONArray("source");

		
		for (Object s : sourceSourceString) {
			for (Object t : sourceTargetString) {
    			JSONObject sourceJSONObject = (JSONObject) s;
    			JSONObject targetJSONObject = (JSONObject) t;
    			    
    			String sourceTitle = sourceJSONObject.getString("title");
    			String targetTitle = targetJSONObject.getString("title");
    			double simTitle = new Levenshtein().getSimilarity(sourceTitle, targetTitle); 
    
    
    			boolean hasDate = false;
    			int sourcePubYear = 0;
    			int targetPubYear = 0;
    			if(sourceJSONObject.has("pubYear") && targetJSONObject.has("pubYear")) {
    				hasDate = true;
    				sourcePubYear = sourceJSONObject.getInt("pubYear");
        			targetPubYear = targetJSONObject.getInt("pubYear");
    			}
    
    			if (simTitle > 0.8) {
    				if(!hasDate) {
    					return 2; // title but no date
    				}
    				int delta = Math.abs(sourcePubYear - targetPubYear);
    				if(delta == 0) {
    					return DiscretCompType.ALWAYS; // title and date ok
    				} else if (delta <= 3) {
    					return 3; // title and near date 
    				} else {
    					return 1; // title and distant date
    				}
    			}
    		}
		}
		return DiscretCompType.NEUTRAL;
	}
}
