/**
 * This file is part of the SudoQual project.
 */
package fr.abes.sudoqual.sudoqual1;


import fr.abes.sudoqual.cli.AbstractSudoqualConfig;
import fr.abes.sudoqual.cli.SudoqualCLI;


/**
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public final class Sudoqual1Config extends AbstractSudoqualConfig {

	private static String SCENARIO_DIR = "/fr/abes/sudoqual/sudoqual1/scenarios/";

	public Sudoqual1Config() {
	}

	@Override
	public String getScenarioDir() {
		return SCENARIO_DIR;
	}
	
	public static void main(String args[]) {
		SudoqualCLI.run(new Sudoqual1Config(), args);
	}

}
