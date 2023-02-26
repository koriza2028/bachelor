package com.distractors.generation.general.services;

import java.util.LinkedHashMap;

public class PrimeFactorFindingService {

	public LinkedHashMap<Integer, Integer> getPrimeFactorsWithPower(int x) {
		LinkedHashMap<Integer, Integer> primeRootsWithPowerValue = new LinkedHashMap<Integer, Integer>();

		for (var i = 2; i <= x && x != 1; i++) {
	        int power = 0;

	        while( x % i == 0) {
	            power++;
	            x /= i;
	        }

	        if (power > 0) {
	        	primeRootsWithPowerValue.put(i, power);
	        }

	    }

	    return primeRootsWithPowerValue; 
	}
}
