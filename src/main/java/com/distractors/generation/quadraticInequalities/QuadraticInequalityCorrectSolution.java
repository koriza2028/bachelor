package com.distractors.generation.quadraticInequalities;

import com.distractors.generation.general.maths.SymbolicNumberFraction;

public record QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution nonNumericalSolution, QuadraticInequalityRange range_1, QuadraticInequalityRange range_2) implements QuadraticInequalitySolution {

	public static QuadraticInequalityCorrectSolution createRCorrectSolution() {
		final var range = new QuadraticInequalityRange(SymbolicNumberFraction.ONE, InequalitySign.GREATER);
		return new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.R, range, range);
	}

	public static QuadraticInequalityCorrectSolution createEmptySetCorrectSolution() {
		final var range = new QuadraticInequalityRange(SymbolicNumberFraction.ONE, InequalitySign.GREATER);
		return new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, range, range);
	}

	public static QuadraticInequalityCorrectSolution createRExceptZeroCorrectSolution(SymbolicNumberFraction exceptedZero) {
		final var range = new QuadraticInequalityRange(exceptedZero, InequalitySign.GREATER);
		return new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, range, range);
	}

	public static QuadraticInequalityCorrectSolution createOnlyZeroCorrectSolution(SymbolicNumberFraction onlyZero) {
		final var range = new QuadraticInequalityRange(onlyZero, InequalitySign.GREATER);
		return new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.ONLY_ZERO, range, range);
	}
}
