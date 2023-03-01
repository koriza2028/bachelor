package com.distractors.generation.quadraticEquations.extraction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.extraction.RootExtractionSolutionService;

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

	@Test
	void testSolveAdditivelyInsteadOfMultiplicatively() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForExtraction();
		final var correctSolution = this.testParametersGenerator.generateSolveAdditivelyInsteadOfMultiplicativelySolutionForExtraction();

		// when
		final var extractionCorrectSolution = rootExtraction.solveWithChosenError(testParameters, QuadraticEquationErrorType.SOLVE_ADDITIVELY_INSTEAD_OF_MULTIPLICATIVELY);

		// then
		Assertions.assertTrue(extractionCorrectSolution.equals(correctSolution));
	}
}
