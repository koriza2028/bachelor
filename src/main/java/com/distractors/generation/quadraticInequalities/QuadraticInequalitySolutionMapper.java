package com.distractors.generation.quadraticInequalities;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractors;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;

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
				return findQuadraticInequalitySolutionForSingleRoot(root_1, standardQuadraticInequalityParameters, roots.correct());
			} else return findQuadraticInequalitySolutionForDifferentRoots(roots, standardQuadraticInequalityParameters);

		} else if (root_1 == null && root_2 != null) {
			return findQuadraticInequalitySolutionForSingleRoot(root_2, standardQuadraticInequalityParameters, roots.correct());
		} else if (root_2 == null && root_1 != null) {
			return findQuadraticInequalitySolutionForSingleRoot(root_1, standardQuadraticInequalityParameters, roots.correct());
		} else {
			return QuadraticInequalitySolution.createEmptySetSolution(roots.correct());
		}
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
					return QuadraticInequalitySolution.createRSolution(roots.correct());
				} else if (solutionBeforeMin > 0 && solutionAfterMax > 0) {
					return generateOutsideExclusiveRootsResult(x_1, x_2, roots.correct());
				} else if (solutionBetween > 0) {
					return generateBetweenExclusiveRootsResult(x_1, x_2, roots.correct());
				} else {
					return QuadraticInequalitySolution.createEmptySetSolution(roots.correct());
				}
			case GREATER_OR_EQUALS:
				if (solutionBeforeMin > 0 && solutionAfterMax > 0 && solutionBetween > 0) {
					return QuadraticInequalitySolution.createRSolution(roots.correct());
				} else if (solutionBeforeMin > 0 && solutionAfterMax > 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2, roots.correct());
				} else if (solutionBetween > 0) {
					return generateBetweenInclusiveRootsResult(x_1, x_2, roots.correct());
				} else {
					return QuadraticInequalitySolution.createEmptySetSolution(roots.correct());
				}
			case LESS:
				if (solutionBeforeMin < 0 && solutionAfterMax < 0 && solutionBetween < 0) {
					return QuadraticInequalitySolution.createRSolution(roots.correct());
				} else if (solutionBeforeMin < 0 && solutionAfterMax < 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2, roots.correct());
				} else if (solutionBetween < 0) {
					return generateBetweenExclusiveRootsResult(x_1, x_2, roots.correct());
				} else {
					return QuadraticInequalitySolution.createEmptySetSolution(roots.correct());
				}
			case LESS_OR_EQUALS:
				if (solutionBeforeMin < 0 && solutionAfterMax < 0 && solutionBetween < 0) {
					return QuadraticInequalitySolution.createRSolution(roots.correct());
				} else if (solutionBeforeMin < 0 && solutionAfterMax < 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2, roots.correct());
				} else if (solutionBetween < 0) {
					return generateBetweenInclusiveRootsResult(x_1, x_2, roots.correct());
				} else {
					return QuadraticInequalitySolution.createEmptySetSolution(roots.correct());
				}
			default:
				throw new IllegalStateException("Invalid sign");
		}
	}

	private QuadraticInequalitySolution findQuadraticInequalitySolutionForSingleRoot(SymbolicNumberFraction root, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters, boolean correct) {
		final var rootAsDouble = root.toDouble();
	
		final var standardQuadraticEquationParameters = standardQuadraticInequalityParameters.equationParameters();
		final var beforeMin = rootAsDouble - 10;

		final var solutionBeforeMin = this.solveFor(standardQuadraticEquationParameters, beforeMin);

		final var sign = standardQuadraticInequalityParameters.sign();
		switch (sign) {
			case GREATER:
				if (solutionBeforeMin > 0) {
					return QuadraticInequalitySolution.createRExceptZeroSolution(root, correct);
				} else {
					return QuadraticInequalitySolution.createEmptySetSolution(correct);
				}
			case GREATER_OR_EQUALS:
				if (solutionBeforeMin > 0) {
					return QuadraticInequalitySolution.createRSolution(correct);
				} else {
					return QuadraticInequalitySolution.createOnlyZeroSolution(root, correct);
				}
			case LESS:
				if (solutionBeforeMin < 0) {
					return QuadraticInequalitySolution.createRExceptZeroSolution(root, correct);
				} else {
					return QuadraticInequalitySolution.createEmptySetSolution(correct);
				}
			case LESS_OR_EQUALS:
				if (solutionBeforeMin < 0) {
					return QuadraticInequalitySolution.createRSolution(correct);
				} else {
					return QuadraticInequalitySolution.createOnlyZeroSolution(root, correct);
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

	private QuadraticInequalitySolution generateOutsideExclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, boolean correct) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.LESS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, correct);
	}

	private QuadraticInequalitySolution generateBetweenExclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, boolean correct) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.GREATER);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.LESS);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, correct);
	}

	private QuadraticInequalitySolution generateBetweenInclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, boolean correct) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.GREATER_OR_EQUALS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.LESS_OR_EQUALS);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, correct);
	}

	private QuadraticInequalitySolution generateOutsideInclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, boolean correct) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.LESS_OR_EQUALS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.GREATER_OR_EQUALS);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, correct);
	}

}
