package com.distractors.generation.quadraticInequalities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.distractors.generation.quadraticEquations.QuadraticSolutionImpact;

public enum QuadraticInequalityNonNumericalSolution implements QuadraticSolutionImpact {

	R,
	R_EXCEPT_ZERO,
	EMPTY_SET,
	ONLY_ZERO,
	NORMAL;

	private static final List<QuadraticInequalityNonNumericalSolution> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static QuadraticInequalityNonNumericalSolution randomType()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
