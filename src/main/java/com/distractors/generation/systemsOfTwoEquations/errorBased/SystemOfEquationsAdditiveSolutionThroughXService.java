package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.services.LcmFindingService;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsNonNumericalSolution;

public class SystemOfEquationsAdditiveSolutionThroughXService {

	final LinearEquationSolutionService linearEquationSolutionService = new LinearEquationSolutionService();

	public SystemOfTwoEquationsCorrectSolution solveCorrectly(SystemOfTwoEquationsParameters equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var standardLinearEquationForX = this.createEquationForX(simpleEquation_1, simpleEquation_2);

		return findSystemOfEquationsCorrectSolution(simpleEquation_1, standardLinearEquationForX);
	}
	
	public SystemOfTwoEquationsDistractor solveIgnoringFreeCoefficientMultiplication(SystemOfTwoEquationsParameters equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();
		
		final var standardLinearEquationForX = this.createEquationForXIgnoringFreeCoefficientMultiplication(simpleEquation_1, simpleEquation_2);
		
		return findSystemOfEquationsSolutionIgnoringFreeCoefficientMultiplication(simpleEquation_1,
				standardLinearEquationForX);
	}
	
	public SystemOfTwoEquationsDistractor solveReplacingWrongParameter(SystemOfTwoEquationsParameters equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();
		
		final var standardLinearEquationForX = this.createEquationForX(simpleEquation_1, simpleEquation_2);
		
		return findSystemOfEquationsSolutionReplacingWrongParameter(simpleEquation_1, standardLinearEquationForX);
	}
	
	private StandardLinearEquationFractionParameters createEquationForX(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var coefficientOfY_1 = simpleEquation_1.coefficientOfY();
		final var coefficientOfY_2 = simpleEquation_2.coefficientOfY();
		
		final var lcm = LcmFindingService.lcm(coefficientOfY_1, coefficientOfY_2);
		final var multiplicator_1 = lcm / coefficientOfY_1;
		final var multiplicator_2 = lcm / coefficientOfY_2 * -1;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyBy(multiplicator_2);
		
		return newSimpleEquation_1.add(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}
	
	private StandardLinearEquationFractionParameters createEquationForXIgnoringFreeCoefficientMultiplication(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var coefficientOfY_1 = simpleEquation_1.coefficientOfY();
		final var coefficientOfY_2 = simpleEquation_2.coefficientOfY();
		
		final var lcm = LcmFindingService.lcm(coefficientOfY_1, coefficientOfY_2);
		final var multiplicator_1 = lcm / coefficientOfY_1;
		final var multiplicator_2 = lcm / coefficientOfY_2 * -1;
		
		final var newSimpleEquation_1 = simpleEquation_1.multiplyIgnoringFreeCoefficientBy(multiplicator_1);
		final var newSimpleEquation_2 = simpleEquation_2.multiplyIgnoringFreeCoefficientBy(multiplicator_2);
		
		return newSimpleEquation_1.add(newSimpleEquation_2).toStandardLinearEquationFractionParameters();
	}

	private SystemOfTwoEquationsCorrectSolution findSystemOfEquationsCorrectSolution(final StandardEquationParameters simpleEquation_1, final StandardLinearEquationFractionParameters standardLinearEquationForX) {
		double aForX = standardLinearEquationForX.a().toDouble();
		double bForX = standardLinearEquationForX.b().toDouble();
		
		if (aForX == 0 && bForX == 0) {
			return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.R, null, null);
		} else if (aForX == 0 && bForX != 0) {
			return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null);
		} else {
			final var x = linearEquationSolutionService.solve(standardLinearEquationForX);
			final var y = this.findY(simpleEquation_1, x);
			
			return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y);
		}
	}
	
	private SystemOfTwoEquationsDistractor findSystemOfEquationsSolutionIgnoringFreeCoefficientMultiplication(final StandardEquationParameters simpleEquation_1, final StandardLinearEquationFractionParameters standardLinearEquationForX) {
		double aForX = standardLinearEquationForX.a().toDouble();
		double bForX = standardLinearEquationForX.b().toDouble();
		
		if (aForX == 0 && bForX == 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.R, null, null, SystemOfEquationsErrorType.ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X);
		} else if (aForX == 0 && bForX != 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null, SystemOfEquationsErrorType.ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X);
		} else {
			final var x = linearEquationSolutionService.solve(standardLinearEquationForX);
			final var y = this.findY(simpleEquation_1, x);
			
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsErrorType.ADDITIVE_IGNORE_FREE_COEFFICIENT_MULTIPLICATION_X);
		}
	}

	private SystemOfTwoEquationsDistractor findSystemOfEquationsSolutionReplacingWrongParameter(final StandardEquationParameters simpleEquation_1, final StandardLinearEquationFractionParameters standardLinearEquationForX) {
		double aForX = standardLinearEquationForX.a().toDouble();
		double bForX = standardLinearEquationForX.b().toDouble();
		
		if (aForX == 0 && bForX == 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.R, null, null, SystemOfEquationsErrorType.ADDITIVE_REPLACE_WRONG_PARAMETER_X);
		} else if (aForX == 0 && bForX != 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null, SystemOfEquationsErrorType.ADDITIVE_REPLACE_WRONG_PARAMETER_X);
		} else {
			final var x = linearEquationSolutionService.solve(standardLinearEquationForX);
			final var y = this.findYReplacingY(simpleEquation_1, x);
			
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsErrorType.ADDITIVE_REPLACE_WRONG_PARAMETER_X);
		}
	}

	private Fraction findY(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var standardLinearEquation_1 = createEquationForY(simpleEquation_1, x);
		return linearEquationSolutionService.solve(standardLinearEquation_1);
	}
	
	private Fraction findYReplacingY(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var standardLinearEquation = createEquationForYReplacingY(simpleEquation_1, x);
		return linearEquationSolutionService.solve(standardLinearEquation);
	}

	private StandardLinearEquationFractionParameters createEquationForY(StandardEquationParameters simpleEquation_1, Fraction x) {
		final var setX = x.multiplyBy(simpleEquation_1.coefficientOfX());
		final var freeCoefficientFraction = new Fraction(simpleEquation_1.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation_1.coefficientOfY(), 1);
		final var b = setX.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}

	private StandardLinearEquationFractionParameters createEquationForYReplacingY(StandardEquationParameters simpleEquation, Fraction x) {
		final var setX = x.multiplyBy(simpleEquation.coefficientOfY());
		final var freeCoefficientFraction = new Fraction(simpleEquation.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation.coefficientOfX(), 1);
		final var b = setX.add(freeCoefficientFraction);
		final var standardLinearEquation = new StandardLinearEquationFractionParameters(a, b);
		return standardLinearEquation;
	}
}
