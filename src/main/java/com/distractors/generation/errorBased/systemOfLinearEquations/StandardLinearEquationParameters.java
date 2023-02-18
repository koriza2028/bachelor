package com.distractors.generation.errorBased.systemOfLinearEquations;

import com.distractors.generation.errorBased.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.Fraction;

public record StandardLinearEquationParameters(int coefficientOfX, int coefficientOfY, int freeCoefficient) {

	public StandardLinearEquationParameters multiplyBy(int multiplicator) {
		final var newCoefficientOfX = coefficientOfX * multiplicator;
		final var newCoefficientOfY = coefficientOfY * multiplicator;
		final var newFreeCoefficient = freeCoefficient * multiplicator;

		return new StandardLinearEquationParameters(newCoefficientOfX, newCoefficientOfY, newFreeCoefficient);
	}

	public StandardLinearEquationParameters multiplyIgnoringFreeCoefficientBy(int multiplicator) {
		final var newCoefficientOfX = coefficientOfX * multiplicator;
		final var newCoefficientOfY = coefficientOfY * multiplicator;

		return new StandardLinearEquationParameters(newCoefficientOfX, newCoefficientOfY, freeCoefficient);
	}

	public StandardLinearEquationParameters substract(StandardLinearEquationParameters linearEquation) {
		final var newCoefficientOfX = coefficientOfX - linearEquation.coefficientOfX();
		final var newCoefficientOfY = coefficientOfY - linearEquation.coefficientOfY();
		final var newFreeCoefficient = freeCoefficient - linearEquation.freeCoefficient();

		return new StandardLinearEquationParameters(newCoefficientOfX, newCoefficientOfY, newFreeCoefficient);
	}

	public StandardLinearEquationFractionParameters toStandardLinearEquationFractionParameters() {
		final var b = new Fraction(freeCoefficient, 1);
		if (coefficientOfX == 0) {
			final var a = new Fraction(coefficientOfY, 1);
			return new StandardLinearEquationFractionParameters(a, b);
		} else if (coefficientOfX == 0) {
			final var a = new Fraction(coefficientOfY, 1);
			return new StandardLinearEquationFractionParameters(a, b);
		} else {
			throw new IllegalStateException("one of the coeffiecients must be 0");
		}
	}
}
