package com.distractors.generation.quadraticEquations.basedOnWrongParameters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.distractors.generation.quadraticEquations.QuadraticSolutionImpact;

public enum QuadraticEquationParametersChangeType implements QuadraticSolutionImpact {

	NEGATE_ONE_PARAMETER,
	NEGATE_LEFT_SIDE_PARAMETERS,
	NEGATE_RIGHT_SIDE_PARAMETERS,
	IGNORE_ONE_PARAMETER,
	REVERSE_ONE_PARAMETER;

	private static final List<QuadraticEquationParametersChangeType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationParametersChangeType randomError()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
