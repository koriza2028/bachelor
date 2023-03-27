package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;

public class SystemOfEquationsSubstitutionSolutionThroughXService {

	final LinearEquationSolutionService linearEquationSolutionService = new LinearEquationSolutionService();

	public SystemOfTwoEquationsCorrectSolution solveCorrectly(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var x = this.findXCorrectly(simpleEquation_1, simpleEquation_2);
		final var y = this.findY(simpleEquation_1, x);

		return new SystemOfTwoEquationsCorrectSolution(x, y);
		
	}

	public SystemOfTwoEquationsDistractor solveReplacingWrongParameter(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var x = this.findXCorrectly(simpleEquation_1, simpleEquation_2);
		final var y = this.findYReplacingY(simpleEquation_1, x);

		return new SystemOfTwoEquationsDistractor(x, y, SystemOfEquationsErrorType.SUBSTITUTION_REPLACE_WRONG_PARAMETER_X);
	}

	public SystemOfTwoEquationsDistractor solveIgnoringFreeCoefficientMultiplication(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();
		
		final var x = this.findXIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		final var y = this.findY(simpleEquation_1, x);
		
		return new SystemOfTwoEquationsDistractor(x, y, SystemOfEquationsErrorType.SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X);
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

	private Fraction findXCorrectly(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForX(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForX(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var yExpressedThroughX_1 = this.expressYThroughX(simpleEquation_1);
		return this.substituteY(yExpressedThroughX_1, simpleEquation_2);
	}

	private StandardLinearEquationFractionParameters expressYThroughX(StandardEquationParameters simpleEquation) {
		final var aNominator = simpleEquation.coefficientOfX() * -1;
		final var aDenominator = simpleEquation.coefficientOfY();
		final var bNominator = simpleEquation.freeCoefficient() * -1;
		final var bDenominator = simpleEquation.coefficientOfY();
		final var a = new Fraction(aNominator, aDenominator);
		final var b = new Fraction(bNominator, bDenominator);
		return new StandardLinearEquationFractionParameters(a, b);
	}

	private StandardLinearEquationFractionParameters substituteY(StandardLinearEquationFractionParameters yExpressedThroughX, StandardEquationParameters simpleEquation) {
		final var coefficientOfY = simpleEquation.coefficientOfY();
		final var coefficientOfX = simpleEquation.coefficientOfX();
		final var coeffisientOfXAsFraction = new Fraction(coefficientOfX, 1);
		final var freeCoefficient = simpleEquation.freeCoefficient();
		final var freeCoefficientAsFraction = new Fraction(freeCoefficient, 1);

		final var a = yExpressedThroughX.a().multiplyBy(coefficientOfY).add(coeffisientOfXAsFraction);
		final var b = yExpressedThroughX.b().multiplyBy(coefficientOfY).add(freeCoefficientAsFraction);
		return new StandardLinearEquationFractionParameters(a, b);
	}

	private Fraction findYReplacingY(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var standardLinearEquation = createEquationForYReplacingY(simpleEquation_1, x);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForYReplacingY(StandardEquationParameters simpleEquation, Fraction x) {
		final var setX = x.multiplyBy(simpleEquation.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation.coefficientOfX(), 1);
		final var b = setX.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}


	private Fraction findXIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForXIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForXIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var yExpressedThroughX_1 = this.expressYThroughX(simpleEquation_1);
		return this.substituteYIgnoringFreeCoefficientMultiplication(yExpressedThroughX_1, simpleEquation_2);
	}

	private StandardLinearEquationFractionParameters substituteYIgnoringFreeCoefficientMultiplication(StandardLinearEquationFractionParameters yExpressedThroughX, StandardEquationParameters simpleEquation) {
		final var coefficientOfY = simpleEquation.coefficientOfX();
		final var coefficientOfX = simpleEquation.coefficientOfX();
		final var coeffisientOfXAsFraction = new Fraction(coefficientOfX, 1);
		final var freeCoefficient = simpleEquation.freeCoefficient();
		final var freeCoefficientAsFraction = new Fraction(freeCoefficient, 1);

		final var a = yExpressedThroughX.a().multiplyBy(coefficientOfY).add(coeffisientOfXAsFraction);
		final var b = yExpressedThroughX.b().add(freeCoefficientAsFraction);
		return new StandardLinearEquationFractionParameters(a, b);
	}
}
