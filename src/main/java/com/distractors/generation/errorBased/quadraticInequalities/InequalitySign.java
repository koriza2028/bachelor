package com.distractors.generation.errorBased.quadraticInequalities;

import java.util.ArrayList;
import java.util.List;

public enum InequalitySign {
	LESS,
	LESS_OR_EQUALS,
	GREATER,
	GREATER_OR_EQUALS;
	
	public static List<InequalitySign> validValuesAsList()  {
		List<InequalitySign> values = new ArrayList<InequalitySign> ();
		values.add(LESS);
		values.add(LESS_OR_EQUALS);
		values.add(GREATER);
		values.add(GREATER_OR_EQUALS);
		return values;
	}
}
