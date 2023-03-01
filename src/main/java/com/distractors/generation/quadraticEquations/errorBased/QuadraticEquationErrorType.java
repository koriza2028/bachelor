package com.distractors.generation.quadraticEquations.errorBased;

import java.util.ArrayList;
import java.util.Random;

import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticSolutionImpact;

public enum QuadraticEquationErrorType implements QuadraticSolutionImpact {

	NO_NEGATIVE_SOLUTION_OF_ROOT(QuadraticEquationSolutionApproach.ABC),
	IGNORE_RIGHT_SIDE(QuadraticEquationSolutionApproach.ABC),

	/**
	 * x_1,2 = (+/-b - √(b² - 4·a·c))/(2·a)
	 */
	MOVE_PLUS_MINUS(QuadraticEquationSolutionApproach.ABC),
	/**
	 * x_1,2 = (b² +/- √(b² - 4·a·c))/(2·a)
	 */
	USE_B_QUADRAT(QuadraticEquationSolutionApproach.ABC),
	 /**
	 * x_1,2 = (b +/- √(b² - 4·a·c))/(2·a)
	 */
	NO_MINUS_BEFORE_B(QuadraticEquationSolutionApproach.ABC),
	 /**
	 * x_1,2 = (-b +/- √(b² - 4·a·c))/c
	 */
	DIVIDE_BY_C(QuadraticEquationSolutionApproach.ABC),
	 /**
	 * x_1,2 = (-b +/- √(b² - 4·a·c)/2)/(2·a)
	 */
	DIVIDE_DISCRIMINANT_BY_TWO(QuadraticEquationSolutionApproach.ABC),
	 /**
	 * x_1,2 = (-b +/- √(b²) - √(4·a·c))/(2·a)
	 */
	EXTRACT_ROOT_ADDITIVELY_ABC(QuadraticEquationSolutionApproach.ABC),
	
	 
	IGNORE_C_NOT_ZERO(QuadraticEquationSolutionApproach.EXCLUSION),
	WRONG_SIMPLE_EQUATION_SOLUTION(QuadraticEquationSolutionApproach.EXCLUSION),

	SOLVE_ADDITIVELY_INSTEAD_OF_MULTIPLICATIVELY(QuadraticEquationSolutionApproach.ROOT_EXTRACTION),

	IGNORE_NORMAL_FORM(QuadraticEquationSolutionApproach.PQ),
	/**
	* x_1,2 = (b +/- √(b²) - √(4·a·c))/(2·a)
	*/
	EXTRACT_ROOT_ADDITIVELY_PQ(QuadraticEquationSolutionApproach.PQ),
	
	FACTORING_A_SUM_OF_SQUARE(QuadraticEquationSolutionApproach.FACTOR);

	QuadraticEquationSolutionApproach approach;

	QuadraticEquationErrorType(QuadraticEquationSolutionApproach approach) {
		this.approach = approach;
	}

	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationErrorType randomErrorType(QuadraticEquationParameters equationParameters)  {
		final var rightSide = equationParameters.rightSide();
		final var rightSideA = rightSide.a();
		final var rightSideB = rightSide.b();
		final var rightSideC = rightSide.c();
		final var standardQuadraticEquationParameters = equationParameters.toStandard();
		final var c = standardQuadraticEquationParameters.c();
		final var b = standardQuadraticEquationParameters.b();
		final var a = standardQuadraticEquationParameters.a();

		final var possibleErrorTypes = new ArrayList<QuadraticEquationErrorType>();
		possibleErrorTypes.add(NO_NEGATIVE_SOLUTION_OF_ROOT);
		possibleErrorTypes.add(MOVE_PLUS_MINUS);
		possibleErrorTypes.add(USE_B_QUADRAT);
		possibleErrorTypes.add(NO_MINUS_BEFORE_B);
		possibleErrorTypes.add(DIVIDE_BY_C);
		possibleErrorTypes.add(DIVIDE_DISCRIMINANT_BY_TWO);
		possibleErrorTypes.add(EXTRACT_ROOT_ADDITIVELY_ABC);
		possibleErrorTypes.add(FACTORING_A_SUM_OF_SQUARE);

		if (rightSideA.toDouble() != 0 || rightSideB.toDouble() != 0 || rightSideC.toDouble() != 0) {
			possibleErrorTypes.add(IGNORE_RIGHT_SIDE);
		}
		if (c.toDouble() != 0) {
			possibleErrorTypes.add(IGNORE_C_NOT_ZERO);
		} else {
			possibleErrorTypes.add(WRONG_SIMPLE_EQUATION_SOLUTION);
		}
		if (b.toDouble() == 0) {
			possibleErrorTypes.add(SOLVE_ADDITIVELY_INSTEAD_OF_MULTIPLICATIVELY);
		}
		if (a.toDouble() != 1) {
			possibleErrorTypes.add(IGNORE_NORMAL_FORM);
		} else {
			possibleErrorTypes.add(EXTRACT_ROOT_ADDITIVELY_PQ);
		}

		final var size = possibleErrorTypes.size();
		return possibleErrorTypes.get(RANDOM.nextInt(size));
	}
}
