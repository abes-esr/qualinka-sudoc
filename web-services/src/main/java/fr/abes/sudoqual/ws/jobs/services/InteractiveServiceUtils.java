package fr.abes.sudoqual.ws.jobs.services;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.abes.sudoqual.util.json.JSONObjects;
import fr.abes.sudoqual.ws.Context;
import fr.abes.sudoqual.ws.jobs.JobExecution;

public final class InteractiveServiceUtils {
	
	private InteractiveServiceUtils() {
		
	}
	
	public static JSONObject prepare(JSONObject input, String serviceName) throws Exception, IllegalArgumentException {
		// handle options.interactif
		if(input.has("options")) {
			JSONObject options = input.getJSONObject("options");
			if(options.has("interactif") && options.getBoolean("interactif")) {
				options.put("exportCriterionValues", true);
				options.remove("interactif");
			}
		}
			
		// handle interactive request
		if(input.has("interactif")) {
			return handleInteractiveMode(input, serviceName);
		}
		
		return input;
	}
	
	private static JSONObject handleInteractiveMode(JSONObject input, String serviceName) throws IOException {
		JSONObject interactif = input.getJSONObject("interactif");
		
		// Recovery related input
		String hexaname = interactif.getString("jobId");
		JobExecution jobExecution = Context.JOB_MANAGER.get(hexaname);
		if(jobExecution == null) {
			throw new IllegalArgumentException("The related job " + hexaname + " does not exist");
		}
		JobExecution.State state = jobExecution.getState();
		
		// Check requirements over related input
		if(!state.equals(JobExecution.State.DONE)) {
			throw new IllegalArgumentException("The related job " + hexaname + " is not in a valid state: " + state);
		}
		if(!serviceName.equals(jobExecution.getJob().getServiceName())) {
			throw new IllegalArgumentException("The related job " + hexaname + " was computed with an other service: " + jobExecution.getJob().getServiceName());
		}
		String oldInput = Context.JOB_MANAGER.getInput(hexaname);
		if(oldInput.isEmpty()) {
			throw new IOException("Error when reading the input of the related job " + hexaname);
		}
		
		// Recovery related result
		String result = Context.JOB_MANAGER.getResult(hexaname);
		if(result.isEmpty()) {
			throw new IOException("Error when reading the result of the related job " + hexaname);
		}
		
		// Check requirements over related results
		JSONObject resultJSON = JSONObjects.from(result);
		if(!resultJSON.has("criterionValues")) {
			throw new IllegalArgumentException("The related job " + hexaname + " was not launch in the interactive mode");
		}
		
		// Constructs new input from the previous one
		JSONObject newInputJSON = JSONObjects.from(oldInput);
		JSONArray arrayNewSafeLinks = interactif.getJSONArray("newSafeLinks");
		for(Object link : arrayNewSafeLinks) {
			newInputJSON.append("safeLinks", link);
		}
		
		newInputJSON.remove("criterionValues");
		newInputJSON.put("criterionValues", resultJSON.get("criterionValues"));
		JSONArray computedSafeLinks = resultJSON.getJSONArray("computedLinks");
		for(Object object : computedSafeLinks) {
			JSONObject link = (JSONObject) object;
			String type = link.getString("type");
			if("sameAs".equals(type) || "diffFrom".equals(type)) {
				JSONObject safeLink = new JSONObject();
				safeLink.put("source", link.getString("source"));
				safeLink.put("target", link.getString("target"));
				safeLink.put("type", link.getString("type"));
				newInputJSON.append("safeLinks", safeLink);
			}
		}
		
		return newInputJSON;
	}


}
