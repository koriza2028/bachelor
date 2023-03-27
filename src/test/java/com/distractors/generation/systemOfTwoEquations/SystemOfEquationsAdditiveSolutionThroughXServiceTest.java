package com.distractors.generation.systemOfTwoEquations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsAdditiveSolutionThroughXService;

public class SystemOfEquationsAdditiveSolutionThroughXServiceTest {
	private final SystemOfEquationsAdditiveSolutionThroughXService service = new SystemOfEquationsAdditiveSolutionThroughXService();

	@Test
	void test() {
		final var first = new StandardEquationParameters(3, 2, -16);
		final var second = new StandardEquationParameters(7, 1, -19);
		final var system = new SystemOfTwoEquations(first, second);
		final var correctX = new Fraction(2, 1);
		final var correctY = new Fraction(5, 1);
		final var correctSolution = new SystemOfTwoEquationsCorrectSolution(correctX, correctY);
		final var solution = service.solveCorrectly(system);
		Assertions.assertTrue(correctSolution.equals(solution));
	}

	@Test
	void test2() {
		final var first = new StandardEquationParameters(3, 2, -16);
		final var second = new StandardEquationParameters(7, 1, -19);
		final var system = new SystemOfTwoEquations(first, second);
		final var correctX = new Fraction(-35, 11);
		final var correctY = new Fraction(281, 22);
		final var correctSolution = new SystemOfTwoEquationsCorrectSolution(correctX, correctY);
		final var solution = service.solveIgnoringFreeCoefficientMultiplication(system);
		Assertions.assertTrue(correctSolution.equals(solution));
	}

	@Test
	void test3() {
		final var first = new StandardEquationParameters(3, 2, -16);
		final var second = new StandardEquationParameters(7, 1, -19);
		final var system = new SystemOfTwoEquations(first, second);
		final var correctX = new Fraction(2, 1);
		final var correctY = new Fraction(4, 1);
		final var correctSolution = new SystemOfTwoEquationsCorrectSolution(correctX, correctY);
		final var solution = service.solveReplacingWrongParameter(system);
		Assertions.assertTrue(correctSolution.equals(solution));
	}
}
