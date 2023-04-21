package fr.abes.qualinka.these.filter;

import fr.abes.sudoqual.rule_engine.predicate.Filter;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Set;

public class RcFilter implements Filter {

	public RcFilter() {
	}

	@Override
	public Set<String> featureSet() {
		return Collections.emptySet();
	}

	public String getName() {
		return "rc";
	}

	@Override
	public boolean check(JSONObject data) {
		return data.getString("uri").startsWith("http://www.sudoc.abes.fr/");
	}

}
