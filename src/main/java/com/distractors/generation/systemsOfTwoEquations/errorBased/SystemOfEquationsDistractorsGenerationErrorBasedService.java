package com.distractors.generation.systemsOfTwoEquations.errorBased;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsAnswers;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;

public class SystemOfEquationsDistractorsGenerationErrorBasedService {

	public SystemOfTwoEquationsAnswers generateDistractors(SystemOfTwoEquations systemOfLinearEquations) {
	
		final var systemOfEquationsSolutionThroughYService = new SystemOfEquationsSolutionThroughYService();
		final var correctSolution = systemOfEquationsSolutionThroughYService.solveCorrectly(systemOfLinearEquations);

		var distractors = new ArrayList<SystemOfTwoEquationsSolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(systemOfLinearEquations, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(systemOfLinearEquations, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(systemOfLinearEquations, distractors);

		return new SystemOfTwoEquationsAnswers(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private boolean isDistractorInvalid(SystemOfTwoEquationsSolution possibleDistractor, List<SystemOfTwoEquationsSolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private SystemOfTwoEquationsSolution generateDifferentDistractor(final SystemOfTwoEquations systemOfLinearEquations, List<SystemOfTwoEquationsSolution> distractors) {
		SystemOfTwoEquationsSolution distractor;
		do {
			distractor = this.generateDistractor(systemOfLinearEquations);
		} while (this.isDistractorInvalid(distractor, null));
		return distractor;
	}

	private SystemOfTwoEquationsSolution generateDistractor(SystemOfTwoEquations systemOfLinearEquations) {
		final var randomError = SystemOfEquationsErrorType.randomError();
		final var throughX = new SystemOfEquationsSolutionThroughXService();
		final var throughY = new SystemOfEquationsSolutionThroughYService();

		switch (randomError) {
			case IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X:
				return throughX.solveIgnoringFreeCoefficientMultiplication(systemOfLinearEquations);
			case IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y:
				return throughY.solveIgnoringFreeCoefficientMultiplication(systemOfLinearEquations);
			case NEGATE_FREE_COEFFICIENT_X:
				return throughX.solveNegatingFreeCoefficient(systemOfLinearEquations);
			case NEGATE_FREE_COEFFICIENT_Y:
				return throughY.solveNegatingFreeCoefficient(systemOfLinearEquations);
			case REPLACE_WRONG_PARAMETER_X:
				return throughX.solveReplacingWrongParameter(systemOfLinearEquations);
			case REPLACE_WRONG_PARAMETER_Y:
				return throughY.solveReplacingWrongParameter(systemOfLinearEquations);
			default:
				throw new IllegalArgumentException("Unknown solution approach");

		}
	}
}
