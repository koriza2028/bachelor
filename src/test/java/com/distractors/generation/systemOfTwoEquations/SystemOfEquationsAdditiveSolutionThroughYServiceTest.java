package com.distractors.generation.systemOfTwoEquations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsAdditiveSolutionThroughYService;

public class SystemOfEquationsAdditiveSolutionThroughYServiceTest {

	private final SystemOfEquationsAdditiveSolutionThroughYService service = new SystemOfEquationsAdditiveSolutionThroughYService();

	@Test
	void testSolveCorrectly_1() {
		// given
		final var system = SystemOfEquationsTestParametersCreator.createSystemOfEquationsTestParameters_1();
		final var correctSolution = SystemOfEquationsTestParametersCreator.createSystemOfEquationsCorrectSolution_1();

		// when
		final var solution = service.solveCorrectly(system);

		// then
		Assertions.assertTrue(correctSolution.equals(solution));
	}

	@Test
	void testSolveCorrectly_2() {
		// given
		final var system = SystemOfEquationsTestParametersCreator.createSystemOfEquationsTestParameters_2();
		final var correctSolution = SystemOfEquationsTestParametersCreator.createSystemOfEquationsCorrectSolution_2();

		// when
		final var solution = service.solveCorrectly(system);

		// then
		Assertions.assertTrue(correctSolution.equals(solution));
	}

	@Test
	void testSolveCorrectly_3() {
		// given
		final var system = SystemOfEquationsTestParametersCreator.createSystemOfEquationsTestParameters_3();
		final var correctSolution = SystemOfEquationsTestParametersCreator.createSystemOfEquationsCorrectSolution_3();

		// when
		final var solution = service.solveCorrectly(system);

		// then
		Assertions.assertTrue(correctSolution.equals(solution));
	}

}
