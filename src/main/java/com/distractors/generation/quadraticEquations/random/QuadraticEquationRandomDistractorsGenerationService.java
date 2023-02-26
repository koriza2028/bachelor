package com.distractors.generation.quadraticEquations.random;

import java.util.ArrayList;
import java.util.List;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.general.services.RandomGenerator;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractors;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;

public class QuadraticEquationRandomDistractorsGenerationService {

	private RandomGenerator random = new RandomGenerator();

	public QuadraticEquationDistractors generateDistractors(QuadraticEquationParameters quadraticEquationParameters) {
		final var standardQuadraticEquationParameters = quadraticEquationParameters.toStandard();
	
		final var abc = new AbcSolutionService();
		final var correctSolution = abc.solveCorrectly(standardQuadraticEquationParameters);
		var distractors = new ArrayList<QuadraticEquationRoots> ();
		distractors.add(correctSolution);

		final var distractor_1 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_1);
		final var distractor_2 = this.generateDifferentDistractor(correctSolution, distractors);
		distractors.add(distractor_2);
		final var distractor_3 = this.generateDifferentDistractor(correctSolution, distractors);

		return new QuadraticEquationDistractors(correctSolution, distractor_1, distractor_2, distractor_3);
	}

	private boolean isDistractorInvalid(QuadraticEquationRoots possibleDistractor, List<QuadraticEquationRoots> distractors) {
		return possibleDistractor == null || distractors.stream().anyMatch(distractor -> distractor.equals(possibleDistractor));
	}

	private QuadraticEquationRoots generateDifferentDistractor(final QuadraticEquationRoots correctSolution, List<QuadraticEquationRoots> distractors) {
		QuadraticEquationRoots distractor;
		do {
			distractor = this.generateDistractor(correctSolution);
		} while (this.isDistractorInvalid(distractor, distractors));
		return distractor;
	}

	public QuadraticEquationRoots generateDistractor(QuadraticEquationRoots correctSolution) {
		final var x_1 = this.generateX_1(correctSolution);
		final var x_2 = this.generateX_2(correctSolution);
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	public SymbolicNumberFraction generateX_1(QuadraticEquationRoots correctSolution) {
		final var randomError = SolutionType.randomType();
		return  generateDistractorWithChosenSolutionType(correctSolution, randomError);
	}

	private SymbolicNumberFraction generateX_2(QuadraticEquationRoots correctSolution) {
		final var randomError = SolutionType.randomType();
		return  generateDistractorWithChosenSolutionType(correctSolution, randomError);
	}

	private SymbolicNumberFraction generateDistractorWithChosenSolutionType(QuadraticEquationRoots correctSolution, SolutionType solutionType) {
		switch (solutionType) {
			case FRACTION:
				return random.generateRandomFractionSymbolicNumber(correctSolution);
			case NUMBER:
				return random.generateRandomIntSymbolicNumber(correctSolution);
			case ROOT:
				return random.generateRandomRootSymbolicNumber(correctSolution);
			default:
				throw new IllegalArgumentException("Unknown solution type.");
		}
	}

}
