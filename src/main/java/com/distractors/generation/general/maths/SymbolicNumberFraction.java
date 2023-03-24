package com.distractors.generation.general.maths;

public class SymbolicNumberFraction {

	private SymbolicNumber nominator;
	private SymbolicNumber denominator;

	public static final SymbolicNumberFraction ZERO = new SymbolicNumberFraction(
			new SymbolicNumberBuilder()
			.withInteger(0)
			.build());
	public static final SymbolicNumberFraction ONE = new SymbolicNumberFraction(
			new SymbolicNumberBuilder()
			.withInteger(1)
			.build());
	public static final SymbolicNumberFraction MINUS_ONE = new SymbolicNumberFraction(
			new SymbolicNumberBuilder()
			.withInteger(-1)
			.build());

	public SymbolicNumberFraction(SymbolicNumber nominator, SymbolicNumber denominator) {
		this.nominator = nominator;
		if (denominator.toDouble() == 0) {
			throw new IllegalArgumentException("Denominator cannot be equal to 0.");
		} else {
			this.denominator = denominator;
		}
		this.simplify();
	}

	public SymbolicNumberFraction(SymbolicNumber number) {
		this.nominator = number;
		final var builder = new SymbolicNumberBuilder();
		this.denominator = builder.withFractionPart(Fraction.ONE).build();
		this.simplify();
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

	public void print() {
		if (this.isInt()) {
			System.out.println();
			System.out.print(this.toInt());
			System.out.println();
		} else if (this.denominator.toDouble() != 1) {
			System.out.println();
			this.nominator.print();
			System.out.println();
			System.out.println("------");
			this.denominator.print();
			System.out.println();
		} else {
			System.out.println();
			this.nominator.print();
			System.out.println();
		}
	}

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
			final var builder = new SymbolicNumberBuilder();
			final var symbolicNumber = builder.withInteger(this.nominator.getInteger() / this.denominator.toInt())
											.withFractionPart(this.nominator.getFractionPart())
											.withRoots(this.nominator.getRootsPart().multiplyBy(new Fraction(1, this.denominator.toInt())))
											.build();
			this.nominator = symbolicNumber;
			this.denominator = new SymbolicNumberBuilder()
									.withInteger(1)
									.build();
		}
	}

	public String toString() {
		this.simplify();
		final var stringBuilder = new StringBuilder();
		if (this.isInt()) {
			stringBuilder.append(this.toInt());
		} else if (this.nominator.isInt() && this.denominator.isInt()) {
			final var fraction = new Fraction(this.nominator.toInt(), this.denominator.toInt());
			stringBuilder.append(fraction.toString());
		} else if (this.denominator.toDouble() != 1 && this.denominator.toDouble() != -1) {
			stringBuilder.append("(" + this.nominator.toString() + ")");
			stringBuilder.append("/");
			stringBuilder.append("(" + this.denominator.toString() + ")");
		} else {
			stringBuilder.append(this.nominator.multiplyBy(this.denominator).toString());
		}
		return stringBuilder.toString();
	}
}
