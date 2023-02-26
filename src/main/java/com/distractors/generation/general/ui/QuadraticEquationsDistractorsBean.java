package com.distractors.generation.general.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.distractors.generation.general.maths.Fraction;
import com.distractors.generation.quadraticEquations.QuadraticEquationLeftSideParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationParameters;
import com.distractors.generation.quadraticEquations.QuadraticEquationRightSideParameters;
import com.distractors.generation.quadraticEquations.basedOnResultManipulation.QuadraticEquationDistractorsGenerationBasedOnResultManipulationService;
import com.distractors.generation.quadraticEquations.basedOnWrongParameters.QuadraticEquationDistractorsGenerationBasedOnWrongParametersService;
import com.distractors.generation.quadraticEquations.errorBased.QuadraticEquationDistractorsGenerationErrorBasedService;
import com.distractors.generation.quadraticEquations.random.QuadraticEquationRandomDistractorsGenerationService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class QuadraticEquationsDistractorsBean {

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

	private List<String> distractorsErrorBased = new ArrayList<String>();
	private List<String> distractorsBasedOnWrongParameters = new ArrayList<String>();
	private List<String> distractorsBasedOnResultManipulation = new ArrayList<String>();
	private List<String> randomDistractors = new ArrayList<String>();

	public void generateDistractors() {
		final var leftSide = this.generateQuadraticEquationLeftSideParameters();
		final var rightSide = generateQuadraticEquationRightSideParameters();
		final var quadraticEquation = new QuadraticEquationParameters(leftSide, rightSide);
		this.collectDistractorsErrorBased(quadraticEquation);
		this.collectDistractorsBasedOnWrongParameters(quadraticEquation);
		this.collectDistractorsBasedOnResultManipulation(quadraticEquation);
		this.collectRandomDistractors(quadraticEquation);
	}

	private void collectDistractorsBasedOnResultManipulation(QuadraticEquationParameters quadraticEquation) {
		final var distractorsGenerator = new QuadraticEquationDistractorsGenerationBasedOnResultManipulationService();
		final var solution = distractorsGenerator.generateDistractors(quadraticEquation);

		distractorsBasedOnResultManipulation.add(solution.correctSolution().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_1().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_2().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsBasedOnResultManipulation);
	}

	private void collectDistractorsBasedOnWrongParameters(QuadraticEquationParameters quadraticEquation) {
		final var distractorsGenerator = new QuadraticEquationDistractorsGenerationBasedOnWrongParametersService();
		final var solution = distractorsGenerator.generateDistractors(quadraticEquation);
		 
		distractorsBasedOnWrongParameters.add(solution.correctSolution().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_1().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_2().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsBasedOnWrongParameters);
	}

	private void collectDistractorsErrorBased(QuadraticEquationParameters quadraticEquation) {
		final var distractorsGenerator = new QuadraticEquationDistractorsGenerationErrorBasedService();
		final var solution = distractorsGenerator.generateDistractors(quadraticEquation);
		 
		distractorsErrorBased.add(solution.correctSolution().toString());
		distractorsErrorBased.add(solution.distractor_1().toString());
		distractorsErrorBased.add(solution.distractor_2().toString());
		distractorsErrorBased.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsErrorBased);
	}

	private void collectRandomDistractors(QuadraticEquationParameters quadraticEquation) {
		final var distractorsGenerator = new QuadraticEquationRandomDistractorsGenerationService();
		final var solution = distractorsGenerator.generateDistractors(quadraticEquation);
		 
		randomDistractors.add(solution.correctSolution().toString());
		randomDistractors.add(solution.distractor_1().toString());
		randomDistractors.add(solution.distractor_2().toString());
		randomDistractors.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsErrorBased);
	}

	private QuadraticEquationLeftSideParameters generateQuadraticEquationLeftSideParameters() {
		final var leftA = new Fraction(leftNominatorA, leftDenominatorA);
		final var leftB = new Fraction(leftNominatorB, leftDenominatorB);
		final var leftC = new Fraction(leftNominatorC, leftDenominatorC);
		return new QuadraticEquationLeftSideParameters(leftA, leftB, leftC);
	}

	private QuadraticEquationRightSideParameters generateQuadraticEquationRightSideParameters() {
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

	public List<String> getRandomDistractors() {
		return randomDistractors;
	}

}
