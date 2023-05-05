package com.distractors.generation.systemOfTwoEquations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.systemsOfTwoEquations.basedOnWrongParameters.SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService;
import com.distractors.generation.systemsOfTwoEquations.basedOnWrongParameters.SystemOfEquationsParametersChangeType;

public class SystemOfEquationsDistractorsGenerationBasedOnWrongParametersServiceTest {
	private final SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService service = new SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService();

	@Test
	void test() {
		// given
		final var system = SystemOfEquationsTestParametersCreator.createSystemOfEquationsTestParameters_1();
		final var expected = SystemOfEquationsTestParametersCreator.createSystemOfEquationsCorrectSolution_1();

		// when
		final var actual = service.generateDistractorWithChosenParameterChangeType(system, SystemOfEquationsParametersChangeType.SWAP_X_AND_Y_BOTH);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}
}
