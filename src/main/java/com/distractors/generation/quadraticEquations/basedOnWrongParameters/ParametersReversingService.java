package com.distractors.generation.quadraticEquations.basedOnWrongParameters;

import com.distractors.generation.quadraticEquations.QuadraticEquationLeftSideParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRightSideParameters;

public class ParametersReversingService {

	public static QuadraticEquationParameters reverseOneParameter(QuadraticEquationParameters quadraticEquationParameters, QuadraticEquationParametersType randomParameter) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var leftB = leftSide.b();
		final var leftC = leftSide.c();
		final var rightA = rightSide.a();
		final var rightB = rightSide.b();
		final var rightC = rightSide.c();
		
		switch (randomParameter) {
		case LEFT_A:
			final var leftSideParametersRevertingA = new QuadraticEquationLeftSideParameters(leftA.reverse(), leftB, leftC);
			return new QuadraticEquationParameters(leftSideParametersRevertingA, rightSide);
		case LEFT_B:
			final var leftSideParametersRevertingB = new QuadraticEquationLeftSideParameters(leftA, leftB.reverse(), leftC);
			return new QuadraticEquationParameters(leftSideParametersRevertingB, rightSide);
		case LEFT_C:
			final var leftSideParametersRevertingC = new QuadraticEquationLeftSideParameters(leftA, leftB, leftC.reverse());
			return new QuadraticEquationParameters(leftSideParametersRevertingC, rightSide);
		case RIGHT_A:
			final var rightSideParametersRevertingA = new QuadraticEquationRightSideParameters(rightA.reverse(), rightB, rightC);
			return new QuadraticEquationParameters(leftSide, rightSideParametersRevertingA);
		case RIGHT_B:
			final var rightSideParametersRevertingB = new QuadraticEquationRightSideParameters(rightA, rightB.reverse(), rightC);
			return new QuadraticEquationParameters(leftSide, rightSideParametersRevertingB);
		case RIGHT_C:
			final var rightSideParametersRevertingC = new QuadraticEquationRightSideParameters(rightA, rightB, rightC.reverse());
			return new QuadraticEquationParameters(leftSide, rightSideParametersRevertingC);
		default:
			throw new IllegalArgumentException("Unknown parameter type.");
		}
	}
}
