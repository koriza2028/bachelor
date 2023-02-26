package com.distractors.generation.quadraticEquations;

import com.distractors.generation.general.maths.SymbolicNumberFraction;

public record QuadraticEquationRoots(SymbolicNumberFraction x_1, SymbolicNumberFraction x_2, boolean correct) {

	public static QuadraticEquationRoots createCorrectSolution(SymbolicNumberFraction x_1, SymbolicNumberFraction x_2) {
		return new QuadraticEquationRoots(x_1, x_2, true);
	}

	public static QuadraticEquationRoots createDistractor(SymbolicNumberFraction x_1, SymbolicNumberFraction x_2) {
		return new QuadraticEquationRoots(x_1, x_2, false);
	}

	public boolean equals(QuadraticEquationRoots other) {
		return (this.x_1Equals(other.x_1()) && this.x_2Equals(other.x_2())) || 
				(this.x_1Equals(other.x_2()) && this.x_2Equals(other.x_1()));
	}

	public boolean notEquals(QuadraticEquationRoots other) {
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

	public void print() {
		if (x_1 != null) {
			System.out.print("x₁: ");
			x_1.print();
		} else 	{
			System.out.print("x₁: no solution");
		}
		if (x_2 != null) {
			System.out.print("x_2: ");
			x_2.print();
		} else 	{
			System.out.println("x_2: no solution");
		}
	}

	public String toString() {
		final var stringBuilder = new StringBuilder();
		if ((x_1 != null) && (x_2 != null) && (x_1.equals(x_2))) {
			stringBuilder.append("x₁, x₂= ");
			stringBuilder.append(x_1.toString());
		} else {
			if (x_1 != null) {
				stringBuilder.append("x₁= ");
				stringBuilder.append(x_1.toString());
			}
			if (x_2 != null) {
				stringBuilder.append("; x₂= ");
				stringBuilder.append(x_2.toString());
			}
			if (x_1 == null && x_2 == null) {
				stringBuilder.append("Φ");
			}
		}
		return stringBuilder.toString();
	}

}