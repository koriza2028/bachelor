package com.distractors.generation.errorBased.quadraticEquations.exclusion;

import com.distractors.generation.errorBased.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.errorBased.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationSolutionService;
import com.distractors.generation.errorBased.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.general.Fraction;
import com.distractors.generation.general.SymbolicNumberBuilder;
import com.distractors.generation.general.SymbolicNumberFraction;

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

			return new QuadraticEquationRoots(x_1, x_2);
		} else {
			throw new IllegalStateException("The equation cannot be solved by exclusion method.");
		}

	}

	@Override
	public QuadraticEquationRoots solveIncorrectly(StandardQuadraticEquationParameters equationParameters) throws IllegalArgumentException {
		final var randomError = ExclusionErrorType.randomError(equationParameters);
		
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

			return new QuadraticEquationRoots(x_1, x_2);
		} else {
			throw new IllegalStateException("The equation could be solved by exclusion method.");
		}
	}

	private QuadraticEquationRoots solveWithWrongSimpleEquationSolution(StandardQuadraticEquationParameters equationParameters) throws IllegalStateException {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (c.toDouble() == 0) {
			final var x_1Fraction = Fraction.ZERO;
			final var builder_1 = new SymbolicNumberBuilder();
			final var x_1 = new SymbolicNumberFraction(builder_1.withFractionPart(x_1Fraction).build());

			final var simpleEquationForX_2 = new StandardLinearEquationFractionParameters(a, b);
			final var x_2Fraction = this.simpleEquationSolutionService.solveAdditivelyInsteadOfMultiplicatively(simpleEquationForX_2);
			final var builder_2 = new SymbolicNumberBuilder();
			final var x_2 = new SymbolicNumberFraction(builder_2.withFractionPart(x_2Fraction).build());

			return new QuadraticEquationRoots(x_1, x_2);
		} else {
			throw new IllegalStateException("The equation cannot be solved by exclusion method.");
		}
	}
}
