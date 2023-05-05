package com.distractors.generation.general.maths;

/**
 * Represents a number symbolically imitating calculation by a human
 * Example (2 + 1/3 + √2 - √3)/(1/3 + √2)
 */
public class SymbolicNumberFraction {

	private SymbolicNumber nominator;
	private SymbolicNumber denominator;

	public static final SymbolicNumberFraction ZERO = new SymbolicNumberFraction(SymbolicNumber.ZERO);
	public static final SymbolicNumberFraction ONE = new SymbolicNumberFraction(SymbolicNumber.ONE);
	public static final SymbolicNumberFraction MINUS_ONE = new SymbolicNumberFraction(SymbolicNumber.MINUS_ONE);

	/**
	 * Creates a symbolic fraction for denominator != 1
	 * The symbolic number fraction is simplified automatically by {@link #simplify()} method
	 */
	public SymbolicNumberFraction(SymbolicNumber nominator, SymbolicNumber denominator) {
		this.nominator = nominator;
		this.denominator = checkDenominatorNotEqualsToZero(denominator);
		this.simplify();
	}

	/**
	 * Creates a symbolic fraction for denominator = 1
	 * The symbolic number fraction is simplified automatically by {@link #simplify()} method
	 */
	public SymbolicNumberFraction(SymbolicNumber number) {
		this.nominator = number;
		this.denominator = SymbolicNumber.ONE;
		this.simplify();
	}
	
	private SymbolicNumber checkDenominatorNotEqualsToZero(SymbolicNumber denominator) {
		if (denominator.toDouble() == 0) {
			throw new IllegalArgumentException("Denominator cannot be equal to 0.");
		} else {
			return denominator;
		}
	}

	public SymbolicNumberFraction multiplyBy(int number) {
		final var newNominator = this.nominator.multiplyBy(number);

		return new SymbolicNumberFraction(newNominator, this.denominator);
	}

	public SymbolicNumberFraction reverse() {
		final var reverseNominator = this.denominator;
		final var reverseDenominator = this.nominator;
		return new SymbolicNumberFraction(reverseNominator, reverseDenominator);
	}

	public double toDouble() {
		return this.nominator.toDouble() / this.denominator.toDouble();
	}
	
	public SymbolicNumber getNominator() {
		return nominator;
	}

	public SymbolicNumber getDenominator() {
		return denominator;
	}

	public boolean isInt() {
		return nominator.toDouble() / denominator.toDouble() % 1 == 0;
	}

	public int toInt() {
		return (int) (nominator.toDouble() / denominator.toDouble());
	}

	public boolean equals(SymbolicNumberFraction other) {
		return this.equalsSymbolically(other) || this.equalsNumerically(other);
	}

	private boolean equalsSymbolically(SymbolicNumberFraction other) {
		final var otherIsNotNull = other != null;
		final var nominatorsAreEqual = this.nominator.equals(other.getNominator());
		final var denominatorsAreEqual = this.denominator.equals(other.getDenominator());
		return otherIsNotNull && nominatorsAreEqual && denominatorsAreEqual;
	}

	private boolean equalsNumerically(SymbolicNumberFraction other) {
		return other != null && this.toDouble() == other.toDouble();
	}

	/**
	 * Performs all possible mathematical operations symbolically
	 */
	public void simplify() {
		this.nominator.simplify();
		this.denominator.simplify();
		if (this.denominator.toInt() != 0 && this.denominator.toInt() != 1 && 
				(this.nominator.getInteger() % this.denominator.toInt()) == 0 &&
				(this.nominator.getRootsPart()
						.getRoots()
						.stream()
						.allMatch(
								root -> root.getBeforeTheRoot().toDouble() % this.denominator.toDouble() == 0))) {
			final var symbolicNumber = new SymbolicNumber.SymbolicNumberBuilder()
									.withInteger(this.nominator.getInteger() / this.denominator.toInt())
									.withFractionPart(this.nominator.getFractionPart())
									.withRoots(this.nominator.getRootsPart().multiplyBy(new Fraction(1, this.denominator.toInt())))
									.build();
			this.nominator = symbolicNumber;
			this.denominator = new SymbolicNumber.SymbolicNumberBuilder()
									.withInteger(1)
									.build();
		}
	}

	public String convertToString() {
		this.simplify();
		final var stringBuilder = new StringBuilder();
		if (this.isInt()) {
			stringBuilder.append(this.toInt());
		} else if (this.nominator.isInt() && this.denominator.isInt()) {
			final var fraction = new Fraction(this.nominator.toInt(), this.denominator.toInt());
			stringBuilder.append(fraction.convertToString());
		} else if (this.denominator.toDouble() != 1 && this.denominator.toDouble() != -1) {
			stringBuilder.append("(" + this.nominator.convertToString() + ")");
			stringBuilder.append("/");
			stringBuilder.append("(" + this.denominator.convertToString() + ")");
		} else {
			stringBuilder.append(this.nominator.multiplyBy(this.denominator).convertToString());
		}
		return stringBuilder.toString();
	}
}
