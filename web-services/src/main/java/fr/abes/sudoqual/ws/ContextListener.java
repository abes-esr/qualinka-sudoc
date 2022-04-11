package fr.abes.sudoqual.ws;

import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.abes.sudoqual.ws.jobs.services.*;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.cli.SudoqualCommander;
import fr.abes.sudoqual.cli.SudoqualConfig;
import fr.abes.sudoqual.linking_module.LinkingModule;
import fr.abes.sudoqual.sudoc.SudocConfig;
import fr.abes.sudoqual.ws.jobs.JobManager;

public class ContextListener implements ServletContextListener {
	
	private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

	private static final String NB_THREAD_KEY = "service.linking_module.nbThreads";
	
	private LinkingModule linkingModule = null;
	
	/**
	 * At server startup
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		if (logger.isDebugEnabled()) {
			logger.debug("context initialization starts");
		}
		
		ServletContext context = event.getServletContext();
		
		Context.CONF = loadConfiguration();
		for(Iterator<String> it = Context.CONF.getKeys(); it.hasNext();) {
			String key = it.next();
			context.setAttribute(key, Context.CONF.getString(key));
		}
		
		Context.CONTEXT_PATH = context.getContextPath();
		Context.REAL_PATH = context.getRealPath("/");
		Context.SERVER_INFO = Context.CONF.getString("project.name") + "/" + Context.CONF.getString("project.version");
		Context.JOB_MANAGER = JobManager.create();
		
		SudoqualConfig config = new SudocConfig();
		
		// create linking module
		int nbThread = Runtime.getRuntime().availableProcessors();
		String nbThreadProp = Context.CONF.getString(NB_THREAD_KEY);
		if(nbThreadProp != null) {
    		try {
    			nbThread = Integer.parseInt(nbThreadProp);
    		} catch(NumberFormatException e) {
    			LoggerFactory.getLogger(LinkService.class)
    			.warn("The specified value for {} " + NB_THREAD_KEY + " is not an integer. Details: ", e);
    		}
		}
		Context.EFFECTIVE_NB_THREADS = nbThread;
		linkingModule = LinkingModule.create(nbThread);
		linkingModule.registerPath(config.getScenarioDir());
		
		config.setCharset(Context.CHARSET);
		Context.SUDOQUAL_CMD = new SudoqualCommander(config, linkingModule, false);
		
		registerServices(Context.JOB_MANAGER);
		Context.JOB_MANAGER.cacheRecovery();
		
		if (logger.isDebugEnabled()) {
			logger.debug("context initialization finishes");
		}
	}

	private static void registerServices(JobManager jobManager) {
		jobManager.register("link", LinkService.INSTANCE);
		jobManager.register("diagnostic", DiagnosticService.INSTANCE);
		jobManager.register("complete", CompleteService.INSTANCE);
		jobManager.register("cluster", ClusterService.INSTANCE);
		jobManager.register("align", AlignService.INSTANCE);
		jobManager.register("light", LightService.INSTANCE);
	}

	/**
	 * Actions to perform at server shutdown.
	 * 
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			if(linkingModule != null) {
				linkingModule.close();
			}
			if (Context.JOB_MANAGER != null) {
				Context.JOB_MANAGER.close();
			}
		} catch (Exception e) {
			logger.error("An error occured during application context destroying", e);
		}
	}

	// /////////////////////////////////////////////////////////////////////////
	// PRIVATE
	// /////////////////////////////////////////////////////////////////////////

	private static Configuration loadConfiguration() {

		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class).configure(
		    params.properties()
		    .setFileName(Context.RESOURCE_DIR + "application.properties")
		    .setListDelimiterHandler(new DefaultListDelimiterHandler(';')));
		try {
			return builder.getConfiguration();
		} catch (ConfigurationException e) {
			throw new Error("The configuration loading failed.", e);
		}
	}

}
