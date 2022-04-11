package fr.abes.sudoqual.ws.servlets;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.abes.sudoqual.cli.services.ScenarioService;
import org.json.JSONObject;

import fr.abes.sudoqual.ws.Context;

/**
 * This servlet provides application and services information.
 * 
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public class InfoServlet extends HttpServlet {

	private static final long serialVersionUID = 6965217366753388096L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		exec(request, response);
	}

	public void exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(200);
		response.setContentType("application/json; charset=UTF-8");

		JSONObject content = new JSONObject();
		content.put("version", Context.SERVER_INFO);
		content.put("nbThreads", Context.EFFECTIVE_NB_THREADS);

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String errString = null;
		try (PrintStream err = new PrintStream(new BufferedOutputStream(baos), false, StandardCharsets.UTF_8)) {
			for (Object scenario : ScenarioService.listAvailableScenarios(Context.SUDOQUAL_CMD.getScenarioDir(), err)) {
				content.append("scenarios", scenario.toString());
			}
			err.flush();
			errString = baos.toString(StandardCharsets.UTF_8);
		}
		if (!errString.isBlank()) {
			content.append("scenarios", errString);
		}

		this.sendResponse(response, content.toString());
	}

	/**
	 * Main responding method.
	 * 
	 * Sends to client the message, with the HTTP status code already set.
	 * 
	 * @param response
	 * @param responseContent
	 * @throws IOException
	 */
	protected void sendResponse(HttpServletResponse response, String responseContent) throws IOException {
		response.setHeader("Server", Context.SERVER_INFO);
		response.setDateHeader("Date", new Date().getTime());
		response.setCharacterEncoding("UTF-8");

		// need to be under charset settings
		PrintWriter out = response.getWriter();

		out.print(responseContent);
		out.flush();
	}
}
