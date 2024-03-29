package com.distractors.generation.general;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;
import com.distractors.generation.general.maths.SymbolicNumber;
import com.distractors.generation.general.maths.SymbolicNumberFraction;

public class SymbolicNumberFractionTest {

	@Test
	void testCannotSimpify() {
		// given
		final var nominator = new SymbolicNumber.SymbolicNumberBuilder().withInteger(4).withRoot(new SquareRoot(Fraction.TWO, Fraction.THREE)).build();
		final var denominator = new SymbolicNumber.SymbolicNumberBuilder().withInteger(2).build();
		final var expected = new SymbolicNumberFraction(nominator, denominator);
		final var actual = new SymbolicNumberFraction(nominator, denominator);

		// when
		actual.simplify();

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testCanSimplify() {
		// given
		final var nominator = new SymbolicNumber.SymbolicNumberBuilder().withInteger(4).withRoot(new SquareRoot(Fraction.TWO, Fraction.FOUR)).build();
		final var denominator = new SymbolicNumber.SymbolicNumberBuilder().withInteger(2).build();
		final var expected = new SymbolicNumberFraction(nominator, denominator);
		final var actual = new SymbolicNumberFraction(nominator, denominator);

		// when
		actual.simplify();

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testCanSimplify2() {
		// given
		final var nominator = new SymbolicNumber.SymbolicNumberBuilder().withInteger(8).withRoot(new SquareRoot(Fraction.FIVE, Fraction.TWO)).build();
		final var denominator = new SymbolicNumber.SymbolicNumberBuilder().withInteger(2).build();
		final var expected = new SymbolicNumberFraction(nominator, denominator);
		final var actual = new SymbolicNumberFraction(nominator, denominator);

		// when
		actual.simplify();

		// then
		Assertions.assertTrue(expected.equals(actual));
	}

	@Test
	void testToString() {
		// given
		final var nominator = new SymbolicNumber.SymbolicNumberBuilder().withInteger(8).withRoot(new SquareRoot(Fraction.FIVE, Fraction.TWO)).build();
		final var denominator = new SymbolicNumber.SymbolicNumberBuilder().withInteger(2).build();
		final var expected = "4+√(5)";

		// when
		final var actual = new SymbolicNumberFraction(nominator, denominator).convertToString();

		// then
		Assertions.assertTrue(expected.equals(actual));
	}
}
