package com.distractors.generation.errorBased.systemOfLinearEquations;

import com.distractors.generation.general.Fraction;

public record SystemOfEquationsSolution(Fraction x, Fraction y) {

	public boolean equals(SystemOfEquationsSolution other) {
		return (this.x_1Equals(other.x()) && this.x_2Equals(other.y()));
	}

	private boolean x_1Equals(Fraction other) {
		if (this.x() == null) {
			return other == null;
		} else if (this.x() != null && other != null) {
			return this.x().equals(other);
		} else return false;
	}

	private boolean x_2Equals(Fraction other) {
		if (this.y() == null) {
			return other == null;
		} else if (this.y() != null && other != null) {
			return this.y().equals(other);
		} else return false;
	}

	public String toString() {
		final var stringBuilder = new StringBuilder();
		if (x != null) {
			stringBuilder.append("x = " + x);
		}
		if (y != null) {
			stringBuilder.append("y = " + y);
		}
		return stringBuilder.toString();
	}
}
