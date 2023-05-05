package com.distractors.generation.systemsOfTwoEquations.basedOnResultManipulation;

import java.util.ArrayList;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsParameters;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsAnswers;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsCorrectSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractor;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsNonNumericalSolution;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;
import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsAdditiveSolutionThroughYService;

public class SystemOfEquationsDistractorsGenerationBasedOnResultManipulationService {

	public SystemOfTwoEquationsAnswers generateDistractors(SystemOfTwoEquationsParameters systemOfLinearEquations) {
		
		final var systemOfEquationsDistractorThroughYService = new SystemOfEquationsAdditiveSolutionThroughYService();
		final var correctSolution = systemOfEquationsDistractorThroughYService.solveCorrectly(systemOfLinearEquations);
		var distractors = new ArrayList<SystemOfTwoEquationsSolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(correctSolution, distractors);

		return new SystemOfTwoEquationsAnswers(correctSolution, distractor_1, distractor_2, distractor_3);	
	}
	
	private SystemOfTwoEquationsDistractor generateDifferentDistractor(final SystemOfTwoEquationsCorrectSolution correctSolution, ArrayList<SystemOfTwoEquationsSolution> distractors) {
		SystemOfTwoEquationsDistractor distractor;
		do {
			distractor = this.generateDistractor(correctSolution);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}
	
	private SystemOfTwoEquationsDistractor generateDistractor(SystemOfTwoEquationsCorrectSolution correctSolution) {
		final var randomManipulation = SystemOfEquationsResultManipulationType.randomManipulation(correctSolution);
		
		return generateDistractorWithChosenManipulationType(correctSolution, randomManipulation);
	}

	private boolean isDistractorInvalid(SystemOfTwoEquationsDistractor possibleDistractor, ArrayList<SystemOfTwoEquationsSolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private SystemOfTwoEquationsDistractor generateDistractorWithChosenManipulationType(SystemOfTwoEquationsCorrectSolution correctSolution, final SystemOfEquationsResultManipulationType chosenManipulation) {
		switch (chosenManipulation) {
		case EMPTY_SET:
			return generateEmptySetDistractor();
		case MINUS_ONE_X_ONE_Y:
			return generateMinusOneXOneYDistractor();
		case MINUS_ONE_Y_ONE_X:
			return this.generateMinusOneYOneXDistractor();
		case R:
			return this.generateRDistractor();
		case NEGATE_BOTH:
			return this.generateDistractorNegatingBoth(correctSolution);
		case NEGATE_X:
			return this.generateDistractorNegatingX(correctSolution);
		case NEGATE_Y:
			return this.generateDistractorNegatingY(correctSolution);
		case SWITCH_X_Y:
			return this.generateDistractorSwitchingXY(correctSolution);
		default:
			throw new IllegalArgumentException("Unknown solution approach");
		}
	}

	private SystemOfTwoEquationsDistractor generateRDistractor() {
		return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.R, null, null, SystemOfEquationsResultManipulationType.R);
	}

	private SystemOfTwoEquationsDistractor generateMinusOneYOneXDistractor() {
		final var y = Fraction.ONE.multiplyBy(-1);
		return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, Fraction.ONE, y, SystemOfEquationsResultManipulationType.MINUS_ONE_Y_ONE_X);
	}

	private SystemOfTwoEquationsDistractor generateMinusOneXOneYDistractor() {
		final var x = Fraction.ONE.multiplyBy(-1);
		return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, Fraction.ONE, SystemOfEquationsResultManipulationType.MINUS_ONE_X_ONE_Y);
	}

	private SystemOfTwoEquationsDistractor generateEmptySetDistractor() {
		return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.EMPTY_SET, null, null, SystemOfEquationsResultManipulationType.EMPTY_SET);
	}

	private SystemOfTwoEquationsDistractor generateDistractorSwitchingXY(SystemOfTwoEquationsCorrectSolution solution) {
		final var x = solution.y();
		final var y = solution.x();

		return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsResultManipulationType.SWITCH_X_Y);
	}

	private SystemOfTwoEquationsDistractor generateDistractorNegatingY(SystemOfTwoEquationsCorrectSolution solution) {
		final var y = solution.y().multiplyBy(-1);

		return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, solution.x(), y, SystemOfEquationsResultManipulationType.NEGATE_Y);
	}

	private SystemOfTwoEquationsDistractor generateDistractorNegatingX(SystemOfTwoEquationsCorrectSolution solution) {
		final var x = solution.x().multiplyBy(-1);

		return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, solution.y(), SystemOfEquationsResultManipulationType.NEGATE_X);
	}

	private SystemOfTwoEquationsDistractor generateDistractorNegatingBoth(SystemOfTwoEquationsCorrectSolution correctSolution) {
		final var x = correctSolution.x().multiplyBy(-1);
		final var y = correctSolution.y().multiplyBy(-1);

		return new SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution.NORMAL, x, y, SystemOfEquationsResultManipulationType.NEGATE_BOTH);
	}
}
