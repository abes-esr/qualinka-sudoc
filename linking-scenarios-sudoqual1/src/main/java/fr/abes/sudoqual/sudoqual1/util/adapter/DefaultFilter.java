package fr.abes.sudoqual.sudoqual1.util.adapter;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.predicate.Filter;

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
	
	@Override
	public String getKey() {
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
