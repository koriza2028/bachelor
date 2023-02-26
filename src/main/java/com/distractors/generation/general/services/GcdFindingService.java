package com.distractors.generation.general.services;

public class GcdFindingService {

	public static int gcd(int number_1, int number_2) {
        if (number_1 == 0)
            return number_2;
 
        return gcd(number_2 % number_1, number_1);
    }
}
