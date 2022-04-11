package fr.abes.sudoqual.sudoqual1.heuristic;

import static fr.abes.sudoqual.linking_module.heuristic.BasicHeuristicMode.MANY_TO_ONE;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.linking_module.Link;
import fr.abes.sudoqual.linking_module.exception.LinkHeuristicException;
import fr.abes.sudoqual.linking_module.exception.UnsupportedHeuristicModeException;
import fr.abes.sudoqual.linking_module.heuristic.BasicLinkHeuristic;
import fr.abes.sudoqual.linking_module.heuristic.Candidate;
import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.FeatureManager;
import fr.abes.sudoqual.rule_engine.Reference;
import fr.abes.sudoqual.util.ConfigurationProperties;
import fr.abes.sudoqual.util.ConfigurationPropertiesException;
import fr.abes.sudoqual.util.json.JSONArrays;

public class SudoQual1RcRaHeuristic extends BasicLinkHeuristic {

	private static final Logger LOGGER = LoggerFactory.getLogger(SudoQual1RcRaHeuristic.class);
	
	private static final String LOW_SAME_AS_THRESHOLD_KEY = "lowValidatedSameAsThreshold";
	private int LOW_SAME_AS_THRESHOLD;
	
	public SudoQual1RcRaHeuristic() {
	}
	
	@Override
	public String getKey() {
		return "sudoQual1RcRa";
	}
	
	@Override
	public void configure(ConfigurationProperties options) throws LinkHeuristicException {
		super.configure(options);
		try {
			this.LOW_SAME_AS_THRESHOLD = options.getInteger(LOW_SAME_AS_THRESHOLD_KEY);
		} catch (ConfigurationPropertiesException e) {
			throw new LinkHeuristicException("Error during heuristic configuration.", e);
		}
	}
	
	@Override
	public void setMode(String mode) throws UnsupportedHeuristicModeException {
		super.setMode(mode);
		if(!MANY_TO_ONE.equals(this.mode)) {
			throw new UnsupportedHeuristicModeException("The heuristic impl " + this.getClass() + " does not support following mode: " + mode);
		}
	}

	@Override
	protected Collection<Link> globalLinkDeduction(FeatureManager store, Set<Candidate> allCandidates, Set<Candidate> bestCandidates, int bestProximity, Reference source) {
		Collection<Link> newLinks = new LinkedList<>();
		
		boolean sameAsFound = false;
		if (bestProximity >= super.SAME_AS_THRESHOLD) {
			assert (!bestCandidates.isEmpty());
			if (bestCandidates.size() == 1) {
				Candidate candidate = bestCandidates.iterator().next();
				newLinks.add(Link.create(Link.Type.SAME_AS, source, candidate));
				sameAsFound = true;
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("SameAs {}", candidate.target);
				}
			} else {
				newLinks.addAll(this.handleNToOneSameAsConflict(source, bestCandidates));
			}
		} else if(bestProximity >= LOW_SAME_AS_THRESHOLD && bestCandidates.size() == 1 
				&& confirmExistingSameAsLink(store, source, bestCandidates.iterator().next().target)) {
			Candidate candidate = bestCandidates.iterator().next();
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug(">>> CATCH UP RULE {} sam@Override\n" + 
						"	protected int computeDeltaSameAsDiffFrom(int sameAsClue, int diffFromClue) {\n" + 
						"		return sameAsClue - diffFromClue;\n" + 
						"	}\n" + 
						"	e as {}", source, candidate.target);
			}
			Link link = Link.create(Link.Type.SAME_AS, source, candidate);
			link = upgradeSuggestedSameAsLinkBecause(link, "It confirms an existing link.");
			newLinks.add(link);
			sameAsFound = true;
		} else if (bestProximity >= super.SUGGESTED_THRESHOLD && this.SUGGESTED_ENABLED) {
			for (Candidate candidate : bestCandidates) {
				newLinks.add(Link.create(Link.Type.SUGGESTED, source, candidate));
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("Suggests {}", candidate.target);
				}
			}
		}
		
		if(sameAsFound) {
			// assert diffFrom for all candidate with negative proximityClue
			for(Candidate cand : allCandidates) {
				if(cand.proximityClue < 0 
						&& !(cand.proximityClue <= super.DIFF_FROM_THRESHOLD)
						&& cand.proximityClue != DiscretCompType.NOT_COMPARABLE) {
					Link link = Link.create(Link.Type.DIFF_FROM, source, cand);
					link = upgradeDiffFromLinkBecause(link, "A sameAs was found");
					newLinks.add(link);
				}
			}
		}
		
		return newLinks;
	}
		
		
	protected boolean confirmExistingSameAsLink(FeatureManager store, Reference source, Reference target) {
		JSONArray array = store.get(target).optJSONArray("initialLinks");
		if(array == null) { 
			return false;
		}
		return JSONArrays.contains(array, source.getName());
	}
	
	@Override
	protected int computeDeltaSameAsDiffFrom(int sameAsClue, int diffFromClue) {
		return sameAsClue - diffFromClue;
	}
	
	
}
