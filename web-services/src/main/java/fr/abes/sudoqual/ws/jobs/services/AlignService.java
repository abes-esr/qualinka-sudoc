package fr.abes.sudoqual.ws.jobs.services;

import fr.abes.sudoqual.ws.Context;
import fr.abes.sudoqual.ws.jobs.Service;
import org.json.JSONObject;

import java.io.File;
import java.io.PrintStream;

/**
 * Service which executes the align mode of SudoQual.
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public enum AlignService implements Service {
	INSTANCE;

	@Override
	public JSONObject prepare(JSONObject input) throws Exception {
		return InteractiveServiceUtils.prepare(input, Context.JOB_MANAGER.getRegisteredNameForService(this));
	}

	@Override
	public int exec(String inputFilePath, String outputFilePath, PrintStream err) throws Exception {
		String[] args = {"align", "-f", inputFilePath};
		PrintStream out = new PrintStream(new File(outputFilePath), Context.CHARSET);
		int res = Context.SUDOQUAL_CMD.run(args, out, err);
		out.close();
		return res;
	}

	@Override
	public void close() {
	}
	
}
