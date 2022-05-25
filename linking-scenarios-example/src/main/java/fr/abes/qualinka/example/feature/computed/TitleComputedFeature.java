package fr.abes.qualinka.example.feature.computed;

import java.util.Collection;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.feature.UpdateableComputedFeature;

public class TitleComputedFeature implements UpdateableComputedFeature<JSONArray> {

	@Override
	public Set<String> getRelatedFeatures() {
		return Set.of("title");
	}

	@Override
	public JSONArray compute(Collection<JSONObject> selectedData) {
		return update(null, selectedData);
	}



	@Override
	public JSONArray update(JSONArray array, Collection<JSONObject> newlySelectedData) {
		if(array == null) {
			array = new JSONArray();
		}
		for(JSONObject o: newlySelectedData) {
			if(o.has("title")) {
				array.put(o.optString("title"));
			}
		}
		return array;
	}
	
}
