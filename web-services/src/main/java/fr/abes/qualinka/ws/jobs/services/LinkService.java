package fr.abes.qualinka.ws.jobs.services;

import javax.annotation.Nullable;

import org.json.JSONObject;

import fr.abes.qualinka.ws.Context;
import fr.abes.qualinka.ws.jobs.Service;

import java.io.File;
import java.io.PrintStream;
/**
 * Service which executes the diagnostic module.
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public enum LinkService implements Service {
	INSTANCE;
	
	
	@Override
	public JSONObject prepare(JSONObject input) throws Exception {
		return InteractiveServiceUtils.prepare(input, Context.JOB_MANAGER.getRegisteredNameForService(this));
	}
	
	@Override
	public @Nullable
	int exec(String inputFilePath, String outputFilePath, PrintStream err) throws Exception {
		String[] args = {"link", "-f", inputFilePath};
		PrintStream out = new PrintStream(new File(outputFilePath), Context.CHARSET);
		int res = Context.SUDOQUAL_CMD.run(args, out, err);
		out.close();
		return res;
	}
	
	@Override
	public void close() {
		// do nothing
	}

	
}
