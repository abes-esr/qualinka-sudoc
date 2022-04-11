/**
 * This file is part of the SudoQual project.
 */
package fr.abes.sudoqual.sudoc;

import fr.abes.sudoqual.cli.AbstractSudoqualConfig;
import fr.abes.sudoqual.cli.SudoqualCLI;


/**
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public final class SudocConfig extends AbstractSudoqualConfig {

	private static String SCENARIO_DIR = "/fr/abes/sudoqual/sudoc/scenarios/";

	public SudocConfig() {
	}

	@Override
	public String getScenarioDir() {
		return SCENARIO_DIR;
	}
	
	
	public static void main(String args[]) {
		SudoqualCLI.run(new SudocConfig(), args);
	}
}
