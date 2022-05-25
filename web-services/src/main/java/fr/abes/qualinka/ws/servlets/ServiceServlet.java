package fr.abes.qualinka.ws.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import fr.abes.sudoqual.util.Strings;
import fr.abes.sudoqual.util.json.JSONObjects;
import fr.abes.sudoqual.util.json.JSONValidationReport;
import fr.abes.qualinka.ws.Context;
import fr.abes.qualinka.ws.InputValidator;
import fr.abes.qualinka.ws.jobs.JobExecution;
import fr.abes.qualinka.ws.jobs.Service;

/**
 * This servlet manages all available services.
 * 
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public class ServiceServlet extends HttpServlet {

	private static final long serialVersionUID = 688165805908792020L;

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPut(request, response);
	}
    
    @Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
        	JobExecution job = handle(request, response);
        	if(job != null) {
            	switch(job.getState()) {
        			case PENDING:
        			case IN_PROGRESS:
                		response.setHeader("Location", ServletUtils.relativizePath(request, Context.JOB_MANAGER.getPathToJobStatus(job.getId())));
                		ServletUtils.sendResponse(response, 202);
                		break;
        			case DONE:
        				response.setStatus(303);
                		response.setHeader("Location", ServletUtils.relativizePath(request, Context.JOB_MANAGER.getPathToResult(job.getId())));
                		ServletUtils.sendResponse(response, 303);
                		break;
        			case CANCELLED:
        			case FAIL:
        				ServletUtils.sendError(response, 500, "The job execution failed.", job.getErrorMessage());
            			break;
        		}
        	}
    	} catch (Exception e) {
    		ServletUtils.sendError(response, 500, "An error occurred during the request handling.", e.getMessage());
    	}
    }

	private static JobExecution handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    		String servletPath = request.getServletPath();
        	String service = servletPath.substring(servletPath.lastIndexOf('/') + 1);
    		String input = Strings.toString(request.getInputStream());
    		
    		// Input validation
    		JSONObject parsedInput;
        	try {
        		parsedInput = JSONObjects.from(input);
        	} catch (JSONException e) {
        		ServletUtils.sendError(response, 400, "malformed json input.", e.getMessage());
        		return null;
        	}
        	JSONValidationReport report = InputValidator.INSTANCE.validate(service, parsedInput);    	
        	if(!report.isSuccess()) {
        		ServletUtils.sendError(response, 400, "Json input does not fulfill requirements.", report.getErrorMessage());
        		return null;
        	}
        	
        	Service registeredService = Context.JOB_MANAGER.getRegisteredService(service);
        	if(registeredService == null) {
        		throw new Exception("The requested service " + service + " is not available.");
        	}
        	
        	try {
        		parsedInput = registeredService.prepare(parsedInput);
        	} catch(IllegalArgumentException e) {
        		ServletUtils.sendError(response, 400, "Some requirements are not fulfilled.", e.getMessage());
        		return null;
        	}
        	
    		// use parsedInput.toString() instead of input to normalize the JSON representation
        	return Context.JOB_MANAGER.getOrCreate(service, parsedInput.toString());
	}

}
