package com.distractors.generation.quadraticInequalities.random;

import java.util.ArrayList;
import java.util.List;

import com.distractors.generation.quadraticEquations.QuadraticEquationSolution;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;
import com.distractors.generation.quadraticEquations.random.QuadraticEquationRandomDistractorsGenerationService;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityAnswers;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityCorrectSolutionMapper;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityDistractor;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityDistractorMapper;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityNonNumericalSolution;
import com.distractors.generation.quadraticInequalities.QuadraticInequalityParameters;
import com.distractors.generation.quadraticInequalities.QuadraticInequalitySolution;
import com.distractors.generation.quadraticInequalities.StandardQuadraticInequalityParameters;

public class QuadraticInequalityRandomDistractorsGenerationService {

	private QuadraticEquationRandomDistractorsGenerationService quadraticEquationsDistractorsGenerationService = new QuadraticEquationRandomDistractorsGenerationService();
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

		final var distractor_1 = this.generateDifferentDistractor(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters, distractors);

		return new QuadraticInequalityAnswers(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private boolean isDistractorInvalid(QuadraticInequalitySolution possibleDistractor, List<QuadraticInequalitySolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticInequalitySolution generateDifferentDistractor(QuadraticEquationSolution quadraticEquationCorrectSolution, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters, ArrayList<QuadraticInequalitySolution> distractors) {
		QuadraticInequalitySolution distractor;
		do {
			distractor = this.generateDistractor(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	private QuadraticInequalitySolution generateDistractor(QuadraticEquationSolution quadraticEquationCorrectSolution, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var randomSolutionType = QuadraticInequalityNonNumericalSolution.randomType();

		return generateDistractorWithChosenSolutionType(quadraticEquationCorrectSolution,
				standardQuadraticInequalityParameters, randomSolutionType);
	}

	private QuadraticInequalitySolution generateDistractorWithChosenSolutionType(
			QuadraticEquationSolution quadraticEquationCorrectSolution,
			StandardQuadraticInequalityParameters standardQuadraticInequalityParameters,
			final QuadraticInequalityNonNumericalSolution randomSolutionType) {
		switch(randomSolutionType) {
			case EMPTY_SET:
				return QuadraticInequalityDistractor.createEmptySetDistractor(randomSolutionType);
			case NORMAL:
				return this.generateNormalDistractor(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters);
			case ONLY_ZERO:
				return this.generateOnlyZeroDistractor(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters, randomSolutionType);
			case R:
				return QuadraticInequalityDistractor.createRDistractor(randomSolutionType);
			case R_EXCEPT_ZERO:
				return this.generateRExceptZeroDistractor(quadraticEquationCorrectSolution, standardQuadraticInequalityParameters, randomSolutionType); 
			default:
				throw new IllegalArgumentException("Unknown solution type.");
		}
	}

	private QuadraticInequalitySolution generateNormalDistractor(QuadraticEquationSolution quadraticEquationCorrectSolution, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters) {
		final var quadraticEquationDistractor = quadraticEquationsDistractorsGenerationService.generateDistractor(quadraticEquationCorrectSolution);
		return distractorMapper.findQuadraticInequalityDistractor(quadraticEquationDistractor, standardQuadraticInequalityParameters);
	}

	private QuadraticInequalitySolution generateOnlyZeroDistractor(QuadraticEquationSolution quadraticEquationCorrectSolution, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters, QuadraticInequalityNonNumericalSolution randomSolutionType) {
		final var onlyZero = quadraticEquationsDistractorsGenerationService.generateX_1(quadraticEquationCorrectSolution);
		return QuadraticInequalityDistractor.createOnlyZeroDistractor(onlyZero, randomSolutionType);
	}

	private QuadraticInequalitySolution generateRExceptZeroDistractor(QuadraticEquationSolution quadraticEquationCorrectSolution, StandardQuadraticInequalityParameters standardQuadraticInequalityParameters, QuadraticInequalityNonNumericalSolution randomSolutionType) {
		final var exceptedZero = quadraticEquationsDistractorsGenerationService.generateX_1(quadraticEquationCorrectSolution);
		return QuadraticInequalityDistractor.createRExceptZeroDistractor(exceptedZero, randomSolutionType);
	}
}
