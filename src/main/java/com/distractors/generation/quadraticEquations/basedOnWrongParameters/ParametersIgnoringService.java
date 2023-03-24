package com.distractors.generation.quadraticEquations.basedOnWrongParameters;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationLeftSideParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRightSideParameters;

public class ParametersIgnoringService {

	public static QuadraticEquationParameters ignoreOneParameter(QuadraticEquationParameters quadraticEquationParameters, QuadraticEquationParametersType parameterToIgnore) {
		final var parameterEqualsOne = Fraction.ZERO;
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var leftB = leftSide.b();
		final var leftC = leftSide.c();
		final var rightA = rightSide.a();
		final var rightB = rightSide.b();
		final var rightC = rightSide.c();

		switch (parameterToIgnore) {
			case LEFT_A:
				final var leftSideParametersIgnoringA = new QuadraticEquationLeftSideParameters(parameterEqualsOne, leftB, leftC);
				return new QuadraticEquationParameters(leftSideParametersIgnoringA, rightSide);
			case LEFT_B:
				final var leftSideParametersIgnoringB = new QuadraticEquationLeftSideParameters(leftA, parameterEqualsOne, leftC);
				return new QuadraticEquationParameters(leftSideParametersIgnoringB, rightSide);
			case LEFT_C:
				final var leftSideParametersIgnoringC = new QuadraticEquationLeftSideParameters(leftA, leftB, parameterEqualsOne);
				return new QuadraticEquationParameters(leftSideParametersIgnoringC, rightSide);
			case RIGHT_A:
				final var rightSideParametersIgnoringA = new QuadraticEquationRightSideParameters(parameterEqualsOne, rightB, rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersIgnoringA);
			case RIGHT_B:
				final var rightSideParametersIgnoringB = new QuadraticEquationRightSideParameters(rightA, parameterEqualsOne, rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersIgnoringB);
			case RIGHT_C:
				final var rightSideParametersIgnoringC = new QuadraticEquationRightSideParameters(rightA, rightB, parameterEqualsOne);
				return new QuadraticEquationParameters(leftSide, rightSideParametersIgnoringC);
			default:
				throw new IllegalArgumentException("Unknown parameter type.");
		}
	}

}
