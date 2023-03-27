package com.distractors.generation.systemOfTwoEquations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.StandardEquationParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.basedOnWrongParameters.SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService;
import com.distractors.generation.systemsOfTwoEquations.basedOnWrongParameters.SystemOfEquationsParametersChangeType;

public class SystemOfEquationsDistractorsGenerationBasedOnWrongParametersServiceTest {
	private final SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService service = new SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService();

	@Test
	void test() {
		final var first = new StandardEquationParameters(3, 2, -16);
		final var second = new StandardEquationParameters(7, 1, -19);
		final var system = new SystemOfTwoEquations(first, second);
		final var correctX = new Fraction(2, 1);
		final var correctY = new Fraction(5, 1);
		final var correctSolution = new SystemOfTwoEquationsCorrectSolution(correctX, correctY);
		final var solution = service.generateDistractorWithChosenParameterChangeType(system, SystemOfEquationsParametersChangeType.SWITCH_X_AND_Y_BOTH);
		Assertions.assertTrue(correctSolution.equals(solution));
	}
}