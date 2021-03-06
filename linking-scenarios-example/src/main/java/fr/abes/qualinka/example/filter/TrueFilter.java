package fr.abes.qualinka.example.filter;

import java.util.Collections;
import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;

public class TrueFilter implements Filter {

	@Override
	public Set<String> featureSet() {
		return Collections.emptySet();
	}
	
	@Override
	public String getKey() {
		return "trueF";
	}

	@Override
	public boolean check(JSONObject data) {
		return true;
	}

}
