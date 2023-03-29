package com.distractors.generation.general.maths;

import java.util.HashSet;
import java.util.Set;

public class SquareRoots {

	private Set<SquareRoot> roots = new HashSet<SquareRoot>();

	public SquareRoots() {
	}

	public SquareRoots(SquareRoot root) {
		this.roots.add(root);
	}

	public SquareRoots(Set<SquareRoot> roots) {
		this.roots = roots;
	}

	/**
	 * Adds a sum of roots to a sum of roots symbolically
	 * Example: (√3 + √2) + (√2 + √3) = 2√3 + 2√2
	 * 			(√3 + √2) + (√5 + √3) = 2√3 + √2 + √5
	 */
	public SquareRoots add(SquareRoots rootsToAdd) {
		var rootSum = new SquareRoots(roots);

		for (final var currentRootToAdd : rootsToAdd.roots) {
			rootSum = rootSum.add(currentRootToAdd);
		}

		return rootSum;
	}

	/**
	 * Adds a square root to a sum of square roots symbolically
	 * Example: (√3 + √2) + √2 = √3 + 2√2
	 * 			(√3 + √2) + √5 = √3 + √2 + √5
	 */
	public SquareRoots add(SquareRoot rootToAdd) {
		var rootsResult = new SquareRoots();

		for (final var currentRoot : roots) {

			if (currentRoot.isTheSameUnderTheRootExpression(rootToAdd)) {
				final var sameRootsSum = currentRoot.add(rootToAdd);
				rootsResult = rootsResult.add(sameRootsSum);
				rootToAdd = null;
			} else {
				rootsResult = rootsResult.add(currentRoot);
			}

		}
		
		if (rootToAdd != null && rootToAdd.toDouble() != 0) {
			rootsResult = rootsResult.add(rootToAdd);
		}
		
		return rootsResult;
	}

	/**
	 * Subtracts a sum of roots from a sum of roots symbolically
	 * Example: (√3 + √2) - (√2 + √3) = 0
	 * 			(√3 + √2) - (√5 + √3) = √2 - √5
	 */
	public SquareRoots subtract(SquareRoots rootsToSubstract) {
		var rootsResult = new SquareRoots(roots);

		for (final var currentRoot : rootsToSubstract.roots) {
			rootsResult = rootsResult.subtract(currentRoot);
		}

		return rootsResult;
	}

	/**
	 * Subtracts a sum of roots from a sum of roots symbolically
	 * Example: (√3 + √2) - √2 = √3
	 * 			(√3 + √2) - √5 = √3 + √2 - √5
	 */
	private SquareRoots subtract(SquareRoot rootToSubstract) {
		var rootsResult = new SquareRoots();

		for (final var currentRoot : roots) {

			if (currentRoot.isTheSameUnderTheRootExpression(rootToSubstract)) {
				final var sameRoots = currentRoot.substract(rootToSubstract);
				rootsResult = rootsResult.add(sameRoots);
				rootToSubstract = null;
			} else {
				rootsResult = rootsResult.add(currentRoot);
			}

		}
		
		if (rootToSubstract != null) {
			rootsResult.add(rootToSubstract.multiplyBy(-1));
		}
		
		return rootsResult;
	}

	/**
	 * Multiplies a sum of roots by a sum of roots symbolically
	 * Example: (√3 + √2) * (√3 + √2) = 5 + √6
	 */
	public SquareRoots multiplyBy(SquareRoots rootsToMultiplyBy) {
		if (rootsToMultiplyBy.areNotEmpty()) {
			var rootsResult = new SquareRoots(this.roots);
			
			for (final var currentRoot : rootsToMultiplyBy.roots) {
				rootsResult = rootsResult.multiplyBy(currentRoot);
			}
			
			return rootsResult;	
		}
		return new SquareRoots();
	}

	/**
	 * Multiplies a sum of roots by a root symbolically
	 * Example: (√3 + √2) * √2 = √6 + 2
	 */
	public SquareRoots multiplyBy(SquareRoot rootToMultiplyBy) {
		var rootsResult = new SquareRoots();

		for (final var currentRoot : roots) {
			final var rootResult = currentRoot.multiplyBy(rootToMultiplyBy);
			rootsResult = rootsResult.add(rootResult);
		}

		return rootsResult;
	}

	/**
	 * Multiplies a sum of roots by a fraction symbolically
	 * Example: (√3 + √2) * 1/2 = (√3)/2 + (√2)/2
	 */
	public SquareRoots multiplyBy(Fraction fraction) {
		var rootsResult = new SquareRoots();

		for (final var currentRoot : roots) {
			final var rootResult = currentRoot.multiplyBy(fraction);
			rootsResult = rootsResult.add(rootResult);
		}

		return rootsResult;
	}

	/**
	 * Multiplies a sum of roots by an integer symbolically
	 * Example: (√3 + √2) * 2 = 2√3 + 2√2
	 */
	public SquareRoots multiplyBy(int number) {
		var rootsResult = new SquareRoots();

		for (final var currentRoot : roots) {
			final var rootResult = currentRoot.multiplyBy(number);
			rootsResult = rootsResult.add(rootResult);
		}

		return rootsResult;
	}

	/**
	 * Divides a sum of roots by a root symbolically
	 * Example: (√3 + √2) / √2 = √(3/2) + 1
	 */
	public SquareRoots divideBy(SquareRoot rootToDivideBy) {
		final var rootsResult = new HashSet<SquareRoot>();

		for (final var currentRoot : roots) {
			final var rootResult = currentRoot.divideBy(rootToDivideBy);
			rootsResult.add(rootResult);
		}

		return new SquareRoots(rootsResult);
	}

	/**
	 * Empty set of square roots also means = 0
	 */
	private boolean areNotEmpty() {
		return !roots.isEmpty();
	}

	public Double toDouble() {
		return roots.stream().mapToDouble(SquareRoot::toDouble).sum();
	}

	public boolean contains(SquareRoot root) {
		return roots.stream().anyMatch(otherRoot -> otherRoot.equals(root));
	}
	
	public Set<SquareRoot> getRoots() {
		return roots;
	}

	public boolean equals(SquareRoots other) {
		return equalsSymbolically(other) || equalsNumerically(other);
	}

	private boolean equalsSymbolically(SquareRoots other) {
		return this.roots.stream().allMatch(root -> other.contains(root)) && other.roots.stream().allMatch(root -> this.contains(root));
	}

	private boolean equalsNumerically(SquareRoots other) {
		return this.toDouble() == other.toDouble();
	}

	public String convertToString() {
		final var stringBuilder = new StringBuilder();
		final var currentRootIterator = this.roots.iterator();
		while (currentRootIterator.hasNext()) {
			final var currentRoot = currentRootIterator.next();
			stringBuilder.append(currentRoot.convertToString());
			if (currentRootIterator.hasNext() && !currentRoot.getBeforeTheRoot().equals(Fraction.ONE)) {
				if (currentRoot.getBeforeTheRoot().equals(Fraction.MINUS_ONE)) {
					stringBuilder.append(" - ");
				}else {
					stringBuilder.append(" + ");
				}
	        }
		};
		return stringBuilder.toString();
	}
}
