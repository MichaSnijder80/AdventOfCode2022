package org.micha.apps.adventofcode2022.days;

import org.micha.apps.adventofcode2022.exceptions.AdventOfCodeException;

public class DayFour extends AdventDayRunner<Integer, Integer> {

	protected DayFour(String fileName, String identifierOfDay) throws AdventOfCodeException {
		super(fileName, identifierOfDay);
	}

	@Override
	public Integer partOne() throws AdventOfCodeException {
		return (int) this.getData().stream().map(CleaningPair::new).filter(CleaningPair::thereIsCompleteOverlap).count();
	}

	@Override
	public Integer partTwo() throws AdventOfCodeException {
		return (int) this.getData().stream().map(CleaningPair::new).filter(CleaningPair::thereIsSomeOverlap).count();
	}
	
	private static class CleaningPair {
		
		private final int lowerBoundElfOne;
		private final int upperBoundElfOne;
		private final int lowerBoundElfTwo;
		private final int upperBoundElfTwo;
		
		private CleaningPair(String line) {
			String[] elfArray = line.split(",");
			String[] boundsElfOne = elfArray[0].split("-");
			String[] boundsElfTwo = elfArray[1].split("-");
			this.lowerBoundElfOne = Integer.valueOf(boundsElfOne[0]);
			this.upperBoundElfOne = Integer.valueOf(boundsElfOne[1]);
			this.lowerBoundElfTwo = Integer.valueOf(boundsElfTwo[0]);
			this.upperBoundElfTwo = Integer.valueOf(boundsElfTwo[1]);
		}
		
		private boolean thereIsCompleteOverlap() {
			return this.lowerBoundElfOne >= this.lowerBoundElfTwo && this.upperBoundElfOne <= this.upperBoundElfTwo
					|| this.lowerBoundElfTwo >= this.lowerBoundElfOne && this.upperBoundElfTwo <= this.upperBoundElfOne;
		}
		
		private boolean thereIsSomeOverlap() {
			return lowerBoundElfOne <= upperBoundElfTwo && lowerBoundElfTwo <= upperBoundElfOne;
		}
	}

}
