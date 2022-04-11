package fr.abes.sudoqual.ws.jobs.services;

import javax.annotation.Nullable;

import fr.abes.sudoqual.ws.Context;
import org.json.JSONObject;

import fr.abes.sudoqual.ws.jobs.Service;

import java.io.File;
import java.io.PrintStream;
/**
 * Service which executes diagnostic according to the sudoqual diagnostic module.
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public enum DiagnosticService implements Service {
	INSTANCE;
		
	
	@Override
	public int exec(String inputFilePath, String outputFilePath, PrintStream err) throws Exception {
		String[] args = {"diagnostic", "-f", inputFilePath};
		PrintStream out = new PrintStream(new File(outputFilePath), Context.CHARSET);
		int res = Context.SUDOQUAL_CMD.run(args, out, err);
		out.close();
		return res;
	}
	
	@Override
	public void close() {
		
	}

	@Override
	public JSONObject prepare(JSONObject input) throws IllegalArgumentException {
		return input;
	}

	
}
