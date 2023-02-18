package com.distractors.generation.errorBased.quadraticInequalities;

import com.distractors.generation.general.SymbolicNumberFraction;

public record QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution nonNumericalSolution, SymbolicNumberFraction x_1, SymbolicNumberFraction x_2, InequalitySign sign_1, InequalitySign sign_2) {

	public boolean equals(QuadraticInequalitySolution other) {
		return (this.x_1Equals(other.x_1()) && this.x_2Equals(other.x_2())) &&
				this.sign_1().equals(other.sign_1()) && this.sign_2().equals(other.sign_2()) ||
				(nonNumericalSolution.equals(QuadraticInequalityNonNumericalSolution.EMPTY_SET) && other.nonNumericalSolution().equals(QuadraticInequalityNonNumericalSolution.EMPTY_SET)) ||
				(nonNumericalSolution.equals(QuadraticInequalityNonNumericalSolution.R) && other.nonNumericalSolution().equals(QuadraticInequalityNonNumericalSolution.R));
	}

	private boolean x_1Equals(SymbolicNumberFraction other) {
		if (this.x_1() == null) {
			return other == null;
		} else if (this.x_1() != null && other != null) {
			return this.x_1().equals(other);
		} else return false;
	}

	private boolean x_2Equals(SymbolicNumberFraction other) {
		if (this.x_2() == null) {
			return other == null;
		} else if (this.x_2() != null && other != null) {
			return this.x_2().equals(other);
		} else return false;
	}

	public String toString() {
		final var stringBuilder = new StringBuilder();
		switch (this.nonNumericalSolution()) {
			case EMPTY_SET:
				stringBuilder.append("Φ");
			case NORMAL:
				if (sign_1.equals(InequalitySign.GREATER)) {
					stringBuilder.append("(" + x_1.toString() + "; " + x_2.toString() + ")");
				}
				if (sign_1.equals(InequalitySign.GREATER_OR_EQUALS)) {
					stringBuilder.append("[" + x_1.toString() + "; " + x_2.toString() + "]");
				}
				if (sign_1.equals(InequalitySign.LESS)) {
					stringBuilder.append("(-∞; " + x_1.toString() + "); (" + x_2.toString() + ";+∞)");
				}
				if (sign_1.equals(InequalitySign.LESS_OR_EQUALS)) {
					stringBuilder.append("(-∞; " + x_1.toString() + "]; [" + x_2.toString() + ";+∞)");
				}
			case ONLY_ZERO:
				stringBuilder.append(x_1.toString());
			case R:
				stringBuilder.append("R");
			case R_EXCEPT_ZERO:
				stringBuilder.append("R/{" + x_1.toString() + "}");
		}
		return stringBuilder.toString();
	}
}
