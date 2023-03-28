package com.distractors.generation.systemsOfTwoEquations.basedOnWrongParameters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolutionImpact;

public enum SystemOfEquationsParametersChangeType implements SystemOfTwoEquationsSolutionImpact {

	SWITCH_COEFFICIENTS_OF_X,
	SWITCH_COEFFICIENTS_OF_Y,
	SWITCH_X_AND_Y_1,
	SWITCH_X_AND_Y_2,
	SWITCH_X_AND_Y_BOTH,
	NEGATE_X_1,
	NEGATE_X_2,
	NEGATE_Y_1,
	NEGATE_Y_2,
	NEGATE_FREE_COEFFICIENT_1,
	NEGATE_FREE_COEFFICIENT_2;

	private static final List<SystemOfEquationsParametersChangeType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static SystemOfEquationsParametersChangeType randomChangeType()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
