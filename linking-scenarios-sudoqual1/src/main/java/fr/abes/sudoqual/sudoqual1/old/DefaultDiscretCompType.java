package fr.abes.sudoqual.sudoqual1.old;

import fr.abes.sudoqual.rule_engine.impl.DiscretCompTypeImpl;

/**
 * The Class DefaultDiscretCompType.
 */
public class DefaultDiscretCompType extends  DiscretCompTypeImpl {

	public DefaultDiscretCompType(boolean hasNever, int min, boolean hasNeutral, int max, boolean hasAlways) {
		super(hasNever, min, hasNeutral, max, hasAlways);
	}
}