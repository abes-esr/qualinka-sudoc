package fr.abes.qualinka.these.util.adapter;

import fr.abes.sudoqual.rule_engine.predicate.Filter;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public abstract class DefaultFilter implements Filter {

	private String name;
	private Set<String> attrNames;

	public DefaultFilter(String name, String[] attrNames){
		this.name=name;
		this.attrNames = new HashSet<>();
		for(String attr:attrNames) {
			this.attrNames.add(attr);
		}
	}

	public String getName() {
		return this.name;
	}
	
	@Override
	public Set<String> featureSet() {
		return this.attrNames;
	}

	@Override
	public boolean check(JSONObject data) {
		return this.check(new JSONObjectReferenceAdapter(data));
	}
	
	protected abstract boolean check(IReference ref);

}
