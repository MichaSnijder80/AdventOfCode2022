package org.micha.apps.adventofcode2022.days;

import java.util.List;
import java.util.stream.IntStream;

import org.micha.apps.adventofcode2022.exceptions.AdventOfCodeException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DayThree extends AdventDayRunner<Integer, Integer> {

	protected DayThree(String fileName, String identifierOfDay) throws AdventOfCodeException {
		super(fileName, identifierOfDay);
	}

	@Override
	public Integer partOne() throws AdventOfCodeException {
		return this.getData().stream().mapToInt(this::getDuplicate).map(this::getValueOfDuplicate).sum();
	}

	@Override
	public Integer partTwo() throws AdventOfCodeException {
		final List<String> rucksacks = this.getData();
		return IntStream.range(0, this.getData().size() / 3)
				.map(index -> getBadge(rucksacks.get(index * 3), rucksacks.get(index * 3 + 1), rucksacks.get(index * 3 + 2)))
				.map(this::getValueOfDuplicate).sum();
	}

	private int getDuplicate(String line) {
		int[] firstHalf = line.chars().limit(line.length() / 2).toArray();
		int[] lastHalf = line.chars().skip(line.length() / 2).toArray();
		for (int i = 0; i < firstHalf.length; i++) {
			for (int j = 0; j < lastHalf.length; j++) {
				if (firstHalf[i] == lastHalf[j])
					return firstHalf[i];
			}
		}
		return -1;
	}
	
	private int getValueOfDuplicate(int duplicate) {
		int lowercaseAlpha = 'a';
		int uppercaseAlpha = 'A';
		if(Character.isLowerCase(duplicate)) {
			return (duplicate - lowercaseAlpha) + 1;
		}
		return (duplicate - uppercaseAlpha) + 27;
	}
	
	private int getBadge(String rucksackOne, String rucksackTwo, String rucksackThree) {
		int[] rucksackCharsOne = rucksackOne.chars().toArray();
		int[] rucksackCharsTwo = rucksackTwo.chars().toArray();
		int[] rucksackCharsThree = rucksackThree.chars().toArray();
		for (int i = 0; i < rucksackCharsOne.length; i++) {
			for (int j = 0; j < rucksackCharsTwo.length; j++) {
				for(int k = 0; k < rucksackCharsThree.length; k++) {
					if(rucksackCharsOne[i] == rucksackCharsTwo[j] && rucksackCharsOne[i] == rucksackCharsThree[k])
						return rucksackCharsOne[i];
				}
			}
		}
		return -1;
	}
	

}
