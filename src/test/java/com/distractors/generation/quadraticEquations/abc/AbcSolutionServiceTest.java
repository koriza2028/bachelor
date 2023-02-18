package com.distractors.generation.quadraticEquations.abc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.errorBased.quadraticEquations.abc.AbcSolutionService;
import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;

public class AbcSolutionServiceTest {
	
	private QuadraticEquationTestParametersGenerator testParametersGenerator = new QuadraticEquationTestParametersGenerator();
	private AbcSolutionService abc = new AbcSolutionService();

	@Test
	void testSolveCorrectly() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForAbc();
		final var correctSolution = this.testParametersGenerator.generateCorrectSolutionForAbc();

		// when
		final var abcCorrectSolution = abc.solveCorrectly(testParameters);

		// then
		Assertions.assertTrue(abcCorrectSolution.equals(correctSolution));
	}
}
