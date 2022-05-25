package fr.abes.qualinka.sudoc.feature.abstracts;

import org.json.JSONArray;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;


public abstract class AbstractUniqueValueFromArrayFeature<T> implements PreprocessedFeature<JSONArray, T> {
	
	@SuppressWarnings("unchecked")
	@Override
	public T buildValue(JSONArray rawValue) {
		if(rawValue.isEmpty()) {
			return null;
		} else {
			return (T) rawValue.get(0);
		}
	}

}
