package com.distractors.generation.errorBased.quadraticEquations;

import com.distractors.generation.errorBased.quadraticEquations.abc.AbcSolutionService;
import com.distractors.generation.errorBased.quadraticEquations.exclusion.ExclusionSolutionService;
import com.distractors.generation.errorBased.quadraticEquations.extraction.RootExtractionSolutionService;
import com.distractors.generation.errorBased.quadraticEquations.factoring.FactoringSolutionService;
import com.distractors.generation.errorBased.quadraticEquations.pq.PqSolutionService;

public class QuadraticEquationSolutionServiceFactory {

	private AbcSolutionService abc = new AbcSolutionService();

	private ExclusionSolutionService exclusion = new ExclusionSolutionService();

	private FactoringSolutionService factoring = new FactoringSolutionService();

	private PqSolutionService pq = new PqSolutionService();

	private RootExtractionSolutionService rootExtraction = new RootExtractionSolutionService();

	public QuadraticEquationSolutionService getService(QuadraticEquationSolutionApproach approach) {
		switch (approach) {
			case ABC:
				return this.abc;
			case EXCLUSION:
				return this.exclusion;
			case FACTOR:
				return this.factoring;
			case PQ:
				return this.pq;
			case ROOT_EXTRACTION:
				return this.rootExtraction;
			default:
				throw new IllegalArgumentException("Unknown error type");
		}
 	}
}
