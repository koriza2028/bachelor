package com.distractors.generation.quadraticEquations.basedOnWrongParameters;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractors;
import com.distractors.generation.quadraticEquations.QuadraticEquationLeftSideParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRightSideParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

public class QuadraticEquationDistractorsGenerationBasedOnWrongParametersService {

	final AbcSolutionService abc = new AbcSolutionService();

	public QuadraticEquationDistractors generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var abc = new AbcSolutionService();
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

	private QuadraticEquationRoots generateDifferentDistractor(final QuadraticEquationParameters quadraticEquationParameters, List<QuadraticEquationRoots> distractors) {
		QuadraticEquationRoots distractor;
		do {
			distractor = this.generateDistractor(quadraticEquationParameters);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private QuadraticEquationRoots generateDistractor(QuadraticEquationParameters quadraticEquationParameters) {
		final var randomError = QuadraticEquationParametersChangeType.randomError();
		return generateDistractorWithChosenParametersChangeType(quadraticEquationParameters, randomError);
	}

	public QuadraticEquationRoots generateDistractorWithChosenParametersChangeType(
			QuadraticEquationParameters quadraticEquationParameters,
			final QuadraticEquationParametersChangeType chosenParametersChangeType) {
		switch (chosenParametersChangeType) {
			case IGNORE_ONE_PARAMETER:
				return this.generateDistractorIgnoringOneParameter(quadraticEquationParameters);
			case NEGATE_LEFT_SIDE_PARAMETERS:
				return this.generateDistractorNegatingLeftSideParameters(quadraticEquationParameters);
			case NEGATE_ONE_PARAMETER:
				return this.generateDistractorNegatingOneParameter(quadraticEquationParameters);
			case NEGATE_RIGHT_SIDE_PARAMETERS:
				return this.generateDistractorNegatingRightSideParameters(quadraticEquationParameters);
			case REVERSE_ONE_PARAMETER:
				return this.generateDistractorReversingOneParameter(quadraticEquationParameters);
			default:
				throw new IllegalArgumentException("Unknown parameter change type.");
		}
	}

	private QuadraticEquationRoots generateDistractorReversingOneParameter(QuadraticEquationParameters quadraticEquationParameters) {
		final var randomParameter = QuadraticEquationParametersType.randomParameter(quadraticEquationParameters);
		final var newQuadraticEquationParameters = this.reverseOneParameter(quadraticEquationParameters, randomParameter);
		return solveCorrectly(newQuadraticEquationParameters);
	}

	private QuadraticEquationParameters reverseOneParameter(QuadraticEquationParameters quadraticEquationParameters, QuadraticEquationParametersType randomParameter) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var leftB = leftSide.b();
		final var leftC = leftSide.c();
		final var rightA = rightSide.a();
		final var rightB = rightSide.b();
		final var rightC = rightSide.c();

		switch (randomParameter) {
			case LEFT_A:
				final var leftSideParametersRevertingA = new QuadraticEquationLeftSideParameters(leftA.reverse(), leftB, leftC);
				return new QuadraticEquationParameters(leftSideParametersRevertingA, rightSide);
			case LEFT_B:
				final var leftSideParametersRevertingB = new QuadraticEquationLeftSideParameters(leftA, leftB.reverse(), leftC);
				return new QuadraticEquationParameters(leftSideParametersRevertingB, rightSide);
			case LEFT_C:
				final var leftSideParametersRevertingC = new QuadraticEquationLeftSideParameters(leftA, leftB, leftC.reverse());
				return new QuadraticEquationParameters(leftSideParametersRevertingC, rightSide);
			case RIGHT_A:
				final var rightSideParametersRevertingA = new QuadraticEquationRightSideParameters(rightA.reverse(), rightB, rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersRevertingA);
			case RIGHT_B:
				final var rightSideParametersRevertingB = new QuadraticEquationRightSideParameters(rightA, rightB.reverse(), rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersRevertingB);
			case RIGHT_C:
				final var rightSideParametersRevertingC = new QuadraticEquationRightSideParameters(rightA, rightB, rightC.reverse());
				return new QuadraticEquationParameters(leftSide, rightSideParametersRevertingC);
			default:
				throw new IllegalArgumentException("Unknown parameter type.");
		}
	}

	private QuadraticEquationRoots generateDistractorNegatingRightSideParameters(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = this.negateRightSideParameters(quadraticEquationParameters);
		return solveCorrectly(newQuadraticEquationParameters);
	}

	private QuadraticEquationRoots generateDistractorNegatingOneParameter(QuadraticEquationParameters quadraticEquationParameters) {
		final var randomParameter = QuadraticEquationParametersType.randomParameter(quadraticEquationParameters);
		final var newQuadraticEquationParameters = this.negateOneParameter(quadraticEquationParameters, randomParameter);
		return solveCorrectly(newQuadraticEquationParameters);
	}

	private QuadraticEquationParameters negateOneParameter(QuadraticEquationParameters quadraticEquationParameters, QuadraticEquationParametersType parameterToNegate) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var leftB = leftSide.b();
		final var leftC = leftSide.c();
		final var rightA = rightSide.a();
		final var rightB = rightSide.b();
		final var rightC = rightSide.c();

		switch (parameterToNegate) {
			case LEFT_A:
				final var leftSideParametersNegatingA = new QuadraticEquationLeftSideParameters(leftA.multiplyBy(-1), leftB, leftC);
				return new QuadraticEquationParameters(leftSideParametersNegatingA, rightSide);
			case LEFT_B:
				final var leftSideParametersNegatingB = new QuadraticEquationLeftSideParameters(leftA, leftB.multiplyBy(-1), leftC);
				return new QuadraticEquationParameters(leftSideParametersNegatingB, rightSide);
			case LEFT_C:
				final var leftSideParametersNegatingC = new QuadraticEquationLeftSideParameters(leftA, leftB, leftC.multiplyBy(-1));
				return new QuadraticEquationParameters(leftSideParametersNegatingC, rightSide);
			case RIGHT_A:
				final var rightSideParametersNegatingA = new QuadraticEquationRightSideParameters(rightA.multiplyBy(-1), rightB, rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersNegatingA);
			case RIGHT_B:
				final var rightSideParametersNegatingB = new QuadraticEquationRightSideParameters(rightA, rightB.multiplyBy(-1), rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersNegatingB);
			case RIGHT_C:
				final var rightSideParametersNegatingC = new QuadraticEquationRightSideParameters(rightA, rightB, rightC.multiplyBy(-1));
				return new QuadraticEquationParameters(leftSide, rightSideParametersNegatingC);
			default:
				throw new IllegalArgumentException("Unknown parameter type.");
		}
	}

	private QuadraticEquationRoots solveCorrectly(final QuadraticEquationParameters newQuadraticEquationParameters) {
		final var standardQuadraticEquationParameters = newQuadraticEquationParameters.toStandard();
		
		return abc.solveCorrectly(standardQuadraticEquationParameters);
	}

	private QuadraticEquationRoots generateDistractorNegatingLeftSideParameters(QuadraticEquationParameters quadraticEquationParameters) {
		final var newQuadraticEquationParameters = this.negateLeftSideParameters(quadraticEquationParameters);
		return solveCorrectly(newQuadraticEquationParameters);
	}

	private QuadraticEquationParameters negateLeftSideParameters(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var negatedLeftA = leftSide.a().multiplyBy(-1);
		final var negatedLeftB = leftSide.b().multiplyBy(-1);
		final var negatedLeftC = leftSide.c().multiplyBy(-1);
		final var negatedLeftSide = new QuadraticEquationLeftSideParameters(negatedLeftA, negatedLeftB, negatedLeftC);
		
		return new QuadraticEquationParameters(negatedLeftSide, rightSide);
	}

	private QuadraticEquationParameters negateRightSideParameters(QuadraticEquationParameters quadraticEquationParameters) {
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var negatedRightA = rightSide.a().multiplyBy(-1);
		final var negatedRightB = rightSide.b().multiplyBy(-1);
		final var negatedRightC = rightSide.c().multiplyBy(-1);
		final var negatedRightSide = new QuadraticEquationRightSideParameters(negatedRightA, negatedRightB, negatedRightC);
		
		return new QuadraticEquationParameters(leftSide, negatedRightSide);
	}

	private QuadraticEquationRoots generateDistractorIgnoringOneParameter(QuadraticEquationParameters quadraticEquationParameters) {
		final var randomParameter = QuadraticEquationParametersType.randomParameter(quadraticEquationParameters);
		final var newQuadraticEquationParameters = this.ignoreParameter(quadraticEquationParameters, randomParameter);
		return solveCorrectly(newQuadraticEquationParameters);
	}

	private QuadraticEquationParameters ignoreParameter(QuadraticEquationParameters quadraticEquationParameters, QuadraticEquationParametersType parameterToIgnore) {
		final var parameterEqualsOne = Fraction.ZERO;
		final var leftSide = quadraticEquationParameters.leftSide();
		final var rightSide = quadraticEquationParameters.rightSide();
		final var leftA = leftSide.a();
		final var leftB = leftSide.b();
		final var leftC = leftSide.c();
		final var rightA = rightSide.a();
		final var rightB = rightSide.b();
		final var rightC = rightSide.c();

		switch (parameterToIgnore) {
			case LEFT_A:
				final var leftSideParametersIgnoringA = new QuadraticEquationLeftSideParameters(parameterEqualsOne, leftB, leftC);
				return new QuadraticEquationParameters(leftSideParametersIgnoringA, rightSide);
			case LEFT_B:
				final var leftSideParametersIgnoringB = new QuadraticEquationLeftSideParameters(leftA, parameterEqualsOne, leftC);
				return new QuadraticEquationParameters(leftSideParametersIgnoringB, rightSide);
			case LEFT_C:
				final var leftSideParametersIgnoringC = new QuadraticEquationLeftSideParameters(leftA, leftB, parameterEqualsOne);
				return new QuadraticEquationParameters(leftSideParametersIgnoringC, rightSide);
			case RIGHT_A:
				final var rightSideParametersIgnoringA = new QuadraticEquationRightSideParameters(parameterEqualsOne, rightB, rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersIgnoringA);
			case RIGHT_B:
				final var rightSideParametersIgnoringB = new QuadraticEquationRightSideParameters(rightA, parameterEqualsOne, rightC);
				return new QuadraticEquationParameters(leftSide, rightSideParametersIgnoringB);
			case RIGHT_C:
				final var rightSideParametersIgnoringC = new QuadraticEquationRightSideParameters(rightA, rightB, parameterEqualsOne);
				return new QuadraticEquationParameters(leftSide, rightSideParametersIgnoringC);
			default:
				throw new IllegalArgumentException("Unknown parameter type.");
		}
	}
}
