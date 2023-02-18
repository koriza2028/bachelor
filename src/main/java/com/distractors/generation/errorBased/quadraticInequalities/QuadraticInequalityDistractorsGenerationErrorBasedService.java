package com.distractors.generation.errorBased.quadraticInequalities;

import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationDistractorsGenerationErrorBasedService;

public class QuadraticInequalityDistractorsGenerationErrorBasedService {
	private QuadraticEquationDistractorsGenerationErrorBasedService quadraticEquationsDistractorsGenerationService = new QuadraticEquationDistractorsGenerationErrorBasedService();
	private QuadraticInequalitySolutionMapper solutionMapper = new QuadraticInequalitySolutionMapper();

	public QuadraticInequalityDistractors generateDistractors(QuadraticInequalityParameters quadraticInequalityParameters) {
		final var quadraticEquationParameters = quadraticInequalityParameters.equationParameters();
		final var standardQuadraticInequalityParameters = quadraticInequalityParameters.toStandard();
		
		final var quadraticEquationCorrectSolution = quadraticEquationsDistractorsGenerationService.generateDistractor(quadraticEquationParameters);
		final var correctSolution = solutionMapper.findQuadraticInequalitySolution(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters);

		var distractor_1 = correctSolution;
		var distractor_2 = correctSolution;
		var distractor_3 = correctSolution;

		do {
			try {
				final var quadraticEquationDistractor_1 = quadraticEquationsDistractorsGenerationService.generateDistractor(quadraticEquationParameters);
				distractor_1 = solutionMapper.findQuadraticInequalitySolution(quadraticEquationDistractor_1, standardQuadraticInequalityParameters);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_1 == null || distractor_1.equals(correctSolution));

		do {
			try {
				final var quadraticEquationDistractor_2 = quadraticEquationsDistractorsGenerationService.generateDistractor(quadraticEquationParameters);
				distractor_2 = solutionMapper.findQuadraticInequalitySolution(quadraticEquationDistractor_2, standardQuadraticInequalityParameters);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_2 == null || distractor_2.equals(correctSolution) || distractor_2.equals(distractor_1));

		do {
			try {
				final var quadraticEquationDistractor_3 = quadraticEquationsDistractorsGenerationService.generateDistractor(quadraticEquationParameters);
				distractor_3 = solutionMapper.findQuadraticInequalitySolution(quadraticEquationDistractor_3, standardQuadraticInequalityParameters);
			} catch (IllegalArgumentException e) {
			}
		} while (distractor_3 == null || distractor_3.equals(correctSolution) || distractor_3.equals(distractor_1) || distractor_3.equals(distractor_2));

		return new QuadraticInequalityDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}
}
