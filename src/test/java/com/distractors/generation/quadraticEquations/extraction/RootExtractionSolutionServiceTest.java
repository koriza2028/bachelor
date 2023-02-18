package com.distractors.generation.quadraticEquations.extraction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.errorBased.quadraticEquations.extraction.RootExtractionSolutionService;
import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;

public class RootExtractionSolutionServiceTest {

	private QuadraticEquationTestParametersGenerator testParametersGenerator = new QuadraticEquationTestParametersGenerator();
	private RootExtractionSolutionService rootExtraction = new RootExtractionSolutionService();

	@Test
	void testSolveCorrectly() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForExtraction();
		final var correctSolution = this.testParametersGenerator.generateCorrectSolutionForExtraction();

		// when
		final var extractionCorrectSolution = rootExtraction.solveCorrectly(testParameters);

		// then
		Assertions.assertTrue(extractionCorrectSolution.equals(correctSolution));
	}
}
