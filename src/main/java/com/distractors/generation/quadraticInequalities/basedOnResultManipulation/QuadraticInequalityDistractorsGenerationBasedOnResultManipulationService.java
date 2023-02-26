package com.distractors.generation.quadraticInequalities.basedOnResultManipulation;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;
import com.distractors.generation.quadraticInequalities.InequalitySign;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityDistractors;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityNonNumericalSolution;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityParameters;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityRange;
import com.distractors.generation.quadraticInequalities.QuadraticInequalitySolution;
import com.distractors.generation.quadraticInequalities.QuadraticInequalitySolutionMapper;

public class QuadraticInequalityDistractorsGenerationBasedOnResultManipulationService {
	private AbcSolutionService abc = new AbcSolutionService();
	private QuadraticInequalitySolutionMapper solutionMapper = new QuadraticInequalitySolutionMapper();

	public QuadraticInequalityDistractors generateDistractors(QuadraticInequalityParameters quadraticInequalityParameters) {
		final var standardQuadraticInequalityParameters = quadraticInequalityParameters.toStandard();
		final var standardQuadraticEquationParameters = standardQuadraticInequalityParameters.equationParameters();
		
		final var quadraticEquationCorrectSolution = this.abc.solveCorrectly(standardQuadraticEquationParameters);
		final var correctSolution = this.solutionMapper.findQuadraticInequalitySolution(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters);

		var distractors = new ArrayList<QuadraticInequalitySolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(correctSolution, distractors);

		return new QuadraticInequalityDistractors(correctSolution, distractor_1, distractor_2, distractor_3);	
	}

	private boolean isDistractorInvalid(QuadraticInequalitySolution possibleDistractor, List<QuadraticInequalitySolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticInequalitySolution generateDifferentDistractor(final QuadraticInequalitySolution correctSolution, List<QuadraticInequalitySolution> distractors) {
		QuadraticInequalitySolution distractor;
		do {
			distractor = this.generateDistractor(correctSolution);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private QuadraticInequalitySolution generateDistractor(QuadraticInequalitySolution correctSolution) {
		final var randomManipulationType = QuadraticInequalityResultManipulationType.randomError();

		return generateDistractorWithChosenManipulation(correctSolution, randomManipulationType);
	}

	public QuadraticInequalitySolution generateDistractorWithChosenManipulation(QuadraticInequalitySolution correctSolution, final QuadraticInequalityResultManipulationType randomManipulationType) {
		final var x_1 = correctSolution.range_1().x();
		final var x_2 = correctSolution.range_2().x();
		final var sign_1 = correctSolution.range_1().sign();
		final var sign_2 = correctSolution.range_2().sign();
		final var nonNumericalSolution = correctSolution.nonNumericalSolution();

		switch (randomManipulationType) {
			case R_WITHOUT_X_1:
				return QuadraticInequalitySolution.createRExceptZeroDistractor(x_1);
			case R_WITHOUT_X_2:
				return QuadraticInequalitySolution.createRExceptZeroDistractor(x_2);
			case MAX_TO_MIN:
				return generateSwitchedRootsResult(x_1, x_2, sign_1, sign_2, nonNumericalSolution);
			case X_1:
				return QuadraticInequalitySolution.createOnlyZeroDistractor(x_1);
			case X_2:
				return QuadraticInequalitySolution.createOnlyZeroDistractor(x_2);
			case EMPTY_SET:
				return QuadraticInequalitySolution.createEmptySetDistractor();
			case FORGET_TO_INCLUDE_OR_EXCLUDE_ZEROES:
				return generateForgetingInclusionOrExclusionResult(x_1, x_2, sign_1, sign_2, nonNumericalSolution);
			case R:
				return QuadraticInequalitySolution.createRDistractor();
			case SWITCH_INEQUALITY_SIGNS:
				return generateSwitchedInequalitySignsResult(x_1, x_2, sign_1, sign_2, nonNumericalSolution);
			default:
				return correctSolution;
		}
	}

	private QuadraticInequalitySolution generateSwitchedInequalitySignsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, final InequalitySign sign_1, final InequalitySign sign_2,
			final QuadraticInequalityNonNumericalSolution nonNumericalSolution) {
		final var range_1 = new QuadraticInequalityRange(x_1, sign_2);
		final var range_2 = new QuadraticInequalityRange(x_2, sign_1);
		return QuadraticInequalitySolution.createDistractor(nonNumericalSolution, range_1, range_2);
	}

	private QuadraticInequalitySolution generateForgetingInclusionOrExclusionResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2,
			final InequalitySign sign_1, final InequalitySign sign_2,
			final QuadraticInequalityNonNumericalSolution nonNumericalSolution) {
		final var newSign_1 = this.changeSign(sign_1);
		final var newSign_2 = this.changeSign(sign_2);
		final var range_1 = new QuadraticInequalityRange(x_1, newSign_1);
		final var range_2 = new QuadraticInequalityRange(x_2, newSign_2);
		return QuadraticInequalitySolution.createDistractor(nonNumericalSolution, range_1, range_2);
	}

	private QuadraticInequalitySolution generateSwitchedRootsResult(final SymbolicNumberFraction x_1,
			final SymbolicNumberFraction x_2, final InequalitySign sign_1, final InequalitySign sign_2,
			final QuadraticInequalityNonNumericalSolution nonNumericalSolution) {
		final var range_1 = new QuadraticInequalityRange(x_2.multiplyBy(-1), sign_1);
		final var range_2 = new QuadraticInequalityRange(x_1.multiplyBy(-1), sign_2);
		return QuadraticInequalitySolution.createDistractor(nonNumericalSolution, range_1, range_2);
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
