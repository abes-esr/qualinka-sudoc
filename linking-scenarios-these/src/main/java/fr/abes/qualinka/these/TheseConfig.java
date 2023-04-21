/**
 * This file is part of the SudoQual project.
 */
package fr.abes.qualinka.these;


import fr.abes.sudoqual.cli.AbstractSudoqualConfig;
import fr.abes.sudoqual.cli.SudoqualCLI;


/**
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public final class TheseConfig extends AbstractSudoqualConfig {

	private static String SCENARIO_DIR = "/fr/abes/qualinka/these/scenarios/";

	public TheseConfig() {
	}

	@Override
	public String getScenarioDir() {
		return SCENARIO_DIR;
	}
	
	public static void main(String args[]) {
		SudoqualCLI.run(new TheseConfig(), args);
	}

}
