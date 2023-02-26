package com.distractors.generation.general.maths;

import java.util.HashSet;
import java.util.Set;

public class SquareRoots {

	private Set<SquareRoot> roots = new HashSet<SquareRoot>();

	public SquareRoots() {
	}

	public SquareRoots(Set<SquareRoot> roots) {
		this.roots = roots;
	}

	public SquareRoots add(SquareRoots rootsToAdd) {
		var rootSum = new SquareRoots(this.roots);

		for (final var currentRootToAdd : rootsToAdd.roots) {
			rootSum = rootSum.add(currentRootToAdd);
		}

		return rootSum;
	}

	public SquareRoots add(SquareRoot rootToAdd) {
		final var rootsResult = new HashSet<SquareRoot>();

		for (final var currentRoot : roots) {

			if (currentRoot.isTheSameUnderTheRootExpression(rootToAdd)) {
				final var sameRootsSum = currentRoot.add(rootToAdd);
				rootsResult.add(sameRootsSum);
				rootToAdd = null;
			} else {
				rootsResult.add(currentRoot);
			}

		}
		
		if (rootToAdd != null && rootToAdd.toDouble() != 0) {
			rootsResult.add(rootToAdd);
		}
		
		return new SquareRoots(rootsResult);
	}

	public SquareRoots substract(SquareRoots rootsToSubstract) {
		final var rootsResult = new SquareRoots(this.roots);

		for (final var currentRoot : rootsToSubstract.roots) {
			rootsResult.substract(currentRoot);
		}

		return rootsResult;
	}

	public Set<SquareRoot> substract(SquareRoot rootToSubstract) {
		final var rootsResult = new HashSet<SquareRoot>();

		for (final var currentRoot : roots) {

			if (currentRoot.isTheSameUnderTheRootExpression(rootToSubstract)) {
				final var sameRoots = currentRoot.substract(rootToSubstract);
				rootsResult.add(sameRoots);
				rootToSubstract = null;
			} else {
				rootsResult.add(currentRoot);
			}

		}
		
		if (rootToSubstract != null) {
			rootsResult.add(rootToSubstract);
		}
		
		return rootsResult;
	}

	public SquareRoots multiplyBy(SquareRoot rootToMultiplyBy) {
		final var rootsResult = new HashSet<SquareRoot>();

		for (final var currentRoot : roots) {
			final var rootResult = currentRoot.multiplyBy(rootToMultiplyBy);
			rootsResult.add(rootResult);
		}

		return new SquareRoots(rootsResult);
	}

	public SquareRoots multiplyBy(Fraction fraction) {
		final var rootsResult = new HashSet<SquareRoot>();

		for (final var currentRoot : roots) {
			final var rootResult = currentRoot.multiplyBy(fraction);
			rootsResult.add(rootResult);
		}

		return new SquareRoots(rootsResult);
	}

	public SquareRoots multiplyBy(SquareRoots rootsToMultiplyBy) {
		var rootsResult = new SquareRoots(this.roots);

		for (final var currentRoot : rootsToMultiplyBy.roots) {
			rootsResult = rootsResult.multiplyBy(currentRoot);
		}

		return rootsResult;
	}

	public SquareRoots multiplyBy(int number) {
		var rootsResult = new SquareRoots();

		for (final var currentRoot : roots) {
			final var rootResult = currentRoot.multiplyBy(number);
			rootsResult = rootsResult.add(rootResult);
		}

		return rootsResult;
	}

	public SquareRoots divideBy(SquareRoot rootToDivideBy) {
		final var rootsResult = new HashSet<SquareRoot>();

		for (final var currentRoot : roots) {
			final var rootResult = currentRoot.divideBy(rootToDivideBy);
			rootsResult.add(rootResult);
		}

		return new SquareRoots(rootsResult);
	}

	public SquareRoots divideBy(SquareRoots rootsToDivideBy) {
		var rootsResult = new SquareRoots(this.roots);

		for (final var currentRoot : rootsToDivideBy.roots) {
			rootsResult = rootsResult.divideBy(currentRoot);
		}

		return rootsResult;
	}

	public Set<SquareRoot> getRoots() {
		return roots;
	}

	public boolean contains(SquareRoot root) {
		return this.getRoots().stream().anyMatch(otherRoot -> otherRoot.equals(root));
	}

	public boolean equals(SquareRoots other) {
		return this.equalsSymbolically(other) || this.equalsNumerically(other);
	}

	private boolean equalsSymbolically(SquareRoots other) {
		return this.roots.stream().allMatch(root -> other.contains(root)) && other.roots.stream().allMatch(root -> this.contains(root));
	}

	private boolean equalsNumerically(SquareRoots other) {
		return this.toDouble() == other.toDouble();
	}

	public Double toDouble() {
		return this.roots.stream().mapToDouble(SquareRoot::toDouble).sum();
	}

	public void print() {
		final var currentRootIterator = this.roots.iterator();
		while (currentRootIterator.hasNext()) {
			final var currentRoot = currentRootIterator.next();
			currentRoot.print();
			if (currentRootIterator.hasNext() && !currentRoot.getBeforeTheRoot().equals(Fraction.ONE)) {
				if (currentRoot.getBeforeTheRoot().equals(new Fraction(-1, 1))) {
					System.out.print(" - ");
				}else {
					System.out.print(" + ");
				}
	        }
		};	
	}

	public String toString() {
		final var stringBuilder = new StringBuilder();
		final var currentRootIterator = this.roots.iterator();
		while (currentRootIterator.hasNext()) {
			final var currentRoot = currentRootIterator.next();
			stringBuilder.append(currentRoot.toString());
			if (currentRootIterator.hasNext() && !currentRoot.getBeforeTheRoot().equals(Fraction.ONE)) {
				if (currentRoot.getBeforeTheRoot().equals(new Fraction(-1, 1))) {
					stringBuilder.append(" - ");
				}else {
					stringBuilder.append(" + ");
				}
	        }
		};
		return stringBuilder.toString();
	}
}
