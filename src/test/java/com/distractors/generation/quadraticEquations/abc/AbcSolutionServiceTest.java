package com.distractors.generation.quadraticEquations.abc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

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

	@Test
	void testSolveDividingByC() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForAbc();
		final var expected = this.testParametersGenerator.generateDivideByCSolutionForAbc();

		// when
		final var actual = abc.solveWithChosenError(testParameters, QuadraticEquationErrorType.DIVIDE_BY_C);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testSolveDividingDiscriminantByTwo() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForAbc();
		final var expected = this.testParametersGenerator.generateDivideDiscriminantByTwoSolutionForAbc();

		// when
		final var actual = abc.solveWithChosenError(testParameters, QuadraticEquationErrorType.DIVIDE_DISCRIMINANT_BY_TWO);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testSolveExtractingRootAdditively() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForAbc();
		final var expected = this.testParametersGenerator.generateExtractRootAdditivelySolutionForAbc();

		// when
		final var actual = abc.solveWithChosenError(testParameters, QuadraticEquationErrorType.EXTRACT_ROOT_ADDITIVELY_ABC);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testMovingPlusMinus() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForAbc();
		final var expected = this.testParametersGenerator.generateMovePlusMinusSolutionForAbc();

		// when
		final var actual = abc.solveWithChosenError(testParameters, QuadraticEquationErrorType.MOVE_PLUS_MINUS);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testNoMinusBeforeC() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForAbc();
		final var expected = this.testParametersGenerator.generateNoMinusBeforeBSolutionForAbc();

		// when
		final var actual = abc.solveWithChosenError(testParameters, QuadraticEquationErrorType.NO_MINUS_BEFORE_B);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testUseBQuadrat() {
		// given
		final var testParameters = this.testParametersGenerator.generateStandardQuadraticEquationParametersForAbc();
		final var expected = this.testParametersGenerator.generateUseBQuadratSolutionForAbc();

		// when
		final var actual = abc.solveWithChosenError(testParameters, QuadraticEquationErrorType.USE_B_QUADRAT);

		// then
		Assertions.assertTrue(expected.equals(actual));
	}
}
