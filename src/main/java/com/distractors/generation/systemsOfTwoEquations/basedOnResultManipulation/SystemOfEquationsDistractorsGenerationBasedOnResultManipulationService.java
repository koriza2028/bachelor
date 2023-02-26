package com.distractors.generation.systemsOfTwoEquations.basedOnResultManipulation;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractors;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;
import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsSolutionThroughYService;

public class SystemOfEquationsDistractorsGenerationBasedOnResultManipulationService {

	public SystemOfTwoEquationsDistractors generateDistractors(SystemOfTwoEquations systemOfLinearEquations) {
		
		final var systemOfEquationsSolutionThroughYService = new SystemOfEquationsSolutionThroughYService();
		final var correctSolution = systemOfEquationsSolutionThroughYService.solveCorrectly(systemOfLinearEquations);
		var distractors = new ArrayList<SystemOfTwoEquationsSolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(correctSolution, distractors);

		return new SystemOfTwoEquationsDistractors(correctSolution, distractor_1, distractor_2, distractor_3);	
	}

	private boolean isDistractorInvalid(SystemOfTwoEquationsSolution possibleDistractor, List<SystemOfTwoEquationsSolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private SystemOfTwoEquationsSolution generateDifferentDistractor(final SystemOfTwoEquationsSolution correctSolution, List<SystemOfTwoEquationsSolution> distractors) {
		SystemOfTwoEquationsSolution distractor;
		do {
			distractor = this.generateDistractor(correctSolution);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private SystemOfTwoEquationsSolution generateDistractor(SystemOfTwoEquationsSolution solution) {
		final var randomManipulation = SystemOfEquationsResultManipulationType.randomManipulation();

		return generateDistractorWithChosenManipulationType(solution, randomManipulation);
	}

	private SystemOfTwoEquationsSolution generateDistractorWithChosenManipulationType(SystemOfTwoEquationsSolution solution,
			final SystemOfEquationsResultManipulationType chosenManipulation) {
		switch (chosenManipulation) {
			case NEGATE_BOTH:
				return this.generateDistractorNegatingBoth(solution);
			case NEGATE_X:
				return this.generateDistractorNegatingX(solution);
			case NEGATE_Y:
				return this.generateDistractorNegatingY(solution);
			case SWITCH_X_Y:
				return this.generateDistractorSwitchingXY(solution);
			default:
				throw new IllegalArgumentException("Unknown solution approach");
		}
	}

	private SystemOfTwoEquationsSolution generateDistractorSwitchingXY(SystemOfTwoEquationsSolution solution) {
		final var x = solution.y();
		final var y = solution.x();

		return new SystemOfTwoEquationsSolution(x, y);
	}

	private SystemOfTwoEquationsSolution generateDistractorNegatingY(SystemOfTwoEquationsSolution solution) {
		final var y = solution.y().multiplyBy(-1);

		return new SystemOfTwoEquationsSolution(solution.x(), y);
	}

	private SystemOfTwoEquationsSolution generateDistractorNegatingX(SystemOfTwoEquationsSolution solution) {
		final var x = solution.x().multiplyBy(-1);

		return new SystemOfTwoEquationsSolution(x, solution.y());
	}

	private SystemOfTwoEquationsSolution generateDistractorNegatingBoth(SystemOfTwoEquationsSolution solution) {
		final var x = solution.x().multiplyBy(-1);
		final var y = solution.y().multiplyBy(-1);

		return new SystemOfTwoEquationsSolution(x, y);
	}
}
