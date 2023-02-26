package com.distractors.generation.quadraticEquations.basedOnResultManipulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum QuadraticEquationResultManipulationType {

	NEGATE_X_1,
	NEGATE_X_2,
	NEGATE_BOTH,
	ZERO_X_1,
	ZERO_X_2,
	ONE_X_1,
	ONE_X_2,
	MINUS_ONE_X_1,
	MINUS_ONE_X_2,
	MINUS_ONE_X_1_ONE_X_2,
	REVERSE_X_1,
	REVRESE_X_2,
	REVERSE_BOTH;

	private static final List<QuadraticEquationResultManipulationType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationResultManipulationType randomManipulationType()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
}
