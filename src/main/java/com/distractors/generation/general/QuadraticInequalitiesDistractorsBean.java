package com.distractors.generation.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.distractors.generation.basedOnResultManipulation.quadraticInequalities.QuadraticInequalityDistractorsGenerationBasedOnResultManipulationService;
import com.distractors.generation.basedOnWrongParameters.quadraticInequalities.QuadraticInequalityDistractorsGenerationBasedOnWrongParametersService;
import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationLeftSideParameters;
import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.errorBased.quadraticEquations.QuadraticEquationRightSideParameters;
import com.distractors.generation.errorBased.quadraticInequalities.InequalitySign;
import com.distractors.generation.errorBased.quadraticInequalities.QuadraticInequalityDistractorsGenerationErrorBasedService;
import com.distractors.generation.errorBased.quadraticInequalities.QuadraticInequalityParameters;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class QuadraticInequalitiesDistractorsBean {

	private int leftNominatorA;
	private int leftDenominatorA = 1;
	private int leftNominatorB;
	private int leftDenominatorB = 1;
	private int leftNominatorC;
	private int leftDenominatorC = 1;
	private int rightNominatorA;
	private int rightDenominatorA = 1;
	private int rightNominatorB;
	private int rightDenominatorB = 1;
	private int rightNominatorC;
	private int rightDenominatorC = 1;
	private List<InequalitySign> signs = InequalitySign.validValuesAsList();
	private InequalitySign chosenSign = signs.get(0);

	private List<String> distractorsErrorBased = new ArrayList<String>();
	private List<String> distractorsBasedOnWrongParameters = new ArrayList<String>();
	private List<String> distractorsBasedOnResultManipulation = new ArrayList<String>();

	public void generateDistractors() {
		final var leftSide = this.generateQuadraticInequalityLeftSideParameters();
		final var rightSide = generateQuadraticInequalityRightSideParameters();
		final var quadraticEquation = new QuadraticEquationParameters(leftSide, rightSide);
		final var quadraticInequality = new QuadraticInequalityParameters(quadraticEquation, chosenSign);
		this.collectDistractorsErrorBased(quadraticInequality);
		this.collectDistractorsBasedOnWrongParameters(quadraticInequality);
		this.collectDistractorsBasedOnResultManipulation(quadraticInequality);
	}

	private void collectDistractorsBasedOnResultManipulation(QuadraticInequalityParameters quadraticInequality) {
		final var distractorsGenerator = new QuadraticInequalityDistractorsGenerationBasedOnResultManipulationService();
		final var solution = distractorsGenerator.generateDistractors(quadraticInequality);

		distractorsBasedOnResultManipulation.add(solution.correctSolution().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_1().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_2().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsBasedOnResultManipulation);
	}

	private void collectDistractorsBasedOnWrongParameters(QuadraticInequalityParameters quadraticInequality) {
		final var distractorsGenerator = new QuadraticInequalityDistractorsGenerationBasedOnWrongParametersService();
		final var solution = distractorsGenerator.generateDistractors(quadraticInequality);
		 
		distractorsBasedOnWrongParameters.add(solution.correctSolution().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_1().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_2().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsBasedOnWrongParameters);
	}

	private void collectDistractorsErrorBased(QuadraticInequalityParameters quadraticInequality) {
		final var distractorsGenerator = new QuadraticInequalityDistractorsGenerationErrorBasedService();
		final var solution = distractorsGenerator.generateDistractors(quadraticInequality);
		 
		distractorsErrorBased.add(solution.correctSolution().toString());
		distractorsErrorBased.add(solution.distractor_1().toString());
		distractorsErrorBased.add(solution.distractor_2().toString());
		distractorsErrorBased.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsErrorBased);
	}

	private QuadraticEquationLeftSideParameters generateQuadraticInequalityLeftSideParameters() {
		final var leftA = new Fraction(leftNominatorA, leftDenominatorA);
		final var leftB = new Fraction(leftNominatorB, leftDenominatorB);
		final var leftC = new Fraction(leftNominatorC, leftDenominatorC);
		return new QuadraticEquationLeftSideParameters(leftA, leftB, leftC);
	}

	private QuadraticEquationRightSideParameters generateQuadraticInequalityRightSideParameters() {
		final var rightA = new Fraction(rightNominatorA, rightDenominatorA);
		final var rightB = new Fraction(rightNominatorB, rightDenominatorB);
		final var rightC = new Fraction(rightNominatorC, rightDenominatorC);
		return new QuadraticEquationRightSideParameters(rightA, rightB, rightC);
	}

	public int getLeftNominatorA() {
		return leftNominatorA;
	}

	public void setLeftNominatorA(int leftNominatorA) {
		this.leftNominatorA = leftNominatorA;
	}

	public int getLeftDenominatorA() {
		return leftDenominatorA;
	}

	public void setLeftDenominatorA(int leftDenominatorA) {
		this.leftDenominatorA = leftDenominatorA;
	}

	public int getLeftNominatorB() {
		return leftNominatorB;
	}

	public void setLeftNominatorB(int leftNominatorB) {
		this.leftNominatorB = leftNominatorB;
	}

	public int getLeftDenominatorB() {
		return leftDenominatorB;
	}

	public void setLeftDenominatorB(int leftDenominatorB) {
		this.leftDenominatorB = leftDenominatorB;
	}

	public int getLeftNominatorC() {
		return leftNominatorC;
	}

	public void setLeftNominatorC(int leftNominatorC) {
		this.leftNominatorC = leftNominatorC;
	}


	public int getLeftDenominatorC() {
		return leftDenominatorC;
	}

	public void setLeftDenominatorC(int leftDenominatorC) {
		this.leftDenominatorC = leftDenominatorC;
	}

	public int getRightNominatorA() {
		return rightNominatorA;
	}

	public void setRightNominatorA(int rightNominatorA) {
		this.rightNominatorA = rightNominatorA;
	}

	public int getRightDenominatorA() {
		return rightDenominatorA;
	}

	public void setRightDenominatorA(int rightDenominatorA) {
		this.rightDenominatorA = rightDenominatorA;
	}

	public int getRightNominatorB() {
		return rightNominatorB;
	}

	public void setRightNominatorB(int rightNominatorB) {
		this.rightNominatorB = rightNominatorB;
	}

	public int getRightDenominatorB() {
		return rightDenominatorB;
	}

	public void setRightDenominatorB(int rightDenominatorB) {
		this.rightDenominatorB = rightDenominatorB;
	}

	public int getRightNominatorC() {
		return rightNominatorC;
	}

	public void setRightNominatorC(int rightNominatorC) {
		this.rightNominatorC = rightNominatorC;
	}

	public int getRightDenominatorC() {
		return rightDenominatorC;
	}

	public void setRightDenominatorC(int rightDenominatorC) {
		this.rightDenominatorC = rightDenominatorC;
	}

	public List<String> getDistractorsErrorBased() {
		return distractorsErrorBased;
	}

	public List<String> getDistractorsBasedOnWrongParameters() {
		return distractorsBasedOnWrongParameters;
	}

	public List<String> getDistractorsBasedOnResultManipulation() {
		return distractorsBasedOnResultManipulation;
	}

	public InequalitySign getChosenSign() {
		return chosenSign;
	}

	public void setChosenSign(InequalitySign chosenSign) {
		this.chosenSign = chosenSign;
	}

	public List<InequalitySign> getSigns() {
		return signs;
	}
}
