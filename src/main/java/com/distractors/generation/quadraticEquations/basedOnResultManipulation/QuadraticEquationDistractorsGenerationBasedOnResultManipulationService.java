package com.distractors.generation.quadraticEquations.basedOnResultManipulation;

import java.util.ArrayList;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationAnswers;
import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationSolution;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

public class QuadraticEquationDistractorsGenerationBasedOnResultManipulationService {

	final AbcSolutionService abc = new AbcSolutionService();

	public QuadraticEquationAnswers generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var abc = new AbcSolutionService();
		final var correctSolution = abc.solveCorrectly(standardQuadraticEquationParameters);
		var distractors = new ArrayList<QuadraticEquationSolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(correctSolution, distractors);

		return new QuadraticEquationAnswers(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private boolean isDistractorInvalid(QuadraticEquationDistractor possibleDistractor, ArrayList<QuadraticEquationSolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticEquationDistractor generateDifferentDistractor(final QuadraticEquationCorrectSolution correctSolution, ArrayList<QuadraticEquationSolution> distractors) {
		QuadraticEquationDistractor distractor;
		do {
			distractor = this.generateDistractor(correctSolution);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private QuadraticEquationDistractor generateDistractor(QuadraticEquationCorrectSolution correctSolution) {
		final var randomResultManipulationType = QuadraticEquationResultManipulationType.randomManipulationType();

		return generateDistractorWithResultManipulationType(correctSolution, randomResultManipulationType);
	}

	public QuadraticEquationDistractor generateDistractorWithResultManipulationType(QuadraticEquationCorrectSolution correctSolution, final QuadraticEquationResultManipulationType resultManipulationType) {
		try {			
			switch (resultManipulationType) {
				case MINUS_ONE_X_1:
					return this.generateDistractorReplacingX_1ByMinusOne(correctSolution);
				case MINUS_ONE_X_2:
					return this.generateDistractorReplacingX_2ByMinusOne(correctSolution);
				case NEGATE_BOTH:
					return this.generateDistractorNegatingBothRoots(correctSolution);
				case NEGATE_X_1:
					return this.generateDistractorNegatingX_1(correctSolution);
				case NEGATE_X_2:
					return this.generateDistractorNegatingX_2(correctSolution);
				case ONE_X_1:
					return this.generateDistractorReplacingX_1ByOne(correctSolution);
				case MINUS_ONE_X_1_ONE_X_2:
					return this.generateDistractorReplacingRootsByOneAndMinusOne(correctSolution);
				case ONE_X_2:
					return this.generateDistractorReplacingX_2ByOne(correctSolution);
				case REVERSE_BOTH:
					return this.generateDistractorReversingBothRoots(correctSolution);
				case REVERSE_X_1:
					return this.generateDistractorReversingX_1(correctSolution);
				case REVRESE_X_2:
					return this.generateDistractorReversingX_2(correctSolution);
				case ZERO_X_1:
					return this.generateDistractorReplacingX_1ByZero(correctSolution);
				case ZERO_X_2:
					return this.generateDistractorReplacingX_2ByZero(correctSolution);
				default:
					throw new IllegalArgumentException("Unknown result manipulation type.");
			}
		} catch (IllegalArgumentException e) {
			return new QuadraticEquationDistractor(correctSolution.x_1(), correctSolution.x_2(), QuadraticEquationResultManipulationType.ZERO_X_2);
		}
	}
	
	private QuadraticEquationDistractor generateDistractorReplacingX_2ByZero(QuadraticEquationCorrectSolution correctSolution) {
		return new QuadraticEquationDistractor(correctSolution.x_1(), SymbolicNumberFraction.ZERO, QuadraticEquationResultManipulationType.ZERO_X_2);
	}
	
	private QuadraticEquationDistractor generateDistractorReplacingX_1ByZero(QuadraticEquationCorrectSolution correctSolution) {
		return new QuadraticEquationDistractor(SymbolicNumberFraction.ZERO, correctSolution.x_2(), QuadraticEquationResultManipulationType.ZERO_X_1);
	}

	private QuadraticEquationDistractor generateDistractorReversingBothRoots(QuadraticEquationCorrectSolution correctSolution) {
		final var x_1 = correctSolution.x_1().reverse();
		final var x_2 = correctSolution.x_2().reverse();
		return new QuadraticEquationDistractor(x_1, x_2, QuadraticEquationResultManipulationType.REVERSE_BOTH);
	}

	private QuadraticEquationDistractor generateDistractorReversingX_2(QuadraticEquationCorrectSolution correctSolution) {
		final var x_2 = correctSolution.x_2().reverse();
		return new QuadraticEquationDistractor(correctSolution.x_1(), x_2, QuadraticEquationResultManipulationType.REVRESE_X_2);
	}

	private QuadraticEquationDistractor generateDistractorReversingX_1(QuadraticEquationCorrectSolution correctSolution) {
		final var x_1 = correctSolution.x_1().reverse();
		return new QuadraticEquationDistractor(x_1, correctSolution.x_2(), QuadraticEquationResultManipulationType.REVERSE_X_1);
	}

	private QuadraticEquationDistractor generateDistractorReplacingRootsByOneAndMinusOne(QuadraticEquationCorrectSolution correctSolution) {
		return new QuadraticEquationDistractor(SymbolicNumberFraction.MINUS_ONE, SymbolicNumberFraction.ONE, QuadraticEquationResultManipulationType.MINUS_ONE_X_1_ONE_X_2);
	}

	private QuadraticEquationDistractor generateDistractorReplacingX_2ByOne(QuadraticEquationCorrectSolution correctSolution) {
		return new QuadraticEquationDistractor(correctSolution.x_1(), SymbolicNumberFraction.ONE, QuadraticEquationResultManipulationType.ONE_X_2);
	}

	private QuadraticEquationDistractor generateDistractorReplacingX_1ByOne(QuadraticEquationCorrectSolution correctSolution) {
		return new QuadraticEquationDistractor(SymbolicNumberFraction.ONE, correctSolution.x_2(), QuadraticEquationResultManipulationType.ONE_X_1);
	}

	private QuadraticEquationDistractor generateDistractorNegatingX_2(QuadraticEquationCorrectSolution correctSolution) {
		final var x_2 = correctSolution.x_2().multiplyBy(-1);
		return new QuadraticEquationDistractor(correctSolution.x_1(), x_2, QuadraticEquationResultManipulationType.NEGATE_X_2);
	}

	private QuadraticEquationDistractor generateDistractorNegatingX_1(QuadraticEquationCorrectSolution correctSolution) {
		final var x_1 = correctSolution.x_1().multiplyBy(-1);
		return new QuadraticEquationDistractor(x_1, correctSolution.x_2(), QuadraticEquationResultManipulationType.NEGATE_X_1);
	}

	private QuadraticEquationDistractor generateDistractorNegatingBothRoots(QuadraticEquationCorrectSolution correctSolution) {
		final var x_1 = correctSolution.x_1().multiplyBy(-1);
		final var x_2 = correctSolution.x_2().multiplyBy(-1);
		return new QuadraticEquationDistractor(x_1, x_2, QuadraticEquationResultManipulationType.NEGATE_BOTH);
	}

	private QuadraticEquationDistractor generateDistractorReplacingX_1ByMinusOne(QuadraticEquationCorrectSolution correctSolution) {
		return new QuadraticEquationDistractor(SymbolicNumberFraction.MINUS_ONE, correctSolution.x_2(), QuadraticEquationResultManipulationType.MINUS_ONE_X_1);
	}

	private QuadraticEquationDistractor generateDistractorReplacingX_2ByMinusOne(QuadraticEquationCorrectSolution correctSolution) {
		return new QuadraticEquationDistractor(correctSolution.x_1(), SymbolicNumberFraction.MINUS_ONE, QuadraticEquationResultManipulationType.MINUS_ONE_X_2);
	}
}
