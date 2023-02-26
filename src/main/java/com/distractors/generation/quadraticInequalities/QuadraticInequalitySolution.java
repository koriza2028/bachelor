package com.distractors.generation.quadraticInequalities;

import com.distractors.generation.general.maths.SymbolicNumberFraction;

public record QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution nonNumericalSolution, QuadraticInequalityRange range_1, QuadraticInequalityRange range_2, boolean correct) {

	public static QuadraticInequalitySolution createEmptySetSolution(boolean correct) {
		return correct ? QuadraticInequalitySolution.createEmptySetCorrectSolution() : QuadraticInequalitySolution.createEmptySetDistractor();
	}

	public static QuadraticInequalitySolution createRSolution(boolean correct) {
		return correct ? QuadraticInequalitySolution.createRCorrectSolution() : QuadraticInequalitySolution.createRDistractor();
	}

	public static QuadraticInequalitySolution createRExceptZeroSolution(SymbolicNumberFraction root, boolean correct) {
		return correct ? QuadraticInequalitySolution.createRExceptZeroCorrectSolution(root) : QuadraticInequalitySolution.createRExceptZeroDistractor(root);
	}

	public static QuadraticInequalitySolution createOnlyZeroSolution(SymbolicNumberFraction root, boolean correct) {
		return correct ? QuadraticInequalitySolution.createOnlyZeroCorrectSolution(root) : QuadraticInequalitySolution.createOnlyZeroDistractor(root);
	}

	public static QuadraticInequalitySolution createCorrectSolution(QuadraticInequalityNonNumericalSolution nonNumericalSolution, QuadraticInequalityRange range_1, QuadraticInequalityRange range_2) {
		return new QuadraticInequalitySolution(nonNumericalSolution, range_1, range_2, true);
	}

	public static QuadraticInequalitySolution createDistractor(QuadraticInequalityNonNumericalSolution nonNumericalSolution, QuadraticInequalityRange range_1, QuadraticInequalityRange range_2) {
		return new QuadraticInequalitySolution(nonNumericalSolution, range_1, range_2, false);
	}

	public static QuadraticInequalitySolution createRDistractor() {
		final var range = new QuadraticInequalityRange(SymbolicNumberFraction.ONE, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, range, range, false);
	}

	public static QuadraticInequalitySolution createEmptySetDistractor() {
		final var range = new QuadraticInequalityRange(SymbolicNumberFraction.ONE, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, range, range, false);
	}

	public static QuadraticInequalitySolution createRExceptZeroDistractor(SymbolicNumberFraction exceptedZero) {
		final var range = new QuadraticInequalityRange(exceptedZero, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, range, range, false);
	}

	public static QuadraticInequalitySolution createOnlyZeroDistractor(SymbolicNumberFraction onlyZero) {
		final var range = new QuadraticInequalityRange(onlyZero, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.ONLY_ZERO, range, range, false);
	}

	public static QuadraticInequalitySolution createRCorrectSolution() {
		final var range = new QuadraticInequalityRange(SymbolicNumberFraction.ONE, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R, range, range, true);
	}

	public static QuadraticInequalitySolution createEmptySetCorrectSolution() {
		final var range = new QuadraticInequalityRange(SymbolicNumberFraction.ONE, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.EMPTY_SET, range, range, true);
	}

	public static QuadraticInequalitySolution createRExceptZeroCorrectSolution(SymbolicNumberFraction exceptedZero) {
		final var range = new QuadraticInequalityRange(exceptedZero, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.R_EXCEPT_ZERO, range, range, true);
	}

	public static QuadraticInequalitySolution createOnlyZeroCorrectSolution(SymbolicNumberFraction onlyZero) {
		final var range = new QuadraticInequalityRange(onlyZero, InequalitySign.GREATER);
		return new QuadraticInequalitySolution(QuadraticInequalityNonNumericalSolution.ONLY_ZERO, range, range, true);
	}

	public boolean equals(QuadraticInequalitySolution other) {
		return (this.x_1Equals(other.range_1().x()) && this.x_2Equals(other.range_2().x())) &&
				this.range_1().sign().equals(other.range_1().sign()) && this.range_2().sign().equals(other.range_2().sign()) ||
				(nonNumericalSolution.equals(QuadraticInequalityNonNumericalSolution.EMPTY_SET) && other.nonNumericalSolution().equals(QuadraticInequalityNonNumericalSolution.EMPTY_SET)) ||
				(nonNumericalSolution.equals(QuadraticInequalityNonNumericalSolution.R) && other.nonNumericalSolution().equals(QuadraticInequalityNonNumericalSolution.R));
	}

	private boolean x_1Equals(SymbolicNumberFraction other) {
		if (this.range_1().x() == null) {
			return other == null;
		} else if (this.range_1().x() != null && other != null) {
			return this.range_1().x().equals(other);
		} else return false;
	}

	private boolean x_2Equals(SymbolicNumberFraction other) {
		if (this.range_2().x() == null) {
			return other == null;
		} else if (this.range_2().x() != null && other != null) {
			return this.range_2().x().equals(other);
		} else return false;
	}

	public String toString() {
		final var stringBuilder = new StringBuilder();
		switch (this.nonNumericalSolution()) {
			case EMPTY_SET:
				stringBuilder.append("Φ");
				break;
			case NORMAL:
				if (this.range_1().sign().equals(InequalitySign.GREATER)) {
					stringBuilder.append("(" + this.range_1().x().toString() + "; " + this.range_2().x().toString() + ")");
				}
				if (this.range_1().sign().equals(InequalitySign.GREATER_OR_EQUALS)) {
					stringBuilder.append("[" + this.range_1().x().toString() + "; " + this.range_2().x().toString() + "]");
				}
				if (this.range_1().sign().equals(InequalitySign.LESS)) {
					stringBuilder.append("(-∞; " + this.range_1().x().toString() + "); (" + this.range_2().x().toString() + ";+∞)");
				}
				if (this.range_1().sign().equals(InequalitySign.LESS_OR_EQUALS)) {
					stringBuilder.append("(-∞; " + this.range_1().x().toString() + "]; [" + this.range_2().x().toString() + ";+∞)");
				}
				break;
			case ONLY_ZERO:
				stringBuilder.append(this.range_1().x().toString());
				break;
			case R:
				stringBuilder.append("R");
				break;
			case R_EXCEPT_ZERO:
				stringBuilder.append("R/{" + this.range_1().x().toString() + "}");
				break;
		}
		return stringBuilder.toString();
	}

}
