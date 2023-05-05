package com.distractors.generation.systemsOfTwoEquations.basedOnWrongParameters;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsAnswers;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;
import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsAdditiveSolutionThroughXService;

public class SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService {

	final SystemOfEquationsAdditiveSolutionThroughXService solutionThroughXService = new SystemOfEquationsAdditiveSolutionThroughXService();

	public SystemOfTwoEquationsAnswers generateDistractors(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var correctDistractor = solutionThroughXService.solveCorrectly(systemOfLinearEquations);

		var distractors = new ArrayList<SystemOfTwoEquationsSolution> ();
		distractors.add(correctDistractor);

		final var distractor_1 = this.generateDifferentDistractor(systemOfLinearEquations, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(systemOfLinearEquations, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(systemOfLinearEquations, distractors);

		return new SystemOfTwoEquationsAnswers(correctDistractor, distractor_1, distractor_2, distractor_3);
	}
	
	private SystemOfTwoEquationsDistractor generateDifferentDistractor(final SystemOfTwoEquationsParameters systemOfLinearEquations, List<SystemOfTwoEquationsSolution> distractors) {
		SystemOfTwoEquationsDistractor distractor;
		do {
			distractor = this.generateDistractor(systemOfLinearEquations);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}
	
	private SystemOfTwoEquationsDistractor generateDistractor(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var randomChangeType = SystemOfEquationsParametersChangeType.randomChangeType();
		
		return generateDistractorWithChosenParameterChangeType(systemOfLinearEquations, randomChangeType);
	}

	private boolean isDistractorInvalid(SystemOfTwoEquationsDistractor possibleDistractor, List<SystemOfTwoEquationsSolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	public SystemOfTwoEquationsDistractor generateDistractorWithChosenParameterChangeType(SystemOfTwoEquationsParameters systemOfLinearEquations, final SystemOfEquationsParametersChangeType randomChangeType) {
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
			case SWAP_COEFFICIENTS_OF_X:
				return this.generateSwitchingCoefficientsOfX(systemOfLinearEquations);
			case SWAP_COEFFICIENTS_OF_Y:
				return this.generateSwitchingCoefficientsOfY(systemOfLinearEquations);
			case SWAP_X_AND_Y_1:
				return this.generateSwitchingCoefficientsOfXAndY_1(systemOfLinearEquations);
			case SWAP_X_AND_Y_2:
				return this.generateSwitchingCoefficientsOfXAndY_2(systemOfLinearEquations);
			case SWAP_X_AND_Y_BOTH:
				return this.generateSwitchingCoefficientsOfXAndYBoth(systemOfLinearEquations);
			default:
				throw new IllegalArgumentException("Unknown Distractor approach");
		}
	}

	private SystemOfTwoEquationsDistractor generateSwitchingCoefficientsOfXAndYBoth(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_1 = simpleEquation_1.coefficientOfX();
		final var y_1 = simpleEquation_1.coefficientOfY();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var x_2 = simpleEquation_2.coefficientOfX();
		final var y_2 = simpleEquation_2.coefficientOfY();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(x_1, y_1, free_1);
		final var newSimpleEquation_2 = new StandardEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(newSimpleEquation_1, newSimpleEquation_2);

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.SWAP_X_AND_Y_BOTH);
	}

	private SystemOfTwoEquationsDistractor createDistractorFromSolution(final SystemOfTwoEquationsCorrectSolution solution, SystemOfEquationsParametersChangeType impact) {
		final var nonNumericalSolution = solution.nonNumericalSolution();
		final var x = solution.x();
		final var y = solution.y();
		return new SystemOfTwoEquationsDistractor(nonNumericalSolution, x, y, impact);
	}

	private SystemOfTwoEquationsDistractor generateSwitchingCoefficientsOfXAndY_2(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();

		final var x_2 = simpleEquation_2.coefficientOfX();
		final var y_2 = simpleEquation_2.coefficientOfY();
		final var free_2 = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardEquationParameters(x_2, y_2, free_2);

		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.SWAP_X_AND_Y_2);
	}

	private SystemOfTwoEquationsDistractor generateSwitchingCoefficientsOfXAndY_1(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();

		final var x_1 = simpleEquation_1.coefficientOfX();
		final var y_1 = simpleEquation_1.coefficientOfY();
		final var free_1 = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(x_1, y_1, free_1);

		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.SWAP_X_AND_Y_1);
	}

	private SystemOfTwoEquationsDistractor generateSwitchingCoefficientsOfY(SystemOfTwoEquationsParameters systemOfLinearEquations) {
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

		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(newSimpleEquation_1, newSimpleEquation_2);

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.SWAP_COEFFICIENTS_OF_Y);
	}

	private SystemOfTwoEquationsDistractor generateSwitchingCoefficientsOfX(SystemOfTwoEquationsParameters systemOfLinearEquations) {
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

		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(newSimpleEquation_1, newSimpleEquation_2);

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.SWAP_COEFFICIENTS_OF_X);
	}

	private SystemOfTwoEquationsDistractor generateNegatingY_2(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var coefficientOfX = simpleEquation_2.coefficientOfX();
		final var negatedCoefficientOfY = simpleEquation_2.coefficientOfY() * -1;
		final var freeCoefficient = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardEquationParameters(coefficientOfX, negatedCoefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.NEGATE_Y_2);
	}

	private SystemOfTwoEquationsDistractor generateNegatingY_1(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var coefficientOfX = simpleEquation_1.coefficientOfX();
		final var negatedCoefficientOfY = simpleEquation_1.coefficientOfY() * -1;
		final var freeCoefficient = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(coefficientOfX, negatedCoefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.NEGATE_Y_1);
	}

	private SystemOfTwoEquationsDistractor generateNegatingX_2(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var negatedCoefficientOfX = simpleEquation_2.coefficientOfX() * -1;
		final var coefficientOfY = simpleEquation_2.coefficientOfY();
		final var freeCoefficient = simpleEquation_2.freeCoefficient();

		final var newSimpleEquation_2 = new StandardEquationParameters(negatedCoefficientOfX, coefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.NEGATE_X_2);
	}

	private SystemOfTwoEquationsDistractor generateNegatingX_1(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var negatedCoefficientOfX = simpleEquation_1.coefficientOfX() * -1;
		final var coefficientOfY = simpleEquation_1.coefficientOfY();
		final var freeCoefficient = simpleEquation_1.freeCoefficient();

		final var newSimpleEquation_1 = new StandardEquationParameters(negatedCoefficientOfX, coefficientOfY, freeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.NEGATE_X_1);
	}

	private SystemOfTwoEquationsDistractor generateNegatingFreeCoefficient_2(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_2 = systemOfLinearEquations.equation_2();
		final var coefficientOfX = simpleEquation_2.coefficientOfX();
		final var coefficientOfY = simpleEquation_2.coefficientOfY();
		final var negatedFreeCoefficient = simpleEquation_2.freeCoefficient() * -1;

		final var newSimpleEquation_2 = new StandardEquationParameters(coefficientOfX, coefficientOfY, negatedFreeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(systemOfLinearEquations.equation_1(), newSimpleEquation_2);

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.NEGATE_FREE_COEFFICIENT_2);
	}

	private SystemOfTwoEquationsDistractor generateNegatingFreeCoefficient_1(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		final var simpleEquation_1 = systemOfLinearEquations.equation_1();
		final var coefficientOfX = simpleEquation_1.coefficientOfX();
		final var coefficientOfY = simpleEquation_1.coefficientOfY();
		final var negatedFreeCoefficient = simpleEquation_1.freeCoefficient() * -1;

		final var newSimpleEquation_1 = new StandardEquationParameters(coefficientOfX, coefficientOfY, negatedFreeCoefficient);
		final var newSystemOfLinearEquations = new SystemOfTwoEquationsParameters(newSimpleEquation_1, systemOfLinearEquations.equation_2());

		final var solution = this.solutionThroughXService.solveCorrectly(newSystemOfLinearEquations);
		return createDistractorFromSolution(solution, SystemOfEquationsParametersChangeType.NEGATE_FREE_COEFFICIENT_1);
	}
}
