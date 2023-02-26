package com.distractors.generation.quadraticEquations.random;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SolutionType {

	NUMBER,
	FRACTION,
	ROOT;

	private static final List<SolutionType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static SolutionType randomType()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
