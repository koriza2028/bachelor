package com.distractors.generation.errorBased.quadraticEquations.extraction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum RootExtractionErrorType {

	SOLVE_ADDITIVELY_INSTEAD_OF_MULTIPLICATIVELY;

	private static final List<RootExtractionErrorType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static RootExtractionErrorType randomError()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
