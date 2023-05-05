package com.distractors.generation.quadraticInequalities.basedOnResultManipulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.distractors.generation.quadraticEquations.QuadraticEquationSolutionImpact;

public enum QuadraticInequalityResultManipulationType implements QuadraticEquationSolutionImpact {

	FORGET_TO_INCLUDE_OR_EXCLUDE_ZEROES,
	SWITCH_INEQUALITY_SIGNS,
	EMPTY_SET,
	R,
	SWITCH_ROOTS,
	R_WITHOUT_X_1,
	R_WITHOUT_X_2,
	X_1,
	X_2;

	private static final List<QuadraticInequalityResultManipulationType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static QuadraticInequalityResultManipulationType randomError()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
