package com.distractors.generation.systemsOfTwoEquations.errorBased;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsAnswers;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;

public class SystemOfEquationsDistractorsGenerationErrorBasedService {

	public SystemOfTwoEquationsAnswers generateDistractors(SystemOfTwoEquations systemOfLinearEquations) {
	
		final var systemOfEquationsSolutionThroughYService = new SystemOfEquationsAdditiveSolutionThroughYService();
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
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private SystemOfTwoEquationsSolution generateDistractor(SystemOfTwoEquations systemOfLinearEquations) {
		final var randomError = SystemOfEquationsErrorType.randomError();
		final var additiveThroughX = new SystemOfEquationsAdditiveSolutionThroughXService();
		final var additiveThroughY = new SystemOfEquationsAdditiveSolutionThroughYService();
		final var substitutionThroughX = new SystemOfEquationsSubstitutionSolutionThroughXService();
		final var substitutionThroughY = new SystemOfEquationsSubstitutionSolutionThroughYService();
		final var equalizationThroughX = new SystemOfEquationsEqualizationSolutionThroughXService();
		final var equalizationThroughY = new SystemOfEquationsEqualizationSolutionThroughYService();

		switch (randomError) {
			case ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X:
				return additiveThroughX.solveIgnoringFreeCoefficientMultiplication(systemOfLinearEquations);
			case ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y:
				return additiveThroughY.solveIgnoringFreeCoefficientMultiplication(systemOfLinearEquations);
			case ADDITIVE_REPLACE_WRONG_PARAMETER_X:
				return additiveThroughX.solveReplacingWrongParameter(systemOfLinearEquations);
			case ADDITIVE_REPLACE_WRONG_PARAMETER_Y:
				return additiveThroughY.solveReplacingWrongParameter(systemOfLinearEquations);
			case EQUALIZATION_REPLACE_WRONG_PARAMETER_X:
				return equalizationThroughX.solveReplacingWrongParameter(systemOfLinearEquations);
			case EQUALIZATION_REPLACE_WRONG_PARAMETER_Y:
				return equalizationThroughY.solveReplacingWrongParameter(systemOfLinearEquations);
			case SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X:
				return substitutionThroughX.solveIgnoringFreeCoefficientMultiplication(systemOfLinearEquations);
			case SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y:
				return substitutionThroughY.solveIgnoringFreeCoefficientMultiplication(systemOfLinearEquations);
			case SUBSTITUTION_REPLACE_WRONG_PARAMETER_X:
				return substitutionThroughX.solveReplacingWrongParameter(systemOfLinearEquations);
			case SUBSTITUTION_REPLACE_WRONG_PARAMETER_Y:
				return substitutionThroughY.solveReplacingWrongParameter(systemOfLinearEquations);
			default:
				throw new IllegalArgumentException("Unknown solution approach");

		}
	}
}
