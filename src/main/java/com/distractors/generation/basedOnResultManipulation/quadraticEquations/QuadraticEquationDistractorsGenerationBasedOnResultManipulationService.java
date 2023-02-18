package com.distractors.generation.basedOnResultManipulation.quadraticEquations;

import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationDistractors;
import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.errorBased.quadraticEquations.abc.AbcSolutionService;
import com.distractors.generation.general.SymbolicNumberFraction;

public class QuadraticEquationDistractorsGenerationBasedOnResultManipulationService {

	final AbcSolutionService abc = new AbcSolutionService();

	public QuadraticEquationDistractors generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var abc = new AbcSolutionService();
		final var correctSolution = abc.solveCorrectly(standardQuadraticEquationParameters);

		QuadraticEquationRoots distractor_1 = correctSolution;
		QuadraticEquationRoots distractor_2 = correctSolution;
		QuadraticEquationRoots distractor_3 = correctSolution;

		do {
			try {
				distractor_1 = this.generateDistractor(correctSolution);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_1 == null || distractor_1.equals(correctSolution));

		do {
			try {
				distractor_2 = this.generateDistractor(correctSolution);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_2 == null || distractor_2.equals(correctSolution) || distractor_2.equals(distractor_1));

		do {
			try {
				distractor_3 = this.generateDistractor(correctSolution);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_3 == null || distractor_3.equals(correctSolution) || distractor_3.equals(distractor_1) || distractor_3.equals(distractor_2));

		return new QuadraticEquationDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private QuadraticEquationRoots generateDistractor(QuadraticEquationRoots correctSolution) {
		final var randomResultManipulationType = QuadraticEquationResultManipulationType.randomManipulationType();
		final var randomRoot = QuadraticEquationRoot.randomRoot();
		
		switch (randomResultManipulationType) {
			case MINUS_ONE:
				return generateDistractorReplacingARootByMinusOne(correctSolution, randomRoot);
			case NEGATE_BOTH_ANSWERS:
				return generateDistractorNegatingBothRoots(correctSolution);
			case NEGATE_ONE_ANSWER:
				return generateDistractorNegatingOneRoot(correctSolution, randomRoot);
			case ONE:
				return generateDistractorReplacingARootByOne(correctSolution, randomRoot);
			case ONE_MINUS_ONE:
				return generateDistractorReplacingRootsByOneAndMinusOne(correctSolution);
			case REVERSE_ONE_FRACTION:
				return generateDistractorReversingOneRoot(correctSolution, randomRoot);
			case REVERSE_TWO_FRACTIONS:
				return generateDistractorReversingBothRoots(correctSolution);
			case ZERO:
				return generateDistractorReplacingARootByZero(correctSolution, randomRoot);
			default:
				throw new IllegalArgumentException("Unknown result manipulation type.");
		}
	}

	private QuadraticEquationRoots generateDistractorReplacingARootByZero(QuadraticEquationRoots correctSolution, QuadraticEquationRoot rootToReplace) {
		switch (rootToReplace) {
			case X_1:
				return this.generateDistractorReplacingX_1ByZero(correctSolution);
			case X_2:
				return this.generateDistractorReplacingX_2ByZero(correctSolution);
			default:
				throw new IllegalArgumentException("Unknown result manipulation type.");
		}
	}
	
	private QuadraticEquationRoots generateDistractorReplacingX_2ByZero(QuadraticEquationRoots correctSolution) {
		return new QuadraticEquationRoots(correctSolution.x_1(), SymbolicNumberFraction.ZERO);
	}
	
	private QuadraticEquationRoots generateDistractorReplacingX_1ByZero(QuadraticEquationRoots correctSolution) {
		return new QuadraticEquationRoots(SymbolicNumberFraction.ZERO, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorReversingBothRoots(QuadraticEquationRoots correctSolution) {
		final var x_1 = correctSolution.x_1().reverse();
		final var x_2 = correctSolution.x_2().reverse();
		return new QuadraticEquationRoots(x_1, x_2);
	}

	private QuadraticEquationRoots generateDistractorReversingOneRoot(QuadraticEquationRoots correctSolution, QuadraticEquationRoot rootToReplace) {
		switch (rootToReplace) {
			case X_1:
				return this.generateDistractorReversingX_1(correctSolution);
			case X_2:
				return this.generateDistractorReversingX_2(correctSolution);
			default:
				throw new IllegalArgumentException("Unknown result manipulation type.");
		}
	}

	private QuadraticEquationRoots generateDistractorReversingX_2(QuadraticEquationRoots correctSolution) {
		final var x_2 = correctSolution.x_2().reverse();
		return new QuadraticEquationRoots(correctSolution.x_1(), x_2);
	}

	private QuadraticEquationRoots generateDistractorReversingX_1(QuadraticEquationRoots correctSolution) {
		final var x_1 = correctSolution.x_1().reverse();
		return new QuadraticEquationRoots(x_1, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorReplacingRootsByOneAndMinusOne(QuadraticEquationRoots correctSolution) {
		return new QuadraticEquationRoots(SymbolicNumberFraction.MINUS_ONE, SymbolicNumberFraction.ONE);
	}

	private QuadraticEquationRoots generateDistractorReplacingARootByOne(QuadraticEquationRoots correctSolution, QuadraticEquationRoot rootToReplace) {
		switch (rootToReplace) {
			case X_1:
				return this.generateDistractorReplacingX_1ByOne(correctSolution);
			case X_2:
				return this.generateDistractorReplacingX_2ByOne(correctSolution);
			default:
				throw new IllegalArgumentException("Unknown result manipulation type.");
		}
	}

	private QuadraticEquationRoots generateDistractorReplacingX_2ByOne(QuadraticEquationRoots correctSolution) {
		return new QuadraticEquationRoots(correctSolution.x_1(), SymbolicNumberFraction.ONE);
	}

	private QuadraticEquationRoots generateDistractorReplacingX_1ByOne(QuadraticEquationRoots correctSolution) {
		return new QuadraticEquationRoots(SymbolicNumberFraction.ONE, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorNegatingOneRoot(QuadraticEquationRoots correctSolution, QuadraticEquationRoot rootToReplace) {
		switch (rootToReplace) {
			case X_1:
				return this.generateDistractorNegatingX_1(correctSolution);
			case X_2:
				return this.generateDistractorNegatingX_2(correctSolution);
			default:
				throw new IllegalArgumentException("Unknown result manipulation type.");
		}
	}

	private QuadraticEquationRoots generateDistractorNegatingX_2(QuadraticEquationRoots correctSolution) {
		final var x_2 = correctSolution.x_2().multiplyBy(-1);
		return new QuadraticEquationRoots(correctSolution.x_1(), x_2);
	}

	private QuadraticEquationRoots generateDistractorNegatingX_1(QuadraticEquationRoots correctSolution) {
		final var x_1 = correctSolution.x_1().multiplyBy(-1);
		return new QuadraticEquationRoots(x_1, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorNegatingBothRoots(QuadraticEquationRoots correctSolution) {
		final var x_1 = correctSolution.x_1().multiplyBy(-1);
		final var x_2 = correctSolution.x_2().multiplyBy(-1);
		return new QuadraticEquationRoots(x_1, x_2);
	}

	private QuadraticEquationRoots generateDistractorReplacingARootByMinusOne(QuadraticEquationRoots correctSolution, QuadraticEquationRoot rootToReplace) {
		switch (rootToReplace) {
			case X_1:
				return this.generateDistractorReplacingX_1ByMinusOne(correctSolution);
			case X_2:
				return this.generateDistractorReplacingX_2ByMinusOne(correctSolution);
			default:
				throw new IllegalArgumentException("Unknown result manipulation type.");
		}
	}

	private QuadraticEquationRoots generateDistractorReplacingX_1ByMinusOne(QuadraticEquationRoots correctSolution) {
		return new QuadraticEquationRoots(SymbolicNumberFraction.MINUS_ONE, correctSolution.x_2());
	}

	private QuadraticEquationRoots generateDistractorReplacingX_2ByMinusOne(QuadraticEquationRoots correctSolution) {
		return new QuadraticEquationRoots(correctSolution.x_1(), SymbolicNumberFraction.MINUS_ONE);
	}
}
