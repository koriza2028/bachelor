package com.distractors.generation.quadraticEquations.errorBased.abc;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.general.maths.SquareRoot;
import com.distractors.generation.general.maths.SquareRoots;
import com.distractors.generation.quadraticEquations.QuadraticEquationRoots;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationErrorType;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationSolutionService;

public class AbcSolutionService implements QuadraticEquationSolutionService {

	@Override
	public QuadraticEquationRoots solveCorrectly(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();

		final var discriminant = this.findDiscriminantCorrectly(equationParameters);
		if (discriminant.isGreaterOrEqualsToZero()) {
			try {
				final var rootOfDiscriminant = new SquareRoot(discriminant, new Fraction(1, 1));
				final var x_1 = (b.multiplyBy(-1).add(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
				final var x_2 = (b.multiplyBy(-1).substract(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
				return QuadraticEquationRoots.createCorrectSolution(x_1, x_2);
			} catch (IllegalArgumentException e) {
				return QuadraticEquationRoots.createCorrectSolution(null, null);
			}
		} else {
			return QuadraticEquationRoots.createCorrectSolution(null, null);
		}
	}

	@Override
	public QuadraticEquationRoots solveWithChosenError(StandardQuadraticEquationParameters equationParameters, final QuadraticEquationErrorType error) {
		switch (error) {
			case DIVIDE_BY_C:
				return this.solveDividingByC(equationParameters);
			case DIVIDE_DISCRIMINANT_BY_TWO:
				return this.solveWithWrongDiscriminant(equationParameters);
			case MOVE_PLUS_MINUS:
				return this.solveMovingPlusMinus(equationParameters);
			case NO_MINUS_BEFORE_B:
				return this.solveWithNoMinusBeforeB(equationParameters);
			case USE_B_QUADRAT:
				return this.solveWithBQuadrat(equationParameters);
			case EXTRACT_ROOT_ADDITIVELY_ABC:
				return this.solveExtractingRootAdditively(equationParameters);
			default:
				throw new IllegalArgumentException("Unknown error type.");
		}
	}

	private QuadraticEquationRoots solveExtractingRootAdditively(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();

		final var rootOfDiscriminant = this.extractRootOfDiscriminantAdditively(equationParameters);
		final var x_1 = (b.multiplyBy(-1).add(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		final var x_2 = (b.multiplyBy(-1).substract(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	private QuadraticEquationRoots solveDividingByC(StandardQuadraticEquationParameters equationParameters) {
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		final var discriminant = this.findDiscriminantCorrectly(equationParameters);
		final var rootOfDiscriminant = new SquareRoot(discriminant, Fraction.ONE);
		final var x_1 = (b.multiplyBy(-1).add(rootOfDiscriminant)).divideBy(c);
		final var x_2 = (b.multiplyBy(-1).substract(rootOfDiscriminant)).divideBy(c);
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	private QuadraticEquationRoots solveWithWrongDiscriminant(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();

		final var discriminant = this.findDiscriminantIncorrectly(equationParameters);
		final var rootOfDiscriminant = new SquareRoot(discriminant, Fraction.ONE);
		final var x_1 = (b.multiplyBy(-1).add(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		final var x_2 = (b.multiplyBy(-1).substract(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	} 

	private QuadraticEquationRoots solveMovingPlusMinus(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();

		final var discriminant = this.findDiscriminantCorrectly(equationParameters);
		final var rootOfDiscriminant = new SquareRoot(discriminant, Fraction.ONE);
		final var x_1 = (b.multiplyBy(-1).substract(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		final var x_2 = (b.substract(rootOfDiscriminant)).divideBy(a.multiplyBy(2));;
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	private QuadraticEquationRoots solveWithNoMinusBeforeB(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();

		final var discriminant = this.findDiscriminantCorrectly(equationParameters);
		final var rootOfDiscriminant = new SquareRoot(discriminant);
		final var x_1 = (b.add(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		final var x_2 = (b.substract(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	private QuadraticEquationRoots solveWithBQuadrat(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();

		final var discriminant = this.findDiscriminantCorrectly(equationParameters);
		final var rootOfDiscriminant = new SquareRoot(discriminant);
		final var x_1 = (b.multiplyBy(b).add(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		final var x_2 = (b.multiplyBy(b).substract(rootOfDiscriminant)).divideBy(a.multiplyBy(2));
		return QuadraticEquationRoots.createDistractor(x_1, x_2);
	}

	private SquareRoots extractRootOfDiscriminantAdditively(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();
		
		var rootOfDiscriminant = new SquareRoots();
		final var rootOfBQuadrat = new SquareRoot(b.multiplyBy(b));
		final var ac = a.multiplyBy(c);
		
		final var rootOfFourAC = ac.isGreaterOrEqualsToZero() ? new SquareRoot(a.multiplyBy(c).multiplyBy(4)).multiplyBy(-1) : new SquareRoot(a.multiplyBy(c).multiplyBy(4).multiplyBy(-1));
		rootOfDiscriminant = rootOfDiscriminant.add(rootOfBQuadrat);
		rootOfDiscriminant = rootOfDiscriminant.add(rootOfFourAC);

		return rootOfDiscriminant;
	}
	
	private Fraction findDiscriminantCorrectly(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		return b.multiplyBy(b).substract(a.multiplyBy(c).multiplyBy(Fraction.FOUR));
	}

	private Fraction findDiscriminantIncorrectly(StandardQuadraticEquationParameters equationParameters) {
		final var a = equationParameters.a();
		final var b = equationParameters.b();
		final var c = equationParameters.c();

		return b.multiplyBy(b).substract(a.multiplyBy(c).multiplyBy(4)).divideBy(Fraction.TWO);
	}
}
