package com.distractors.generation.quadraticEquations.errorBased.extraction;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;
import com.distractors.generation.general.maths.SymbolicNumberBuilder;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationSolutionService;

/**
 * a·x² - c = 0 |+c
 * a·x² = c     |:a
 * x² = c/a 	|extract the root
 * x_1,2 = √(c/a)
 * Only possible for equations in form a·x² - c = 0
 */
public class RootExtractionSolutionService implements QuadraticEquationSolutionService {

	@Override
	public QuadraticEquationCorrectSolution solveCorrectly(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (b.toDouble() == 0) {
			final var x_1Root = new SquareRoot(c.multiplyBy(-1).divideBy(a), Fraction.ONE);
			final var x_2Root = new SquareRoot(c.multiplyBy(-1).divideBy(a), Fraction.ONE).multiplyBy(-1);

			final var x_1 = new SymbolicNumberFraction(new SymbolicNumberBuilder().withRoot(x_1Root).build());
			final var x_2 = new SymbolicNumberFraction(new SymbolicNumberBuilder().withRoot(x_2Root).build());

			return new QuadraticEquationCorrectSolution(x_1, x_2);
		} else {
			throw new IllegalStateException("The equation cannot be solved by root extraction method");
		}
	}

	private QuadraticEquationDistractor solveAdditivelyInsteadOfMultiplicatively(StandardQuadraticEquationParameters equationParameters) throws IllegalStateException {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (b.toDouble() == 0) {
			final var x_1Root = new SquareRoot(c.multiplyBy(-1).substract(a), Fraction.ONE);
			final var x_2Root = new SquareRoot(c.multiplyBy(-1).substract(a), Fraction.ONE).multiplyBy(-1);

			final var x_1 = new SymbolicNumberFraction(new SymbolicNumberBuilder().withRoot(x_1Root).build());
			final var x_2 = new SymbolicNumberFraction(new SymbolicNumberBuilder().withRoot(x_2Root).build());

			return new QuadraticEquationDistractor(x_1, x_2, QuadraticEquationErrorType.SOLVE_ADDITIVELY_INSTEAD_OF_MULTIPLICATIVELY);
		} else {
			throw new IllegalStateException("The equation cannot be solved by root extraction method.");
		}
	}

	@Override
	public QuadraticEquationDistractor solveWithChosenError(StandardQuadraticEquationParameters equationParameters,
			QuadraticEquationErrorType errorType) {
		return this.solveAdditivelyInsteadOfMultiplicatively(equationParameters);
	}

}
