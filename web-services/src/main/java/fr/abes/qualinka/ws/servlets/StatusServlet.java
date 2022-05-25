/*
 * Copyright (C) Inria Sophia Antipolis - Méditerranée / LIRMM
 * (Université de Montpellier & CNRS) (2014 - 2015)
 *
 * Contributors :
 *
 * Clément SIPIETER <clement.sipieter@inria.fr>
 * Mélanie KÖNIG
 * Swan ROCHER
 * Jean-François BAGET
 * Michel LECLÈRE
 * Marie-Laure MUGNIER <mugnier@lirmm.fr>
 *
 *
 * This file is part of Graal <https://graphik-team.github.io/graal/>.
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */
package fr.abes.qualinka.ws.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import fr.abes.qualinka.ws.Context;
import fr.abes.qualinka.ws.jobs.JobExecution;
import fr.abes.qualinka.ws.jobs.JobManager;

/**
 * This servlet provides information about the state of the application.
 * 
 * @author Clément Sipieter (INRIA) {@literal <clement@6pi.fr>}
 *
 */
public class StatusServlet extends HttpServlet {

	private static final long serialVersionUID = -8483599883510294654L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		exec(request, response);
	}
	
	public void exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(200);
		response.setContentType("application/json; charset=UTF-8");
		
		JSONObject jobs = new JSONObject();
		for(JobExecution e : Context.JOB_MANAGER.listJobExecution()) {
			jobs.append(e.getState().toString(), JobManager.filenameFromId(e.getId()));
	    }
		
		JSONObject content = new JSONObject();
		content.put("jobs", jobs);

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
		String version = (String) getServletContext().getAttribute("version");
		response.setHeader("Server", "SudoQualWS/" + version);
		response.setDateHeader("Date", new Date().getTime());
		response.setCharacterEncoding("UTF-8");

		// need to be under charset settings
		response.getWriter().append(responseContent);
	}

}
