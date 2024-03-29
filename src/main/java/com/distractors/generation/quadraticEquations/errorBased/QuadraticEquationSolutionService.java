package com.distractors.generation.quadraticEquations.errorBased;

import com.distractors.generation.quadraticEquations.QuadraticEquationCorrectSolution;
import com.distractors.generation.quadraticEquations.QuadraticEquationDistractor;
import com.distractors.generation.quadraticEquations.StandardQuadraticEquationParameters;

public interface QuadraticEquationSolutionService {

	public QuadraticEquationCorrectSolution solveCorrectly(StandardQuadraticEquationParameters equationParameters);
	public QuadraticEquationDistractor solveWithChosenError(StandardQuadraticEquationParameters equationParameters, QuadraticEquationErrorType errorType);

}
