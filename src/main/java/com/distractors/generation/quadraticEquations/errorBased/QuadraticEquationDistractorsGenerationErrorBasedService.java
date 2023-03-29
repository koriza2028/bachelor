package com.distractors.generation.quadraticEquations.errorBased;

import java.util.ArrayList;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationAnswers;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationSolution;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

public class QuadraticEquationDistractorsGenerationErrorBasedService {

	private QuadraticEquationSolutionServiceFactory solutionServiceFactory = new QuadraticEquationSolutionServiceFactory();
	private AbcSolutionService abc = new AbcSolutionService();

	public QuadraticEquationAnswers generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var correctSolution = abc.solveCorrectly(standardQuadraticEquationParameters);

		var distractors = new ArrayList<QuadraticEquationSolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(quadraticEquationParameters, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(quadraticEquationParameters, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(quadraticEquationParameters, distractors);

		return new QuadraticEquationAnswers(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private QuadraticEquationDistractor generateDifferentDistractor(final QuadraticEquationParameters quadraticEquationParameters, ArrayList<QuadraticEquationSolution> distractors) {
		QuadraticEquationDistractor distractor;
		do {
			distractor = this.generateDistractor(quadraticEquationParameters);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}
	
	public QuadraticEquationDistractor generateDistractor(QuadraticEquationParameters quadraticEquationParameters) {
		final var randomErrorType = QuadraticEquationErrorType.randomErrorType(quadraticEquationParameters);
		
		return generateDistractorWithChosenErrorType(quadraticEquationParameters, randomErrorType);
	}

	private boolean isDistractorInvalid(QuadraticEquationDistractor possibleDistractor, ArrayList<QuadraticEquationSolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticEquationDistractor generateDistractorWithChosenErrorType(QuadraticEquationParameters quadraticEquationParameters,
			final QuadraticEquationErrorType randomErrorType) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();

		switch (randomErrorType) {
			case DIVIDE_DISCRIMINANT_BY_TWO:
			case EXTRACT_ROOT_ADDITIVELY_ABC:
			case EXTRACT_ROOT_ADDITIVELY_PQ:
			case FACTORING_A_SUM_OF_SQUARE:
			case IGNORE_C_NOT_ZERO:
			case IGNORE_NORMAL_FORM:
			case MOVE_PLUS_MINUS:
			case NO_MINUS_BEFORE_B:
			case SOLVE_ADDITIVELY_INSTEAD_OF_MULTIPLICATIVELY:
			case USE_B_QUADRAT:
			case WRONG_SIMPLE_EQUATION_SOLUTION:
			case DIVIDE_BY_C:
				return this.solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(standardQuadraticEquationParameters, randomErrorType);
			case IGNORE_RIGHT_SIDE:
				return this.generateDistractorIgnoringRightSide(quadraticEquationParameters);
			case NO_NEGATIVE_SOLUTION_OF_ROOT:
				return this.generateNoNegativeSolutionDistractor(quadraticEquationParameters);
			default:
				throw new IllegalArgumentException("Unknown approach.");
		}
	}

	private QuadraticEquationDistractor generateDistractorIgnoringRightSide(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandardIgnoringRightSide();
		final var solution = abc.solveCorrectly(standardQuadraticEquationParameters);
		return new QuadraticEquationDistractor(solution.x_1(), solution.x_2(), QuadraticEquationErrorType.IGNORE_RIGHT_SIDE);
	}

	private QuadraticEquationDistractor generateNoNegativeSolutionDistractor(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();

		final var correctRoots = abc.solveCorrectly(standardQuadraticEquationParameters);
		final var x_1 = correctRoots.x_1();
		final SymbolicNumberFraction x_2 = null;

		return new QuadraticEquationDistractor(x_1, x_2, QuadraticEquationErrorType.NO_NEGATIVE_SOLUTION_OF_ROOT);
	}

	private QuadraticEquationDistractor solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(StandardQuadraticEquationParameters equationParameters, QuadraticEquationErrorType errorType) {
		try {
			return this.solutionServiceFactory.getService(errorType.approach).solveWithChosenError(equationParameters, errorType);
		} catch (IllegalArgumentException e) {
			final var correctSolution = abc.solveCorrectly(equationParameters);
			// return the correct solution as distractor so that the program continues searching for distractors
			return new QuadraticEquationDistractor(correctSolution.x_1(), correctSolution.x_2(), QuadraticEquationErrorType.NO_NEGATIVE_SOLUTION_OF_ROOT);
		}
	}
}
