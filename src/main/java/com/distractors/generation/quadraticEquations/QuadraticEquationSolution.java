package com.distractors.generation.quadraticEquations;

import com.distractors.generation.general.maths.SymbolicNumberFraction;

public interface QuadraticEquationSolution {

	public SymbolicNumberFraction x_1();

	public SymbolicNumberFraction x_2();

	public default boolean equals(QuadraticEquationSolution other) {
		return (this.x_1Equals(other.x_1()) && this.x_2Equals(other.x_2())) || 
				(this.x_1Equals(other.x_2()) && this.x_2Equals(other.x_1()));
	}

	public default boolean notEquals(QuadraticEquationSolution other) {
		return !this.equals(other);
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

	public default String convertToString() {
		final var stringBuilder = new StringBuilder();
		if ((x_1() != null) && (x_2() != null) && (x_1().equals(x_2()))) {
			stringBuilder.append("x₁, x₂= ");
			stringBuilder.append(x_1().toString());
		} else {
			if (x_1() != null) {
				stringBuilder.append("x₁= ");
				stringBuilder.append(x_1().toString());
			}
			if (x_2() != null) {
				stringBuilder.append("; x₂= ");
				stringBuilder.append(x_2().toString());
			}
			if (x_1() == null && x_2() == null) {
				stringBuilder.append("Φ");
			}
		}
		return stringBuilder.toString();
	}

	public default boolean correct() {
		return this instanceof QuadraticEquationCorrectSolution;
	}
}