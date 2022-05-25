package fr.abes.qualinka.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.util.ResourceNotFoundException;
import fr.abes.sudoqual.util.ResourceUtils;
import fr.abes.sudoqual.util.json.JSONValidationReport;
import fr.abes.sudoqual.util.json.JSONValidator;

public enum InputValidator {
	INSTANCE;
	
	private static final Logger logger = LoggerFactory.getLogger(InputValidator.class);

	private final Map<String, JSONValidator> validators;
	
	InputValidator() {
		this.validators = new HashMap<>();
	}
	
	private synchronized @Nullable JSONValidator createValidator(String serviceName) {
		// double-check
		JSONValidator validator = this.validators.get(serviceName);
		if(validator == null) {
    		URL linkInputSchemaURL;
    		try {
    			linkInputSchemaURL = ResourceUtils.getResource(getClass(), Context.RESOURCE_DIR, "json-schemas/" + serviceName + "-input.json");
    			try(InputStream stream = linkInputSchemaURL.openStream()) {
    				JSONObject jsonSchemaObject = new JSONObject(new JSONTokener(new InputStreamReader(stream, Context.CHARSET)));
    				validator = JSONValidator.instance(jsonSchemaObject);
    			}
    			this.validators.put(serviceName, validator);
    		} catch (ResourceNotFoundException | IOException e) {
    			logger.warn("Error while trying to create a JSONValidator for following service: " + serviceName + ".", e);
    		}
		}
		return validator;
	}
	
	/**
	 * Checks if the given input is valid for the specified service.
	 * @param serviceName the name of a service
	 * @param input the input to check
	 * @return a JSONValidationReport
	 */
	public JSONValidationReport validate(String serviceName, JSONObject input) {
		JSONValidator validator = this.validators.get(serviceName);
		if(validator == null) {
			validator = createValidator(serviceName);
			if(validator == null) {
				logger.warn("Input validated for {} in the lack of a validator.", serviceName);
				return JSONValidationReport.SUCCESS_REPORT;
			}
		}
		return validator.validate(input);
	}
	
	
	
}
