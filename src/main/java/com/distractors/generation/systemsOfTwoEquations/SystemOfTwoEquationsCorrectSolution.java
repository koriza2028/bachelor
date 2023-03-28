package com.distractors.generation.systemsOfTwoEquations;

import com.distractors.generation.general.maths.Fraction;

public record SystemOfTwoEquationsCorrectSolution(SystemOfTwoEquationsNonNumericalSolution nonNumericalSolution, Fraction x, Fraction y) implements SystemOfTwoEquationsSolution {

}
