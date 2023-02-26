package com.distractors.generation.systemsOfTwoEquations.random;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.general.services.RandomGenerator;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquations;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsDistractors;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;
import com.distractors.generation.systemsOfTwoEquations.errorBased.SystemOfEquationsSolutionThroughYService;

public class SystemOfTwoEquationsRandomDistractorsGenerationService {
	private RandomGenerator random = new RandomGenerator();

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
		final var x = random.generateRandomFraction(solution);
		final var y = random.generateRandomFraction(solution);
		return new SystemOfTwoEquationsSolution(x, y);
	}

}
