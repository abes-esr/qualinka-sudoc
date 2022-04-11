package fr.abes.sudoqual.ws.servlets;

import fr.abes.sudoqual.ws.Context;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;

/**
 * This servlet produce and send json-schema corresponding to the scenario name provided
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public class FeaturesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(200);
		response.setContentType("application/json; charset=UTF-8");
		try {
    		String scenarioName = request.getPathInfo().substring(1);
    		JSONArray array = new JSONArray(Context.SUDOQUAL_CMD.getLinkingModule().listFeatures(scenarioName));
			response.getWriter().append(array.toString());
		} catch (Exception e) {
			ServletUtils.sendError(response, 500, "An error occurred during the request handling.", e.getMessage());
    	}
	}



	

}
