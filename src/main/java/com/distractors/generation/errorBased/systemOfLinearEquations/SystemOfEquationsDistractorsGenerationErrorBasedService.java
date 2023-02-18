package com.distractors.generation.errorBased.systemOfLinearEquations;

public class SystemOfEquationsDistractorsGenerationErrorBasedService {

	public SystemOfEquationsDistractors generateDistractors(SystemOfEquations systemOfLinearEquations) {
	
		final var systemOfEquationsSolutionThroughYService = new SystemOfEquationsSolutionThroughYService();
		final var correctSolution = systemOfEquationsSolutionThroughYService.solveCorrectly(systemOfLinearEquations);

		SystemOfEquationsSolution distractor_1;
		SystemOfEquationsSolution distractor_2;
		SystemOfEquationsSolution distractor_3;

		do {
			distractor_1 = this.generateDistractor(systemOfLinearEquations);
		} while (distractor_1 == null || distractor_1.equals(correctSolution));

		do {
			distractor_2 = this.generateDistractor(systemOfLinearEquations);
		} while (distractor_2 == null || distractor_2.equals(correctSolution) || distractor_2.equals(distractor_1));

		do {
			distractor_3 = this.generateDistractor(systemOfLinearEquations);
		} while (distractor_3 == null || distractor_3.equals(correctSolution) || distractor_3.equals(distractor_1) || distractor_3.equals(distractor_2));

		return new SystemOfEquationsDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private SystemOfEquationsSolution generateDistractor(SystemOfEquations systemOfLinearEquations) {
		final var randomApproach = SystemOfEquationsSolutionApproach.randomApproach();

		switch (randomApproach) {
			case X:
				final var solutionThroughXService = new SystemOfEquationsSolutionThroughXService();
				return solutionThroughXService.solveIncorrectly(systemOfLinearEquations);
			case Y:
				final var solutionThroughYService = new SystemOfEquationsSolutionThroughYService();
				return solutionThroughYService.solveIncorrectly(systemOfLinearEquations);
			default:
				throw new IllegalArgumentException("Unknown solution approach");
		}
	}
}
