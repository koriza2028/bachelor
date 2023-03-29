package com.distractors.generation.general.maths;

import java.util.AbstractMap.SimpleEntry;

import com.distractors.generation.general.services.PrimeFactorFindingService;

import java.util.Iterator;
import java.util.Map.Entry;

public class SquareRoot {

	private PrimeFactorFindingService primeFactorFindingService = new PrimeFactorFindingService();
	
	private Fraction underTheRoot;
	private Fraction beforeTheRoot;

	public SquareRoot(Fraction underTheRoot, Fraction beforeTheRoot) {
		this.underTheRoot = checkUnderTheRootIsGreaterOrEqualsToZero(underTheRoot);
		this.beforeTheRoot = beforeTheRoot;
		this.simplify();
	}
	
	public SquareRoot(Fraction underTheRoot) {
		this.underTheRoot = checkUnderTheRootIsGreaterOrEqualsToZero(underTheRoot);
		this.beforeTheRoot = Fraction.ONE;
		this.simplify();
	}

	private Fraction checkUnderTheRootIsGreaterOrEqualsToZero(Fraction underTheRoot) {
		if (underTheRoot.isGreaterOrEqualsToZero()) {
			return underTheRoot;
		} else {
			throw new IllegalArgumentException("The under the root expression cannot be smaller than 0.");
		}
	}

	protected SquareRoot add(SquareRoot root) {
		if (this.isTheSameUnderTheRootExpression(root)) {
			final var beforeTheRootResult = this.beforeTheRoot.add(root.getBeforeTheRoot());
			return new SquareRoot(this.underTheRoot, beforeTheRootResult);
		} else {
			throw new IllegalArgumentException("Roots can only be added to each other if the under the root expression is same.");
		}
	}

	public SymbolicNumber add(Fraction fraction) {
		return new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(fraction).withRoot(this).build();
	}

	protected SquareRoot substract(SquareRoot root) {
		if (this.isTheSameUnderTheRootExpression(root)) {
			final var beforeTheRootResult = this.beforeTheRoot.substract(root.getBeforeTheRoot());
			return new SquareRoot(this.underTheRoot, beforeTheRootResult);
		} else {
			throw new IllegalArgumentException("Roots can only be substracted if the under the root expression is same.");
		}
	}

	public SymbolicNumber substract(Fraction fraction) {
		final var minusOneFraction = new Fraction(-1, 1);
		final var negativeFraction = fraction.multiplyBy(minusOneFraction);
		return new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(negativeFraction).withRoot(this).build();
	}

	public SquareRoot multiplyBy(SquareRoot root) {
		final var underTheRootResult = this.underTheRoot.multiplyBy(root.getUnderTheRoot());
		final var beforeTheRootResult = this.beforeTheRoot.multiplyBy(root.getBeforeTheRoot());

		final var rootResult = new SquareRoot(underTheRootResult, beforeTheRootResult);
		rootResult.simplify();
		return rootResult;
	}

	public SquareRoot multiplyBy(Fraction fraction) {
		final var beforeTheRootResult = this.beforeTheRoot.multiplyBy(fraction);

		final var rootResult = new SquareRoot(this.underTheRoot, beforeTheRootResult);
		rootResult.simplify();
		return rootResult;
	}

	public SquareRoot multiplyBy(int number) {
		final var beforeTheRootResult = this.beforeTheRoot.multiplyBy(number);

		final var rootResult = new SquareRoot(this.underTheRoot, beforeTheRootResult);
		rootResult.simplify();
		return rootResult;
	}

	public SquareRoot divideBy(SquareRoot root) {
		final var underTheRootResult = this.underTheRoot.divideBy(root.getUnderTheRoot());
		final var beforeTheRootResult = this.beforeTheRoot.divideBy(root.getBeforeTheRoot());

		final var rootResult = new SquareRoot(underTheRootResult, beforeTheRootResult);
		rootResult.simplify();
		return rootResult;
	}

	public SquareRoot divideBy(Fraction fraction) {
		final var beforeTheRootResult = this.beforeTheRoot.divideBy(fraction);

		final var rootResult = new SquareRoot(this.underTheRoot, beforeTheRootResult);
		rootResult.simplify();
		return rootResult;
	}

	/**
	 * This method extracts the part roots.
	 * The part roots for nominator and denominator are found separately.
	 * Example: √(8/3) = √(2*2*2)/√3 = √(2³)/√3 = 2√2/√3 = 2√(2/3)
	 */
	public void simplify() {
		if (!this.underTheRoot.equals(Fraction.ZERO)) { 
			final var nominator = this.simplifyNominator();
			final var denominator = this.simplifyDenominator();
	
			final var beforeTheRootFraction = new Fraction(nominator.getKey(), denominator.getKey());
			final var underTheRootFraction = new Fraction(nominator.getValue(), denominator.getValue());
	
			this.beforeTheRoot = beforeTheRootFraction;
			this.underTheRoot = underTheRootFraction;
		}
	}

	private SimpleEntry<Integer, Integer> simplifyNominator() {
		// the number before the root should be increased by the extracted primes
		var beforeTheRootNominator = this.beforeTheRoot.getNominator();

		// the number under the root should be built over again
		var underTheRootNominator = 1;

		final var underTheRootNominatorBefore = this.underTheRoot.getNominator();		
		final var primeFactorsOfNominator = primeFactorFindingService.getPrimeFactorsWithPower(underTheRootNominatorBefore);
		final var primesIterator = primeFactorsOfNominator.entrySet().iterator();

		return this.extractPrimes(beforeTheRootNominator, underTheRootNominator, primesIterator);
	}

	private SimpleEntry<Integer, Integer> simplifyDenominator() {
		// the number before the root should be increased by the extracted primes
		var beforeTheRootDenominator = this.beforeTheRoot.getDenominator();

		// the number under the root should be built over again
		var underTheRootDenominator = 1;

		final var underTheRootDenominatorBefore = this.underTheRoot.getDenominator();
		final var primeFactorsOfDenominator = primeFactorFindingService.getPrimeFactorsWithPower(underTheRootDenominatorBefore);
		final var primesIterator = primeFactorsOfDenominator.entrySet().iterator();

		return this.extractPrimes(beforeTheRootDenominator, underTheRootDenominator, primesIterator);
	}

	/**
	 * @return number before the root multiplied by extracted primes and number under the root after prime extraction
	 */
	private SimpleEntry<Integer, Integer> extractPrimes(int beforeTheRootNumber, int underTheRootNumber, Iterator<Entry<Integer, Integer>> primesIterator) {

		final var primeWithPower = primesIterator.next();
		final var power = primeWithPower.getValue();
		final var prime = primeWithPower.getKey();

		if (power == 1) {
			// Example √2 -> √2
			underTheRootNumber *= prime;
		} else if (power % 2 == 0) {
			// Example √(2^4) -> 2^2
			final var powerOfThePrimeBeforeTheRoot = power / 2;
			beforeTheRootNumber *= Math.pow(prime, powerOfThePrimeBeforeTheRoot);
		} else {
			// Example √(2^5) -> 2^2√2
			underTheRootNumber *= prime;
			final var powerOfThePrimeBeforeTheRoot = power / 2;
			beforeTheRootNumber *= Math.pow(prime, powerOfThePrimeBeforeTheRoot);
		}

		if (primesIterator.hasNext()) {
			return this.extractPrimes(beforeTheRootNumber, underTheRootNumber, primesIterator);
		} else {
			return new SimpleEntry<Integer, Integer> (beforeTheRootNumber, underTheRootNumber);
		}
	
	}

	public boolean isTheSameUnderTheRootExpression(SquareRoot root) {
		return root != null && this.underTheRoot.equals(root.underTheRoot);
	}

	public boolean isInt() {
		return Math.sqrt(this.underTheRoot.toDouble()) % 1 == 0 && this.beforeTheRoot.isInt();
	}

	public boolean isFraction() {
		return Math.sqrt(this.underTheRoot.toDouble()) % 1 == 0 && !this.beforeTheRoot.isInt();
	}

	public Fraction toFraction() {
		return this.beforeTheRoot.multiplyBy((int)Math.sqrt(this.underTheRoot.toDouble()));
	}

	public int toInt() {
		return this.toFraction().toInt();
	}

	public double toDouble() {
		return Math.sqrt(this.underTheRoot.toDouble()) * this.beforeTheRoot.toDouble();
	}

	public Fraction getUnderTheRoot() {
		return underTheRoot;
	}
	
	public Fraction getBeforeTheRoot() {
		return beforeTheRoot;
	}
	
	public boolean equals(SquareRoot other) {
		return this.equalsSymbolically(other) || this.equalsNumerically(other);
	}
	
	private boolean equalsNumerically(SquareRoot other) {
		return this.toDouble() == other.toDouble();
	}
	
	private boolean equalsSymbolically(SquareRoot other) {
		return this.underTheRoot.equals(other.getUnderTheRoot()) && this.beforeTheRoot.equals(other.getBeforeTheRoot());
	}

	public String convertToString() {
		final var stringBuilder = new StringBuilder();
		if (this.toDouble() != 0) {
			
			if (this.beforeTheRoot.toDouble() != 1 && this.beforeTheRoot.toDouble() != -1) {
				stringBuilder.append(this.beforeTheRoot.convertToString());
			} else if (this.beforeTheRoot.toDouble() == -1) {
				stringBuilder.append("-");
			} 
				
			if (this.underTheRoot.toDouble() != 1) {
				stringBuilder.append("√(");
				stringBuilder.append(this.underTheRoot.convertToString());
				stringBuilder.append(")");
			}
		}

		return stringBuilder.toString();
	}
}
