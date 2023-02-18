package com.distractors.generation.basedOnResultManipulation.systemOfEquations;

import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquations;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquationsDistractors;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquationsSolution;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquationsSolutionThroughYService;

public class SystemOfEquationsDistractorsGenerationBasedOnResultManipulationService {

	public SystemOfEquationsDistractors generateDistractors(SystemOfEquations systemOfLinearEquations) {
		
		final var systemOfEquationsSolutionThroughYService = new SystemOfEquationsSolutionThroughYService();
		final var correctSolution = systemOfEquationsSolutionThroughYService.solveCorrectly(systemOfLinearEquations);

		SystemOfEquationsSolution distractor_1;
		SystemOfEquationsSolution distractor_2;
		SystemOfEquationsSolution distractor_3;

		do {
			distractor_1 = this.generateDistractor(correctSolution);
		} while (distractor_1 == null || distractor_1.equals(correctSolution));

		do {
			distractor_2 = this.generateDistractor(correctSolution);
		} while (distractor_2 == null || distractor_2.equals(correctSolution) || distractor_2.equals(distractor_1));

		do {
			distractor_3 = this.generateDistractor(correctSolution);
		} while (distractor_3 == null || distractor_3.equals(correctSolution) || distractor_3.equals(distractor_1) || distractor_3.equals(distractor_2));

		return new SystemOfEquationsDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private SystemOfEquationsSolution generateDistractor(SystemOfEquationsSolution solution) {
		final var randomManipulation = SystemOfEquationsResultManipulationType.randomManipulation();

		switch (randomManipulation) {
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

	private SystemOfEquationsSolution generateDistractorSwitchingXY(SystemOfEquationsSolution solution) {
		final var x = solution.y();
		final var y = solution.x();

		return new SystemOfEquationsSolution(x, y);
	}

	private SystemOfEquationsSolution generateDistractorNegatingY(SystemOfEquationsSolution solution) {
		final var y = solution.y().multiplyBy(-1);

		return new SystemOfEquationsSolution(solution.x(), y);
	}

	private SystemOfEquationsSolution generateDistractorNegatingX(SystemOfEquationsSolution solution) {
		final var x = solution.x().multiplyBy(-1);

		return new SystemOfEquationsSolution(x, solution.y());
	}

	private SystemOfEquationsSolution generateDistractorNegatingBoth(SystemOfEquationsSolution solution) {
		final var x = solution.x().multiplyBy(-1);
		final var y = solution.y().multiplyBy(-1);

		return new SystemOfEquationsSolution(x, y);
	}
}
