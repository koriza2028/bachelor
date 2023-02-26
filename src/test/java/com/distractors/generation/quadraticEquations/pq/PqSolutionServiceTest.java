package com.distractors.generation.quadraticEquations.pq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.pq.PqSolutionService;

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

	@Test
	void testSolveIgnoringNormalForm() {
		// given
		final var testParameters = this.testParametersGenerator.generateQuadraticEquationParametersNotInNormalFormForPq();
		final var correctSolution = this.testParametersGenerator.generateCorrectSolutionForPq();

		// when
		final var pqCorrectSolution = pq.solveWithChosenError(testParameters, QuadraticEquationErrorType.IGNORE_NORMAL_FORM);

		// then
		Assertions.assertTrue(pqCorrectSolution.equals(correctSolution));
	}

	@Test
	void testSolveExtractingRootFromDiscriminantAdditively() {
		// given
		final var testParameters = this.testParametersGenerator.generateQuadraticEquationParametersForWrongRootExtractionForPq();
		final var correctSolution = this.testParametersGenerator.generateExtractRootAdditivelyInsteadOfMultiplicativelySolutionForPq();

		// when
		final var pqCorrectSolution = pq.solveWithChosenError(testParameters, QuadraticEquationErrorType.EXTRACT_ROOT_ADDITIVELY_PQ);

		// then
		Assertions.assertTrue(pqCorrectSolution.equals(correctSolution));
	}
}
