package fr.abes.qualinka.ws.servlets;

import fr.abes.qualinka.ws.Context;

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
public class SchemaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(200);
		response.setContentType("application/json; charset=UTF-8");
		try {
    		String scenarioName = request.getPathInfo().substring(1);
			String[] args = {"schema", scenarioName};

			PrintStream out = new PrintStream(response.getOutputStream(), true, Context.CHARSET);
			Context.SUDOQUAL_CMD.run(args, out, out);
			out.close();
		} catch (Exception e) {
			ServletUtils.sendError(response, 500, "An error occurred during the request handling.", e.getMessage());
    	}
	}



	

}
