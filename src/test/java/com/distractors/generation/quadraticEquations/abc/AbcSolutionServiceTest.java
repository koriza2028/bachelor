package com.distractors.generation.quadraticEquations.abc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SymbolicNumberBuilder;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;
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
	void testSolveCorrectlyOther() {
		// given
		final var a = new Fraction(3, 8);
		final var b = new Fraction(-9, 20);
		final var c = new Fraction(2, 15);
		final var testParameters = new StandardQuadraticEquationParameters(a, b, c);
		final var nom = new SymbolicNumberBuilder().withFractionPart(new Fraction(3,2)).build();
		final var denom = new SymbolicNumberBuilder().withFractionPart(new Fraction(15, 8)).build();
		final var frac1 = new SymbolicNumberFraction(nom);
		final var frac2 = new SymbolicNumberFraction(denom);
		final var correctSolution = new QuadraticEquationCorrectSolution(frac1, frac2);

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
