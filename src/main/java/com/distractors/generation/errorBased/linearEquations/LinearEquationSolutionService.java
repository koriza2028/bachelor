package com.distractors.generation.errorBased.linearEquations;

import com.distractors.generation.general.Fraction;
import com.distractors.generation.general.SquareRoot;

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
