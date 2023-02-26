package com.distractors.generation.general.maths;

public class SymbolicNumberBuilder {
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
