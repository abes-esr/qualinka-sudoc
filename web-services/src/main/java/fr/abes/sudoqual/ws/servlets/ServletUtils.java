package fr.abes.sudoqual.ws.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.json.JSONObject;

import fr.abes.sudoqual.util.Strings;
import fr.abes.sudoqual.ws.Context;

public final class ServletUtils {


	private ServletUtils() {
	}
	
	public static String relativizePath(@NonNull HttpServletRequest request, @NonNull String targetPath) {
		int nbOcc = Strings.countChar(request.getServletPath(), '/');
		nbOcc += Strings.countChar(request.getPathInfo(), '/');
		--nbOcc; // remove the first '/' count
		
		if(nbOcc <= 0) {
			return "." + targetPath;
		}
		
		StringBuilder sb = new StringBuilder();
		Strings.repeat(sb, "../", nbOcc);
		sb.append(targetPath.substring(1)); // remove the first '/'
		
		return sb.toString();
	}
	
	public static void sendJsonResponse(HttpServletResponse response, int httpStatus, JSONObject responseContent) throws IOException {
		sendResponse(response, httpStatus, "application/json", responseContent.toString());
	}
	
	public static void sendError(HttpServletResponse response, int httpStatus, String errorName, String errorDetail) throws IOException {
		JSONObject object = new JSONObject();
		object.put("error", errorName);
		object.put("detail", errorDetail);
		sendJsonResponse(response, httpStatus, object);
	}
	
	/** 
	 * Sends to client the message, with the HTTP status code already set.
	 * 
	 * @param response
	 * @param responseContent
	 * @throws IOException
	 */
	public static void sendResponse(HttpServletResponse response, int httpStatus, String contentType, String responseContent) throws IOException {
		response.setStatus(httpStatus);
		if(contentType != null) {
			response.setContentType(contentType);
		}
		response.setHeader("Server", Context.SERVER_INFO);
		response.setDateHeader("Date", new Date().getTime());
		response.setCharacterEncoding("UTF-8");

		if(responseContent != null) {
    		// need to be under charset settings
    		response.getWriter().append(responseContent);
		}
	}
	
	public static void sendResponse(HttpServletResponse response, int httpStatus) throws IOException {
		sendResponse(response, httpStatus, null, null);
	}

}
