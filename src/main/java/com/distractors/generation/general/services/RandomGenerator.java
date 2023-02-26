package com.distractors.generation.general.services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;
import com.distractors.generation.general.maths.SymbolicNumberBuilder;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.systemsOfTwoEquations.SystemOfTwoEquationsSolution;

public class RandomGenerator {
	private static final Random RANDOM = new Random();

	public SymbolicNumberFraction generateRandomIntSymbolicNumber(QuadraticEquationRoots correctSolution) {
		final var range = this.defineRange(correctSolution);
		final var intNumber = this.generateRandomInt(range);
		final var symbolicNumber = new SymbolicNumberBuilder().withInteger(intNumber).build();
		return new SymbolicNumberFraction(symbolicNumber);
	}

	public SymbolicNumberFraction generateRandomRootSymbolicNumber(QuadraticEquationRoots correctSolution) {
		final var range = this.defineRange(correctSolution);
		final var beforeTheRoot = this.generateRandomFraction(range);
		var underTheRoot = this.generateRandomFraction(range);
		do {
			underTheRoot = this.generateRandomFraction(range);
		} while (underTheRoot.isSmallerThanZero());
		final var root = new SquareRoot(underTheRoot, beforeTheRoot);
		final var symbolicNumber = new SymbolicNumberBuilder().withRoot(root).build();
		return new SymbolicNumberFraction(symbolicNumber);
	}

	public SymbolicNumberFraction generateRandomFractionSymbolicNumber(QuadraticEquationRoots correctSolution) {
		final var range = this.defineRange(correctSolution);
		final var fraction = generateRandomFraction(range);
		final var symbolicNumber = new SymbolicNumberBuilder().withFractionPart(fraction).build();
		return new SymbolicNumberFraction(symbolicNumber);
	}

	public Fraction generateRandomFraction(SystemOfTwoEquationsSolution correctSolution) {
		final var range = this.defineRange(correctSolution);
		return this.generateRandomFraction(range);
	}

	private Fraction generateRandomFraction(List<Integer> range) {
		final var nominator = generateRandomInt(range);
		var denominator = 0;
		do {
			denominator = this.generateRandomInt(range);
		} while (denominator == 0);
		final var fraction = new Fraction(nominator, denominator);
		return fraction;
	}

	private int generateRandomInt(List<Integer> range) {
		final var size = range.size();
		return range.get(RANDOM.nextInt(size));
	}

	private List<Integer> defineRange(QuadraticEquationRoots correctSolution) {
		final var x_1Abs = Math.abs(correctSolution.x_1().toDouble());
		final var x_2Abs = Math.abs(correctSolution.x_2().toDouble());
		final var max = (int) Math.max(x_1Abs, x_2Abs);

		return IntStream.range(-max, max).boxed().collect(Collectors.toUnmodifiableList());
	}

	private List<Integer> defineRange(SystemOfTwoEquationsSolution correctSolution) {
		final var xAbs = Math.abs(correctSolution.x().toDouble());
		final var yAbs = Math.abs(correctSolution.y().toDouble());
		final var max = (int) Math.max(xAbs, yAbs);

		return IntStream.range(-max, max).boxed().collect(Collectors.toUnmodifiableList());
	}
}
