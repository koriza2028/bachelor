package com.distractors.generation.errorBased.quadraticEquations;

import com.distractors.generation.errorBased.quadraticEquations.abc.AbcSolutionService;
import com.distractors.generation.general.SymbolicNumberFraction;

public class QuadraticEquationDistractorsGenerationErrorBasedService {

	private QuadraticEquationSolutionServiceFactory solutionServiceFactory = new QuadraticEquationSolutionServiceFactory();
	private AbcSolutionService abc = new AbcSolutionService();


	public QuadraticEquationDistractors generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var correctSolution = abc.solveCorrectly(standardQuadraticEquationParameters);

		var distractor_1 = correctSolution;
		var distractor_2 = correctSolution;
		var distractor_3 = correctSolution;

		do {
			try {
				distractor_1 = this.generateDistractor(quadraticEquationParameters);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_1 == null || distractor_1.equals(correctSolution));

		do {
			try {
				distractor_2 = this.generateDistractor(quadraticEquationParameters);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_2 == null || distractor_2.equals(correctSolution) || distractor_2.equals(distractor_1));

		do {
			try {
				distractor_3 = this.generateDistractor(quadraticEquationParameters);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_3 == null || distractor_3.equals(correctSolution) || distractor_3.equals(distractor_1) || distractor_3.equals(distractor_2));

		return new QuadraticEquationDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	public QuadraticEquationRoots generateDistractor(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
		final var rightSideA = quadraticEquationParameters.rightSide().a();
		final var rightSideB = quadraticEquationParameters.rightSide().b();

		final var randomApproach = QuadraticEquationSolutionApproach.randomApproach(quadraticEquationParameters);

		switch (randomApproach) {
			case IGNORE_RIGHT_SIDE:
				if (rightSideA.toDouble() != 0 && rightSideB.toDouble() != 0) {
					return this.generateDistractorIgnoringRightSide(quadraticEquationParameters);
				}
			case NO_NEGATIVE_SOLUTION_OF_ROOT:
				return this.generateNoNegativeSolutionDistractor(quadraticEquationParameters);
			case ABC:
				return this.solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(standardQuadraticEquationParameters, QuadraticEquationSolutionApproach.ABC);
			case EXCLUSION:
				return this.solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(standardQuadraticEquationParameters, QuadraticEquationSolutionApproach.EXCLUSION);
			case FACTOR:
				return this.solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(standardQuadraticEquationParameters, QuadraticEquationSolutionApproach.FACTOR);
			case PQ:
				return this.solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(standardQuadraticEquationParameters, QuadraticEquationSolutionApproach.PQ);
			case ROOT_EXTRACTION:
				return this.solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(standardQuadraticEquationParameters, QuadraticEquationSolutionApproach.ROOT_EXTRACTION);
			default:
				throw new IllegalArgumentException("Unknown approach.");
		}
	}

	private QuadraticEquationRoots generateDistractorIgnoringRightSide(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandardIgnoringRightSide();
		return this.solutionServiceFactory.getService(QuadraticEquationSolutionApproach.ABC).solveCorrectly(standardQuadraticEquationParameters);
	}

	private QuadraticEquationRoots generateNoNegativeSolutionDistractor(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();

		final var correctRoots = this.solutionServiceFactory.getService(QuadraticEquationSolutionApproach.ABC).solveCorrectly(standardQuadraticEquationParameters);
		final var x_1 = correctRoots.x_1();
		final SymbolicNumberFraction x_2 = null;

		return new QuadraticEquationRoots(x_1, x_2);
	}

	private QuadraticEquationRoots solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(StandardQuadraticEquationParameters equationParameters, QuadraticEquationSolutionApproach chosenApproach) {
		try {
			return this.solutionServiceFactory.getService(chosenApproach).solveIncorrectly(equationParameters);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + " Try another method.");
		}
		return abc.solveCorrectly(equationParameters);
	}
}
