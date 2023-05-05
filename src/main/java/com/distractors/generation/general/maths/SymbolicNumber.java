package com.distractors.generation.general.maths;

/**
 * Represents a number symbolically imitating calculation by a human
 * Example 2 + 1/3 + √2 - √3
 */
public class SymbolicNumber {

	private SquareRoots rootsPart;
	private Fraction fractionPart;
	private int integer;

	public static final SymbolicNumber ZERO = new SymbolicNumber.SymbolicNumberBuilder()
			.withInteger(0)
			.build();

	public static final SymbolicNumber ONE = new SymbolicNumber.SymbolicNumberBuilder()
			.withInteger(1)
			.build();

	public static final SymbolicNumber MINUS_ONE = new SymbolicNumber.SymbolicNumberBuilder()
			.withInteger(-1)
			.build();

	/**
	 * The symbolic number is simplified automatically by {@link #simplify()} method
	 */
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
		final var rootsPartResult = this.rootsPart.subtract(number.rootsPart);
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

	/**
	 * Performs all possible mathematical operations symbolically
	 */
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

	/**
	 * Checks if a root can be presented in form of an integer or a fraction, and transforms the symbolic number if so
	 */
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
	
	public boolean isInt() {
		return this.toDouble() % 1 == 0;
	}
	
	public double toDouble() {
		return this.integer + this.fractionPart.toDouble() + this.rootsPart.toDouble();
	}
	
	public int toInt() {
		return (int) this.toDouble();
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

	public String convertToString() {
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
				stringBuilder.append(this.fractionPart.convertToString());
			} 
			if (this.rootsPart.toDouble() != 0) {
				if (this.integer != 0 || this.fractionPart.toDouble() != 0) {
					stringBuilder.append("+");
				} 
				stringBuilder.append(this.rootsPart.convertToString());
			}
		}
		return stringBuilder.toString();
	}

	public static class SymbolicNumberBuilder {
		private SquareRoots rootsPart = new SquareRoots();
		private Fraction fractionPart = Fraction.ZERO;
		private int integer = 0;
		
		public SymbolicNumberBuilder() {
		}
		
		public SymbolicNumberBuilder withFractionPart(Fraction fractionPart) {
			this.fractionPart = fractionPart;
			return this;
		}
		
		public SymbolicNumberBuilder withRoots(SquareRoots roots) {
			this.rootsPart = this.rootsPart.add(roots);
			return this;
		}
		
		public SymbolicNumberBuilder withRoot(SquareRoot root) {
			this.rootsPart = this.rootsPart.add(root);
			return this;
		}
		
		public SymbolicNumberBuilder withInteger(int integer) {
			this.integer = integer;
			return this;
		}
		
		public SymbolicNumber build() {
			return new SymbolicNumber(this);
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
		
	}
}
