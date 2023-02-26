package com.distractors.generation.quadraticEquations.factoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;
import com.distractors.generation.quadraticEquations.errorBased.factoring.FactoringSolutionService;

public class FactoringSolutionServiceTest {

	private QuadraticEquationTestParametersGenerator testParametersGenerator = new QuadraticEquationTestParametersGenerator();
	private FactoringSolutionService factoring = new FactoringSolutionService();

	@Test
	void testSolveCorrectly() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForFactoring();
		final var correctSolution = this.testParametersGenerator.generateCorrectSolutionForFactoring();

		// when
		final var factoringCorrectSolution = factoring.solveCorrectly(testParameters);

		// then
		Assertions.assertTrue(factoringCorrectSolution.equals(correctSolution));
	}
}
