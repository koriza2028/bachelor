package com.distractors.generation.quadraticInequalities;

import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;

public record QuadraticInequalityParameters(QuadraticEquationParameters equationParameters, InequalitySign sign) {

	public StandardQuadraticInequalityParameters toStandard() {
		final var standardEquationParameters = this.equationParameters.toStandard();
		return new StandardQuadraticInequalityParameters(standardEquationParameters, sign);
	}

}
