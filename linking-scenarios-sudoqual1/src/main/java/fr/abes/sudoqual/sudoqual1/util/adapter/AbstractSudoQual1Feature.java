package fr.abes.sudoqual.sudoqual1.util.adapter;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.rule_engine.feature.PreprocessedFeature;
import fr.abes.sudoqual.util.json.JSONArrays;

public abstract class AbstractSudoQual1Feature<T> implements PreprocessedFeature<JSONArray, Object> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractSudoQual1Feature.class);

	private boolean monoValue;

	public AbstractSudoQual1Feature(boolean monoValue) {
		this.monoValue = monoValue;
	}
	
	@Override
	public Object buildValue(JSONArray value) {
		assert value instanceof JSONArray: "the argument value must be a JSONArray";
		
		JSONArray res = new JSONArray();
		JSONArray array = (JSONArray) value;
		for(Object o : array) {
			try {
				Object v = process((T)o);
				if(v != null) {
    				if(monoValue && res.length() > 0 && !JSONArrays.contains(res, v)) {
    					if(logger.isWarnEnabled()) {
    						logger.warn("A single value is expected for feature {}", getKey());
    					}
    				} else {
    					res.put(v);
    				}
				}
			} catch (ClassCastException e) {
				if(logger.isWarnEnabled()) {
					logger.warn("'{}' of type {} is not a correct value for {}",o, o.getClass(), getKey());
				}
			}
		}

		return res;
	}
	
	protected abstract Object process(T json);
	
	@Override
	public boolean checkValue(Object o) {
		return o instanceof JSONArray;
	}
}
