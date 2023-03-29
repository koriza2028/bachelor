package com.distractors.generation.quadraticEquations.errorBased.exclusion;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SymbolicNumber;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
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
	public QuadraticEquationCorrectSolution solveCorrectly(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (c.toDouble() == 0) {
			final var x_1Fraction = Fraction.ZERO;
			final var x_1 = new SymbolicNumberFraction(new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(x_1Fraction).build());

			final var simpleEquationForX_2 = new StandardLinearEquationFractionParameters(a, b);
			final var x_2Fraction = this.simpleEquationSolutionService.solve(simpleEquationForX_2);
			final var x_2 = new SymbolicNumberFraction(new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(x_2Fraction).build());

			return new QuadraticEquationCorrectSolution(x_1, x_2);
		} else {
			throw new IllegalStateException("The equation cannot be solved by exclusion method.");
		}

	}

	@Override
	public QuadraticEquationDistractor solveWithChosenError(StandardQuadraticEquationParameters equationParameters,
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

	private QuadraticEquationDistractor solveIgnoringCIsNotZero(StandardQuadraticEquationParameters equationParameters) throws IllegalStateException {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (c.toDouble() != 0) {
			final var x_1Fraction = Fraction.ZERO;
			final var x_1 = new SymbolicNumberFraction(new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(x_1Fraction).build());

			final var simpleEquationForX_2 = new StandardLinearEquationFractionParameters(a, b);
			final var x_2Fraction = this.simpleEquationSolutionService.solve(simpleEquationForX_2);
			final var x_2 = new SymbolicNumberFraction(new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(x_2Fraction).build());

			return new QuadraticEquationDistractor(x_1, x_2, QuadraticEquationErrorType.IGNORE_C_NOT_ZERO);
		} else {
			throw new IllegalStateException("The equation could be solved by exclusion method.");
		}
	}

	private QuadraticEquationDistractor solveWithWrongSimpleEquationSolution(StandardQuadraticEquationParameters equationParameters) throws IllegalStateException {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		if (c.toDouble() == 0) {
			final var x_1 = SymbolicNumberFraction.ZERO;

			final var simpleEquationForX_2 = new StandardLinearEquationFractionParameters(a, b);
			final var x_2Fraction = this.simpleEquationSolutionService.solveAdditivelyInsteadOfMultiplicatively(simpleEquationForX_2);
			final var x_2 = new SymbolicNumberFraction(new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(x_2Fraction).build());

			return new QuadraticEquationDistractor(x_1, x_2, QuadraticEquationErrorType.WRONG_SIMPLE_EQUATION_SOLUTION);
		} else {
			throw new IllegalStateException("The equation cannot be solved by exclusion method.");
		}
	}
}
