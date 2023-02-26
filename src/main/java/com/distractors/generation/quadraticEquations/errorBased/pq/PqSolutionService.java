package com.distractors.generation.quadraticEquations.errorBased.pq;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationSolutionService;

/**
 * x_1,2 = -(p/2) +/- √((p/2)² - q) 
 * Only possible for equations in form x² + p·x + q = 0
 */
public class PqSolutionService implements QuadraticEquationSolutionService {

	@Override
	public QuadraticEquationRoots solveCorrectly(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		
		final var p = b.divideBy(a);
		final var q = c.divideBy(a);

		return this.solveWithPQFormula(p, q);
	}

	@Override
	public QuadraticEquationRoots solveWithChosenError(StandardQuadraticEquationParameters equationParameters,
			final QuadraticEquationErrorType randomError) {
		switch (randomError) {
			case IGNORE_NORMAL_FORM:
				return this.solveIgnoringNormalForm(equationParameters);
			case EXTRACT_ROOT_ADDITIVELY_PQ:
				return this.solveExtractingRootAdditively(equationParameters);
			default:
				throw new IllegalArgumentException("Unknown error type.");
		}
	}

	private QuadraticEquationRoots solveExtractingRootAdditively(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		
		final var p = b.divideBy(a);
		final var q = c.divideBy(a);

		final var pDividedByTwo = p.divideBy(Fraction.TWO);
		final var pDividedByTwoQuadrat = pDividedByTwo.multiplyBy(pDividedByTwo);
		final var x_1Number = pDividedByTwo.multiplyBy(-1).add(new SquareRoot(pDividedByTwoQuadrat).substract(new SquareRoot(q)));
		final var x_2Number = pDividedByTwo.multiplyBy(-1).substract(new SquareRoot(pDividedByTwoQuadrat).substract(new SquareRoot(q)));
	
		final var x_1 = new SymbolicNumberFraction(x_1Number);
		final var x_2 = new SymbolicNumberFraction(x_2Number);

		return new QuadraticEquationRoots(x_1, x_2, false);
	}

	private QuadraticEquationRoots solveWithPQFormula(Fraction p, Fraction q) {
		final var pDividedByTwo = p.divideBy(Fraction.TWO);
		final var root = new SquareRoot(pDividedByTwo.multiplyBy(pDividedByTwo).substract(q));
		final var x_1Number = pDividedByTwo.multiplyBy(-1).add(root);
		final var x_2Number = pDividedByTwo.multiplyBy(-1).substract(root);
	
		final var x_1 = new SymbolicNumberFraction(x_1Number);
		final var x_2 = new SymbolicNumberFraction(x_2Number);

		return new QuadraticEquationRoots(x_1, x_2, true);
	}

	private QuadraticEquationRoots solveIgnoringNormalForm(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var p = equationParameters.b();
		final var q = equationParameters.c();

		if (a.toDouble() != 1) {
			final var roots = this.solveWithPQFormula(p, q);
			return new QuadraticEquationRoots(roots.x_1(), roots.x_2(), false);
		} else {
			throw new IllegalStateException("The equation is in normal form");
		}
	}

}
