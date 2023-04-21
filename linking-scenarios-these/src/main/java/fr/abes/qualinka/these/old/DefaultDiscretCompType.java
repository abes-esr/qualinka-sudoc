package fr.abes.qualinka.these.old;

import fr.abes.sudoqual.rule_engine.DiscretCompType;

/**
 * The Class DefaultDiscretCompType.
 */
public class DefaultDiscretCompType implements DiscretCompType {

	/** The max value. zero if no positive value */
	private int max;
	/** The min value. zero if no negative value */
	private int min;

	/** The has never. */
	private boolean hasNeutral, hasAlways, hasNever;

	/**
	 * Instantiates a new default discret comp type.
	 *
	 * @param hasNever the has never
	 * @param min the min
	 * @param hasNeutral the has neutral
	 * @param max the max
	 * @param hasAlways the has always
	 */
	public DefaultDiscretCompType(boolean hasNever, int min, boolean hasNeutral, int max,boolean hasAlways) {
		this.max = max;
		this.min = min;
		this.hasNeutral = hasNeutral;
		this.hasAlways = hasAlways;
		this.hasNever = hasNever;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lirmm.graphik.qualinca.model.IDiscretCompType#hasNeutral()
	 */
	public boolean hasNeutral() {
		return hasNeutral;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lirmm.graphik.qualinca.model.IDiscretCompType#hasAlways()
	 */
	public boolean hasAlways() {
		return hasAlways;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lirmm.graphik.qualinca.model.IDiscretCompType#hasNever()
	 */
	public boolean hasNever() {
		return hasNever;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lirmm.graphik.qualinca.model.IDiscretCompType#getMaxValue()
	 */
	@Override
	public int getMaxValue() {
		return max;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lirmm.graphik.qualinca.model.IDiscretCompType#getMinValue()
	 */
	@Override
	public int getMinValue() {
		return min;
	}

	/**
	 * Checks for positive values.
	 * 
	 * @return true, if successful
	 */
	public boolean hasPositiveValues() {
		return max > 0;
	}

	/**
	 * Checks for negative value.
	 * 
	 * @return true, if successful
	 */
	public boolean hasNegativeValue() {
		return min < 0;
	}

	public boolean check(int value) {
		switch (value) {
		case NOT_COMPARABLE:
		case INCOHERENT:
			return true;
		case NEUTRAL:
			return hasNeutral;
		case ALWAYS:
			return hasAlways;
		case NEVER:
			return hasNever;
		}
		if (value < 0)
			return value >= min;
		else
			return value <= max;
	}
	
	public boolean check(String value) {
		switch (value) {
		case "not_comparable":
		case "incoherent":
			return true;
		case "neutral":
			return hasNeutral;
		case "always":
			return hasAlways;
		case "never":
			return hasNever;
		default:
			return false;
		}
	}


	/**
	 * Control if value is in the defined scope.
	 * 
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public boolean control(int value) {
		return check(value);
	}

	public String toString(int value)
	{
		switch(value)
		{
		case NOT_COMPARABLE:
			return "not_comparable";
		case INCOHERENT:
			return "incoherent";
		case NEUTRAL:
			return "neutral";
		case ALWAYS:
			return "always";
		case NEVER:
			return "never";
		}
		String s="";
		if (value < 0)
			for(int i=0;i>value;i--) s+="-";
		else
			for(int i=0;i<value;i++) s+="+";
		return s;
	}
}
