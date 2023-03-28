package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.services.LcmFindingService;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsNonNumericalSolution;

public class SystemOfEquationsAdditiveSolutionThroughYService {

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
		final var coefficientOfX_1 = simpleEquation_1.coefficientOfX();
		final var coefficientOfX_2 = simpleEquation_2.coefficientOfX();
		
		final var lcm = LcmFindingService.lcm(coefficientOfX_1, coefficientOfX_2);
		final var multiplicator_1 = lcm / coefficientOfX_1;
		final var multiplicator_2 = lcm / coefficientOfX_2 * -1;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyBy(multiplicator_2);
		
		return newSimpleEquation_1.add(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}
	
	private StandardLinearEquationFractionParameters createEquationForYIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var coefficientOfX_1 = simpleEquation_1.coefficientOfX();
		final var coefficientOfX_2 = simpleEquation_2.coefficientOfX();
		
		final var lcm = LcmFindingService.lcm(coefficientOfX_1, coefficientOfX_2);
		final var multiplicator_1 = lcm / coefficientOfX_1;
		final var multiplicator_2 = lcm / coefficientOfX_2 * -1;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyIgnoringFreeCoefficientBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyIgnoringFreeCoefficientBy(multiplicator_2);
		
		return newSimpleEquation_1.add(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}

	private SystemOfTwoEquationsCorrectSolution findSystemOfEquationsCorrectSolution(final StandardEquationParameters simpleEquation_1, final StandardLinearEquationFractionParameters standardLinearEquationForY) {
		double aForY = standardLinearEquationForY.a().toDouble();
		double bForY = standardLinearEquationForY.b().toDouble();

		if (aForY == 0 && bForY == 0) {
			return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.R, null, null);
		} else if (aForY == 0 && bForY != 0) {
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
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.R, null, null, SystemOfEquationsErrorType.ADDITIVE_REPLACE_WRONG_PARAMETER_Y);
		} else if (aForY == 0 && bForY != 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null, SystemOfEquationsErrorType.ADDITIVE_REPLACE_WRONG_PARAMETER_Y);
		} else {
			final var y = linearEquationSolutionService.solve(standardLinearEquationForY);
			final var x = this.findXReplacingX(simpleEquation_1, y);
			
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsErrorType.ADDITIVE_REPLACE_WRONG_PARAMETER_Y);
		}
	}
	
	private SystemOfTwoEquationsDistractor findSystemOfEquationsSolutionIgnoringFreeCoefficientMultiplication(final StandardEquationParameters simpleEquation_1, final StandardLinearEquationFractionParameters standardLinearEquationForY) {
		double aForY = standardLinearEquationForY.a().toDouble();
		double bForY = standardLinearEquationForY.b().toDouble();
		
		if (aForY == 0 && bForY == 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.R, null, null, SystemOfEquationsErrorType.ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y);
		} else if (aForY == 0 && bForY != 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null, SystemOfEquationsErrorType.ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y);
		} else {
			final var y = linearEquationSolutionService.solve(standardLinearEquationForY);
			final var x = this.findX(simpleEquation_1, y);
			
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsErrorType.ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_Y);
		}
	}

	private Fraction findX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation_1 = createEquationForX(simpleEquation_1, y);
		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}
	
	private Fraction findXReplacingX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var standardLinearEquation_1 = createEquationForXReplacingX(simpleEquation_1, y);
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

	private StandardLinearEquationFractionParameters createEquationForXReplacingX(StandardEquationParameters simpleEquation_1, Fraction y) {
		final var setY = y.multiplyBy(simpleEquation_1.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfX(), 1);
		final var b = setY.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}
}