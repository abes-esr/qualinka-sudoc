package fr.abes.qualinka.example.feature.computed;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.UpdateableComputedFeature;

public class PublicationDateComputedFeature implements UpdateableComputedFeature<JSONObject> {

	@Override
	public Set<String> getRelatedFeatures() {
		return Collections.singleton("publicationDate");
	}

	@Override
	public JSONObject compute(Collection<JSONObject> selectedData) {
		return update(null, selectedData);
	}

	@Override
	public JSONObject update(JSONObject oldValue, Collection<JSONObject> newlySelectedData) {
		JSONObject value = oldValue;
		if(value == null) {
			value = new JSONObject();
		}
		for(JSONObject o: newlySelectedData) {
			if(o.has("publicationDate")) {
				int date = o.optInt("publicationDate");
				if(!value.has("min") || value.optInt("min") > date) {
					value.put("min", date);
				}
				if(!value.has("max") || value.optInt("max") < date) {
					value.put("max", date);
				}
			}
		}
		return value;
	}

	

}
