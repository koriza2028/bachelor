package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;

public class SystemOfEquationsSubstitutionSolutionThroughYService {

	final LinearEquationSolutionService linearEquationSolutionService = new LinearEquationSolutionService();

	public SystemOfTwoEquationsCorrectSolution solveCorrectly(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findYCorrectly(simpleEquation_1, simpleEquation_2);
		final var x = this.findX(simpleEquation_1, y);

		return new SystemOfTwoEquationsCorrectSolution(x, y);
		
	}

	public SystemOfTwoEquationsDistractor solveReplacingWrongParameter(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var y = this.findYCorrectly(simpleEquation_1, simpleEquation_2);
		final var x = this.findXReplacingX(simpleEquation_1, y);

		return new SystemOfTwoEquationsDistractor(x, y, SystemOfEquationsErrorType.SUBSTITUTION_REPLACE_WRONG_PARAMETER_Y);
	}

	public SystemOfTwoEquationsDistractor solveIgnoringFreeCoefficientMultiplication(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();
		
		final var y = this.findYIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		final var x = this.findX(simpleEquation_1, y);
		
		return new SystemOfTwoEquationsDistractor(x, y, SystemOfEquationsErrorType.SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y);
	}

	private Fraction findX(StandardEquationParameters simpleEquation_1, Fraction x) {
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

	private Fraction findYCorrectly(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForY(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForY(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var xExpressedThroughY_1 = this.expressXThroughY(simpleEquation_1);
		return this.substituteX(xExpressedThroughY_1, simpleEquation_2);
	}

	private StandardLinearEquationFractionParameters expressXThroughY(StandardEquationParameters simpleEquation) {
		final var aNominator = simpleEquation.coefficientOfY() * -1;
		final var aDenominator = simpleEquation.coefficientOfX();
		final var bNominator = simpleEquation.freeCoefficient() * -1;
		final var bDenominator = simpleEquation.coefficientOfX();
		final var a = new Fraction(aNominator, aDenominator);
		final var b = new Fraction(bNominator, bDenominator);
		return new StandardLinearEquationFractionParameters(a, b);
	}

	private StandardLinearEquationFractionParameters substituteX(StandardLinearEquationFractionParameters xExpressedThroughY, StandardEquationParameters simpleEquation) {
		final var coefficientOfX = simpleEquation.coefficientOfY();
		final var coefficientOfY = simpleEquation.coefficientOfX();
		final var coeffisientOfYAsFraction = new Fraction(coefficientOfY, 1);
		final var freeCoefficient = simpleEquation.freeCoefficient();
		final var freeCoefficientAsFraction = new Fraction(freeCoefficient, 1);

		final var a = xExpressedThroughY.a().multiplyBy(coefficientOfX).add(coeffisientOfYAsFraction);
		final var b = xExpressedThroughY.b().multiplyBy(coefficientOfX).add(freeCoefficientAsFraction);
		return new StandardLinearEquationFractionParameters(a, b);
	}

	private Fraction findXReplacingX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation = createEquationForXReplacingX(simpleEquation_1, y);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForXReplacingX(StandardEquationParameters simpleEquation, Fraction y) {
		final var setY = y.multiplyBy(simpleEquation.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation.coefficientOfY(), 1);
		final var b = setY.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}


	private Fraction findYIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForYIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForYIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var xExpressedThroughY_1 = this.expressXThroughY(simpleEquation_1);
		return this.substituteXIgnoringFreeCoefficientMultiplication(xExpressedThroughY_1, simpleEquation_2);
	}

	private StandardLinearEquationFractionParameters substituteXIgnoringFreeCoefficientMultiplication(StandardLinearEquationFractionParameters xExpressedThroughY, StandardEquationParameters simpleEquation) {
		final var coefficientOfX = simpleEquation.coefficientOfX();
		final var coefficientOfY = simpleEquation.coefficientOfY();
		final var coefficientOfYAsFraction = new Fraction(coefficientOfY, 1);
		final var freeCoefficient = simpleEquation.freeCoefficient();
		final var freeCoefficientAsFraction = new Fraction(freeCoefficient, 1);

		final var a = xExpressedThroughY.a().multiplyBy(coefficientOfX).add(coefficientOfYAsFraction);
		final var b = xExpressedThroughY.b().add(freeCoefficientAsFraction);
		return new StandardLinearEquationFractionParameters(a, b);
	}
}
