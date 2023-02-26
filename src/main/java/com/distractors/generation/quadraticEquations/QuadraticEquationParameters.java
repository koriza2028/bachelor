package com.distractors.generation.quadraticEquations;

public record QuadraticEquationParameters(QuadraticEquationLeftSideParameters leftSide, QuadraticEquationRightSideParameters rightSide) {

	public StandardQuadraticEquationParameters toStandard() {
		final var a = leftSide.a().substract(rightSide.a());
		final var b = leftSide.b().substract(rightSide.b());
		final var c = leftSide.c().substract(rightSide.c());

		return new StandardQuadraticEquationParameters(a, b, c);
	}

	public StandardQuadraticEquationParameters toStandardIgnoringRightSide() {
		final var a = leftSide.a();
		final var b = leftSide.b();
		final var c = leftSide.c();

		return new StandardQuadraticEquationParameters(a, b, c);
	}
}
