package com.distractors.generation.quadraticInequalities.basedOnWrongParameters;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.basedOnWrongParameters.QuadraticEquationDistractorsGenerationBasedOnWrongParametersService;
import com.distractors.generation.quadraticEquations.basedOnWrongParameters.QuadraticEquationParametersChangeType;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityAnswers;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityCorrectSolutionMapper;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityDistractorMapper;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityParameters;
import com.distractors.generation.quadraticInequalities.QuadraticInequalitySolution;
import com.distractors.generation.quadraticInequalities.StandardQuadraticInequalityParameters;

public class QuadraticInequalityDistractorsGenerationBasedOnWrongParametersService {

	private QuadraticEquationDistractorsGenerationBasedOnWrongParametersService quadraticEquationsDistractorsGenerationService = new QuadraticEquationDistractorsGenerationBasedOnWrongParametersService();
	private QuadraticInequalityCorrectSolutionMapper correctSolutionMapper = new QuadraticInequalityCorrectSolutionMapper();
	private QuadraticInequalityDistractorMapper distractorMapper = new QuadraticInequalityDistractorMapper();

	public QuadraticInequalityAnswers generateDistractors(QuadraticInequalityParameters quadraticInequalityParameters) {
		final var quadraticEquationParameters = quadraticInequalityParameters.equationParameters();
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
		final var standardQuadraticInequalityParameters = quadraticInequalityParameters.toStandard();
		
		final var abc = new AbcSolutionService();
		final var quadraticEquationCorrectSolution = abc.solveCorrectly(standardQuadraticEquationParameters);
		final var correctSolution = correctSolutionMapper.findQuadraticInequalityCorrectSolution(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters);
		var distractors = new ArrayList<QuadraticInequalitySolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(quadraticEquationParameters, standardQuadraticInequalityParameters, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(quadraticEquationParameters, standardQuadraticInequalityParameters, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(quadraticEquationParameters, standardQuadraticInequalityParameters, distractors);

		return new QuadraticInequalityAnswers(correctSolution, distractor_1, distractor_2, distractor_3);
	}
	
	private QuadraticInequalitySolution generateDifferentDistractor(QuadraticEquationParameters quadraticEquationParameters, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters, ArrayList<QuadraticInequalitySolution> distractors) {
		QuadraticInequalitySolution distractor;
		do {
			distractor = this.generateDistractor(quadraticEquationParameters, standardQuadraticInequalityParameters);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private boolean isDistractorInvalid(QuadraticInequalitySolution possibleDistractor, List<QuadraticInequalitySolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticInequalitySolution generateDistractor(QuadraticEquationParameters quadraticEquationParameters, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var randomError = QuadraticEquationParametersChangeType.randomParametersChangeType(quadraticEquationParameters);
		final var quadraticEquationDistractor = quadraticEquationsDistractorsGenerationService.generateDistractorWithChosenParametersChangeType(quadraticEquationParameters, randomError);
		return distractorMapper.findQuadraticInequalityDistractor(quadraticEquationDistractor, standardQuadraticInequalityParameters);
	}
}
