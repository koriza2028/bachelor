package com.distractors.generation.systemsOfTwoEquations.basedOnResultManipulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolutionImpact;

public enum SystemOfEquationsResultManipulationType implements SystemOfTwoEquationsSolutionImpact {

	SWITCH_X_Y,
	NEGATE_X,
	NEGATE_Y,
	NEGATE_BOTH;

	private static final List<SystemOfEquationsResultManipulationType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static SystemOfEquationsResultManipulationType randomManipulation()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
