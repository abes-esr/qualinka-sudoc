package fr.abes.sudoqual.sudoc.criterion.aa;

import java.util.Set;

public class DeathDateAACriterion extends BirthDateAACriterion {
	
	@Override
	public Set<String> sourceFeatureSet() {
		return Set.of("deathDate");
	}

	@Override
	public Set<String> targetFeatureSet() {
		return Set.of("deathDate");
	}


}
