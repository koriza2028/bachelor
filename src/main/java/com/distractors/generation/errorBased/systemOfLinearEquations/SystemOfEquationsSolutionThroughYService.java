package com.distractors.generation.errorBased.systemOfLinearEquations;

import com.distractors.generation.errorBased.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.errorBased.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.Fraction;
import com.distractors.generation.general.LcmFindingService;

public class SystemOfEquationsSolutionThroughYService {

	final LinearEquationSolutionService linearEquationSolutionService = new LinearEquationSolutionService();


	public SystemOfEquationsSolution solveCorrectly(SystemOfEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findY(simpleEquation_1, simpleEquation_2);
		final var x = this.findX(simpleEquation_1, y);

		return new SystemOfEquationsSolution(x, y);
		
	}

	private Fraction findX(StandardLinearEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation_1 = createEquationForX(simpleEquation_1, y);
		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}

	private StandardLinearEquationFractionParameters createEquationForX(StandardLinearEquationParameters simpleEquation_1, Fraction y) {
		final var setY = y.multiplyBy(simpleEquation_1.coefficientOfY());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfX(), 1);
		final var b = setY.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}

	private Fraction findY(StandardLinearEquationParameters simpleEquation_1, StandardLinearEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForY(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForY(StandardLinearEquationParameters simpleEquation_1, StandardLinearEquationParameters simpleEquation_2) {
		final var coefficientOfX_1 = simpleEquation_1.coefficientOfX();
		final var coefficientOfX_2 = simpleEquation_2.coefficientOfX();

		final var lcm = LcmFindingService.lcm(coefficientOfX_1, coefficientOfX_2);
		final var multiplicator_1 = lcm / coefficientOfX_1;
		final var multiplicator_2 = lcm / coefficientOfX_2;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyBy(multiplicator_2);

		return newSimpleEquation_1.substract(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}

	public SystemOfEquationsSolution solveIncorrectly(SystemOfEquations equationParameters) {
		final var randomError = SystemOfEquationsErrorType.randomError();

		switch (randomError) {
			case IGNORE_FREE_COEFFICIENT_MULTIPLICATION:
				return this.solveIgnoringFreeCoefficientMultiplication(equationParameters);
			case NEGATE_FREE_COEFFICIENT:
				return this.solveNegatingFreeCoefficient(equationParameters);
			case REPLACE_WRONG_PARAMETER:
				return this.solveReplacingWrongParameter(equationParameters);
			default:
				throw new IllegalArgumentException("Unknown error type.");
		}
	}

	private SystemOfEquationsSolution solveReplacingWrongParameter(SystemOfEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findY(simpleEquation_1, simpleEquation_2);
		final var x = this.findXReplacingX(simpleEquation_1, y);

		return new SystemOfEquationsSolution(x, y);
	}

	private Fraction findXReplacingX(StandardLinearEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation_1 = createEquationForXReplacingX(simpleEquation_1, y);
		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}

	private StandardLinearEquationFractionParameters createEquationForXReplacingX(StandardLinearEquationParameters simpleEquation_1, Fraction y) {
		final var setY = y.multiplyBy(simpleEquation_1.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfY(), 1);
		final var b = setY.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}

	private SystemOfEquationsSolution solveNegatingFreeCoefficient(SystemOfEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findYNegatingFreeCoefficient(simpleEquation_1, simpleEquation_2);
		final var x = this.findX(simpleEquation_1, y);

		return new SystemOfEquationsSolution(x, y);
	}

	private Fraction findYNegatingFreeCoefficient(StandardLinearEquationParameters simpleEquation_1, StandardLinearEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForYNegatingFreeCoefficient(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForYNegatingFreeCoefficient(StandardLinearEquationParameters simpleEquation_1,
			StandardLinearEquationParameters simpleEquation_2) {
		final var coefficientOfX_1 = simpleEquation_1.coefficientOfX();
		final var coefficientOfX_2 = simpleEquation_2.coefficientOfX();

		final var lcm = LcmFindingService.lcm(coefficientOfX_1, coefficientOfX_2);
		final var multiplicator_1 = lcm / coefficientOfX_1;
		final var multiplicator_2 = lcm / coefficientOfX_2;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyBy(multiplicator_2);

		final var standardLinearEquation = newSimpleEquation_1.substract(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
		final var b = standardLinearEquation.b().multiplyBy(-1);

		return new StandardLinearEquationFractionParameters(standardLinearEquation.a(), b);
	}

	private SystemOfEquationsSolution solveIgnoringFreeCoefficientMultiplication(SystemOfEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findYIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		final var x = this.findX(simpleEquation_1, y);

		return new SystemOfEquationsSolution(x, y);
	}

	private Fraction findYIgnoringFreeCoefficientMultiplication(StandardLinearEquationParameters simpleEquation_1, StandardLinearEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForYIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForYIgnoringFreeCoefficientMultiplication(StandardLinearEquationParameters simpleEquation_1, StandardLinearEquationParameters simpleEquation_2) {
		final var coefficientOfX_1 = simpleEquation_1.coefficientOfX();
		final var coefficientOfX_2 = simpleEquation_2.coefficientOfX();

		final var lcm = LcmFindingService.lcm(coefficientOfX_1, coefficientOfX_2);
		final var multiplicator_1 = lcm / coefficientOfX_1;
		final var multiplicator_2 = lcm / coefficientOfX_2;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyIgnoringFreeCoefficientBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyIgnoringFreeCoefficientBy(multiplicator_2);

		return newSimpleEquation_1.substract(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}
}