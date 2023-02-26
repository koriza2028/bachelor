package com.distractors.generation.quadraticInequalities.errorBased;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationDistractorsGenerationErrorBasedService;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityDistractors;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityParameters;
import com.distractors.generation.quadraticInequalities.QuadraticInequalitySolution;
import com.distractors.generation.quadraticInequalities.QuadraticInequalitySolutionMapper;
import com.distractors.generation.quadraticInequalities.StandardQuadraticInequalityParameters;

public class QuadraticInequalityDistractorsGenerationErrorBasedService {
	private QuadraticEquationDistractorsGenerationErrorBasedService quadraticEquationsDistractorsGenerationService = new QuadraticEquationDistractorsGenerationErrorBasedService();
	private QuadraticInequalitySolutionMapper solutionMapper = new QuadraticInequalitySolutionMapper();

	public QuadraticInequalityDistractors generateDistractors(QuadraticInequalityParameters quadraticInequalityParameters) {
		final var quadraticEquationParameters = quadraticInequalityParameters.equationParameters();
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
		final var standardQuadraticInequalityParameters = quadraticInequalityParameters.toStandard();
		
		final var abc = new AbcSolutionService();
		final var quadraticEquationCorrectSolution = abc.solveCorrectly(standardQuadraticEquationParameters);
		final var correctSolution = solutionMapper.findQuadraticInequalitySolution(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters);

		var distractors = new ArrayList<QuadraticInequalitySolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(quadraticEquationParameters, standardQuadraticInequalityParameters, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(quadraticEquationParameters, standardQuadraticInequalityParameters, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(quadraticEquationParameters, standardQuadraticInequalityParameters, distractors);

		return new QuadraticInequalityDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private boolean isDistractorInvalid(QuadraticInequalitySolution possibleDistractor, List<QuadraticInequalitySolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticInequalitySolution generateDifferentDistractor(QuadraticEquationParameters quadraticEquationParameters, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters, ArrayList<QuadraticInequalitySolution> distractors) {
		QuadraticInequalitySolution distractor;
		do {
			distractor = this.generateDistractor(quadraticEquationParameters, standardQuadraticInequalityParameters);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private QuadraticInequalitySolution generateDistractor(QuadraticEquationParameters quadraticEquationParameters, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var quadraticEquationDistractor = quadraticEquationsDistractorsGenerationService.generateDistractor(quadraticEquationParameters);
		return solutionMapper.findQuadraticInequalitySolution(quadraticEquationDistractor, standardQuadraticInequalityParameters);
	}
}
