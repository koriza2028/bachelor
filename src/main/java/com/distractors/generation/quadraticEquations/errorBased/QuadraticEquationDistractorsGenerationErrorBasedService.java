package com.distractors.generation.quadraticEquations.errorBased;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractors;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

public class QuadraticEquationDistractorsGenerationErrorBasedService {

	private QuadraticEquationSolutionServiceFactory solutionServiceFactory = new QuadraticEquationSolutionServiceFactory();
	private AbcSolutionService abc = new AbcSolutionService();

	public QuadraticEquationDistractors generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var correctSolution = abc.solveCorrectly(standardQuadraticEquationParameters);

		var distractors = new ArrayList<QuadraticEquationRoots> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(quadraticEquationParameters, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(quadraticEquationParameters, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(quadraticEquationParameters, distractors);

		return new QuadraticEquationDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private boolean isDistractorInvalid(QuadraticEquationRoots possibleDistractor, List<QuadraticEquationRoots> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticEquationRoots generateDifferentDistractor(final QuadraticEquationParameters quadraticEquationParameters, ArrayList<QuadraticEquationRoots> distractors) {
		QuadraticEquationRoots distractor;
		do {
			distractor = this.generateDistractor(quadraticEquationParameters);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	public QuadraticEquationRoots generateDistractor(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
		final var rightSideA = quadraticEquationParameters.rightSide().a();
		final var rightSideB = quadraticEquationParameters.rightSide().b();

		final var randomErrorType = QuadraticEquationErrorType.randomErrorType(quadraticEquationParameters);

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
			if (rightSideA.toDouble() != 0 && rightSideB.toDouble() != 0) {
				return this.generateDistractorIgnoringRightSide(quadraticEquationParameters);
			}
		case NO_NEGATIVE_SOLUTION_OF_ROOT:
			return this.generateNoNegativeSolutionDistractor(quadraticEquationParameters);
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

		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	private QuadraticEquationRoots solveStandardQuadraticEquationIncorrectlyUsingChosenApproach(StandardQuadraticEquationParameters equationParameters, QuadraticEquationErrorType errorType) {
		try {
			return this.solutionServiceFactory.getService(errorType.approach).solveWithChosenError(equationParameters, errorType);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage() + " Try another method.");
		}
		return abc.solveCorrectly(equationParameters);
	}
}
