package com.distractors.generation.systemOfTwoEquations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsSubstitutionSolutionThroughXService;

public class SystemOfEquationsSubstitutionSolutionThroughXServiceTest {

	private final SystemOfEquationsSubstitutionSolutionThroughXService service = new SystemOfEquationsSubstitutionSolutionThroughXService();

	@Test
	void test() {
		// given
		final var system = SystemOfEquationsTestParametersCreator.createSystemOfEquationsTestParameters_1();
		final var expected = SystemOfEquationsTestParametersCreator.createSystemOfEquationsCorrectSolution_1();

		// when
		final var actual = service.solveCorrectly(system);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}
}
