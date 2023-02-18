package com.distractors.generation.quadraticEquations.exclusion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.errorBased.quadraticEquations.exclusion.ExclusionSolutionService;
import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;

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
}
