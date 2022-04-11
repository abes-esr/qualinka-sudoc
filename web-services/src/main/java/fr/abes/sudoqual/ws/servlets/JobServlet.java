package fr.abes.sudoqual.ws.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import fr.abes.sudoqual.ws.Context;
import fr.abes.sudoqual.ws.jobs.JobExecution;

/**
 * This servlet manages job execution
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public class JobServlet extends HttpServlet {	

	private static final long serialVersionUID = 2707085110173248718L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
    		JobExecution job = null;
    		try {
        		job = getJobExecution(request.getPathInfo());
        	} catch (IllegalArgumentException e) {
        		ServletUtils.sendError(response, 400, "The jobId is not a valid id", e.getMessage());
        		return;
        	}
    		
        	if(job != null) {
            	switch(job.getState()) {
            		case PENDING:
            		case IN_PROGRESS:
            		case FAIL:
            			JSONObject json = new JSONObject();
            			json.put("status", job.getState().toString());
            			json.put("detail", job.getErrorMessage());
            			ServletUtils.sendResponse(response, 200, "application/json", json.toString());
            			break;
            		case CANCELLED:
            			ServletUtils.sendResponse(response, 410);
            			break;
            		case DONE:
            			ServletUtils.sendResponse(response, 303);
                		response.setHeader("Location", ServletUtils.relativizePath(request, Context.JOB_MANAGER.getPathToResult(job.getId())));
            	}
        	} else {
        		ServletUtils.sendResponse(response, 404);
        	}
		} catch (Exception e) {
			ServletUtils.sendError(response, 500, "An error occurred during the request handling.", e.getMessage());
    	}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
    		JobExecution job = null;
    		try {
        		job = getJobExecution(request.getPathInfo());
        	} catch (IllegalArgumentException e) {
        		ServletUtils.sendError(response, 400, "The jobId is not a valid id", e.getMessage());
        		return;
        	}
    		
        	if(job != null) {
            	Context.JOB_MANAGER.deleteJob(job.getId());
            	ServletUtils.sendResponse(response, 202);
        	} else {
        		ServletUtils.sendResponse(response, 404);
        	}
    	} catch (Exception e) {
    		ServletUtils.sendError(response, 500, "An error occurred during the request handling.", e.getMessage());
    	}
	}
	
	private static JobExecution getJobExecution(String pathInfo) throws IllegalArgumentException {
    	return Context.JOB_MANAGER.get(pathInfo.substring(1));
	}

	

}
