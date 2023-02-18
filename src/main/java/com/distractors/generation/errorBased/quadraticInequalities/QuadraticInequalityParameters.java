package com.distractors.generation.errorBased.quadraticInequalities;

import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationParameters;

public record QuadraticInequalityParameters(QuadraticEquationParameters equationParameters, InequalitySign sign) {

	public StandardQuadraticInequalityParameters toStandard() {
		final var standardEquationParameters = this.equationParameters.toStandard();
		return new StandardQuadraticInequalityParameters(standardEquationParameters, sign);
	}

}
