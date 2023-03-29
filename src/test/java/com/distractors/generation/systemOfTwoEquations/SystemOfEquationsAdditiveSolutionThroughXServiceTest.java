package com.distractors.generation.systemOfTwoEquations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsAdditiveSolutionThroughXService;

public class SystemOfEquationsAdditiveSolutionThroughXServiceTest {
	private final SystemOfEquationsAdditiveSolutionThroughXService service = new SystemOfEquationsAdditiveSolutionThroughXService();

	@Test
	void testSolveCorrectly() {
		// given
		final var system = SystemOfEquationsTestParametersCreator.createSystemOfEquationsTestParameters_1();
		final var expected = SystemOfEquationsTestParametersCreator.createSystemOfEquationsCorrectSolution_1();

		// when
		final var actual = service.solveCorrectly(system);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testSolveIgnoringFreeCoefficientMultiplication() {
		// given
		final var system = SystemOfEquationsTestParametersCreator.createSystemOfEquationsTestParameters_1();
		final var expected = SystemOfEquationsTestParametersCreator.createSystemOfEquationsSolutionIgnoringFreeCoefficientMultiplication();

		// when
		final var actual = service.solveIgnoringFreeCoefficientMultiplication(system);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testSolveReplacingWrongParameter() {
		// given
		final var system = SystemOfEquationsTestParametersCreator.createSystemOfEquationsTestParameters_1();
		final var expected = SystemOfEquationsTestParametersCreator.createSystemOfEquationsSolutionReplacingWrongParameters();

		// when
		final var actual = service.solveReplacingWrongParameter(system);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}
}
