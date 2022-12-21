package org.micha.apps.adventofcode2022.days;

import java.util.stream.IntStream;

import org.micha.apps.adventofcode2022.exceptions.AdventOfCodeException;

public class DaySix extends AdventDayRunner<Integer, Integer> {

	protected DaySix(String fileName, String identifierOfDay) throws AdventOfCodeException {
		super(fileName, identifierOfDay);
	}

	@Override
	public Integer partOne() throws AdventOfCodeException {
		final String code = this.getData().get(0);
		return searchStringForFirstMarkerOfLenghtN(code, 4);
	}

	@Override
	public Integer partTwo() throws AdventOfCodeException {
		final String code = this.getData().get(0);
		return searchStringForFirstMarkerOfLenghtN(code, 14);
	}
	
	private int searchStringForFirstMarkerOfLenghtN(String string, int n) throws AdventOfCodeException {
		return IntStream.iterate(n, i -> i <= string.length(), i -> i + 1).parallel()
				.filter(i -> stringContainsNoDiplucates(string.substring(i - n, i)))
				.findFirst().orElseThrow(() -> new AdventOfCodeException("No marker found"));
	}
	
	private boolean stringContainsNoDiplucates(String chuck) {
		final int[] chars = chuck.chars().toArray();
		for(int character : chars) {
			long count = IntStream.of(chars).filter(i -> i == character).count();
			if(count != 1) return false;
		}
		return true;
	}

}
