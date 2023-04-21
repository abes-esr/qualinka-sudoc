package fr.abes.qualinka.these.thesedir.feature;

import fr.abes.sudoqual.rule_engine.feature.UpdateableComputedFeature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;

public class ThesisDefenseDateFeature implements UpdateableComputedFeature<Integer> {

	private static final Logger logger = LoggerFactory.getLogger(ThesisDefenseDateFeature.class);

	@Override
	public Integer compute(Collection<JSONObject> selectedData) {
		return update(null, selectedData);
	}

	@Override
	public Set<String> getRelatedFeatures() {
		return Set.of("role","docType","pubDate");
	}

	@Override
	public Integer update(Integer oldValue, Collection<JSONObject> newlySelectedData) {
		Integer date = oldValue;

		for (JSONObject json : newlySelectedData)
		{
			if(json.has("role") && json.has("docType") && json.has("pubDate"))
			{
				JSONArray roleArray = (JSONArray) json.get("role");
				JSONArray docTypeArray = (JSONArray) json.get("docType");
				JSONArray pubDateArray = (JSONArray) json.get("pubDate");
								
				if (roleArray.length() > 0 && docTypeArray.length() > 0 && pubDateArray.length() > 0) 
				{
					String role = roleArray.getJSONObject(0).getString("role");
					
					if ("author".equals(role) && "thesis".equals(docTypeArray.get(0))) {
						
						Integer dateN =  pubDateArray.getInt(0);
						if (date == null || dateN<date)
							{date = dateN;}
					}
				}
			}
		}
		return date;
	}
}
