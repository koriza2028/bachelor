package com.distractors.generation.general.maths;

import com.distractors.generation.general.services.GcdFindingService;

public class Fraction {

	private int nominator;
	private int denominator;

	public static Fraction ZERO = new Fraction(0, 1);
	public static Fraction ONE = new Fraction(1, 1);
	public static Fraction TWO = new Fraction(2, 1);
	public static Fraction THREE = new Fraction(3, 1);
	public static Fraction FOUR = new Fraction(4, 1);
	public static Fraction FIVE = new Fraction(5, 1);

	public Fraction(int nominator, int denominator) {
		this.nominator = nominator;
		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot be equal to 0.");
		} else {
			this.denominator = denominator;
		}
		this.reduce();
	}

	public Fraction add(Fraction number) {
		final var resultNominator = this.nominator * number.denominator + number.nominator * this.denominator;
		final var resultDenominator = this.denominator * number.denominator;
		final var resultFraction = new Fraction(resultNominator, resultDenominator);
		resultFraction.reduce();
		return resultFraction;
	}

	public SymbolicNumber add(SquareRoot root) {
		final var result = new SymbolicNumberBuilder().withFractionPart(this).withRoot(root).build();
		result.simplify();
		return result;
	}

	public SymbolicNumber add(SquareRoots roots) {
		final var result = new SymbolicNumberBuilder().withFractionPart(this).withRoots(roots).build();
		result.simplify();
		return result;
	}

	public Fraction substract(Fraction number) {
		final var resultNominator = this.nominator * number.denominator - number.nominator * this.denominator;
		final var resultDenominator = this.denominator * number.denominator;
		final var resultFraction = new Fraction(resultNominator, resultDenominator);
		resultFraction.reduce();
		return resultFraction;
	}

	public SymbolicNumber substract(SquareRoot root) {
		final var result = new SymbolicNumberBuilder().withFractionPart(this).withRoot(root.multiplyBy(-1)).build();
		result.simplify();
		return result;
	}

	public SymbolicNumber substract(SquareRoots roots) {
		final var result = new SymbolicNumberBuilder().withFractionPart(this).withRoots(roots.multiplyBy(-1)).build();
		result.simplify();
		return result;
	}

	public Fraction multiplyBy(Fraction number) {
		final var resultNominator = this.nominator * number.nominator;
		final var resultDenominator = this.denominator * number.denominator;
		final var resultFraction = new Fraction(resultNominator, resultDenominator);
		resultFraction.reduce();
		return resultFraction;
	}

	public Fraction multiplyBy(int number) {
		final var resultNominator = this.nominator * number;
		final var resultFraction = new Fraction(resultNominator, this.denominator);
		resultFraction.reduce();
		return resultFraction;
	}

	public Fraction divideBy(Fraction number) {
		final var resultNominator = this.nominator * number.denominator;
		final var resultDenominator = this.denominator * number.nominator;
		final var resultFraction = new Fraction(resultNominator, resultDenominator);
		resultFraction.reduce();
		return resultFraction;
	}

	public void reduce() {
		final var gcd = GcdFindingService.gcd(nominator, denominator);
		this.nominator = this.nominator / gcd;
		this.denominator = this.denominator / gcd;
	}

	public boolean isGreaterOrEqualsToZero() {
		return this.toDouble() >= 0;
	}

	public boolean isSmallerThanZero() {
		return this.toDouble() < 0;
	}

	public double toDouble() {
		return (double) this.nominator / (double) this.denominator;
	}

	public boolean isInt() {
		return this.toDouble() % 1 == 0;
	}

	public int toInt() {
		return (int) this.toDouble();
	}

	public int getNominator() {
		return nominator;
	}

	public int getDenominator() {
		return denominator;
	}

	public boolean equals(Fraction other) {
		return this.equalsNumerically(other) || this.equalsSymbolically(other);
	}

	private boolean equalsSymbolically(Fraction other) {
		final var hui = this.nominator == other.getNominator();
		final var ugdcgd = this.denominator == this.getDenominator();
		return (this.nominator == other.nominator) && (this.denominator == other.nominator);
	}

	private boolean equalsNumerically(Fraction other) {
		return this.toDouble() == other.toDouble();
	}

	public void print() {
		if (this.denominator != 1 || this.denominator != -1) {
			System.out.print(this.nominator + "/" + this.denominator + " ");
		} else {
			System.out.print(this.toInt());
		}
	}

	public String toString() {
		final var stringBuilder = new StringBuilder();
		if (this.denominator != 1 && this.denominator != -1) {
			stringBuilder.append(this.nominator);
			stringBuilder.append("/");
			stringBuilder.append(this.denominator);
		} else {
			stringBuilder.append(this.toInt());
		}
		return stringBuilder.toString();
	}

	public Fraction reverse() {
		final var reverseNominator = this.denominator;
		final var reverseDenominator = this.nominator;
		return new Fraction(reverseNominator, reverseDenominator);
	}
}
