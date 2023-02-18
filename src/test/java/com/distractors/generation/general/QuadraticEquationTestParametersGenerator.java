package com.distractors.generation.general;

import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.errorBased.quadraticEquations.StandardQuadraticEquationParameters;

public class QuadraticEquationTestParametersGenerator {

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForAbc() {
		final var a = Fraction.ONE;
		final var b = new Fraction(-4, 1);
		final var c = new Fraction(-5, 1);
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationRoots generateCorrectSolutionForAbc() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-4).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(2).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-10).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(2).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationRoots(x_1, x_2);
	}

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForExclusion() {
		final var a = Fraction.ONE;
		final var b = new Fraction(-8, 1);
		final var c = Fraction.ZERO;
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationRoots generateCorrectSolutionForExclusion() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(0).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(8).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationRoots(x_1, x_2);
	}

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForExtraction() {
		final var a = Fraction.ONE;
		final var b = Fraction.ZERO;
		final var c = new Fraction(-9, 1);
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationRoots generateCorrectSolutionForExtraction() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(3).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-3).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationRoots(x_1, x_2);
	}

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForFactoring() {
		final var a = Fraction.ONE;
		final var b = new Fraction(-1, 1);
		final var c = new Fraction(-6, 1);
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationRoots generateCorrectSolutionForFactoring() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-2).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(3).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationRoots(x_1, x_2);
	}

	public StandardQuadraticEquationParameters generateStandardQuadraticEquationParametersForPq() {
		final var a = Fraction.ONE;
		final var b = Fraction.TWO;
		final var c = Fraction.ONE;
		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public QuadraticEquationRoots generateCorrectSolutionForPq() {
		final var x_1Nominator = new SymbolicNumberBuilder().withInteger(-1).build();
		final var x_1Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_1 = new SymbolicNumberFraction(x_1Nominator, x_1Denominator);

		final var x_2Nominator = new SymbolicNumberBuilder().withInteger(-1).build();
		final var x_2Denominator = new SymbolicNumberBuilder().withInteger(1).build();
		final var x_2 = new SymbolicNumberFraction(x_2Nominator, x_2Denominator);
		
		return new QuadraticEquationRoots(x_1, x_2);
	}
}
