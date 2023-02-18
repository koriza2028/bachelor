package com.distractors.generation.basedOnResultManipulation.quadraticEquations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum QuadraticEquationRoot {

	X_1,
	X_2;

	private static final List<QuadraticEquationRoot> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationRoot randomRoot()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
