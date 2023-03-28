package com.distractors.generation.systemsOfTwoEquations.basedOnResultManipulation;

import java.util.ArrayList;
import java.util.Random;

import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsNonNumericalSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolutionImpact;

public enum SystemOfEquationsResultManipulationType implements SystemOfTwoEquationsSolutionImpact {

	SWITCH_X_Y,
	NEGATE_X,
	NEGATE_Y,
	NEGATE_BOTH,
	MINUS_ONE_X_ONE_Y,
	MINUS_ONE_Y_ONE_X,
	R,
	EMPTY_SET;

	private static final Random RANDOM = new Random();
	
	public static SystemOfEquationsResultManipulationType randomManipulation(SystemOfTwoEquationsCorrectSolution correctSolution)  {
		final var possibleManipulationTypes = new ArrayList<SystemOfEquationsResultManipulationType>();
		if (correctSolution.nonNumericalSolution().equals(SystemOfTwoEquationsNonNumericalSolution.NORMAL)) {
			possibleManipulationTypes.add(NEGATE_BOTH);
			possibleManipulationTypes.add(SWITCH_X_Y);
			possibleManipulationTypes.add(NEGATE_X);
			possibleManipulationTypes.add(NEGATE_Y);
		}
		possibleManipulationTypes.add(MINUS_ONE_X_ONE_Y);
		possibleManipulationTypes.add(MINUS_ONE_Y_ONE_X);
		possibleManipulationTypes.add(R);
		possibleManipulationTypes.add(EMPTY_SET);

		final var size = possibleManipulationTypes.size();
		return possibleManipulationTypes.get(RANDOM.nextInt(size));
	}
}
