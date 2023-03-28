package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsNonNumericalSolution;

public class SystemOfEquationsSubstitutionSolutionThroughYService {

	final LinearEquationSolutionService linearEquationSolutionService = new LinearEquationSolutionService();

	public SystemOfTwoEquationsCorrectSolution solveCorrectly(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var standardLinearEquationForY = this.createEquationForY(simpleEquation_1, simpleEquation_2);

		return findSystemOfEquationsCorrectSolution(simpleEquation_1, standardLinearEquationForY);	
	}

	public SystemOfTwoEquationsDistractor solveReplacingWrongParameter(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();
		
		final var standardLinearEquationForY = this.createEquationForY(simpleEquation_1, simpleEquation_2);
		
		return findSystemOfEquationsSolutionReplacingWrongParameter(simpleEquation_1, standardLinearEquationForY);
	}
	
	public SystemOfTwoEquationsDistractor solveIgnoringFreeCoefficientMultiplication(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();
		
		final var standardLinearEquationForY = this.createEquationForYIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		
		return findSystemOfEquationsSolutionIgnoringFreeCoefficientMultiplication(simpleEquation_1,
				standardLinearEquationForY);
	}
	
	private StandardLinearEquationFractionParameters createEquationForY(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var xExpressedThroughY_1 = this.expressXThroughY(simpleEquation_1);
		return this.substituteX(xExpressedThroughY_1, simpleEquation_2);
	}
	
	private StandardLinearEquationFractionParameters createEquationForYIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var xExpressedThroughY_1 = this.expressXThroughY(simpleEquation_1);
		return this.substituteXIgnoringFreeCoefficientMultiplication(xExpressedThroughY_1, simpleEquation_2);
	}

	private SystemOfTwoEquationsCorrectSolution findSystemOfEquationsCorrectSolution(final StandardEquationParameters simpleEquation_1, final StandardLinearEquationFractionParameters standardLinearEquationForY) {
		double aForY = standardLinearEquationForY.a().toDouble();
		double bForY = standardLinearEquationForY.b().toDouble();

		if (aForY == 0 && bForY == 0) {
			return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.R, null, null);
		} else if (aForY == 0 && bForY == 0) {
			return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null);
		} else {
			final var y = linearEquationSolutionService.solve(standardLinearEquationForY);
			final var x = this.findX(simpleEquation_1, y);

			return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y);
		}
	}

	private SystemOfTwoEquationsDistractor findSystemOfEquationsSolutionReplacingWrongParameter(final StandardEquationParameters simpleEquation_1, final StandardLinearEquationFractionParameters standardLinearEquationForY) {
		double aForY = standardLinearEquationForY.a().toDouble();
		double bForY = standardLinearEquationForY.b().toDouble();

		if (aForY == 0 && bForY == 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.R, null, null, SystemOfEquationsErrorType.SUBSTITUTION_REPLACE_WRONG_PARAMETER_Y);
		} else if (aForY == 0 && bForY == 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null, SystemOfEquationsErrorType.SUBSTITUTION_REPLACE_WRONG_PARAMETER_Y);
		} else {
			final var y = linearEquationSolutionService.solve(standardLinearEquationForY);
			final var x = this.findXReplacingX(simpleEquation_1, y);

			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsErrorType.SUBSTITUTION_REPLACE_WRONG_PARAMETER_Y);
		}
	}

	private SystemOfTwoEquationsDistractor findSystemOfEquationsSolutionIgnoringFreeCoefficientMultiplication(
			final StandardEquationParameters simpleEquation_1,
			final StandardLinearEquationFractionParameters standardLinearEquationForY) {
		double aForY = standardLinearEquationForY.a().toDouble();
		double bForY = standardLinearEquationForY.b().toDouble();

		if (aForY == 0 && bForY == 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.R, null, null,SystemOfEquationsErrorType.SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y);
		} else if (aForY == 0 && bForY == 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null, SystemOfEquationsErrorType.SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y);
		} else {
			final var y = linearEquationSolutionService.solve(standardLinearEquationForY);
			final var x = this.findX(simpleEquation_1, y);

			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsErrorType.SUBSTITUTION_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y);
		}
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

	private Fraction findX(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var standardLinearEquation_1 = createEquationForX(simpleEquation_1, x);
		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}
	
	private Fraction findXReplacingX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation = createEquationForXReplacingX(simpleEquation_1, y);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForX(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var setX = x.multiplyBy(simpleEquation_1.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfY(), 1);
		final var b = setX.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}

	private StandardLinearEquationFractionParameters createEquationForXReplacingX(StandardEquationParameters simpleEquation, Fraction y) {
		final var setY = y.multiplyBy(simpleEquation.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation.coefficientOfY(), 1);
		final var b = setY.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}
}
