package com.distractors.generation.systemsOfTwoEquations.basedOnWrongParameters;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsAnswers;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;
import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsSolutionThroughXService;

public class SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService {

	final SystemOfEquationsSolutionThroughXService solutionThroughXService = new SystemOfEquationsSolutionThroughXService();

	public SystemOfTwoEquationsAnswers generateDistractors(SystemOfTwoEquations systemOfLinearEquations) {
		final var correctSolution = solutionThroughXService.solveCorrectly(systemOfLinearEquations);

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
		final var randomChangeType = SystemOfEquationsParametersChangeType.randomChangeType();

		switch (randomChangeType) {
			case NEGATE_FREE_COEFFICIENT_1:
				return this.generateNegatingFreeCoefficient_1(systemOfLinearEquations);
			case NEGATE_FREE_COEFFICIENT_2:
				return this.generateNegatingFreeCoefficient_2(systemOfLinearEquations);
			case NEGATE_X_1:
				return this.generateNegatingX_1(systemOfLinearEquations);
			case NEGATE_X_2:
				return this.generateNegatingX_2(systemOfLinearEquations);
			case NEGATE_Y_1:
				return this.generateNegatingY_1(systemOfLinearEquations);
			case NEGATE_Y_2:
				return this.generateNegatingY_2(systemOfLinearEquations);
			case SWITCH_COEFFICIENTS_OF_X:
				return this.generateSwitchingCoefficientsOfX(systemOfLinearEquations);
			case SWITCH_COEFFICIENTS_OF_Y:
				return this.generateSwitchingCoefficientsOfY(systemOfLinearEquations);
			case SWITCH_X_AND_Y_1:
				return this.generateSwitchingCoefficientsOfXAndY_1(systemOfLinearEquations);
			case SWITCH_X_AND_Y_2:
				return this.generateSwitchingCoefficientsOfXAndY_2(systemOfLinearEquations);
			case SWITCH_X_AND_Y_BOTH:
				return this.generateSwitchingCoefficientsOfXAndYBoth(systemOfLinearEquations);
			default:
				throw new IllegalArgumentException("Unknown solution approach");
		}
	}

	private SystemOfTwoEquationsSolution generateSwitchingCoefficientsOfXAndYBoth(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_1 = simpleEquation_1.coefficientOfY();
		final var y_1 = simpleEquation_1.coefficientOfX();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var x_2 = simpleEquation_2.coefficientOfY();
		final var y_2 = simpleEquation_2.coefficientOfX();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(x_1, y_1, free_1);
		final var newSimpleEquation_2 = new StandardEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfTwoEquations(newSimpleEquation_1, newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateSwitchingCoefficientsOfXAndY_2(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_2 = simpleEquation_2.coefficientOfY();
		final var y_2 = simpleEquation_2.coefficientOfX();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfTwoEquations(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateSwitchingCoefficientsOfXAndY_1(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();

		final var x_1 = simpleEquation_1.coefficientOfY();
		final var y_1 = simpleEquation_1.coefficientOfX();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(x_1, y_1, free_1);

		final var newSystemOfLinearEquations = new SystemOfTwoEquations(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateSwitchingCoefficientsOfY(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_1 = simpleEquation_1.coefficientOfX();
		final var y_1 = simpleEquation_2.coefficientOfY();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var x_2 = simpleEquation_2.coefficientOfX();
		final var y_2 = simpleEquation_1.coefficientOfY();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(x_1, y_1, free_1);
		final var newSimpleEquation_2 = new StandardEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfTwoEquations(newSimpleEquation_1, newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateSwitchingCoefficientsOfX(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_1 = simpleEquation_2.coefficientOfX();
		final var y_1 = simpleEquation_1.coefficientOfY();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var x_2 = simpleEquation_1.coefficientOfX();
		final var y_2 = simpleEquation_2.coefficientOfY();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(x_1, y_1, free_1);
		final var newSimpleEquation_2 = new StandardEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfTwoEquations(newSimpleEquation_1, newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateNegatingY_2(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var coefficientOfX = simpleEquation_2.coefficientOfX();
		final var negatedCoefficientOfY = simpleEquation_2.coefficientOfY() * -1;
		final var freeCoefficient = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardEquationParameters(coefficientOfX, negatedCoefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquations(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateNegatingY_1(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var coefficientOfX = simpleEquation_1.coefficientOfX();
		final var negatedCoefficientOfY = simpleEquation_1.coefficientOfY() * -1;
		final var freeCoefficient = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(coefficientOfX, negatedCoefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquations(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateNegatingX_2(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var negatedCoefficientOfX = simpleEquation_2.coefficientOfX() * -1;
		final var coefficientOfY = simpleEquation_2.coefficientOfY();
		final var freeCoefficient = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardEquationParameters(negatedCoefficientOfX, coefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquations(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateNegatingX_1(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var negatedCoefficientOfX = simpleEquation_1.coefficientOfX() * -1;
		final var coefficientOfY = simpleEquation_1.coefficientOfY();
		final var freeCoefficient = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(negatedCoefficientOfX, coefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquations(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateNegatingFreeCoefficient_2(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var coefficientOfX = simpleEquation_2.coefficientOfX();
		final var coefficientOfY = simpleEquation_2.coefficientOfY();
		final var negatedFreeCoefficient = simpleEquation_2.freeCoefficient() * -1;

		final var newSimpleEquation_2 = new StandardEquationParameters(coefficientOfX, coefficientOfY, negatedFreeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquations(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfTwoEquationsSolution generateNegatingFreeCoefficient_1(SystemOfTwoEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var coefficientOfX = simpleEquation_1.coefficientOfX();
		final var coefficientOfY = simpleEquation_1.coefficientOfY();
		final var negatedFreeCoefficient = simpleEquation_1.freeCoefficient() * -1;

		final var newSimpleEquation_1 = new StandardEquationParameters(coefficientOfX, coefficientOfY, negatedFreeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquations(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}
}
