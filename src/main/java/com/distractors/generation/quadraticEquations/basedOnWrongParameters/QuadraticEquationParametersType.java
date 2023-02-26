package com.distractors.generation.quadraticEquations.basedOnWrongParameters;

import java.util.ArrayList;
import java.util.Random;

import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;

public enum QuadraticEquationParametersType {

	LEFT_A,
	LEFT_B,
	LEFT_C,
	RIGHT_A,
	RIGHT_B,
	RIGHT_C;

	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationParametersType randomParameter(QuadraticEquationParameters quadraticEquationParameters)  {
		final var leftA = quadraticEquationParameters.leftSide().a();
		final var leftB = quadraticEquationParameters.leftSide().b();
		final var leftC = quadraticEquationParameters.leftSide().c();
		final var rightA = quadraticEquationParameters.rightSide().a();
		final var rightB = quadraticEquationParameters.rightSide().b();
		final var rightC = quadraticEquationParameters.rightSide().c();
		
		var values = new ArrayList<QuadraticEquationParametersType>();
		if (leftA.toDouble() != 0) {
			values.add(LEFT_A);
		}
		if (leftB.toDouble() != 0) {
			values.add(LEFT_B);
		}
		if (leftC.toDouble() != 0) {
			values.add(LEFT_C);
		}
		if (rightA.toDouble() != 0) {
			values.add(RIGHT_A);
		}
		if (rightB.toDouble() != 0) {
			values.add(RIGHT_B);
		}
		if (rightC.toDouble() != 0) {
			values.add(RIGHT_C);
		}
		return values.get(RANDOM.nextInt(values.size()));
	}
}
