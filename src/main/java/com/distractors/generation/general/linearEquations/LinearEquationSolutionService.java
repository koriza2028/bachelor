package com.distractors.generation.general.linearEquations;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;

public class LinearEquationSolutionService {

	public Fraction solve(StandardLinearEquationFractionParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		return b.multiplyBy(-1).divideBy(a);
	}

	public Fraction solveAdditivelyInsteadOfMultiplicatively(StandardLinearEquationFractionParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		return b.multiplyBy(-1).substract(a);
	}

	public SquareRoot solve(StandardLinearEquationSquareRootParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		return b.multiplyBy(-1).divideBy(a);
	}
}
