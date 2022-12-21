package org.micha.apps.adventofcode2022.days;

import java.util.ArrayList;
import java.util.List;

import org.micha.apps.adventofcode2022.exceptions.AdventOfCodeException;

public class DayOne extends AdventDayRunner<Integer, Integer> {

	protected DayOne(String fileName, String identifierOfDay) throws AdventOfCodeException {
		super(fileName, identifierOfDay);
	}

	@Override
	public Integer partOne() throws AdventOfCodeException {
		List<List<Integer>> partitionedCalories = partitionData();
		return partitionedCalories.stream()
				.mapToInt(caloriesOfElf -> caloriesOfElf.stream().reduce(0, (a, b) -> a + b))
				.max().orElseThrow(() -> new AdventOfCodeException("Issues summing the calories"));
	}

	@Override
	public Integer partTwo() throws AdventOfCodeException {
		List<List<Integer>> partitionedCalories = partitionData();
		return partitionedCalories.stream()
			.mapToInt(caloriesOfElf -> caloriesOfElf.stream().reduce(0, (a, b) -> a + b))
			.boxed().sorted((a,b) -> {
				if(a == b) return 0;
				if(a > b) return -1;
				return 1;
			}).limit(3).reduce(0, (a, b) -> a + b);
	}
	
	private List<List<Integer>> partitionData() {
		List<String> grabbedData = this.getData();
		List<List<Integer>> partitionedCalories = new ArrayList<>();
		partitionedCalories.add(new ArrayList<>());
		for(String line : grabbedData) {
			if(line.equals("")) {
				partitionedCalories.add(new ArrayList<>());
				continue;
			}
			partitionedCalories.get(partitionedCalories.size() - 1).add(Integer.valueOf(line));
		}
		return partitionedCalories;
	}
	
	

}
