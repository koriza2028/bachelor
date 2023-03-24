package com.distractors.generation.quadraticEquations.basedOnWrongParameters;

import com.distractors.generation.quadraticEquations.QuadraticEquationLeftSideParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRightSideParameters;

public class ParametersNegatingService {

	public static QuadraticEquationParameters negateOneParameter(QuadraticEquationParameters quadraticEquationParameters, QuadraticEquationParametersType parameterToNegate) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var leftB = leftSide.b();
		final var leftC = leftSide.c();
		final var rightA = rightSide.a();
		final var rightB = rightSide.b();
		final var rightC = rightSide.c();

		switch (parameterToNegate) {
			case LEFT_A:
				final var leftSideParametersNegatingA = new QuadraticEquationLeftSideParameters(leftA.multiplyBy(-1), leftB, leftC);
				return new QuadraticEquationParameters(leftSideParametersNegatingA, rightSide);
			case LEFT_B:
				final var leftSideParametersNegatingB = new QuadraticEquationLeftSideParameters(leftA, leftB.multiplyBy(-1), leftC);
				return new QuadraticEquationParameters(leftSideParametersNegatingB, rightSide);
			case LEFT_C:
				final var leftSideParametersNegatingC = new QuadraticEquationLeftSideParameters(leftA, leftB, leftC.multiplyBy(-1));
				return new QuadraticEquationParameters(leftSideParametersNegatingC, rightSide);
			case RIGHT_A:
				final var rightSideParametersNegatingA = new QuadraticEquationRightSideParameters(rightA.multiplyBy(-1), rightB, rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersNegatingA);
			case RIGHT_B:
				final var rightSideParametersNegatingB = new QuadraticEquationRightSideParameters(rightA, rightB.multiplyBy(-1), rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersNegatingB);
			case RIGHT_C:
				final var rightSideParametersNegatingC = new QuadraticEquationRightSideParameters(rightA, rightB, rightC.multiplyBy(-1));
				return new QuadraticEquationParameters(leftSide, rightSideParametersNegatingC);
			default:
				throw new IllegalArgumentException("Unknown parameter type.");
		}
	}

	public static QuadraticEquationParameters negateRightSideParameters(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var negatedRightA = rightSide.a().multiplyBy(-1);
		final var negatedRightB = rightSide.b().multiplyBy(-1);
		final var negatedRightC = rightSide.c().multiplyBy(-1);
		final var negatedRightSide = new QuadraticEquationRightSideParameters(negatedRightA, negatedRightB, negatedRightC);
		
		return new QuadraticEquationParameters(leftSide, negatedRightSide);
	}

	public static QuadraticEquationParameters negateLeftSideParameters(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var negatedLeftA = leftSide.a().multiplyBy(-1);
		final var negatedLeftB = leftSide.b().multiplyBy(-1);
		final var negatedLeftC = leftSide.c().multiplyBy(-1);
		final var negatedLeftSide = new QuadraticEquationLeftSideParameters(negatedLeftA, negatedLeftB, negatedLeftC);
		
		return new QuadraticEquationParameters(negatedLeftSide, rightSide);
	}

}
