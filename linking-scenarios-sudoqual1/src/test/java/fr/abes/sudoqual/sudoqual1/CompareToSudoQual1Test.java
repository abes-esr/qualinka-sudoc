package fr.abes.sudoqual.sudoqual1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.abes.sudoqual.linking_module.LinkingModule;
import fr.abes.sudoqual.linking_module.exception.LinkingModuleException;
import fr.abes.sudoqual.linking_module.util.OutputComparator;
import fr.abes.sudoqual.util.ResourceNotFoundException;
import fr.abes.sudoqual.util.ResourceUtils;

class CompareToSudoQual1Test {

	private static LinkingModule module;
	private static final Set<String> dirs = Set.of("/fr/abes/sudoqual/sudoqual1/sudoqual1_export/", "/sudoqual1_export/");

	@BeforeAll
	static void beforeAll() throws Exception {
		module = LinkingModule.create(4);
		module.registerPath(new Sudoqual1Config().getScenarioDir());
	}

	@Test
	void testAudibertJacques() throws Exception {
		check("audibert_jacques");
	}
	
	@Test
	void testBessiereChristian() throws Exception {
		check("bessiere_christian");
	}
	
	@Test
	void testBidoitMichel() throws Exception {
		check("bidoit_michel");
	}
	
	@Test
	void testCheinMichel() throws Exception {
		check("chein_michel");
	}
	
	@Test
	void testDiengRose() throws Exception {
		check("dieng_rose");
	}
	
	@Test
	void testKerryJohn() throws Exception {
		check("kerry_john");
	}
	
	@Test
	void testMairesseJean() throws Exception {
		check("mairesse_jean");
	}
	
	@Test
	void testMartinCecile() throws Exception {
		check("martin_cécile");
	}
	
	@Test
	void testMugnierMarieLaure() throws Exception {
		check("mugnier_marie_laure");
	}
	
	@Test
	void testSchumannRobert() throws Exception {
		check("schumann_robert");
	}
	
	@Test
	void testSimplotDavid() throws Exception {
		check("simplot_david");
	}
	
	@Test
	void testTuringAlan() throws Exception {
		check("turing_alan");
	}
	
	@Test
	void testDelattreOlivier() throws Exception {
		check("delattre_olivier");
	}
	
	
	private static final void check(String appellation) throws JSONException, IOException, LinkingModuleException, ResourceNotFoundException, InterruptedException {
		// given
		URL input = ResourceUtils.getResource(CompareToSudoQual1Test.class, dirs, appellation + "-input.json");
		URL expected = ResourceUtils.getResource(CompareToSudoQual1Test.class, dirs,  appellation + "-expected.json");
		JSONObject expectedJSON  = new JSONObject(new JSONTokener(expected.openStream()));
		
		// when
		JSONObject result = module.execute(new JSONObject(new JSONTokener(input.openStream())));

		// then
		String report = OutputComparator.cmp(result, expectedJSON);
		assertTrue(report.isEmpty(), report);
	}

}
