/**
 * This file is part of the SudoQual project.
 */
package fr.abes.sudoqual.example;

import fr.abes.sudoqual.cli.AbstractSudoqualConfig;
import fr.abes.sudoqual.cli.SudoqualCLI;


/**
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public final class ExampleConfig extends  AbstractSudoqualConfig {

	private static String SCENARIO_DIR = "/fr/abes/sudoqual/example/scenarios/";

	public ExampleConfig() {
	}

	@Override
	public String getScenarioDir() {
		return SCENARIO_DIR;
	}
	
	public static void main(String args[]) {
		SudoqualCLI.run(new ExampleConfig(), args);
	}

}
