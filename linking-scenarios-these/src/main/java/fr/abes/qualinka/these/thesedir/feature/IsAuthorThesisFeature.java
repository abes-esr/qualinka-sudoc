package fr.abes.qualinka.these.thesedir.feature;

import fr.abes.sudoqual.rule_engine.feature.ComputedFeature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;

public class IsAuthorThesisFeature implements ComputedFeature<Boolean> {

	private static final Logger logger = LoggerFactory.getLogger(IsAuthorThesisFeature.class);

	@Override
	public Boolean compute(Collection<JSONObject> selectedData) {
		
		for (JSONObject json : selectedData)
		{
			if(json.has("role") && json.has("docType"))
			{
				JSONArray roleArray = (JSONArray) json.get("role");
				JSONArray docTypeArray = (JSONArray) json.get("docType");
				
				if (roleArray.length() > 0 && docTypeArray.length() > 0) 
				{
					String role = roleArray.getJSONObject(0).getString("role");
					if ("author".equals(role) && "thesis".equals(docTypeArray.get(0))) {
						
						return true;
					}
				}
				
			}
		}
		return false;
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return Set.of("role","docType");
	}
}
