package fr.abes.qualinka.ws;

import java.nio.charset.Charset;

import org.apache.commons.configuration2.Configuration;

import fr.abes.sudoqual.cli.SudoqualCommander;
import fr.abes.sudoqual.linking_module.LinkingModule;
import fr.abes.qualinka.ws.jobs.JobManager;

public final class Context {

	private Context() {}
	
	public static final String SCENARIO_DIR = "/fr/abes/qualinka/sudoc/scenarios/";
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public static String CONTEXT_PATH;
	public static String RESOURCE_DIR = "/fr/abes/qualinka/ws/resources/";
	
	public static Object LOCK = new Object();
	public static Configuration CONF;
	
	public static String REAL_PATH;
	public static String SERVER_INFO;
	
	public static JobManager JOB_MANAGER;
	public static SudoqualCommander SUDOQUAL_CMD;

	public static int EFFECTIVE_NB_THREADS;

	
}
