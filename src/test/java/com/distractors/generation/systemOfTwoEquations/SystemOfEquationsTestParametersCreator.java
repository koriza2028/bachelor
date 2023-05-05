package com.distractors.generation.systemOfTwoEquations;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsNonNumericalSolution;

public class SystemOfEquationsTestParametersCreator {

	
	public static SystemOfTwoEquationsCorrectSolution createSystemOfEquationsCorrectSolution_1() {
		final var correctX = new Fraction(2, 1);
		final var correctY = new Fraction(5, 1);
		return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.NORMAL, correctX, correctY);
	}
	
	public static SystemOfTwoEquationsCorrectSolution createSystemOfEquationsSolutionIgnoringFreeCoefficientMultiplication() {
		final var correctX = new Fraction(-35, 11);
		final var correctY = new Fraction(281, 22);
		return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.NORMAL, correctX, correctY);
	}

	public static SystemOfTwoEquationsCorrectSolution createSystemOfEquationsSolutionReplacingWrongParameters() {
		final var correctX = new Fraction(2, 1);
		final var correctY = new Fraction(4, 1);
		return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.NORMAL, correctX, correctY);
	}
	
	public static SystemOfTwoEquationsParameters createSystemOfEquationsTestParameters_1() {
		final var first = new StandardEquationParameters(3, 2, -16);
		final var second = new StandardEquationParameters(7, 1, -19);
		return new SystemOfTwoEquationsParameters(first, second);
	}

	public static SystemOfTwoEquationsParameters createSystemOfEquationsTestParameters_2() {
		final var first = new StandardEquationParameters(-4, -4, 0);
		final var second = new StandardEquationParameters(4, 4, 0);
		return new SystemOfTwoEquationsParameters(first, second);
	}

	public static SystemOfTwoEquationsCorrectSolution createSystemOfEquationsCorrectSolution_2() {
		return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.R, null, null);
	}

	public static SystemOfTwoEquationsParameters createSystemOfEquationsTestParameters_3() {
		final var first = new StandardEquationParameters(1, -1, -6);
		final var second = new StandardEquationParameters(-2, 2, -1);
		return new SystemOfTwoEquationsParameters(first, second);
	}

	public static SystemOfTwoEquationsCorrectSolution createSystemOfEquationsCorrectSolution_3() {
		return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null);
	}

	public static SystemOfTwoEquationsCorrectSolution createSystemOfEquationsCorrectSolutionSwitchingXAndYBoth() {
		final var correctX = new Fraction(2, 1);
		final var correctY = new Fraction(5, 1);
		return new SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution.NORMAL, correctX, correctY);
	}
}
