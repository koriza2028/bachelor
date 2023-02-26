package com.distractors.generation.quadraticEquations.errorBased.exclusion;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SymbolicNumberBuilder;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationSolutionService;

/**
* a·x² + b·x = 0 -> x·(a·x + b) = 0
* Only possible for equations in form a·x² + b·x = 0
*/
public class ExclusionSolutionService implements QuadraticEquationSolutionService {

	private LinearEquationSolutionService simpleEquationSolutionService = new LinearEquationSolutionService();

	@Override
	public QuadraticEquationRoots solveCorrectly(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (c.toDouble() == 0) {
			final var x_1Fraction = Fraction.ZERO;
			final var builder_1 = new SymbolicNumberBuilder();
			final var x_1 = new SymbolicNumberFraction(builder_1.withFractionPart(x_1Fraction).build());

			final var simpleEquationForX_2 = new StandardLinearEquationFractionParameters(a, b);
			final var x_2Fraction = this.simpleEquationSolutionService.solve(simpleEquationForX_2);
			final var builder_2 = new SymbolicNumberBuilder();
			final var x_2 = new SymbolicNumberFraction(builder_2.withFractionPart(x_2Fraction).build());

			return new QuadraticEquationRoots(x_1, x_2, true);
		} else {
			throw new IllegalStateException("The equation cannot be solved by exclusion method.");
		}

	}

	@Override
	public QuadraticEquationRoots solveWithChosenError(StandardQuadraticEquationParameters equationParameters,
			final QuadraticEquationErrorType randomError) {
		switch (randomError) {
		case IGNORE_C_NOT_ZERO:
			return this.solveIgnoringCIsNotZero(equationParameters);
		case WRONG_SIMPLE_EQUATION_SOLUTION:
			return this.solveWithWrongSimpleEquationSolution(equationParameters);
		default:
			throw new IllegalArgumentException("Unknown error type.");
		}
	}

	private QuadraticEquationRoots solveIgnoringCIsNotZero(StandardQuadraticEquationParameters equationParameters) throws IllegalStateException {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (c.toDouble() != 0) {
			final var x_1Fraction = Fraction.ZERO;
			final var builder_1 = new SymbolicNumberBuilder();
			final var x_1 = new SymbolicNumberFraction(builder_1.withFractionPart(x_1Fraction).build());

			final var simpleEquationForX_2 = new StandardLinearEquationFractionParameters(a, b);
			final var x_2Fraction = this.simpleEquationSolutionService.solve(simpleEquationForX_2);
			final var builder_2 = new SymbolicNumberBuilder();
			final var x_2 = new SymbolicNumberFraction(builder_2.withFractionPart(x_2Fraction).build());

			return new QuadraticEquationRoots(x_1, x_2, false);
		} else {
			throw new IllegalStateException("The equation could be solved by exclusion method.");
		}
	}

	private QuadraticEquationRoots solveWithWrongSimpleEquationSolution(StandardQuadraticEquationParameters equationParameters) throws IllegalStateException {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (c.toDouble() == 0) {
			final var x_1 = SymbolicNumberFraction.ZERO;

			final var simpleEquationForX_2 = new StandardLinearEquationFractionParameters(a, b);
			final var x_2Fraction = this.simpleEquationSolutionService.solveAdditivelyInsteadOfMultiplicatively(simpleEquationForX_2);
			final var builder_2 = new SymbolicNumberBuilder();
			final var x_2 = new SymbolicNumberFraction(builder_2.withFractionPart(x_2Fraction).build());

			return new QuadraticEquationRoots(x_1, x_2, false);
		} else {
			throw new IllegalStateException("The equation cannot be solved by exclusion method.");
		}
	}
}
