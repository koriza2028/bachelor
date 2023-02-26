package com.distractors.generation.quadraticEquations.basedOnResultManipulation;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractors;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

public class QuadraticEquationDistractorsGenerationBasedOnResultManipulationService {

	final AbcSolutionService abc = new AbcSolutionService();

	public QuadraticEquationDistractors generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var abc = new AbcSolutionService();
		final var correctSolution = abc.solveCorrectly(standardQuadraticEquationParameters);
		var distractors = new ArrayList<QuadraticEquationRoots> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(correctSolution, distractors);

		return new QuadraticEquationDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private boolean isDistractorInvalid(QuadraticEquationRoots possibleDistractor, List<QuadraticEquationRoots> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticEquationRoots generateDifferentDistractor(final QuadraticEquationRoots correctSolution, List<QuadraticEquationRoots> distractors) {
		QuadraticEquationRoots distractor;
		do {
			distractor = this.generateDistractor(correctSolution);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private QuadraticEquationRoots generateDistractor(QuadraticEquationRoots correctSolution) {
		final var randomResultManipulationType = QuadraticEquationResultManipulationType.randomManipulationType();

		return generateDistractorWithResultManipulationType(correctSolution, randomResultManipulationType);
	}

	public QuadraticEquationRoots generateDistractorWithResultManipulationType(QuadraticEquationRoots correctSolution, final QuadraticEquationResultManipulationType resultManipulationType) {
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
			return correctSolution;
		}
	}
	
	private QuadraticEquationRoots generateDistractorReplacingX_2ByZero(QuadraticEquationRoots correctSolution) {
		return QuadraticEquationRoots.createDistractor(correctSolution.x_1(), SymbolicNumberFraction.ZERO);
	}
	
	private QuadraticEquationRoots generateDistractorReplacingX_1ByZero(QuadraticEquationRoots correctSolution) {
		return QuadraticEquationRoots.createDistractor(SymbolicNumberFraction.ZERO, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorReversingBothRoots(QuadraticEquationRoots correctSolution) {
		final var x_1 = correctSolution.x_1().reverse();
		final var x_2 = correctSolution.x_2().reverse();
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	private QuadraticEquationRoots generateDistractorReversingX_2(QuadraticEquationRoots correctSolution) {
		final var x_2 = correctSolution.x_2().reverse();
		return QuadraticEquationRoots.createDistractor(correctSolution.x_1(), x_2);
	}

	private QuadraticEquationRoots generateDistractorReversingX_1(QuadraticEquationRoots correctSolution) {
		final var x_1 = correctSolution.x_1().reverse();
		return QuadraticEquationRoots.createDistractor(x_1, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorReplacingRootsByOneAndMinusOne(QuadraticEquationRoots correctSolution) {
		return QuadraticEquationRoots.createDistractor(SymbolicNumberFraction.MINUS_ONE, SymbolicNumberFraction.ONE);
	}

	private QuadraticEquationRoots generateDistractorReplacingX_2ByOne(QuadraticEquationRoots correctSolution) {
		return QuadraticEquationRoots.createDistractor(correctSolution.x_1(), SymbolicNumberFraction.ONE);
	}

	private QuadraticEquationRoots generateDistractorReplacingX_1ByOne(QuadraticEquationRoots correctSolution) {
		return QuadraticEquationRoots.createDistractor(SymbolicNumberFraction.ONE, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorNegatingX_2(QuadraticEquationRoots correctSolution) {
		final var x_2 = correctSolution.x_2().multiplyBy(-1);
		return QuadraticEquationRoots.createDistractor(correctSolution.x_1(), x_2);
	}

	private QuadraticEquationRoots generateDistractorNegatingX_1(QuadraticEquationRoots correctSolution) {
		final var x_1 = correctSolution.x_1().multiplyBy(-1);
		return QuadraticEquationRoots.createDistractor(x_1, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorNegatingBothRoots(QuadraticEquationRoots correctSolution) {
		final var x_1 = correctSolution.x_1().multiplyBy(-1);
		final var x_2 = correctSolution.x_2().multiplyBy(-1);
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	private QuadraticEquationRoots generateDistractorReplacingX_1ByMinusOne(QuadraticEquationRoots correctSolution) {
		return QuadraticEquationRoots.createDistractor(SymbolicNumberFraction.MINUS_ONE, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorReplacingX_2ByMinusOne(QuadraticEquationRoots correctSolution) {
		return QuadraticEquationRoots.createDistractor(correctSolution.x_1(), SymbolicNumberFraction.MINUS_ONE);
	}
}
