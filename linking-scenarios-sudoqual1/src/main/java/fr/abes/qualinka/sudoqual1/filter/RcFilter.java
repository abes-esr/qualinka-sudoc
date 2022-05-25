package fr.abes.qualinka.sudoqual1.filter;

import java.util.Collections;
import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;

public class RcFilter implements Filter {

	public RcFilter() {
	}

	@Override
	public Set<String> featureSet() {
		return Collections.emptySet();
	}
	
	@Override
	public String getKey() {
		return "rc";
	}

	@Override
	public boolean check(JSONObject data) {
		return data.getString("uri").startsWith("http://www.sudoc.abes.fr/");
	}

}
