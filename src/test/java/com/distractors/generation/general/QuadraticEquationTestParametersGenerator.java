package com.distractors.generation.general;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;
import com.distractors.generation.general.maths.SymbolicNumberBuilder;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.QuadraticEquationSolution;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;

public class QuadraticEquationTestParametersGenerator {

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForAbc() {
		final var a = Fraction.ONE;
		final var b = new Fraction(-4, 1);
		final var c = new Fraction(-5, 1);
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationSolution generateCorrectSolutionForAbc() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-4).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(2).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-10).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(2).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateDivideByCSolutionForAbc() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-2).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(2).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(5).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateMovePlusMinusSolutionForAbc() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-1).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-5).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateUseBQuadratSolutionForAbc() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(11).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(5).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateNoMinusBeforeBSolutionForAbc() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-5).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateDivideDiscriminantByTwoSolutionForAbc() {
		final var root = new SquareRoot(Fraction.TWO, Fraction.THREE);
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(4).withRoot(root).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(2).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(4).withRoot(root.multiplyBy(-1)).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(2).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateExtractRootAdditivelySolutionForAbc() {
		final var root = new SquareRoot(Fraction.FIVE, Fraction.ONE);
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(4).withRoot(root).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withRoot(root.multiplyBy(-1)).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForExclusion() {
		final var a = Fraction.ONE;
		final var b = new Fraction(-8, 1);
		final var c = Fraction.ZERO;
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationCorrectSolution generateCorrectSolutionForExclusion() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(0).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(8).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateWrongSimpleEquationSolutionForExclusion() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(0).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(7).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateIgnoreCNotZeroSolutionForExclusion() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(0).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(4).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForExtraction() {
		final var a = Fraction.ONE;
		final var b = Fraction.ZERO;
		final var c = new Fraction(-9, 1);
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationSolution generateCorrectSolutionForExtraction() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(3).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-3).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateSolveAdditivelyInsteadOfMultiplicativelySolutionForExtraction() {
		final var root = new SquareRoot(new Fraction(8, 1), Fraction.ONE);
		final var x_1Nominator = new SymbolicNumberBuilder().withRoot(root).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withRoot(root.multiplyBy(-1)).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForFactoring() {
		final var a = Fraction.ONE;
		final var b = new Fraction(-1, 1);
		final var c = new Fraction(-6, 1);
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationSolution generateCorrectSolutionForFactoring() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-2).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(3).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForPq() {
		final var a = Fraction.ONE;
		final var b = Fraction.TWO;
		final var c = Fraction.ONE;
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public StandardQuadraticEquationParameters generateQuadraticEquationParametersNotInNormalFormForPq() {
		final var a = Fraction.TWO;
		final var b = Fraction.TWO;
		final var c = Fraction.ONE;
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public StandardQuadraticEquationParameters generateQuadraticEquationParametersForWrongRootExtractionForPq() {
		final var a = Fraction.ONE;
		final var b = Fraction.FOUR;
		final var c = Fraction.ONE;
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationSolution generateCorrectSolutionForPq() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-1).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-1).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}

	public QuadraticEquationSolution generateExtractRootAdditivelyInsteadOfMultiplicativelySolutionForPq() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-1).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-3).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationCorrectSolution(x_1, x_2);
	}
}
