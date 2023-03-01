package com.distractors.generation.quadraticEquations.random;

import java.util.ArrayList;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.general.services.RandomGenerator;
import com.distractors.generation.quadraticEquations.QuadraticEquationAnswers;
import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationSolution;
import com.distractors.generation.quadraticEquations.basedOnResultManipulation.QuadraticEquationResultManipulationType;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

public class QuadraticEquationRandomDistractorsGenerationService {

	private RandomGenerator random = new RandomGenerator();

	public QuadraticEquationAnswers generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var abc = new AbcSolutionService();
		final var correctSolution = abc.solveCorrectly(standardQuadraticEquationParameters);
		var distractors = new ArrayList<QuadraticEquationSolution> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(correctSolution, distractors);

		return new QuadraticEquationAnswers(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private boolean isDistractorInvalid(QuadraticEquationDistractor possibleDistractor, ArrayList<QuadraticEquationSolution> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticEquationDistractor generateDifferentDistractor(final QuadraticEquationCorrectSolution correctSolution, ArrayList<QuadraticEquationSolution> distractors) {
		QuadraticEquationDistractor distractor;
		do {
			distractor = this.generateDistractor(correctSolution);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	public QuadraticEquationDistractor generateDistractor(QuadraticEquationSolution quadraticEquationCorrectSolution) {
		final var x_1 = this.generateX_1(quadraticEquationCorrectSolution);
		final var x_2 = this.generateX_2(quadraticEquationCorrectSolution);
		return new QuadraticEquationDistractor(x_1, x_2, QuadraticEquationResultManipulationType.MINUS_ONE_X_1);
	}

	public SymbolicNumberFraction generateX_1(QuadraticEquationSolution quadraticEquationCorrectSolution) {
		final var randomError = SolutionType.randomType();
		return generateDistractorWithChosenDistractorType(quadraticEquationCorrectSolution, randomError);
	}

	private SymbolicNumberFraction generateX_2(QuadraticEquationSolution quadraticEquationCorrectSolution) {
		final var randomError = SolutionType.randomType();
		return generateDistractorWithChosenDistractorType(quadraticEquationCorrectSolution, randomError);
	}

	private SymbolicNumberFraction generateDistractorWithChosenDistractorType(QuadraticEquationSolution quadraticEquationCorrectSolution, SolutionType solutionType) {
		switch (solutionType) {
			case FRACTION:
				return random.generateRandomFractionSymbolicNumber(quadraticEquationCorrectSolution);
			case NUMBER:
				return random.generateRandomIntSymbolicNumber(quadraticEquationCorrectSolution);
			case ROOT:
				return random.generateRandomRootSymbolicNumber(quadraticEquationCorrectSolution);
			default:
				throw new IllegalArgumentException("Unknown solution type.");
		}
	}

}
