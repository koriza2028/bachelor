package com.distractors.generation.systemsOfTwoEquations;

import com.distractors.generation.general.maths.Fraction;

public interface SystemOfTwoEquationsSolution {
	
	SystemOfTwoEquationsNonNumericalSolution nonNumericalSolution();
	Fraction x();
	Fraction y();

	public default boolean equals(SystemOfTwoEquationsSolution other) {
		return this.nonNumericalSolution().equals(other.nonNumericalSolution()) && this.x_1Equals(other.x()) && this.x_2Equals(other.y());
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

	public default String convertToString() {
		final var stringBuilder = new StringBuilder();
		switch (this.nonNumericalSolution()) {
			case EMPTY_SET:
				stringBuilder.append("Φ");
				break;
			case R:
				stringBuilder.append("R");
				break;
			default:
				if (x() != null) {
					stringBuilder.append("x = " + x().toString());
				}
				if (y() != null) {
					stringBuilder.append("y = " + y().toString());
				}
				break;
		}
		return stringBuilder.toString();
	}
}
