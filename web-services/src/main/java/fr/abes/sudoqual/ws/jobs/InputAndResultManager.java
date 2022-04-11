package fr.abes.sudoqual.ws.jobs;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;

interface InputAndResultManager {
	
	static InputAndResultManager instance() {
		return InputAndResultManagerImpl.INSTANCE;
	}

	boolean saveInput(String jobHexaName, String input);

	boolean saveService(String filenameFromId, String serviceName);
	
	boolean saveResult(String jobHexaName, String input);
	
	void delete(String jobHexaName);

	String getPathToResult(String jobHexaName);

	String getPathToInput(String jobHexaName);
	
	String getPathToService(String jobHexaName);

	Collection<Entry> listEntries() throws IOException;

	interface Entry {
	
		String getHexaname();

		String getService(); 

		String getInput();

		String getResult();
		
		Instant getLastUpdate();
		
	}

	String getResult(String filenameFromId);
	
	String getInput(String filenameFromId);

	String getService(String filenameFromId);

	String getRealPathToService(String jobHexaName);

	String getRealPathToResult(String jobHexaName);

	String getRealPathToInput(String jobHexaName);


}
