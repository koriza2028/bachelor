package com.distractors.generation.quadraticInequalities;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;

public class QuadraticInequalityCorrectSolutionMapper {

	public QuadraticInequalityCorrectSolution findQuadraticInequalityCorrectSolution(QuadraticEquationCorrectSolution quadraticEquationCorrectSolution, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var root_1 = quadraticEquationCorrectSolution.x_1();
		final var root_2 = quadraticEquationCorrectSolution.x_2();
		
		if (root_1 != null && root_2 != null) {
			if (root_1.equals(root_2)) {
				return findQuadraticInequalityCorrectSolutionForSingleRoot(root_1, standardQuadraticInequalityParameters, quadraticEquationCorrectSolution.correct());
			} else return findQuadraticInequalityCorrectSolutionForDifferentRoots(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters);

		} else if (root_1 == null && root_2 != null) {
			return findQuadraticInequalityCorrectSolutionForSingleRoot(root_2, standardQuadraticInequalityParameters, quadraticEquationCorrectSolution.correct());
		} else if (root_2 == null && root_1 != null) {
			return findQuadraticInequalityCorrectSolutionForSingleRoot(root_1, standardQuadraticInequalityParameters, quadraticEquationCorrectSolution.correct());
		} else {
			return QuadraticInequalityCorrectSolution.createEmptySetCorrectSolution();
		}
	}

	private QuadraticInequalityCorrectSolution findQuadraticInequalityCorrectSolutionForDifferentRoots(QuadraticEquationCorrectSolution roots, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var root_1 = roots.x_1();
		final var root_2 = roots.x_2();
		
		final var root_1AsDouble = root_1.toDouble();
		final var root_2AsDouble = root_2.toDouble();
		final var min = Math.min(root_1AsDouble, root_2AsDouble);
		final var max = Math.max(root_1AsDouble, root_2AsDouble);
		final var x_1 = root_1AsDouble == min ? root_1 : root_2;
		final var x_2 = root_2AsDouble == max ? root_2 : root_1;
	
		final var standardQuadraticEquationParameters = standardQuadraticInequalityParameters.equationParameters();
		final var beforeMin = Math.min(root_1AsDouble, root_2AsDouble) - 10;
		final var afterMax = Math.max(root_1AsDouble, root_2AsDouble) + 10;
		final var between = (root_1AsDouble + root_2AsDouble) / 2;

		final var solutionBeforeMin = this.solveFor(standardQuadraticEquationParameters, beforeMin);
		final var solutionAfterMax = this.solveFor(standardQuadraticEquationParameters, afterMax);
		final var solutionBetween = this.solveFor(standardQuadraticEquationParameters, between);

		final var sign = standardQuadraticInequalityParameters.sign();

		switch (sign) {
			case GREATER:
				if (solutionBeforeMin > 0 && solutionAfterMax > 0 && solutionBetween > 0) {
					return QuadraticInequalityCorrectSolution.createRCorrectSolution();
				} else if (solutionBeforeMin > 0 && solutionAfterMax > 0) {
					return generateOutsideExclusiveRootsResult(x_1, x_2);
				} else if (solutionBetween > 0) {
					return generateBetweenExclusiveRootsResult(x_1, x_2);
				} else {
					return QuadraticInequalityCorrectSolution.createEmptySetCorrectSolution();
				}
			case GREATER_OR_EQUALS:
				if (solutionBeforeMin > 0 && solutionAfterMax > 0 && solutionBetween > 0) {
					return QuadraticInequalityCorrectSolution.createRCorrectSolution();
				} else if (solutionBeforeMin > 0 && solutionAfterMax > 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2);
				} else if (solutionBetween > 0) {
					return generateBetweenInclusiveRootsResult(x_1, x_2);
				} else {
					return QuadraticInequalityCorrectSolution.createEmptySetCorrectSolution();
				}
			case LESS:
				if (solutionBeforeMin < 0 && solutionAfterMax < 0 && solutionBetween < 0) {
					return QuadraticInequalityCorrectSolution.createRCorrectSolution();
				} else if (solutionBeforeMin < 0 && solutionAfterMax < 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2);
				} else if (solutionBetween < 0) {
					return generateBetweenExclusiveRootsResult(x_1, x_2);
				} else {
					return QuadraticInequalityCorrectSolution.createEmptySetCorrectSolution();
				}
			case LESS_OR_EQUALS:
				if (solutionBeforeMin < 0 && solutionAfterMax < 0 && solutionBetween < 0) {
					return QuadraticInequalityCorrectSolution.createRCorrectSolution();
				} else if (solutionBeforeMin < 0 && solutionAfterMax < 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2);
				} else if (solutionBetween < 0) {
					return generateBetweenInclusiveRootsResult(x_1, x_2);
				} else {
					return QuadraticInequalityCorrectSolution.createEmptySetCorrectSolution();
				}
			default:
				throw new IllegalStateException("Invalid sign");
		}
	}

	private QuadraticInequalityCorrectSolution findQuadraticInequalityCorrectSolutionForSingleRoot(SymbolicNumberFraction root, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters, boolean correct) {
		final var rootAsDouble = root.toDouble();
	
		final var standardQuadraticEquationParameters = standardQuadraticInequalityParameters.equationParameters();
		final var beforeMin = rootAsDouble - 10;

		final var solutionBeforeMin = this.solveFor(standardQuadraticEquationParameters, beforeMin);

		final var sign = standardQuadraticInequalityParameters.sign();
		switch (sign) {
			case GREATER:
				if (solutionBeforeMin > 0) {
					return QuadraticInequalityCorrectSolution.createRExceptZeroCorrectSolution(root);
				} else {
					return QuadraticInequalityCorrectSolution.createEmptySetCorrectSolution();
				}
			case GREATER_OR_EQUALS:
				if (solutionBeforeMin > 0) {
					return QuadraticInequalityCorrectSolution.createRCorrectSolution();
				} else {
					return QuadraticInequalityCorrectSolution.createOnlyZeroCorrectSolution(root);
				}
			case LESS:
				if (solutionBeforeMin < 0) {
					return QuadraticInequalityCorrectSolution.createRExceptZeroCorrectSolution(root);
				} else {
					return QuadraticInequalityCorrectSolution.createEmptySetCorrectSolution();
				}
			case LESS_OR_EQUALS:
				if (solutionBeforeMin < 0) {
					return QuadraticInequalityCorrectSolution.createRCorrectSolution();
				} else {
					return QuadraticInequalityCorrectSolution.createOnlyZeroCorrectSolution(root);
				}
			default:
				throw new IllegalStateException("Invalid sign");
		}
	}

	private double solveFor(StandardQuadraticEquationParameters standardQuadraticEquationParameters, double x) {
		final var a = standardQuadraticEquationParameters.a().toDouble();
		final var b = standardQuadraticEquationParameters.b().toDouble();
		final var c = standardQuadraticEquationParameters.c().toDouble();

		return a * Math.pow(x, 2) + b * x + c;
	}

	private QuadraticInequalityCorrectSolution generateOutsideExclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.LESS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.GREATER);
		return new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2);
	}

	private QuadraticInequalityCorrectSolution generateBetweenExclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.GREATER);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.LESS);
		return new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2);
	}

	private QuadraticInequalityCorrectSolution generateBetweenInclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.GREATER_OR_EQUALS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.LESS_OR_EQUALS);
		return new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2);
	}

	private QuadraticInequalityCorrectSolution generateOutsideInclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.LESS_OR_EQUALS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.GREATER_OR_EQUALS);
		return new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2);
	}
}
