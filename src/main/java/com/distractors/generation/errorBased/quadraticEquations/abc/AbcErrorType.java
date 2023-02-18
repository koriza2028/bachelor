package com.distractors.generation.errorBased.quadraticEquations.abc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * https://jestp.com/index.php/estp/article/view/649/586
 * The right formula: x_1,2 = (-b +/- √(b² - 4·a·c))/(2·a)
 * @author karin
 *
 */
public enum AbcErrorType {

	/**
	 * x_1,2 = (+/-b - √(b² - 4·a·c))/(2·a)
	 */
	MOVE_PLUS_MINUS,
	/**
	 * x_1,2 = (b² +/- √(b² - 4·a·c))/(2·a)
	 */
	 USE_B_QUADRAT,
	 /**
	 * x_1,2 = (b +/- √(b² - 4·a·c))/(2·a)
	 */
	 NO_MINUS_BEFORE_B,
	 /**
	 * x_1,2 = (-b +/- √(b² - 4·a·c))/c
	 */
	 DIVIDE_BY_C,
	 /**
	 * x_1,2 = (b +/- √(b² - 4·a·c)/2)/(2·a)
	 */
	 DIVIDE_DISCRIMINANT_BY_TWO,
	 /**
	 * x_1,2 = (b +/- √(b²) - √(4·a·c))/(2·a)
	 */
	 EXTRACT_ROOT_ADDITIVELY;
	 
	 private static final List<AbcErrorType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	 private static final int SIZE = VALUES.size();
	 private static final Random RANDOM = new Random();
	
	 public static AbcErrorType randomError()  {
		 return VALUES.get(RANDOM.nextInt(SIZE));
	 }
}
