package com.distractors.generation.errorBased.systemOfLinearEquations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SystemOfEquationsSolutionApproach {

	X,
	Y;

	private static final List<SystemOfEquationsSolutionApproach> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static SystemOfEquationsSolutionApproach randomApproach()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
