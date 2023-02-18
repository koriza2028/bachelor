package com.distractors.generation.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.distractors.generation.basedOnResultManipulation.systemOfEquations.SystemOfEquationsDistractorsGenerationBasedOnResultManipulationService;
import com.distractors.generation.basedOnWrongParameters.systemOfEquations.SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService;
import com.distractors.generation.errorBased.systemOfLinearEquations.StandardLinearEquationParameters;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquations;
import com.distractors.generation.errorBased.systemOfLinearEquations.SystemOfEquationsDistractorsGenerationErrorBasedService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class SystemOfEquationsDistractorsBean {

	private int x_1;
	private int y_1;
	private int free_1;

	private int x_2;
	private int y_2;
	private int free_2;

	private List<String> distractorsErrorBased = new ArrayList<String>();
	private List<String> distractorsBasedOnWrongParameters = new ArrayList<String>();
	private List<String> distractorsBasedOnResultManipulation = new ArrayList<String>();

	public void generateDistractors() {
		final var equation_1 = this.generateEquation_1();
		final var equation_2 = this.generateEquation_2();
		final var systemOfEquations = new SystemOfEquations(equation_1, equation_2);
		this.collectDistractorsErrorBased(systemOfEquations);
		this.collectDistractorsBasedOnWrongParameters(systemOfEquations);
		this.collectDistractorsBasedOnResultManipulation(systemOfEquations);
	}

	private void collectDistractorsBasedOnResultManipulation(SystemOfEquations SystemOfEquations) {
		final var distractorsGenerator = new SystemOfEquationsDistractorsGenerationBasedOnResultManipulationService();
		final var solution = distractorsGenerator.generateDistractors(SystemOfEquations);

		distractorsBasedOnResultManipulation.add(solution.correctSolution().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_1().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_2().toString());
		distractorsBasedOnResultManipulation.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsBasedOnResultManipulation);
	}

	private void collectDistractorsBasedOnWrongParameters(SystemOfEquations systemOfEquations) {
		final var distractorsGenerator = new SystemOfEquationsDistractorsGenerationBasedOnWrongParametersService();
		final var solution = distractorsGenerator.generateDistractors(systemOfEquations);
		 
		distractorsBasedOnWrongParameters.add(solution.correctSolution().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_1().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_2().toString());
		distractorsBasedOnWrongParameters.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsBasedOnWrongParameters);
	}

	private void collectDistractorsErrorBased(SystemOfEquations systemOfEquations) {
		final var distractorsGenerator = new SystemOfEquationsDistractorsGenerationErrorBasedService();
		final var solution = distractorsGenerator.generateDistractors(systemOfEquations);
		 
		distractorsErrorBased.add(solution.correctSolution().toString());
		distractorsErrorBased.add(solution.distractor_1().toString());
		distractorsErrorBased.add(solution.distractor_2().toString());
		distractorsErrorBased.add(solution.distractor_3().toString());
		Collections.shuffle(distractorsErrorBased);
	}

	private StandardLinearEquationParameters generateEquation_1() {
		return new StandardLinearEquationParameters(x_1, y_1, free_1);
	}

	private StandardLinearEquationParameters generateEquation_2() {
		return new StandardLinearEquationParameters(x_2, y_2, free_2);
	}	

	public int getX_1() {
		return x_1;
	}

	public void setX_1(int x_1) {
		this.x_1 = x_1;
	}

	public int getY_1() {
		return y_1;
	}

	public void setY_1(int y_1) {
		this.y_1 = y_1;
	}

	public int getFree_1() {
		return free_1;
	}

	public void setFree_1(int free_1) {
		this.free_1 = free_1;
	}

	public int getX_2() {
		return x_2;
	}

	public void setX_2(int x_2) {
		this.x_2 = x_2;
	}

	public int getY_2() {
		return y_2;
	}

	public void setY_2(int y_2) {
		this.y_2 = y_2;
	}

	public int getFree_2() {
		return free_2;
	}

	public void setFree_2(int free_2) {
		this.free_2 = free_2;
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
}
