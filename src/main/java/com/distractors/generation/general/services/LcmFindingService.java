package com.distractors.generation.general.services;

public class LcmFindingService {

	public static int lcm(int number_1, int number_2) {
	    if (number_1 == 0 || number_2 == 0) {
	        return 0;
	    }
	    int absNumber_1 = Math.abs(number_1);
	    int absNumber_2 = Math.abs(number_2);
	    int absHigherNumber = Math.max(absNumber_1, absNumber_2);
	    int absLowerNumber = Math.min(absNumber_1, absNumber_2);
	    int lcm = absHigherNumber;
	    while (lcm % absLowerNumber != 0) {
	        lcm += absHigherNumber;
	    }
	    return lcm;
	}
}
