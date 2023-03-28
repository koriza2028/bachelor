package com.distractors.generation.systemsOfTwoEquations.errorBased;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsNonNumericalSolution;

public class SystemOfEquationsEqualizationSolutionThroughXService {

	final LinearEquationSolutionService linearEquationSolutionService = new LinearEquationSolutionService();

	public SystemOfTwoEquationsCorrectSolution solveCorrectly(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var standardLinearEquationForX = this.createEquationForX(simpleEquation_1, simpleEquation_2);

		return findSystemOfEquationsCorrectSolution(simpleEquation_1, standardLinearEquationForX);
	}

	public SystemOfTwoEquationsDistractor solveReplacingWrongParameter(SystemOfTwoEquations equationParameters) {
		final var simpleEquation_1 = equationParameters.equation_1();
		final var simpleEquation_2 = equationParameters.equation_2();

		final var standardLinearEquationForX = this.createEquationForX(simpleEquation_1, simpleEquation_2);

		return findSystemOfEquationsSolutionReplacingWrongParameter(simpleEquation_1, standardLinearEquationForX);
	}
	
	private StandardLinearEquationFractionParameters createEquationForX(StandardEquationParameters simpleEquation_1, StandardEquationParameters simpleEquation_2) {
		final var yExpressedThroughX_1 = this.expressYThroughX(simpleEquation_1);
		final var yExpressedThroughX_2 = this.expressYThroughX(simpleEquation_2);
		
		final var a = yExpressedThroughX_1.a().substract(yExpressedThroughX_2.a());
		final var b = yExpressedThroughX_1.b().substract(yExpressedThroughX_2.b());
		
		return new StandardLinearEquationFractionParameters(a, b);
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
	
	private SystemOfTwoEquationsDistractor findSystemOfEquationsSolutionReplacingWrongParameter(
			final StandardEquationParameters simpleEquation_1,
			final StandardLinearEquationFractionParameters standardLinearEquationForX) {
		double aForX = standardLinearEquationForX.a().toDouble();
		double bForX = standardLinearEquationForX.b().toDouble();
		
		if (aForX == 0 && bForX == 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.R, null, null, SystemOfEquationsErrorType.EQUALIZATION_REPLACE_WRONG_PARAMETER_X);
		} else if (aForX == 0 && bForX != 0) {
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null, SystemOfEquationsErrorType.EQUALIZATION_REPLACE_WRONG_PARAMETER_X);
		} else {
			final var x = linearEquationSolutionService.solve(standardLinearEquationForX);
			final var y = this.findYReplacingY(simpleEquation_1, x);
			
			return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsErrorType.EQUALIZATION_REPLACE_WRONG_PARAMETER_X);
		}
	}
	
	private StandardLinearEquationFractionParameters expressYThroughX(StandardEquationParameters simpleEquation) {
		final var aNominator = simpleEquation.coefficientOfY() * -1;
		final var aDenominator = simpleEquation.coefficientOfX();
		final var bNominator = simpleEquation.freeCoefficient() * -1;
		final var bDenominator = simpleEquation.coefficientOfX();
		final var a = new Fraction(aNominator, aDenominator);
		final var b = new Fraction(bNominator, bDenominator);
		
		return new StandardLinearEquationFractionParameters(a, b);
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
		final var a = new Fraction(simpleEquation_1.coefficientOfX(), 1);
		final var b = setX.add(freeCoefficientFraction);

		return new StandardLinearEquationFractionParameters(a, b);
	}

	private StandardLinearEquationFractionParameters createEquationForYReplacingY(StandardEquationParameters simpleEquation, Fraction x) {
		final var setX = x.multiplyBy(simpleEquation.coefficientOfY());
		final var freeCoefficientFraction = new Fraction(simpleEquation.freeCoefficient(), 1);
		final var a = new Fraction(simpleEquation.coefficientOfX(), 1);
		final var b = setX.add(freeCoefficientFraction);

		return new StandardLinearEquationFractionParameters(a, b);
	}

}
