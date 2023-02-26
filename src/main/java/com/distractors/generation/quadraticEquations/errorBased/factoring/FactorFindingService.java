package com.distractors.generation.quadraticEquations.errorBased.factoring;

import java.util.ArrayList;
import java.util.List;

public class FactorFindingService {

	public List<Integer> findAllFactorsOf(int number) {
		if (number < 0) {
			return this.findAllFactorsOfNegative(number);
		} else return this.findAllFactorsOfPositive(number);
	}

	private List<Integer> findAllFactorsOfNegative(int number) {
		final var factors = new ArrayList<Integer>();
		
		for(int possibleFactor = number; possibleFactor <= Math.abs(number); ++possibleFactor) {
			if(possibleFactor == 0) {
				continue;
			} else {
				if (number % possibleFactor == 0) {
					factors.add(possibleFactor);
		        }
			}
		}
		
		return factors;
	}

	private List<Integer> findAllFactorsOfPositive(int number) {
		final var factors = new ArrayList<Integer>();
		
		for(int possibleFactor = 1; possibleFactor <= number; ++possibleFactor) {
		    if (number % possibleFactor == 0) {
		    	factors.add(possibleFactor);
		    }
		}
		
		return factors;
	}
}
