package com.distractors.generation.systemsOfTwoEquations;

import com.distractors.generation.general.maths.Fraction;

public record SystemOfTwoEquationsDistractor(SystemOfTwoEquationsNonNumericalSolution nonNumericalSolution, Fraction x, Fraction y, SystemOfTwoEquationsSolutionImpact solutionImpact) implements SystemOfTwoEquationsSolution {

	public String convertToString() {
		return SystemOfTwoEquationsSolution.super.convertToString() + " (" + solutionImpact + ")";
	}
}
