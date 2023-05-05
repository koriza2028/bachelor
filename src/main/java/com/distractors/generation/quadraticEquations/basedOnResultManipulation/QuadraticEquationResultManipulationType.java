package com.distractors.generation.quadraticEquations.basedOnResultManipulation;

import java.util.ArrayList;
import java.util.Random;

import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.QuadraticEquationSolutionImpact;

public enum QuadraticEquationResultManipulationType implements QuadraticEquationSolutionImpact {

	NEGATE_X_1,
	NEGATE_X_2,
	NEGATE_BOTH,
	ZERO_X_1,
	ZERO_X_2,
	ONE_X_1,
	ONE_X_2,
	MINUS_ONE_X_1,
	MINUS_ONE_X_2,
	MINUS_ONE_X_1_ONE_X_2,
	REVERSE_X_1,
	REVERSE_X_2,
	REVERSE_BOTH;

	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationResultManipulationType randomManipulationType(QuadraticEquationCorrectSolution correctSolution)  {
		final var possibleManipulationTypes = new ArrayList<QuadraticEquationResultManipulationType>();
		possibleManipulationTypes.add(ZERO_X_1);
		possibleManipulationTypes.add(ZERO_X_2);
		possibleManipulationTypes.add(ONE_X_1);
		possibleManipulationTypes.add(ONE_X_2);
		possibleManipulationTypes.add(MINUS_ONE_X_1);
		possibleManipulationTypes.add(MINUS_ONE_X_2);
		possibleManipulationTypes.add(MINUS_ONE_X_1_ONE_X_2);

		if (correctSolution.x_1() != null && correctSolution.x_2() != null) {
			possibleManipulationTypes.add(NEGATE_X_1);
			possibleManipulationTypes.add(NEGATE_X_2);
			possibleManipulationTypes.add(NEGATE_BOTH);
			possibleManipulationTypes.add(REVERSE_X_1);
			possibleManipulationTypes.add(REVERSE_X_2);
			possibleManipulationTypes.add(REVERSE_BOTH);

		}

		final var size = possibleManipulationTypes.size();
		return possibleManipulationTypes.get(RANDOM.nextInt(size));
	}
	
}
