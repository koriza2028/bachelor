package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.services.LcmFindingService;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;

public class SystemOfEquationsSolutionThroughYService {

	final LinearEquationSolutionService linearEquationSolutionService = new LinearEquationSolutionService();


	public SystemOfTwoEquationsCorrectSolution solveCorrectly(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findY(simpleEquation_1, simpleEquation_2);
		final var x = this.findX(simpleEquation_1, y);

		return new SystemOfTwoEquationsCorrectSolution(x, y);
		
	}

	private Fraction findX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation_1 = createEquationForX(simpleEquation_1, y);
		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}

	private StandardLinearEquationFractionParameters createEquationForX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var setY = y.multiplyBy(simpleEquation_1.coefficientOfY());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfX(), 1);
		final var b = setY.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}

	private Fraction findY(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForY(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForY(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var coefficientOfX_1 = simpleEquation_1.coefficientOfX();
		final var coefficientOfX_2 = simpleEquation_2.coefficientOfX();

		final var lcm = LcmFindingService.lcm(coefficientOfX_1, coefficientOfX_2);
		final var multiplicator_1 = lcm / coefficientOfX_1;
		final var multiplicator_2 = lcm / coefficientOfX_2;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyBy(multiplicator_2);

		return newSimpleEquation_1.substract(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}

	public SystemOfTwoEquationsDistractor solveReplacingWrongParameter(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findY(simpleEquation_1, simpleEquation_2);
		final var x = this.findXReplacingX(simpleEquation_1, y);

		return new SystemOfTwoEquationsDistractor(x, y);
	}

	private Fraction findXReplacingX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation_1 = createEquationForXReplacingX(simpleEquation_1, y);
		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}

	private StandardLinearEquationFractionParameters createEquationForXReplacingX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var setY = y.multiplyBy(simpleEquation_1.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfY(), 1);
		final var b = setY.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}

	public SystemOfTwoEquationsDistractor solveNegatingFreeCoefficient(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findYNegatingFreeCoefficient(simpleEquation_1, simpleEquation_2);
		final var x = this.findX(simpleEquation_1, y);

		return new SystemOfTwoEquationsDistractor(x, y);
	}

	private Fraction findYNegatingFreeCoefficient(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForYNegatingFreeCoefficient(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForYNegatingFreeCoefficient(StandardEquationParameters simpleEquation_1,
			StandardEquationParameters simpleEquation_2) {
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

	public SystemOfTwoEquationsDistractor solveIgnoringFreeCoefficientMultiplication(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findYIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		final var x = this.findX(simpleEquation_1, y);

		return new SystemOfTwoEquationsDistractor(x, y);
	}

	private Fraction findYIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForYIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForYIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
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