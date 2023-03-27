package com.distractors.generation.systemsOfTwoEquations;

import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.maths.Fraction;

public record StandardEquationParameters(int coefficientOfX, int coefficientOfY, int freeCoefficient) {

	public StandardEquationParameters multiplyBy(int multiplicator) {
		final var newCoefficientOfX = coefficientOfX * multiplicator;
		final var newCoefficientOfY = coefficientOfY * multiplicator;
		final var newFreeCoefficient = freeCoefficient * multiplicator;

		return new StandardEquationParameters(newCoefficientOfX, newCoefficientOfY, newFreeCoefficient);
	}

	public StandardEquationParameters multiplyIgnoringFreeCoefficientBy(int multiplicator) {
		final var newCoefficientOfX = coefficientOfX * multiplicator;
		final var newCoefficientOfY = coefficientOfY * multiplicator;

		return new StandardEquationParameters(newCoefficientOfX, newCoefficientOfY, freeCoefficient);
	}

	public StandardEquationParameters add(StandardEquationParameters linearEquation) {
		final var newCoefficientOfX = coefficientOfX + linearEquation.coefficientOfX();
		final var newCoefficientOfY = coefficientOfY + linearEquation.coefficientOfY();
		final var newFreeCoefficient = freeCoefficient + linearEquation.freeCoefficient();

		return new StandardEquationParameters(newCoefficientOfX, newCoefficientOfY, newFreeCoefficient);
	}

	public StandardLinearEquationFractionParameters toStandardLinearEquationFractionParameters() {
		final var b = new Fraction(freeCoefficient, 1);
		if (coefficientOfX == 0) {
			final var a = new Fraction(coefficientOfY, 1);
			return new StandardLinearEquationFractionParameters(a, b);
		} else if (coefficientOfY == 0) {
			final var a = new Fraction(coefficientOfX, 1);
			return new StandardLinearEquationFractionParameters(a, b);
		} else {
			throw new IllegalStateException("one of the coeffiecients must be 0");
		}
	}
}
