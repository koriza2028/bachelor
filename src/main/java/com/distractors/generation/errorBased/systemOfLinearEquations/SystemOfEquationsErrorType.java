package com.distractors.generation.errorBased.systemOfLinearEquations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SystemOfEquationsErrorType {

	REPLACE_WRONG_PARAMETER,
	IGNORE_FREE_COEFFICIENT_MULTIPLICATION,
	NEGATE_FREE_COEFFICIENT;

	private static final List<SystemOfEquationsErrorType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	 private static final int SIZE = VALUES.size();
	 private static final Random RANDOM = new Random();
	
	 public static SystemOfEquationsErrorType randomError()  {
		 return VALUES.get(RANDOM.nextInt(SIZE));
	 }
}
