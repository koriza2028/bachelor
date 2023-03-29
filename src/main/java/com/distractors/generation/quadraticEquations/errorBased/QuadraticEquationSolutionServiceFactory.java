package com.distractors.generation.quadraticEquations.errorBased;

import com.distractors.generation.quadraticEquations.errorBased.abc.AbcSolutionService;
import com.distractors.generation.quadraticEquations.errorBased.exclusion.ExclusionSolutionService;
import com.distractors.generation.quadraticEquations.errorBased.extraction.RootExtractionSolutionService;
import com.distractors.generation.quadraticEquations.errorBased.factoring.FactoringSolutionService;
import com.distractors.generation.quadraticEquations.errorBased.pq.PqSolutionService;

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
				throw new IllegalArgumentException("Unknown solution approach.");
		}
 	}
}
