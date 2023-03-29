package com.distractors.generation.quadraticEquations.errorBased.factoring;

import java.util.List;

import com.distractors.generation.general.linearEquations.LinearEquationSolutionService;
import com.distractors.generation.general.linearEquations.StandardLinearEquationFractionParameters;
import com.distractors.generation.general.linearEquations.StandardLinearEquationSquareRootParameters;
import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;
import com.distractors.generation.general.maths.SymbolicNumber;
import com.distractors.generation.general.maths.SymbolicNumberFraction;
import com.distractors.generation.general.services.GcdFindingService;
import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationSolutionService;

/**
 * x² + (a + b)·x + a·b -> (x + a)·(x + b) = 0
 * x_1 = -a
 * x_2 = -b
 */
public class FactoringSolutionService implements QuadraticEquationSolutionService {
	
	private FactorFindingService factorFindingService = new FactorFindingService();

	private LinearEquationSolutionService simpleEquationSolutionService = new LinearEquationSolutionService();

	@Override
	public QuadraticEquationCorrectSolution solveCorrectly(StandardQuadraticEquationParameters equationParameters) {
		final var aFraction = equationParameters.a();
		final var bFraction = equationParameters.b();
		final var cFraction = equationParameters.c();

		if (aFraction.isInt() && bFraction.isInt() && cFraction.isInt()) {
			final var a = aFraction.toInt();
			final var b = bFraction.toInt();
			final var c = cFraction.toInt();

			final var ac = a * c;
			
			final var factors = this.factorFindingService.findAllFactorsOf(ac);
			final var factorPair = this.findFactorPairFor(factors, b);

			final var b_1 = factorPair.b_1();
			final var b_2 = factorPair.b_2();

			final var b_1Fraction = new Fraction(b_1, 1);

			final var firstSimpleEquationParameter_1 = new Fraction(GcdFindingService.gcd(a, b_1), 1);
			final var firstSimpleEquationParameter_2 = new Fraction(GcdFindingService.gcd(b_2, c), 1);
			final var firstSimpleEquationParameters = new StandardLinearEquationFractionParameters(firstSimpleEquationParameter_1, firstSimpleEquationParameter_2);
			
			final var secondSimpleEquationParameter_1 = aFraction.divideBy(firstSimpleEquationParameter_1);
			final var secondSimpleEquationParameter_2 = b_1Fraction.divideBy(firstSimpleEquationParameter_1);
			final var secondSimpleEquationParameters = new StandardLinearEquationFractionParameters(secondSimpleEquationParameter_1, secondSimpleEquationParameter_2);

			Fraction x_1Fraction = this.simpleEquationSolutionService.solve(firstSimpleEquationParameters);
			Fraction x_2Fraction = this.simpleEquationSolutionService.solve(secondSimpleEquationParameters);

			final var x_1 = new SymbolicNumberFraction(new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(x_1Fraction).build());
			final var x_2 = new SymbolicNumberFraction(new SymbolicNumber.SymbolicNumberBuilder().withFractionPart(x_2Fraction).build());

			return new QuadraticEquationCorrectSolution(x_1, x_2);
		} else {
			throw new IllegalStateException("The Equation cannot be solved by factoring");
		}

	}

	private QuadraticEquationDistractor solveFactoringASumOfSquaresIncorrectlyWithTwoSameRoots(StandardQuadraticEquationParameters equationParameters) {
		final var simpleEquationParameter = this.factorToASimpleEquation(equationParameters);

		final var x_1SquareRoot = this.simpleEquationSolutionService.solve(simpleEquationParameter);
		final var x_1 = new SymbolicNumberFraction(new SymbolicNumber.SymbolicNumberBuilder().withRoot(x_1SquareRoot).build());
		final var x_2 = x_1;

		return new QuadraticEquationDistractor(x_1, x_2, QuadraticEquationErrorType.FACTORING_A_SUM_OF_SQUARE);
	}

	private StandardLinearEquationSquareRootParameters factorToASimpleEquation(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var c = equationParameters.c();

		final var simpleEquationParameter_1 = new SquareRoot(a, Fraction.ONE);
		final var simpleEquationParameter_2 = c.isSmallerThanZero()? new SquareRoot(c.multiplyBy(-1), Fraction.ONE).multiplyBy(-1) : new SquareRoot(c, Fraction.ONE);

		return new StandardLinearEquationSquareRootParameters(simpleEquationParameter_1, simpleEquationParameter_2);
	}

	private FactorPair findFactorPairFor(List<Integer> factors, int b) {
		for(int possibleB_1Index = 0; possibleB_1Index < factors.size(); ++possibleB_1Index){
			var possibleB_2 = b - factors.get(possibleB_1Index);
	        if(factors.contains(possibleB_2)) {
	        	final var b_1 = factors.get(possibleB_1Index);
	        	final var b_2 = possibleB_2;
	        	return new FactorPair(b_1, b_2);
	        }
	    }
		throw new RuntimeException("No factors could be found.");
	}

	@Override
	public QuadraticEquationDistractor solveWithChosenError(StandardQuadraticEquationParameters equationParameters,
			QuadraticEquationErrorType errorType) {
		return this.solveFactoringASumOfSquaresIncorrectlyWithTwoSameRoots(equationParameters);
	}
}
