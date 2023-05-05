package com.distractors.generation.general.maths;

import java.util.LinkedHashMap;

public class PrimeFactorFindingService {

	/**
	 * Decomposes an integer value into prime factors
	 * @param x integer value
	 * @return pairs of decomposed primes with corresponding power
	 * Example: 24 = 3*(2)^3 -> (2, 3), (3, 1)
	 */
	public LinkedHashMap<Integer, Integer> getPrimeFactorsWithPower(int x) {
		LinkedHashMap<Integer, Integer> primeFactorsWithPowerValue = new LinkedHashMap<Integer, Integer>();

		// primes start with 2
		for (var prime = 2; prime <= x && x != 1; prime++) {
	        int power = 0;

	        // find out how many times the number can be divided by the prime
	        while( x % prime == 0) {
	            power++;
	            x /= prime;
	        }

	        if (power > 0) {
	        	primeFactorsWithPowerValue.put(prime, power);
	        }

	    }

	    return primeFactorsWithPowerValue; 
	}
}
