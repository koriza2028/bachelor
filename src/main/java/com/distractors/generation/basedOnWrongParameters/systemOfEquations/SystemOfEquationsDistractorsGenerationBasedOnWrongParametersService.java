package com.distractors.generation.basedOnWrongParameters.systemOfEquations;

import com.distractors.generation.errorBased.systemOfLinearEquations.StandardLinearEquationParameters;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquations;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquationsDistractors;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquationsSolution;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquationsSolutionThroughXService;

public class SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService {

	final SystemOfEquationsSolutionThroughXService solutionThroughXService = new SystemOfEquationsSolutionThroughXService();

	public SystemOfEquationsDistractors generateDistractors(SystemOfEquations systemOfLinearEquations) {
		final var correctSolution = solutionThroughXService.solveCorrectly(systemOfLinearEquations);

		SystemOfEquationsSolution distractor_1;
		SystemOfEquationsSolution distractor_2;
		SystemOfEquationsSolution distractor_3;

		do {
			distractor_1 = this.generateDistractor(systemOfLinearEquations);
		} while (distractor_1 == null || distractor_1.equals(correctSolution));

		do {
			distractor_2 = this.generateDistractor(systemOfLinearEquations);
		} while (distractor_2 == null || distractor_2.equals(correctSolution) || distractor_2.equals(distractor_1));

		do {
			distractor_3 = this.generateDistractor(systemOfLinearEquations);
		} while (distractor_3 == null || distractor_3.equals(correctSolution) || distractor_3.equals(distractor_1) || distractor_3.equals(distractor_2));

		return new SystemOfEquationsDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private SystemOfEquationsSolution generateDistractor(SystemOfEquations systemOfLinearEquations) {
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

	private SystemOfEquationsSolution generateSwitchingCoefficientsOfXAndYBoth(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_1 = simpleEquation_1.coefficientOfY();
		final var y_1 = simpleEquation_1.coefficientOfX();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var x_2 = simpleEquation_2.coefficientOfY();
		final var y_2 = simpleEquation_2.coefficientOfX();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_1 = new StandardLinearEquationParameters(x_1, y_1, free_1);
		final var newSimpleEquation_2 = new StandardLinearEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfEquations(newSimpleEquation_1, newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateSwitchingCoefficientsOfXAndY_2(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_2 = simpleEquation_2.coefficientOfY();
		final var y_2 = simpleEquation_2.coefficientOfX();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardLinearEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfEquations(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateSwitchingCoefficientsOfXAndY_1(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();

		final var x_1 = simpleEquation_1.coefficientOfY();
		final var y_1 = simpleEquation_1.coefficientOfX();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardLinearEquationParameters(x_1, y_1, free_1);

		final var newSystemOfLinearEquations = new SystemOfEquations(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateSwitchingCoefficientsOfY(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_1 = simpleEquation_1.coefficientOfX();
		final var y_1 = simpleEquation_2.coefficientOfY();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var x_2 = simpleEquation_2.coefficientOfX();
		final var y_2 = simpleEquation_1.coefficientOfY();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_1 = new StandardLinearEquationParameters(x_1, y_1, free_1);
		final var newSimpleEquation_2 = new StandardLinearEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfEquations(newSimpleEquation_1, newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateSwitchingCoefficientsOfX(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_1 = simpleEquation_2.coefficientOfX();
		final var y_1 = simpleEquation_1.coefficientOfY();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var x_2 = simpleEquation_1.coefficientOfX();
		final var y_2 = simpleEquation_2.coefficientOfY();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_1 = new StandardLinearEquationParameters(x_1, y_1, free_1);
		final var newSimpleEquation_2 = new StandardLinearEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfEquations(newSimpleEquation_1, newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateNegatingY_2(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var coefficientOfX = simpleEquation_2.coefficientOfX();
		final var negatedCoefficientOfY = simpleEquation_2.coefficientOfY() * -1;
		final var freeCoefficient = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardLinearEquationParameters(coefficientOfX, negatedCoefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfEquations(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateNegatingY_1(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var coefficientOfX = simpleEquation_1.coefficientOfX();
		final var negatedCoefficientOfY = simpleEquation_1.coefficientOfY() * -1;
		final var freeCoefficient = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardLinearEquationParameters(coefficientOfX, negatedCoefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfEquations(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateNegatingX_2(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var negatedCoefficientOfX = simpleEquation_2.coefficientOfX() * -1;
		final var coefficientOfY = simpleEquation_2.coefficientOfY();
		final var freeCoefficient = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardLinearEquationParameters(negatedCoefficientOfX, coefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfEquations(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateNegatingX_1(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var negatedCoefficientOfX = simpleEquation_1.coefficientOfX() * -1;
		final var coefficientOfY = simpleEquation_1.coefficientOfY();
		final var freeCoefficient = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardLinearEquationParameters(negatedCoefficientOfX, coefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfEquations(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateNegatingFreeCoefficient_2(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var coefficientOfX = simpleEquation_2.coefficientOfX();
		final var coefficientOfY = simpleEquation_2.coefficientOfY();
		final var negatedFreeCoefficient = simpleEquation_2.freeCoefficient() * -1;

		final var newSimpleEquation_2 = new StandardLinearEquationParameters(coefficientOfX, coefficientOfY, negatedFreeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfEquations(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}

	private SystemOfEquationsSolution generateNegatingFreeCoefficient_1(SystemOfEquations systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var coefficientOfX = simpleEquation_1.coefficientOfX();
		final var coefficientOfY = simpleEquation_1.coefficientOfY();
		final var negatedFreeCoefficient = simpleEquation_1.freeCoefficient() * -1;

		final var newSimpleEquation_1 = new StandardLinearEquationParameters(coefficientOfX, coefficientOfY, negatedFreeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfEquations(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		return this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
	}
}
