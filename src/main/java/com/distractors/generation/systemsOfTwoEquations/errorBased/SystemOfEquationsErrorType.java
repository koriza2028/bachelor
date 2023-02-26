package com.distractors.generation.systemsOfTwoEquations.errorBased;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SystemOfEquationsErrorType {

	REPLACE_WRONG_PARAMETER_X,
	IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X,
	NEGATE_FREE_COEFFICIENT_X,
	REPLACE_WRONG_PARAMETER_Y,
	IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y,
	NEGATE_FREE_COEFFICIENT_Y;

	private static final List<SystemOfEquationsErrorType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	 private static final int SIZE = VALUES.size();
	 private static final Random RANDOM = new Random();
	
	 public static SystemOfEquationsErrorType randomError()  {
		 return VALUES.get(RANDOM.nextInt(SIZE));
	 }
}
