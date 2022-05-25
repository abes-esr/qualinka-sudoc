package fr.abes.qualinka.sudoqual1.util.adapter;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.Reference;

public class JSONObjectReferenceAdapter implements IReference {

	private static final String URI_KEY = "uri";
	private JSONObject object;
	private long id;

	public JSONObjectReferenceAdapter(JSONObject object) {
		this.object = object;
		this.id = Reference.nameToId(object.getString(URI_KEY));
	}
	

	@Override
	public long getId() {
		return this.id;
	}


	@Override
	public JSONObject getAttributes() {
		return object;
	}



	@Override
	public Object getUniqueValue(String attrName) {
		JSONArray array = getValue(attrName);
		if(array != null && array.length() > 0) {
			return  array.get(0);
		}
		return null;
	}


	@Override
	public JSONArray getValue(String attrName) {
		if(this.object.has(attrName)) {
			return this.object.getJSONArray(attrName);
		}
		return new JSONArray();
	}





}
