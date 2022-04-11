package fr.abes.sudoqual.example.criterion.rcra;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Ce critère compare la date de publication de la source (publicationDate) 
 * avec l'interval des dates de publications de la cible (publicationDateComputed). <br/>
 * <br/>
 * Valeurs possibles : <br/>
 * always - la date de publication de la source est dans l'interval <br/>
 * 3 - la date de publication est à une distance de 1 an de l'interval (avant ou après) <br/>
 * 2 - la date de publication est à une distance <= 5 ans de l'interval <br/>
 * 1 - la date de publication est à une distance <= 10 ans de l'interval <br/>
 *
 */
public class PublicationDateRCRACriterion implements Criterion {

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("publicationDate") || !target.has("publicationDateComputed")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		int publicationDate = source.optInt("publicationDate");
		JSONObject pubInterval = target.optJSONObject("publicationDateComputed");
		
		if(!pubInterval.has("min") || !pubInterval.has("max")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		int min = pubInterval.optInt("min");
		int max = pubInterval.optInt("max");
		if(publicationDate >= min && publicationDate <= max) {
			return DiscretCompTypeImpl.ALWAYS;
		} else if(publicationDate >= (min - 1) && publicationDate <= (max + 1)) {
			return 3;
		} else if(publicationDate >= (min - 5) && publicationDate <= (max + 5)) {
			return 2;
		} else if(publicationDate >= (min - 10) && publicationDate <= (max + 10)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public DiscretCompType getComparisonType() {
		return new DiscretCompTypeImpl(false, 0, true, 3, true);
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("publicationDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("publicationDateComputed");
	}

}
