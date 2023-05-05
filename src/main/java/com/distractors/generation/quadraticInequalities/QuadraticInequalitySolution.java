	package com.distractors.generation.quadraticInequalities;

import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationSolutionImpact;

public interface QuadraticInequalitySolution {
	
	QuadraticInequalityNonNumericalSolution nonNumericalSolution();
	QuadraticInequalityRange range_1();
	QuadraticInequalityRange range_2();

	public default boolean equals(QuadraticInequalitySolution other) {
		return (this.x_1Equals(other.range_1().x()) && this.x_2Equals(other.range_2().x())) &&
				this.range_1().sign().equals(other.range_1().sign()) && this.range_2().sign().equals(other.range_2().sign()) ||
				(nonNumericalSolution().equals(QuadraticInequalityNonNumericalSolution.EMPTY_SET) && other.nonNumericalSolution().equals(QuadraticInequalityNonNumericalSolution.EMPTY_SET)) ||
				(nonNumericalSolution().equals(QuadraticInequalityNonNumericalSolution.R) && other.nonNumericalSolution().equals(QuadraticInequalityNonNumericalSolution.R));
	}

	private boolean x_1Equals(SymbolicNumberFraction other) {
		if (this.range_1().x() == null) {
			return other == null;
		} else if (this.range_1().x() != null && other != null) {
			return this.range_1().x().equals(other);
		} else return false;
	}

	private boolean x_2Equals(SymbolicNumberFraction other) {
		if (this.range_2().x() == null) {
			return other == null;
		} else if (this.range_2().x() != null && other != null) {
			return this.range_2().x().equals(other);
		} else return false;
	}

	public default String convertToString() {
		final var stringBuilder = new StringBuilder();
		switch (this.nonNumericalSolution()) {
			case EMPTY_SET:
				stringBuilder.append("Φ");
				break;
			case NORMAL:
				if (this.range_1().sign().equals(InequalitySign.GREATER)) {
					stringBuilder.append("(" + this.range_1().x().convertToString() + "; " + this.range_2().x().convertToString() + ")");
				}
				if (this.range_1().sign().equals(InequalitySign.GREATER_OR_EQUALS)) {
					stringBuilder.append("[" + this.range_1().x().convertToString() + "; " + this.range_2().x().convertToString() + "]");
				}
				if (this.range_1().sign().equals(InequalitySign.LESS)) {
					stringBuilder.append("(-∞; " + this.range_1().x().convertToString() + "); (" + this.range_2().x().convertToString() + ";+∞)");
				}
				if (this.range_1().sign().equals(InequalitySign.LESS_OR_EQUALS)) {
					stringBuilder.append("(-∞; " + this.range_1().x().convertToString() + "]; [" + this.range_2().x().convertToString() + ";+∞)");
				}
				break;
			case ONLY_ZERO:
				stringBuilder.append(this.range_1().x().convertToString());
				break;
			case R:
				stringBuilder.append("R");
				break;
			case R_EXCEPT_ZERO:
				stringBuilder.append("R/{" + this.range_1().x().convertToString() + "}");
				break;
		}
		return stringBuilder.toString();
	}

	public static QuadraticInequalitySolution createEmptySetSolution(boolean correct, QuadraticEquationSolutionImpact solutionImpact) {
		return correct ? QuadraticInequalityCorrectSolution.createEmptySetCorrectSolution() : QuadraticInequalityDistractor.createEmptySetDistractor(solutionImpact);
	}

	public static QuadraticInequalitySolution createRSolution(boolean correct, QuadraticEquationSolutionImpact solutionImpact) {
		return correct ? QuadraticInequalityCorrectSolution.createRCorrectSolution() : QuadraticInequalityDistractor.createRDistractor(solutionImpact);
	}

	public  static QuadraticInequalitySolution createRExceptZeroSolution(SymbolicNumberFraction root, boolean correct, QuadraticEquationSolutionImpact solutionImpact) {
		return correct ? QuadraticInequalityCorrectSolution.createRExceptZeroCorrectSolution(root) : QuadraticInequalityDistractor.createRExceptZeroDistractor(root, solutionImpact);
	}

	public static QuadraticInequalitySolution createOnlyZeroSolution(SymbolicNumberFraction root, boolean correct, QuadraticEquationSolutionImpact solutionImpact) {
		return correct ? QuadraticInequalityCorrectSolution.createOnlyZeroCorrectSolution(root) : QuadraticInequalityDistractor.createOnlyZeroDistractor(root, solutionImpact);
	}

	public static QuadraticInequalitySolution createNormalSolution(final QuadraticInequalityRange range_1, final QuadraticInequalityRange range_2, boolean correct, QuadraticEquationSolutionImpact solutionImpact) {
		return correct ? 
				new QuadraticInequalityCorrectSolution(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2) : 
				new QuadraticInequalityDistractor(QuadraticInequalityNonNumericalSolution.NORMAL, range_1, range_2, solutionImpact);
	}

}
