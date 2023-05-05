package com.distractors.generation.quadraticEquations;

import com.distractors.generation.general.maths.SymbolicNumberFraction;

public record QuadraticEquationDistractor(SymbolicNumberFraction x_1, SymbolicNumberFraction x_2, QuadraticEquationSolutionImpact impact) implements QuadraticEquationSolution {

	public String convertToString() {
		return QuadraticEquationSolution.super.convertToString() + "(" + impact + ")";
	}

}
