package com.distractors.generation.quadraticEquations.pq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.errorBased.quadraticEquations.pq.PqSolutionService;
import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;

public class PqSolutionServiceTest {

	private QuadraticEquationTestParametersGenerator testParametersGenerator = new QuadraticEquationTestParametersGenerator();
	private PqSolutionService pq = new PqSolutionService();

	@Test
	void testSolveCorrectly() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForPq();
		final var correctSolution = this.testParametersGenerator.generateCorrectSolutionForPq();

		// when
		final var pqCorrectSolution = pq.solveCorrectly(testParameters);

		// then
		Assertions.assertTrue(pqCorrectSolution.equals(correctSolution));
	}
}
