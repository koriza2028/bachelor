package com.distractors.generation.errorBased.quadraticInequalities;

import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationDistractors;
import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.errorBased.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.general.SymbolicNumberFraction;

public class QuadraticInequalitySolutionMapper {

	public QuadraticInequalityDistractors findQuadraticInequalityDistractors(QuadraticEquationDistractors distractors, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var correctSolution = this.findQuadraticInequalitySolution(distractors.correctSolution(), standardQuadraticInequalityParameters);
		final var distractor_1 = this.findQuadraticInequalitySolution(distractors.distractor_1(), standardQuadraticInequalityParameters);
		final var distractor_2 = this.findQuadraticInequalitySolution(distractors.distractor_2(), standardQuadraticInequalityParameters);
		final var distractor_3 = this.findQuadraticInequalitySolution(distractors.distractor_3(), standardQuadraticInequalityParameters);

		return new QuadraticInequalityDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	public QuadraticInequalitySolution findQuadraticInequalitySolution(QuadraticEquationRoots roots, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var root_1 = roots.x_1();
		final var root_2 = roots.x_2();
		
		if (root_1 != null && root_2 != null) {
			if (root_1.equals(root_2)) {
				return findQuadraticInequalitySolutionForEqualRoots(root_1, standardQuadraticInequalityParameters);
			} else return findQuadraticInequalitySolutionForDifferentRoots(roots, standardQuadraticInequalityParameters);

		} else if (root_1 == null && root_2 != null) {
			return findQuadraticInequalitySolutionForEqualRoots(root_2, standardQuadraticInequalityParameters);
		} else if (root_2 == null && root_1 != null) {
			return findQuadraticInequalitySolutionForEqualRoots(root_1, standardQuadraticInequalityParameters);
		} else 	return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, root_1, root_1, InequalitySign.GREATER, InequalitySign.GREATER);
	}

	private QuadraticInequalitySolution findQuadraticInequalitySolutionForDifferentRoots(QuadraticEquationRoots roots, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
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
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
				} else if (solutionBeforeMin > 0 && solutionAfterMax > 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, x_1, x_2, InequalitySign.LESS, InequalitySign.GREATER);
				} else if (solutionBetween > 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, x_1, x_2, InequalitySign.GREATER, InequalitySign.LESS);
				} else {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
				}
			case GREATER_OR_EQUALS:
				if (solutionBeforeMin > 0 && solutionAfterMax > 0 && solutionBetween > 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
				} else if (solutionBeforeMin > 0 && solutionAfterMax > 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, x_1, x_2, InequalitySign.LESS_OR_EQUALS, InequalitySign.GREATER_OR_EQUALS);
				} else if (solutionBetween > 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, x_1, x_2, InequalitySign.GREATER_OR_EQUALS, InequalitySign.LESS_OR_EQUALS);
				} else {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
				}
			case LESS:
				if (solutionBeforeMin < 0 && solutionAfterMax < 0 && solutionBetween < 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
				} else if (solutionBeforeMin < 0 && solutionAfterMax < 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, x_1, x_2, InequalitySign.LESS, InequalitySign.GREATER);
				} else if (solutionBetween < 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, x_1, x_2, InequalitySign.GREATER, InequalitySign.LESS);
				} else {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
				}
			case LESS_OR_EQUALS:
				if (solutionBeforeMin < 0 && solutionAfterMax < 0 && solutionBetween < 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
				} else if (solutionBeforeMin < 0 && solutionAfterMax < 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, x_1, x_2, InequalitySign.LESS_OR_EQUALS, InequalitySign.GREATER_OR_EQUALS);
				} else if (solutionBetween < 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, x_1, x_2, InequalitySign.GREATER_OR_EQUALS, InequalitySign.LESS_OR_EQUALS);
				} else {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
				}
			default:
				throw new IllegalStateException("Invalid sign");
		}
	}

	private QuadraticInequalitySolution findQuadraticInequalitySolutionForEqualRoots(SymbolicNumberFraction root, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var rootAsDouble = root.toDouble();
	
		final var standardQuadraticEquationParameters = standardQuadraticInequalityParameters.equationParameters();
		final var beforeMin = rootAsDouble - 10;

		final var solutionBeforeMin = this.solveFor(standardQuadraticEquationParameters, beforeMin);

		final var sign = standardQuadraticInequalityParameters.sign();
		switch (sign) {
			case GREATER:
				if (solutionBeforeMin > 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, root, root, InequalitySign.GREATER, InequalitySign.GREATER);
				} else {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, root, root, InequalitySign.GREATER, InequalitySign.GREATER);
				}
			case GREATER_OR_EQUALS:
				if (solutionBeforeMin > 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, root, root, InequalitySign.GREATER, InequalitySign.GREATER);
				} else {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.ONLY_ZERO, root, root, InequalitySign.GREATER, InequalitySign.GREATER);
				}
			case LESS:
				if (solutionBeforeMin < 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, root, root, InequalitySign.GREATER, InequalitySign.GREATER);
				} else {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, root, root, InequalitySign.GREATER, InequalitySign.GREATER);
				}
			case LESS_OR_EQUALS:
				if (solutionBeforeMin < 0) {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, root, root, InequalitySign.GREATER, InequalitySign.GREATER);
				} else {
					return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.ONLY_ZERO, root, root, InequalitySign.GREATER, InequalitySign.GREATER);
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
}
