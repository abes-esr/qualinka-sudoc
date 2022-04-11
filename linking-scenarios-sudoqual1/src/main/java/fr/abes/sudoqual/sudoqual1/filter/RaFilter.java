package fr.abes.sudoqual.sudoqual1.filter;

import java.util.Collections;
import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;

public class RaFilter implements Filter {

	public RaFilter() {
	}

	@Override
	public Set<String> featureSet() {
		return Collections.emptySet();
	}
	
	@Override
	public String getKey() {
		return "ra";
	}

	@Override
	public boolean check(JSONObject data) {
		return data.getString("uri").startsWith("http://www.idref.fr/");
	}

}
