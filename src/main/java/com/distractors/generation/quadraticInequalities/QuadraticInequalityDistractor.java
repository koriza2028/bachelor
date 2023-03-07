package com.distractors.generation.quadraticInequalities;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticSolutionImpact;

public record QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution nonNumericalSolution, QuadraticInequalityRange range_1, QuadraticInequalityRange range_2, QuadraticSolutionImpact solutionImpact) implements QuadraticInequalitySolution {

	public static QuadraticInequalityDistractor createNormalDistractor(QuadraticInequalityNonNumericalSolution nonNumericalSolution, QuadraticInequalityRange range_1, QuadraticInequalityRange range_2, QuadraticSolutionImpact solutionImpact) {
		return new QuadraticInequalityDistractor(nonNumericalSolution, range_1, range_2, solutionImpact);
	}

	public static QuadraticInequalityDistractor createRDistractor(QuadraticSolutionImpact solutionImpact) {
		final var range = new QuadraticInequalityRange(SymbolicNumberFraction.ONE, InequalitySign.GREATER);
		return new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.R, range, range, solutionImpact);
	}

	public static QuadraticInequalityDistractor createEmptySetDistractor(QuadraticSolutionImpact solutionImpact) {
		final var range = new QuadraticInequalityRange(SymbolicNumberFraction.ONE, InequalitySign.GREATER);
		return new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.EMPTY_SET, range, range, solutionImpact);
	}

	public static QuadraticInequalityDistractor createRExceptZeroDistractor(SymbolicNumberFraction exceptedZero, QuadraticSolutionImpact solutionImpact) {
		final var range = new QuadraticInequalityRange(exceptedZero, InequalitySign.GREATER);
		return new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, range, range, solutionImpact);
	}

	public static QuadraticInequalityDistractor createOnlyZeroDistractor(SymbolicNumberFraction onlyZero, QuadraticSolutionImpact solutionImpact) {
		final var range = new QuadraticInequalityRange(onlyZero, InequalitySign.GREATER);
		return new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.ONLY_ZERO, range, range, solutionImpact);
	}

	public String convertToString() {
		return QuadraticInequalitySolution.super.convertToString() + " (" + solutionImpact + ")";
	}
}
