package com.distractors.generation.errorBased.quadraticEquations;

import java.util.ArrayList;
import java.util.Random;

public enum QuadraticEquationSolutionApproach {

	/**
	 * x² + (a + b)·x + a·b -> (x + a)·(x + b) = 0
	 * x_1 = -a
	 * x_2 = -b
	 */
	FACTOR,
	/**
	 * x_1,2 = -(p/2) +/- √((p/2)² - q) 
	 * Only possible for equations in form x² + p·x + q = 0
	 */
	PQ,
	/**
	 * x_1,2 = (-b +/- √(b² - 4·a·c))/(2·a)
	 */
	ABC,
	/**
	 * a·x² + b·x = 0 -> x·(a·x + b) = 0
	 * Only possible for equations in form a·x² + b·x = 0
	 */
	EXCLUSION,
	/**
	 * a·x² - c = 0 |+c
	 * a·x² = c     |:a
	 * x² = c/a 	 |extract the root
	 * x_1,2 = √(c/a)
	 * Only possible for equations in form a·x² - c = 0
	 */
	ROOT_EXTRACTION,
	NO_NEGATIVE_SOLUTION_OF_ROOT,
	IGNORE_RIGHT_SIDE;;

	private static final Random RANDOM = new Random();
	
	public static QuadraticEquationSolutionApproach randomApproach(QuadraticEquationParameters quadraticEquationParameters)  {
		final var equationParameters = quadraticEquationParameters.toStandard();
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		var values = new ArrayList<QuadraticEquationSolutionApproach>();
		values.add(ABC);
		values.add(FACTOR);
		values.add(EXCLUSION);
		if (a.toDouble() != 1) {
			values.add(PQ);
		}
		if (b.toDouble() == 0) {
			values.add(ROOT_EXTRACTION);
		}
		
		return values.get(RANDOM.nextInt(values.size()));
	}
}
