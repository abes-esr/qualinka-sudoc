package fr.abes.qualinka.these.thesedir.filter;

import fr.abes.sudoqual.rule_engine.predicate.Filter;
import org.json.JSONObject;

import java.util.Set;

public class IsAuthorThesis implements Filter {

	@Override
	public boolean check(JSONObject ra) {
		return ra.optBoolean("isAuthorThesis");
	}

	@Override
	public Set<String> featureSet() {
		return Set.of("isAuthorThesis");
	}

}
