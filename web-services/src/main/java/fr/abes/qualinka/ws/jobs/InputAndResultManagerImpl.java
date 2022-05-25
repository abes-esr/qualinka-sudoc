package fr.abes.qualinka.ws.jobs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.util.Files;
import fr.abes.qualinka.ws.Context;

enum InputAndResultManagerImpl implements InputAndResultManager {
	INSTANCE;
	
	private static final Logger logger = LoggerFactory.getLogger(InputAndResultManagerImpl.class);

	private final File outputDir;
	private final File inputDir;
	private final File serviceDir;
	
	// /////////////////////////////////////////////////////////////////////////
	//	
	// /////////////////////////////////////////////////////////////////////////
	
	InputAndResultManagerImpl() {
		this.outputDir = new File(Context.REAL_PATH + Context.CONF.getString("service.path.results"));
		this.outputDir.mkdir();
		this.inputDir = new File(Context.REAL_PATH + Context.CONF.getString("service.path.inputs"));
		this.inputDir.mkdir();
		this.serviceDir = new File(Context.REAL_PATH + Context.CONF.getString("service.path.services"));
		this.serviceDir.mkdir();
	}
	
	// /////////////////////////////////////////////////////////////////////////
	//	
	// /////////////////////////////////////////////////////////////////////////
	
	@Override
	public boolean saveInput(String jobHexaName, String input) {
		return write(this.getRealPathToInput(jobHexaName), input);
	}
	
	@Override
	public boolean saveService(String jobHexaName, String service) {
		return write(this.getRealPathToService(jobHexaName), service);
	}
	
	@Override
	public boolean saveResult(String jobHexaName, String result) {
		return write(this.getRealPathToResult(jobHexaName), result);
	}
	
	
	
	@Override
	public String getInput(String jobHexaName) {
		try {
			return read(this.getRealPathToInput(jobHexaName));
		} catch (IOException e) {
			logger.warn("Error when reading cache entry", e);
			return "";
		}
	}
	
	@Override
	public String getService(String jobHexaName) {
	try {
		return read(this.getRealPathToService(jobHexaName));
	} catch (IOException e) {
		logger.warn("Error when reading cache entry", e);
		return "";
	}
}
	
	@Override
	public String getResult(String jobHexaName) {
		try {
			return read(this.getRealPathToResult(jobHexaName));
		} catch (IOException e) {
			logger.warn("Error when reading cache entry", e);
			return "";
		}
	}
	
	@Override
	public void delete(String jobHexaName) {
		if(logger.isDebugEnabled()) {
			logger.debug("delete {}/{}", outputDir, jobHexaName);
		}
		new File(outputDir, jobHexaName).delete();
		if(logger.isDebugEnabled()) {
			logger.debug("delete {}/{} ", inputDir, jobHexaName);
		}
		new File(inputDir, jobHexaName).delete();
		if(logger.isDebugEnabled()) {
			logger.debug("delete {}/{}", serviceDir, jobHexaName);
		}
		new File(serviceDir, jobHexaName).delete();
	}
	
	@Override
	public Collection<Entry> listEntries() throws IOException {
		Collection<Entry> res = null;
		try (Stream<Path> stream = java.nio.file.Files.list(this.inputDir.toPath())) {
			res = stream.filter(java.nio.file.Files::isRegularFile)
			        .map(path -> path.getFileName().toString())
			        .map(filename -> EntryImpl.from(this, filename))
			        .collect(Collectors.toList());
		}
		if (res == null) {
			res = Collections.emptyList();
		}
		return res;
	}
	
	@Override
	public String getPathToResult(String jobHexaName) {
		return Context.CONF.getString("service.path.results") + jobHexaName;
	}
	
	@Override
	public String getPathToInput(String jobHexaName) {
		return Context.CONF.getString("service.path.inputs") + jobHexaName;
	}
	
	@Override
	public String getPathToService(String jobHexaName) {
		return Context.CONF.getString("service.path.services") + jobHexaName;
	}
	
	@Override
	public String getRealPathToService(String jobHexaName) {
		return new File(serviceDir, jobHexaName).getAbsolutePath();
	}
	
	@Override
	public String getRealPathToResult(String jobHexaName) {
		return new File(outputDir, jobHexaName).getAbsolutePath();
	}
	
	@Override
	public String getRealPathToInput(String jobHexaName) {
		return new File(inputDir, jobHexaName).getAbsolutePath();
	}
	
	private static boolean write(String path, String content) {
		if(logger.isDebugEnabled()) {
			logger.debug("create {}", path.toString());
		}

		try(Writer w = new FileWriter(path, Context.CHARSET)) {
			w.write(content);
			return true;
		} catch (IOException e) {
			logger.error("Exception while writing file", e);
			return false;
		}
	}
	
	private static String read(String path) throws IOException {
		if(logger.isDebugEnabled()) {
			logger.debug("read {}", path.toString());
		}

		return Files.readFile(new File(path), Context.CHARSET);
	}
	
    // /////////////////////////////////////////////////////////////////////////
    //	INNER CLASS
    // /////////////////////////////////////////////////////////////////////////

	public static class EntryImpl implements Entry {
		private final String hexaname;
		private final String service;
		private final String input;
		private final String result;
		private final Instant lastUpdate;
		
		public static Entry from(InputAndResultManagerImpl mng, String hexaname) {
			Instant lastUpdate = null;
			String service = null;
			try {
				File file = new File(mng.getRealPathToService(hexaname));
				if(file.exists()) {
					service = Files.readFile(file, Context.CHARSET);
				}
			} catch (IOException e) {
				logger.warn("Error when reading cache entry", e);
			}
			String input = null;
			try {
				File file = new File(mng.getRealPathToInput(hexaname));
				if(file.exists()) {
					input = Files.readFile(file, Context.CHARSET);
					lastUpdate = java.nio.file.Files.getLastModifiedTime(file.toPath()).toInstant();
				}
			} catch (IOException e) {
				logger.warn("Error when reading cache entry", e);
			}
			String result = null;
			try {
				File file = new File(mng.getRealPathToResult(hexaname));
				if(file.exists()) {
					result = Files.readFile(file, Context.CHARSET);
					lastUpdate = java.nio.file.Files.getLastModifiedTime(file.toPath()).toInstant();
				}
			} catch (IOException e) {
				logger.warn("Error when reading cache entry", e);
			}
			
			return new EntryImpl(hexaname, service, input, result, lastUpdate);
		}
		
		public EntryImpl(String hexaname, String service, String input, String result, Instant lastUpdate) {
			this.lastUpdate = lastUpdate;
			this.hexaname = hexaname;
			this.service = service;
			this.input = input;
			this.result = result;
		}
		
		@Override
		public String getHexaname() {
			return this.hexaname;
		}

		@Override
		public String getService() {
			return service;
		}

		@Override
		public String getInput() {
			return input;
		}

		@Override
		public String getResult() {
			return result;
		}	
		
		@Override
		public Instant getLastUpdate() {
			return this.lastUpdate;
		}
		
	}
	
}
