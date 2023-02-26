package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.services.LcmFindingService;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;

public class SystemOfEquationsSolutionThroughXService {

	final LinearEquationSolutionService linearEquationSolutionService = new LinearEquationSolutionService();

	public SystemOfTwoEquationsSolution solveCorrectly(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var x = this.findX(simpleEquation_1, simpleEquation_2);
		final var y = this.findY(simpleEquation_1, x);

		return new SystemOfTwoEquationsSolution(x, y);
		
	}

	private Fraction findY(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var standardLinearEquation_1 = createEquationForY(simpleEquation_1, x);
		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}

	private StandardLinearEquationFractionParameters createEquationForY(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var setX = x.multiplyBy(simpleEquation_1.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfY(), 1);
		final var b = setX.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}

	private Fraction findX(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForX(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForX(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var coefficientOfY_1 = simpleEquation_1.coefficientOfY();
		final var coefficientOfY_2 = simpleEquation_2.coefficientOfY();

		final var lcm = LcmFindingService.lcm(coefficientOfY_1, coefficientOfY_2);
		final var multiplicator_1 = lcm / coefficientOfY_1;
		final var multiplicator_2 = lcm / coefficientOfY_2;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyBy(multiplicator_2);

		return newSimpleEquation_1.substract(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}

	public SystemOfTwoEquationsSolution solveReplacingWrongParameter(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var x = this.findX(simpleEquation_1, simpleEquation_2);
		final var y = this.findYReplacingY(simpleEquation_1, x);

		return new SystemOfTwoEquationsSolution(x, y);
	}

	private Fraction findYReplacingY(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var standardLinearEquation = createEquationForYReplacingY(simpleEquation_1, x);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForYReplacingY(StandardEquationParameters simpleEquation, Fraction x) {
		final var setX = x.multiplyBy(simpleEquation.coefficientOfY());
		final var freeCoefficientFraction = new Fraction(simpleEquation.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation.coefficientOfX(), 1);
		final var b = setX.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}

	public SystemOfTwoEquationsSolution solveNegatingFreeCoefficient(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var x = this.findXNegatingFreeCoefficient(simpleEquation_1, simpleEquation_2);
		final var y = this.findY(simpleEquation_1, x);

		return new SystemOfTwoEquationsSolution(x, y);
	}

	private Fraction findXNegatingFreeCoefficient(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForXNegatingFreeCoefficient(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForXNegatingFreeCoefficient(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var coefficientOfY_1 = simpleEquation_1.coefficientOfY();
		final var coefficientOfY_2 = simpleEquation_2.coefficientOfY();

		final var lcm = LcmFindingService.lcm(coefficientOfY_1, coefficientOfY_2);
		final var multiplicator_1 = lcm / coefficientOfY_1;
		final var multiplicator_2 = lcm / coefficientOfY_2;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyBy(multiplicator_2);

		final var standardLinearEquation = newSimpleEquation_1.substract(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
		final var b = standardLinearEquation.b().multiplyBy(-1);

		return new StandardLinearEquationFractionParameters(standardLinearEquation.a(), b);
	}

	public SystemOfTwoEquationsSolution solveIgnoringFreeCoefficientMultiplication(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var x = this.findXIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		final var y = this.findY(simpleEquation_1, x);

		return new SystemOfTwoEquationsSolution(x, y);
	}

	private Fraction findXIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForXIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForXIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var coefficientOfY_1 = simpleEquation_1.coefficientOfY();
		final var coefficientOfY_2 = simpleEquation_2.coefficientOfY();

		final var lcm = LcmFindingService.lcm(coefficientOfY_1, coefficientOfY_2);
		final var multiplicator_1 = lcm / coefficientOfY_1;
		final var multiplicator_2 = lcm / coefficientOfY_2;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyIgnoringFreeCoefficientBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyIgnoringFreeCoefficientBy(multiplicator_2);

		return newSimpleEquation_1.substract(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}
}
