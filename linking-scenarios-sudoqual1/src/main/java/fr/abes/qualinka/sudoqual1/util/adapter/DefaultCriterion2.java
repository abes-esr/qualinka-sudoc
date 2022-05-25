package fr.abes.qualinka.sudoqual1.util.adapter;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * The Class DefaultCriterion.
 */
public abstract class DefaultCriterion2 implements Criterion {
	
	/**  The name of the Criterion. */
	private String name;
	private DiscretCompType compType;

	protected Set<String> attrNamesFirst=new HashSet<String>();
	protected Set<String> attrNamesSecond=new HashSet<String>();
	
	/**
	 * Instantiates a new default criterion.
	 *
	 * @param name the name
	 * @param compType the comp type
	 * @param attrNames the attr names
	 */
	public DefaultCriterion2(String name,DiscretCompType compType, String[] attrFirst,String[] attrSecond){
		this.name=name;
		this.compType = compType;
		for(String attr:attrFirst) {
			attrNamesFirst.add(attr);
		}
		for(String attr:attrSecond) {
			attrNamesSecond.add(attr);
		}
	}
	
	@Override
	public String getKey() {
		return name;
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return attrNamesFirst;
	}
	
	@Override
	public Set<String> targetFeatureSet() {
		return attrNamesSecond;
	}
	
	@Override
	public int compare(JSONObject ref1, JSONObject ref2) {
		return this.compare(new JSONObjectReferenceAdapter(ref1), new JSONObjectReferenceAdapter(ref2));
	}
	

	@Override
	public DiscretCompType getComparisonType() {
		return this.compType;
	}
	
	
	protected abstract int compare(IReference ref1, IReference ref2);

}