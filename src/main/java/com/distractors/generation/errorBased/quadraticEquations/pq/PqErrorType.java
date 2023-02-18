package com.distractors.generation.errorBased.quadraticEquations.pq;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PqErrorType {

	IGNORE_NORMAL_FORM,
	/**
	* x_1,2 = (b +/- √(b²) - √(4·a·c))/(2·a)
	*/
	EXTRACT_ROOT_FROM_DISCRIMINANT_ADDITIVELY;

	private static final List<PqErrorType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public static PqErrorType randomError()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
