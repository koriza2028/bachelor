package com.distractors.generation.quadraticEquations.abc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.QuadraticEquationTestParametersGenerator;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;
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
		final var nom = new SymbolicNumberBuilder().withFractionPart(new Fraction(2, 3)).build();
		final var denom = new SymbolicNumberBuilder().withFractionPart(new Fraction(8, 15)).build();
		final var frac1 = new SymbolicNumberFraction(nom);
		frac1.toString();
		final var frac2 = new SymbolicNumberFraction(denom);
		frac2.toString();
		final var correctSolution = new QuadraticEquationCorrectSolution(frac1, frac2);

		// when
		final var abcCorrectSolution = abc.solveCorrectly(testParameters);

		// then
		Assertions.assertTrue(abcCorrectSolution.equals(correctSolution));
	}

	@Test
	void testSolveCorrectlyOtherOther() {
		// given
		final var a = new Fraction(-5, 1);
		final var b = new Fraction(-31, 1);
		final var c = new Fraction(8, 1);
		final var testParameters = new StandardQuadraticEquationParameters(a, b, c);
		final var root = new SquareRoot(new Fraction(1121, 1), new Fraction(-1, 10));
		final var minusRoot = root.multiplyBy(-1);
		final var frac = new Fraction(-31, 10);
		final var x_1 = new SymbolicNumberBuilder().withFractionPart(frac).withRoot(root).build();
		final var x_2 = new SymbolicNumberBuilder().withFractionPart(frac).withRoot(minusRoot).build();
		final var frac1 = new SymbolicNumberFraction(x_1);
		frac1.toString();
		final var frac2 = new SymbolicNumberFraction(x_2);
		frac2.toString();
		final var correctSolution = new QuadraticEquationCorrectSolution(frac1, frac2);

		// when
		final var abcCorrectSolution = abc.solveCorrectly(testParameters);
		frac1.equals(frac2);

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
