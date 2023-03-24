package com.distractors.generation.general.maths;

public class SymbolicNumber {

	private SquareRoots rootsPart;
	private Fraction fractionPart;
	private int integer;

	SymbolicNumber(SymbolicNumberBuilder symbolicNumberBuilder) {
		this.rootsPart = symbolicNumberBuilder.getRootsPart();
		this.fractionPart = symbolicNumberBuilder.getFractionPart();
		this.integer = symbolicNumberBuilder.getInteger();
		this.simplify();
	}

	public SymbolicNumber add(SymbolicNumber number) {
		final var rootsPartResult = this.rootsPart.add(number.rootsPart);
		final var fractionPartResult = this.fractionPart.add(number.fractionPart);

		return new SymbolicNumberBuilder()
				.withFractionPart(fractionPartResult)
				.withRoots(rootsPartResult)
				.build();
	}

	public SymbolicNumber substract(SymbolicNumber number) {
		final var rootsPartResult = this.rootsPart.substract(number.rootsPart);
		final var fractionPartResult = this.fractionPart.substract(number.fractionPart);

		return new SymbolicNumberBuilder()
				.withFractionPart(fractionPartResult)
				.withRoots(rootsPartResult)
				.build();
	}

	public SymbolicNumber multiplyBy(int number) {
		final var rootsPartResult = this.rootsPart.multiplyBy(number);
		final var fractionPartResult = this.fractionPart.multiplyBy(number);
		final var integerResult = this.integer * number;

		return new SymbolicNumberBuilder()
				.withFractionPart(fractionPartResult)
				.withRoots(rootsPartResult)
				.withInteger(integerResult)
				.build();
	}

	public SymbolicNumber multiplyBy(Fraction fraction) {
		final var rootsPartResult = this.rootsPart.multiplyBy(fraction);
		final var integerResult = fraction.multiplyBy(integer);
		final var fractionPartResult = this.fractionPart.multiplyBy(fraction).add(integerResult);

		return new SymbolicNumberBuilder()
				.withFractionPart(fractionPartResult)
				.withRoots(rootsPartResult)
				.build();
	}

	public SymbolicNumber multiplyBy(SymbolicNumber number) {
		final var rootsByRootsResult = this.rootsPart.multiplyBy(number.rootsPart);
		final var rootsByFractionResult = this.rootsPart.multiplyBy(number.fractionPart);
		final var rootsByIntResult = this.rootsPart.multiplyBy(number.integer);
		final var integerResult = this.integer * number.getInteger();


		final var fractionPartResult = this.fractionPart.multiplyBy(number.integer).add(this.fractionPart.multiplyBy(number.fractionPart));

		return new SymbolicNumberBuilder()
				.withInteger(integerResult)
				.withFractionPart(fractionPartResult)
				.withRoots(rootsByRootsResult)
				.withRoots(rootsByFractionResult)
				.withRoots(rootsByIntResult)
				.build();
	}

	public SymbolicNumber divideBy(Fraction fraction) throws IllegalArgumentException {
		return this.multiplyBy(fraction.reverse());
	}

	public SymbolicNumberFraction divideBy(SymbolicNumber number) {
		return new SymbolicNumberFraction(this, number);
	}

	public boolean isInt() {
		return this.toDouble() % 1 == 0;
	}

	public double toDouble() {
		return this.integer + this.fractionPart.toDouble() + this.rootsPart.toDouble();
	}

	public int toInt() {
		return (int) this.toDouble();
	}

	public void simplify() {
		if (this.fractionPart.isInt()) {
			this.integer += fractionPart.toInt();
			this.fractionPart = Fraction.ZERO;
		}
		var previousRoots = new SquareRoots();
		previousRoots = previousRoots.add(rootsPart);
		previousRoots
				.getRoots()
				.stream()
				.forEach(this::filterRoots);
	}

	private void filterRoots(SquareRoot root) {
		if (root.isInt()) {
			this.integer += root.toInt();
			this.rootsPart.getRoots().remove(root);
		}
		if (root.isFraction()) {
			this.fractionPart = this.fractionPart.add(root.toFraction());
			this.rootsPart.getRoots().remove(root);
		}
	}

	public boolean isGreaterThanZero() {
		return this.toDouble() > 0; 
	}

	public SquareRoots getRootsPart() {
		return rootsPart;
	}

	public Fraction getFractionPart() {
		return fractionPart;
	}

	public int getInteger() {
		return integer;
	}
	public boolean equals(SymbolicNumber other) {
		return this.equalsNumerically(other) || this.equalsSymbolically(other);
	}

	private boolean equalsSymbolically(SymbolicNumber other) {
		return this.integer == other.getInteger() && this.fractionPart.equals(other.getFractionPart()) && this.rootsPart.equals(other.getRootsPart());
	}

	private boolean equalsNumerically(SymbolicNumber other) {
		return this.toDouble() == other.toDouble();
	}

	public void print() {
		if (this.isInt()) {
			if (this.toInt() != 0)
			System.out.print(this.toInt());
		} else {
			if (this.integer != 0) {
				System.out.print(this.integer);
			}
			if (this.fractionPart.toDouble() != 0) {
				System.out.print(" + ");
				this.fractionPart.print();
			}
			if (this.rootsPart.toDouble() != 0) {
				System.out.print(" + ");
				this.rootsPart.print();
			}
		}
	}

	public String toString() {
		final var stringBuilder = new StringBuilder();
		if (this.isInt() && this.toInt() != 0) {
			stringBuilder.append(this.toInt());
		} else {
			if (this.integer != 0) {
				stringBuilder.append(this.integer);
			} 
			if (this.fractionPart.toDouble() != 0) {
				if (this.integer != 0) {
					stringBuilder.append("+");
				} 
				stringBuilder.append(this.fractionPart.toString());
			} 
			if (this.rootsPart.toDouble() != 0) {
				if (this.integer != 0 || this.fractionPart.toDouble() != 0) {
					stringBuilder.append("+");
				} 
				stringBuilder.append(this.rootsPart.toString());
			}
		}
		return stringBuilder.toString();
	}
}
