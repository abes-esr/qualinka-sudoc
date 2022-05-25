package fr.abes.qualinka.ws.jobs;

import org.json.JSONObject;

import java.io.PrintStream;

/**
 * Represents a web service which can be registered by a JobManager.
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public interface Service {
	
	/**
	 * Allows to do some pre-processing over the input
	 * @param input
	 * @return the pre-processed input
	 * @throws Exception 
	 */
	JSONObject prepare(JSONObject input) throws IllegalArgumentException, Exception;
	
	/**
	 * Main methods to implements the service
	 * @param inputFilePath
	 * @param err
	 * @throws Exception
	 * @return
	 */
	int exec(String inputFilePath, String outputFilePath, PrintStream err) throws Exception;

	/**
	 * Allows to release resources.
	 */
	void close();

}
