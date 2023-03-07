package com.distractors.generation.systemsOfTwoEquations.errorBased;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolutionImpact;

public enum SystemOfEquationsErrorType implements SystemOfTwoEquationsSolutionImpact {

	ADDITIVE_REPLACE_WRONG_PARAMETER_X,
	ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X,
	ADDITIVE_REPLACE_WRONG_PARAMETER_Y,
	ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y,

	EQUALIZATION_REPLACE_WRONG_PARAMETER_X,
	EQUALIZATION_REPLACE_WRONG_PARAMETER_Y,
	
	SUBSTITUTION_REPLACE_WRONG_PARAMETER_X,
	SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X,
	SUBSTITUTION_REPLACE_WRONG_PARAMETER_Y,
	SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y;

	private static final List<SystemOfEquationsErrorType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	 private static final int SIZE = VALUES.size();
	 private static final Random RANDOM = new Random();
	
	 public static SystemOfEquationsErrorType randomError()  {
		 return VALUES.get(RANDOM.nextInt(SIZE));
	 }
}
