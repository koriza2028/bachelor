package com.distractors.generation.quadraticEquations.exclusion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.exclusion.ExclusionSolutionService;

public class ExclusionSolutionServiceTest {

	private QuadraticEquationTestParametersGenerator testParametersGenerator = new QuadraticEquationTestParametersGenerator();
	private ExclusionSolutionService exclusion = new ExclusionSolutionService();

	@Test
	void testSolveCorrectly() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForExclusion();
		final var correctSolution = this.testParametersGenerator.generateCorrectSolutionForExclusion();

		// when
		final var abcCorrectSolution = exclusion.solveCorrectly(testParameters);

		// then
		Assertions.assertTrue(abcCorrectSolution.equals(correctSolution));
	}

	@Test
	void testSolveIgnoringCNotZero() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForAbc();
		final var expected = this.testParametersGenerator.generateIgnoreCNotZeroSolutionForExclusion();

		// when
		final var actual = exclusion.solveWithChosenError(testParameters, QuadraticEquationErrorType.IGNORE_C_NOT_ZERO);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testSolveWithWrongSimpleEquationSolution() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForExclusion();
		final var expected = this.testParametersGenerator.generateWrongSimpleEquationSolutionForExclusion();

		// when
		final var actual = exclusion.solveWithChosenError(testParameters, QuadraticEquationErrorType.WRONG_SIMPLE_EQUATION_SOLUTION);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}
}
