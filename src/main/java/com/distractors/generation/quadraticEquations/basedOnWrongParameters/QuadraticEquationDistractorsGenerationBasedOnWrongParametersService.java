package com.distractors.generation.quadraticEquations.basedOnWrongParameters;

import java.util.ArrayList;

import com.distractors.generation.quadraticEquations.QuadraticEquationAnswers;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
import com.distractors.generation.quadraticEquations.QuadraticEquationLeftSideParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRightSideParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationSolution;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

public class QuadraticEquationDistractorsGenerationBasedOnWrongParametersService {

	final AbcSolutionService abc = new AbcSolutionService();

	public QuadraticEquationAnswers generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var abc = new AbcSolutionService();
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
		if (this.isDistractorInvalid(distractor, distractors)) {
			return null;
		}
		return distractor;
	}
	
	private QuadraticEquationDistractor generateDistractor(QuadraticEquationParameters quadraticEquationParameters) {
		final var randomError = QuadraticEquationParametersChangeType.randomParametersChangeType(quadraticEquationParameters);
		return generateDistractorWithChosenParametersChangeType(quadraticEquationParameters, randomError);
	}

	private boolean isDistractorInvalid(QuadraticEquationDistractor possibleDistractor, ArrayList<QuadraticEquationSolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	public QuadraticEquationDistractor generateDistractorWithChosenParametersChangeType(QuadraticEquationParameters quadraticEquationParameters, final QuadraticEquationParametersChangeType chosenParametersChangeType) {
		switch (chosenParametersChangeType) {
		case IGNORE_LEFT_A:
			return this.generateDistractorIgnoringLeftA(quadraticEquationParameters);
		case IGNORE_LEFT_B:
			return this.generateDistractorIgnoringLeftB(quadraticEquationParameters);
		case IGNORE_LEFT_C:
			return this.generateDistractorIgnoringLeftC(quadraticEquationParameters);
		case IGNORE_RIGHT_A:
			return this.generateDistractorIgnoringRightA(quadraticEquationParameters);
		case IGNORE_RIGHT_B:
			return this.generateDistractorIgnoringRightB(quadraticEquationParameters);
		case IGNORE_RIGHT_C:
			return this.generateDistractorIgnoringRightC(quadraticEquationParameters);
		case NEGATE_LEFT_A:
			return this.generateDistractorNegatingLeftA(quadraticEquationParameters);
		case NEGATE_LEFT_B:
			return this.generateDistractorNegatingLeftB(quadraticEquationParameters);
		case NEGATE_LEFT_C:
			return this.generateDistractorNegatingLeftC(quadraticEquationParameters);
		case NEGATE_RIGHT_A:
			return this.generateDistractorNegatingRightA(quadraticEquationParameters);
		case NEGATE_RIGHT_B:
			return this.generateDistractorNegatingRightB(quadraticEquationParameters);
		case NEGATE_RIGHT_C:
			return this.generateDistractorNegatingRightC(quadraticEquationParameters);
		case REVERSE_LEFT_A:
			return this.generateDistractorReversingLeftA(quadraticEquationParameters);
		case REVERSE_LEFT_B:
			return this.generateDistractorReversingLeftB(quadraticEquationParameters);
		case REVERSE_LEFT_C:
			return this.generateDistractorReversingLeftC(quadraticEquationParameters);
		case REVERSE_RIGHT_A:
			return this.generateDistractorReversingRightA(quadraticEquationParameters);
		case REVERSE_RIGHT_B:
			return this.generateDistractorReversingRightB(quadraticEquationParameters);
		case REVERSE_RIGHT_C:
			return this.generateDistractorReversingRightC(quadraticEquationParameters);
		case SWAP_A:
			return this.generateDistractorSwitchingA(quadraticEquationParameters);
		case SWAP_B:
			return this.generateDistractorSwitchingB(quadraticEquationParameters);
		case SWAP_C:
			return this.generateDistractorSwitchingC(quadraticEquationParameters);
		case SWAP_LEFT_A_B:
			return this.generateDistractorSwitchingLeftAB(quadraticEquationParameters);
		case SWAP_LEFT_A_C:
			return this.generateDistractorSwitchingLeftAC(quadraticEquationParameters);
		case SWAP_LEFT_B_C:
			return this.generateDistractorSwitchingLeftBC(quadraticEquationParameters);
		case SWAP_RIGHT_A_B:
			return this.generateDistractorSwitchingRightAB(quadraticEquationParameters);
		case SWAP_RIGHT_A_C:
			return this.generateDistractorSwitchingRightAC(quadraticEquationParameters);
		case SWAP_RIGHT_B_C:
			return this.generateDistractorSwitchingRightBC(quadraticEquationParameters);
		case NEGATE_LEFT_SIDE_PARAMETERS:
			return this.generateDistractorNegatingLeftSideParameters(quadraticEquationParameters);
		case NEGATE_RIGHT_SIDE_PARAMETERS:
			return this.generateDistractorNegatingRightSideParameters(quadraticEquationParameters);
		default:
			throw new IllegalArgumentException("Unknown parameter change type.");
		}
	}

	private QuadraticEquationDistractor generateDistractorSwitchingRightBC(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var rightB = rightSide.b();
		final var rightC = rightSide.c();
		final var newRightSide = new QuadraticEquationRightSideParameters(rightSide.a(), rightC, rightB);
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(leftSide, newRightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_A, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorSwitchingRightAC(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var rightA = rightSide.a();
		final var rightC = rightSide.c();
		final var newRightSide = new QuadraticEquationRightSideParameters(rightC, rightSide.b(), rightA);
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(leftSide, newRightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_RIGHT_A_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorSwitchingRightAB(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var rightA = rightSide.a();
		final var rightB = rightSide.b();
		final var newRightSide = new QuadraticEquationRightSideParameters(rightB, rightA, rightSide.c());
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(leftSide, newRightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_RIGHT_A_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorSwitchingLeftBC(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftB = leftSide.b();
		final var leftC = leftSide.c();
		final var newLeftSide = new QuadraticEquationLeftSideParameters(leftSide.a(), leftC, leftB);
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(newLeftSide, rightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_LEFT_B_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorSwitchingLeftAC(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var leftC = leftSide.c();
		final var newLeftSide = new QuadraticEquationLeftSideParameters(leftC, leftSide.b(), leftA);
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(newLeftSide, rightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_LEFT_A_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorSwitchingLeftAB(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var leftB = leftSide.b();
		final var newLeftSide = new QuadraticEquationLeftSideParameters(leftB, leftA, leftSide.c());
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(newLeftSide, rightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_LEFT_A_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorSwitchingC(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftC = leftSide.c();
		final var rightC = rightSide.c();
		final var newLeftSide = new QuadraticEquationLeftSideParameters(leftSide.a(), leftSide.b(), rightC);
		final var newRightSide = new QuadraticEquationRightSideParameters(rightSide.a(), rightSide.b(), leftC);
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(newLeftSide, newRightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorSwitchingB(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftB = leftSide.b();
		final var rightB = rightSide.b();
		final var newLeftSide = new QuadraticEquationLeftSideParameters(leftSide.a(), rightB, leftSide.c());
		final var newRightSide = new QuadraticEquationRightSideParameters(rightSide.a(), leftB, rightSide.c());
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(newLeftSide, newRightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorSwitchingA(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var rightA = rightSide.a();
		final var newLeftSide = new QuadraticEquationLeftSideParameters(rightA, leftSide.b(), leftSide.c());
		final var newRightSide = new QuadraticEquationRightSideParameters(leftA, rightSide.b(), rightSide.c());
		final var newQuadraticEquationParameters = new QuadraticEquationParameters(newLeftSide, newRightSide);
		return solveCorrectly(QuadraticEquationParametersChangeType.SWAP_A, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorReversingLeftA(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersReversingService.reverseOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_A);
		return solveCorrectly(QuadraticEquationParametersChangeType.REVERSE_LEFT_A, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorReversingLeftB(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersReversingService.reverseOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_B);
		return solveCorrectly(QuadraticEquationParametersChangeType.REVERSE_LEFT_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorReversingLeftC(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersReversingService.reverseOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_C);
		return solveCorrectly(QuadraticEquationParametersChangeType.REVERSE_LEFT_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorReversingRightA(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersReversingService.reverseOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_A);
		return solveCorrectly(QuadraticEquationParametersChangeType.REVERSE_RIGHT_A, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorReversingRightB(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersReversingService.reverseOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_B);
		return solveCorrectly(QuadraticEquationParametersChangeType.REVERSE_RIGHT_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorReversingRightC(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersReversingService.reverseOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_C);
		return solveCorrectly(QuadraticEquationParametersChangeType.REVERSE_RIGHT_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorNegatingRightSideParameters(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersNegatingService.negateRightSideParameters(quadraticEquationParameters);
		return solveCorrectly(QuadraticEquationParametersChangeType.NEGATE_RIGHT_SIDE_PARAMETERS, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorNegatingLeftA(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersNegatingService.negateOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_A);
		return solveCorrectly(QuadraticEquationParametersChangeType.NEGATE_LEFT_A, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorNegatingLeftB(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersNegatingService.negateOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_B);
		return solveCorrectly(QuadraticEquationParametersChangeType.NEGATE_LEFT_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorNegatingLeftC(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersNegatingService.negateOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_C);
		return solveCorrectly(QuadraticEquationParametersChangeType.NEGATE_LEFT_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorNegatingRightA(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersNegatingService.negateOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_A);
		return solveCorrectly(QuadraticEquationParametersChangeType.NEGATE_RIGHT_A, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorNegatingRightB(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersNegatingService.negateOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_B);
		return solveCorrectly(QuadraticEquationParametersChangeType.NEGATE_RIGHT_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorNegatingRightC(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersNegatingService.negateOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_C);
		return solveCorrectly(QuadraticEquationParametersChangeType.NEGATE_RIGHT_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorNegatingLeftSideParameters(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersNegatingService.negateLeftSideParameters(quadraticEquationParameters);
		return solveCorrectly(QuadraticEquationParametersChangeType.NEGATE_LEFT_SIDE_PARAMETERS, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorIgnoringLeftA(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersIgnoringService.ignoreOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_A);
		return solveCorrectly(QuadraticEquationParametersChangeType.IGNORE_LEFT_A, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorIgnoringLeftB(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersIgnoringService.ignoreOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_B);
		return solveCorrectly(QuadraticEquationParametersChangeType.IGNORE_LEFT_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorIgnoringLeftC(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersIgnoringService.ignoreOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.LEFT_C);
		return solveCorrectly(QuadraticEquationParametersChangeType.IGNORE_LEFT_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorIgnoringRightA(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersIgnoringService.ignoreOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_A);
		return solveCorrectly(QuadraticEquationParametersChangeType.IGNORE_RIGHT_A, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorIgnoringRightB(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersIgnoringService.ignoreOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_B);
		return solveCorrectly(QuadraticEquationParametersChangeType.IGNORE_RIGHT_B, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor generateDistractorIgnoringRightC(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = ParametersIgnoringService.ignoreOneParameter(quadraticEquationParameters, QuadraticEquationParametersType.RIGHT_C);
		return solveCorrectly(QuadraticEquationParametersChangeType.IGNORE_RIGHT_C, newQuadraticEquationParameters);
	}

	private QuadraticEquationDistractor solveCorrectly(final QuadraticEquationParametersChangeType parametersChangeType, final QuadraticEquationParameters newQuadraticEquationParameters) {
		final var standardQuadraticEquationParameters = newQuadraticEquationParameters.toStandard();
		
		final var solution = abc.solveCorrectly(standardQuadraticEquationParameters);
		return new QuadraticEquationDistractor(solution.x_1(), solution.x_2(), parametersChangeType);
	}
}
