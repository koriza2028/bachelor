package com.distractors.generation.quadraticEquations.basedOnWrongParameters;

import java.util.ArrayList;
import java.util.Random;

import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticSolutionImpact;

public enum QuadraticEquationParametersChangeType implements QuadraticSolutionImpact {

	NEGATE_LEFT_A,
	NEGATE_LEFT_B,
	NEGATE_LEFT_C,
	NEGATE_RIGHT_A,
	NEGATE_RIGHT_B,
	NEGATE_RIGHT_C,
	IGNORE_LEFT_A,
	IGNORE_LEFT_B,
	IGNORE_LEFT_C,
	IGNORE_RIGHT_A,
	IGNORE_RIGHT_B,
	IGNORE_RIGHT_C,
	REVERSE_LEFT_A,
	REVERSE_LEFT_B,
	REVERSE_LEFT_C,
	REVERSE_RIGHT_A,
	REVERSE_RIGHT_B,
	REVERSE_RIGHT_C,
	NEGATE_LEFT_SIDE_PARAMETERS,
	NEGATE_RIGHT_SIDE_PARAMETERS,
	SWITCH_A,
	SWITCH_B,
	SWITCH_C,
	SWITCH_LEFT_A_B,
	SWITCH_LEFT_A_C,
	SWITCH_LEFT_B_C,
	SWITCH_RIGHT_A_B,
	SWITCH_RIGHT_A_C,
	SWITCH_RIGHT_B_C;

	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationParametersChangeType randomParametersChangeType(QuadraticEquationParameters quadraticEquationParameters) {
		final var left = quadraticEquationParameters.leftSide();
		final var right = quadraticEquationParameters.rightSide();
		final var leftA = left.a().toDouble();
		final var leftB = left.b().toDouble();
		final var leftC = left.c().toDouble();
		final var rightA = right.a().toDouble();
		final var rightB = right.b().toDouble();
		final var rightC = right.c().toDouble();

		final var possibleChangeTypes = new ArrayList<QuadraticEquationParametersChangeType>();
		possibleChangeTypes.add(SWITCH_A);

		if (leftA != 0) {
			possibleChangeTypes.add(IGNORE_LEFT_A);
			possibleChangeTypes.add(NEGATE_LEFT_A);
			possibleChangeTypes.add(REVERSE_LEFT_A);
		}

		if (leftB != 0) {
			possibleChangeTypes.add(IGNORE_LEFT_B);
			possibleChangeTypes.add(NEGATE_LEFT_B);
			possibleChangeTypes.add(REVERSE_LEFT_B);
		}

		if (leftC != 0) {
			possibleChangeTypes.add(IGNORE_LEFT_C);
			possibleChangeTypes.add(NEGATE_LEFT_C);
			possibleChangeTypes.add(REVERSE_LEFT_C);
		}

		if (rightA != 0) {
			possibleChangeTypes.add(IGNORE_RIGHT_A);
			possibleChangeTypes.add(NEGATE_RIGHT_A);
			possibleChangeTypes.add(REVERSE_RIGHT_A);
		}

		if (rightB != 0) {
			possibleChangeTypes.add(IGNORE_RIGHT_B);
			possibleChangeTypes.add(NEGATE_RIGHT_B);
			possibleChangeTypes.add(REVERSE_RIGHT_B);
		}

		if (rightC != 0) {
			possibleChangeTypes.add(IGNORE_RIGHT_C);
			possibleChangeTypes.add(NEGATE_RIGHT_C);
			possibleChangeTypes.add(REVERSE_RIGHT_C);
		}

		if (leftB != 0 && rightB != 0) {
			possibleChangeTypes.add(SWITCH_B);
		}

		if (leftC != 0 && rightC != 0) {
			possibleChangeTypes.add(SWITCH_C);
		}

		if (leftB != 0 && rightA != 0) {
			possibleChangeTypes.add(SWITCH_LEFT_A_B);
		}

		if (leftC != 0 && rightA != 0) {
			possibleChangeTypes.add(SWITCH_LEFT_A_C);
		}

		if (rightB != 0 && leftA != 0) {
			possibleChangeTypes.add(SWITCH_RIGHT_A_B);
		}

		if (rightC != 0 && leftA != 0) {
			possibleChangeTypes.add(SWITCH_RIGHT_A_C);
		}

		possibleChangeTypes.add(SWITCH_LEFT_B_C);
		possibleChangeTypes.add(SWITCH_LEFT_B_C);

		final var size = possibleChangeTypes.size();
		return possibleChangeTypes.get(RANDOM.nextInt(size));
	}
}
