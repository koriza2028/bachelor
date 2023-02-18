package com.distractors.generation.basedOnResultManipulation.quadraticInequalities;

import com.distractors.generation.errorBased.quadraticEquations.abc.AbcSolutionService;
import com.distractors.generation.errorBased.quadraticInequalities.InequalitySign;
import com.distractors.generation.errorBased.quadraticInequalities.QuadraticInequalityDistractors;
import com.distractors.generation.errorBased.quadraticInequalities.QuadraticInequalityNonNumericalSolution;
import com.distractors.generation.errorBased.quadraticInequalities.QuadraticInequalityParameters;
import com.distractors.generation.errorBased.quadraticInequalities.QuadraticInequalitySolution;
import com.distractors.generation.errorBased.quadraticInequalities.QuadraticInequalitySolutionMapper;

public class QuadraticInequalityDistractorsGenerationBasedOnResultManipulationService {
	private AbcSolutionService abc = new AbcSolutionService();
	private QuadraticInequalitySolutionMapper solutionMapper = new QuadraticInequalitySolutionMapper();

	public QuadraticInequalityDistractors generateDistractors(QuadraticInequalityParameters quadraticInequalityParameters) {
		final var standardQuadraticInequalityParameters = quadraticInequalityParameters.toStandard();
		final var standardQuadraticEquationParameters = standardQuadraticInequalityParameters.equationParameters();
		
		final var quadraticEquationCorrectSolution = this.abc.solveCorrectly(standardQuadraticEquationParameters);
		final var correctSolution = this.solutionMapper.findQuadraticInequalitySolution(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters);

		var distractor_1 = correctSolution;
		var distractor_2  = correctSolution;
		var distractor_3 = correctSolution;

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

		return new QuadraticInequalityDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
		
	}

	private QuadraticInequalitySolution generateDistractor(QuadraticInequalitySolution correctSolution) {
		final var randomManipulationType = QuadraticInequalityResultManipulationType.randomError();

		return generateDistractorWithChosenManipulation(correctSolution, randomManipulationType);
	}

	private QuadraticInequalitySolution generateDistractorWithChosenManipulation(QuadraticInequalitySolution correctSolution, final QuadraticInequalityResultManipulationType randomManipulationType) {
		final var x_1 = correctSolution.x_1();
		final var x_2 = correctSolution.x_2();
		final var sign_1 = correctSolution.sign_1();
		final var sign_2 = correctSolution.sign_2();
		final var nonNumericalSolution = correctSolution.nonNumericalSolution();

		switch (randomManipulationType) {
			case R_WITHOUT_X_1:
				return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, x_2, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
			case R_WITHOUT_X_2:
				return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, x_1, x_1, InequalitySign.GREATER, InequalitySign.GREATER);
			case MAX_TO_MIN:
				return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, x_2.multiplyBy(-1), x_1.multiplyBy(-1), InequalitySign.GREATER, InequalitySign.GREATER);
			case X_1:
				return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.ONLY_ZERO, x_1, x_1, InequalitySign.GREATER, InequalitySign.GREATER);
			case X_2:
				return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.ONLY_ZERO, x_2, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
			case EMPTY_SET:
				return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
			case FORGET_TO_INCLUDE_OR_EXCLUDE_ZEROES:
				final var newSign_1 = this.changeSign(sign_1);
				final var newSign_2 = this.changeSign(sign_2);
				return new QuadraticInequalitySolution(nonNumericalSolution, x_1, x_2, newSign_1, newSign_2);
			case R:
				return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, x_1, x_2, InequalitySign.GREATER, InequalitySign.GREATER);
			case SWITCH_INEQUALITY_SIGNS:
				return new QuadraticInequalitySolution(nonNumericalSolution, x_1, x_2, sign_2, sign_1);
			default:
				return correctSolution;
		}
	}

	private InequalitySign changeSign(InequalitySign sign_1) {
		switch (sign_1) {
			case GREATER:
				return InequalitySign.GREATER_OR_EQUALS;
			case GREATER_OR_EQUALS:
				return InequalitySign.GREATER;
			case LESS:
				return InequalitySign.LESS_OR_EQUALS;
			case LESS_OR_EQUALS:
				return InequalitySign.LESS;
			default:
				return sign_1;
		}
	}
}
