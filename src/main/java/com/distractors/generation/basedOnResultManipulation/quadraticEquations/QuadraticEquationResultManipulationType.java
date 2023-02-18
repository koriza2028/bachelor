package com.distractors.generation.basedOnResultManipulation.quadraticEquations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum QuadraticEquationResultManipulationType {

	NEGATE_ONE_ANSWER,
	NEGATE_BOTH_ANSWERS,
	ZERO,
	ONE,
	MINUS_ONE,
	ONE_MINUS_ONE,
	REVERSE_ONE_FRACTION,
	REVERSE_TWO_FRACTIONS;

	private static final List<QuadraticEquationResultManipulationType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationResultManipulationType randomManipulationType()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
}
