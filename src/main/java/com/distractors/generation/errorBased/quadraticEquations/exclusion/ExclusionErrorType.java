package com.distractors.generation.errorBased.quadraticEquations.exclusion;

import com.distractors.generation.errorBased.quadraticEquations.StandardQuadraticEquationParameters;

public enum ExclusionErrorType {

	IGNORE_C_NOT_ZERO,
	WRONG_SIMPLE_EQUATION_SOLUTION;
	
	public static ExclusionErrorType randomError(StandardQuadraticEquationParameters equationParameters)  {
		final var c = equationParameters.c();

		if (c.toDouble() != 0) {
			return IGNORE_C_NOT_ZERO;
		} else return WRONG_SIMPLE_EQUATION_SOLUTION;
	}
}
