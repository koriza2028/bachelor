package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;

public class SystemOfEquationsEqualizationSolutionThroughYService {

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

		return new SystemOfTwoEquationsDistractor(x, y, SystemOfEquationsErrorType.EQUALIZATION_REPLACE_WRONG_PARAMETER_Y);
	}

	private Fraction findYCorrectly(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var standardLinearEquation = this.createEquationForY(simpleEquation_1, simpleEquation_2);

		return linearEquationSolutionService.solve(standardLinearEquation);
	}
	
	private StandardLinearEquationFractionParameters createEquationForY(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var xExpressedThroughY_1 = this.expressXThroughY(simpleEquation_1);
		final var xExpressedThroughY_2 = this.expressXThroughY(simpleEquation_2);

		final var a = xExpressedThroughY_1.a().substract(xExpressedThroughY_2.a());
		final var b = xExpressedThroughY_1.b().substract(xExpressedThroughY_2.b());

		return new StandardLinearEquationFractionParameters(a, b);
	}

	private Fraction findX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation_1 = createEquationForX(simpleEquation_1, y);

		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}

	private StandardLinearEquationFractionParameters createEquationForX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var setX = y.multiplyBy(simpleEquation_1.coefficientOfY());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfY(), 1);
		final var b = setX.add(freeCoefficientFraction);

		return new StandardLinearEquationFractionParameters(a, b);
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

	private Fraction findXReplacingX(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var standardLinearEquation = createEquationForXReplacingX(simpleEquation_1, x);

		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForXReplacingX(StandardEquationParameters simpleEquation, Fraction y) {
		final var setY = y.multiplyBy(simpleEquation.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation.coefficientOfX(), 1);
		final var b = setY.add(freeCoefficientFraction);

		return new StandardLinearEquationFractionParameters(a, b);
	}
}
