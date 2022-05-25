package tmp.scripts;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Charsets;

import fr.abes.sudoqual.util.Files;
import fr.abes.sudoqual.util.ResourceUtils;
import fr.abes.sudoqual.util.json.JSONObjects;

/**
 * This snippet was written to convert export input files from SudoQual1 script with "rc" features as
 * input with "initialLinks" feature.
 * 
 * @author clement
 *
 */
public class Snippet {
	
	private static final Set<String> dirs = Set.of("/fr/abes/qualinka/sudoqual1/sudoqual1_export/", "/sudoqual1_export/");

	public static void convert(String appellation) {
			try {
				URL input = ResourceUtils.getResource(Snippet.class, dirs, appellation + "-input.json");
	
				JSONObject object = JSONObjects.from(input.openStream(), Charsets.UTF_8);
				JSONObject copyFeatures = new JSONObject();
				JSONArray initialLinks = new JSONArray();
	
				JSONObject features = object.getJSONObject("features");
				for(String key : features.keySet()) {
					JSONObject o = features.getJSONObject(key);
					if(o.has("rc")) {
						JSONArray array = o.getJSONArray("rc");
						o.remove("rc");
						convertAndAdd(initialLinks, key, array);
					} 
					copyFeatures.put(key, o);
				}
				
				JSONObject copy = new JSONObject();
				for(String key : object.keySet()) {
					if(!key.equals("features")) {
						copy.put(key, object.get(key));
					}
				}
				copy.put("features", copyFeatures);
				copy.put("initialLinks", initialLinks);
				Files.writeFile(new File("/tmp/" + appellation + "-input.json"), copy.toString(0), Charsets.UTF_8);
				System.out.println("writed " + appellation);
			} catch (Exception e) {
				
			}
			
		}
	
		private static void convertAndAdd(JSONArray initialLinks, String key, JSONArray array) {
			for(Object o : array) {
				String uri = (String) o;
				initialLinks.put(createSameAsLink(uri, key));
			}
			
		}
	
		private static JSONObject createSameAsLink(String source, String target) {
			JSONObject o = new JSONObject();
			o.put("source", source);
			o.put("type", "sameAs");
			o.put("target", target);
			return o;
		}
}

