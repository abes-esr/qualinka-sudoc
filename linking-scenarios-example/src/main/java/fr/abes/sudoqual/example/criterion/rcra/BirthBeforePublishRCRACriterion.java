package fr.abes.sudoqual.example.criterion.rcra;

import java.util.Set;

import org.json.JSONObject;

import fr.abes.sudoqual.rule_engine.DiscretCompType;
import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;
import fr.abes.sudoqual.rule_engine.predicate.Criterion;

/**
 * Ce critère calcul "publicationDate" - "birthDate", il renvoie : <br/>
 * * never, si cette valeur est inférieur ou égale à 0 ;<br/>
 * * +X, où X étant égale à "publicationDate" - "birthDate" si cette valeur est supérieur à 0.
 * <br/>
 * Ce critère est validé ssi : <br/>
 * * le seuil renseigné est 'never' et "publicationDate" - "birthDate" <= 0 ;<br/>
 * * le seuil renseigné est 'X' tel que X >= 0 et "publicationDate" - "birthDate" >= X.
 * 
 */
public class BirthBeforePublishRCRACriterion implements Criterion {

	@Override
	public int compare(JSONObject source, JSONObject target) {
		if(!source.has("publicationDate") || !target.has("birthDate")) {
			return DiscretCompType.NOT_COMPARABLE;
		}
		
		int publicationDate = source.optInt("publicationDate");
		int birthDate = target.optInt("birthDate");
		
		int value = publicationDate - birthDate;
		if(value < 0) {
			return DiscretCompType.NEVER;
		} else {
			return value;
		}
	}
	
	@Override
	public DiscretCompType getComparisonType() {
		return new DiscretCompTypeImpl(true, 0, false, 5000, false);
	}

	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("publicationDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("birthDate");
	}

}
