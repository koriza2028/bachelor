package com.distractors.generation.quadraticInequalities;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
import com.distractors.generation.quadraticEquations.QuadraticEquationSolutionImpact;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;

public class QuadraticInequalityDistractorMapper {

	public QuadraticInequalityDistractor findQuadraticInequalityDistractor(QuadraticEquationDistractor quadraticEquationDistractor, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var root_1 = quadraticEquationDistractor.x_1();
		final var root_2 = quadraticEquationDistractor.x_2();
		
		if (root_1 != null && root_2 != null) {
			if (root_1.equals(root_2)) {
				return findQuadraticInequalityDistractorForSingleRoot(root_1, standardQuadraticInequalityParameters, quadraticEquationDistractor.impact());
			} else return findQuadraticInequalityDistractorForDifferentRoots(quadraticEquationDistractor, standardQuadraticInequalityParameters);

		} else if (root_1 == null && root_2 != null) {
			return findQuadraticInequalityDistractorForSingleRoot(root_2, standardQuadraticInequalityParameters, quadraticEquationDistractor.impact());
		} else if (root_2 == null && root_1 != null) {
			return findQuadraticInequalityDistractorForSingleRoot(root_1, standardQuadraticInequalityParameters, quadraticEquationDistractor.impact());
		} else {
			return QuadraticInequalityDistractor.createEmptySetDistractor(quadraticEquationDistractor.impact());
		}
	}

	private QuadraticInequalityDistractor findQuadraticInequalityDistractorForDifferentRoots(QuadraticEquationDistractor roots, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
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
		final var impact = roots.impact();

		switch (sign) {
			case GREATER:
				if (solutionBeforeMin > 0 && solutionAfterMax > 0 && solutionBetween > 0) {
					return QuadraticInequalityDistractor.createRDistractor(impact);
				} else if (solutionBeforeMin > 0 && solutionAfterMax > 0) {
					return generateOutsideExclusiveRootsResult(x_1, x_2, impact);
				} else if (solutionBetween > 0) {
					return generateBetweenExclusiveRootsResult(x_1, x_2, impact);
				} else {
					return QuadraticInequalityDistractor.createEmptySetDistractor(impact);
				}
			case GREATER_OR_EQUALS:
				if (solutionBeforeMin > 0 && solutionAfterMax > 0 && solutionBetween > 0) {
					return QuadraticInequalityDistractor.createRDistractor(impact);
				} else if (solutionBeforeMin > 0 && solutionAfterMax > 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2, impact);
				} else if (solutionBetween > 0) {
					return generateBetweenInclusiveRootsResult(x_1, x_2, impact);
				} else {
					return QuadraticInequalityDistractor.createEmptySetDistractor(impact);
				}
			case LESS:
				if (solutionBeforeMin < 0 && solutionAfterMax < 0 && solutionBetween < 0) {
					return QuadraticInequalityDistractor.createRDistractor(impact);
				} else if (solutionBeforeMin < 0 && solutionAfterMax < 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2, impact);
				} else if (solutionBetween < 0) {
					return generateBetweenExclusiveRootsResult(x_1, x_2, impact);
				} else {
					return QuadraticInequalityDistractor.createEmptySetDistractor(impact);
				}
			case LESS_OR_EQUALS:
				if (solutionBeforeMin < 0 && solutionAfterMax < 0 && solutionBetween < 0) {
					return QuadraticInequalityDistractor.createRDistractor(impact);
				} else if (solutionBeforeMin < 0 && solutionAfterMax < 0) {
					return generateOutsideInclusiveRootsResult(x_1, x_2, impact);
				} else if (solutionBetween < 0) {
					return generateBetweenInclusiveRootsResult(x_1, x_2, impact);
				} else {
					return QuadraticInequalityDistractor.createEmptySetDistractor(impact);
				}
			default:
				throw new IllegalStateException("Invalid sign");
		}
	}

	private QuadraticInequalityDistractor findQuadraticInequalityDistractorForSingleRoot(SymbolicNumberFraction root, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters, QuadraticEquationSolutionImpact impact) {
		final var rootAsDouble = root.toDouble();
	
		final var standardQuadraticEquationParameters = standardQuadraticInequalityParameters.equationParameters();
		final var beforeMin = rootAsDouble - 10;

		final var solutionBeforeMin = this.solveFor(standardQuadraticEquationParameters, beforeMin);

		final var sign = standardQuadraticInequalityParameters.sign();
		switch (sign) {
			case GREATER:
				if (solutionBeforeMin > 0) {
					return QuadraticInequalityDistractor.createRExceptZeroDistractor(root, impact);
				} else {
					return QuadraticInequalityDistractor.createEmptySetDistractor(impact);
				}
			case GREATER_OR_EQUALS:
				if (solutionBeforeMin > 0) {
					return QuadraticInequalityDistractor.createRDistractor(impact);
				} else {
					return QuadraticInequalityDistractor.createOnlyZeroDistractor(root, impact);
				}
			case LESS:
				if (solutionBeforeMin < 0) {
					return QuadraticInequalityDistractor.createRExceptZeroDistractor(root, impact);
				} else {
					return QuadraticInequalityDistractor.createEmptySetDistractor(impact);
				}
			case LESS_OR_EQUALS:
				if (solutionBeforeMin < 0) {
					return QuadraticInequalityDistractor.createRDistractor(impact);
				} else {
					return QuadraticInequalityDistractor.createOnlyZeroDistractor(root, impact);
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

	private QuadraticInequalityDistractor generateOutsideExclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, QuadraticEquationSolutionImpact impact) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.LESS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.GREATER);
		return new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, impact);
	}

	private QuadraticInequalityDistractor generateBetweenExclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, QuadraticEquationSolutionImpact impact) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.GREATER);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.LESS);
		return new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, impact);
	}

	private QuadraticInequalityDistractor generateBetweenInclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, QuadraticEquationSolutionImpact impact) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.GREATER_OR_EQUALS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.LESS_OR_EQUALS);
		return new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, impact);
	}

	private QuadraticInequalityDistractor generateOutsideInclusiveRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, QuadraticEquationSolutionImpact impact) {
		final var range_1 = new QuadraticInequalityRange(x_1, InequalitySign.LESS_OR_EQUALS);
		final var range_2 = new QuadraticInequalityRange(x_2, InequalitySign.GREATER_OR_EQUALS);
		return new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, impact);
	}

}
