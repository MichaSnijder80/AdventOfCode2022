package org.micha.apps.adventofcode2022.days;

import org.micha.apps.adventofcode2022.exceptions.AdventOfCodeException;

public class AdventOfCodeDayFactory {
	
	public static AdventDayRunner<Integer, Integer> createDayOne() throws AdventOfCodeException {
		return new DayOne("DayOneData.txt", "One");
	}

	public static AdventDayRunner<Integer, Integer> createDayTwo() throws AdventOfCodeException {
		return new DayTwo("DayTwoData.txt", "Two");
	}
	
	public static AdventDayRunner<Integer, Integer> createDayThree() throws AdventOfCodeException {
		return new DayThree("DayThreeData.txt", "Three");
	}
	
	public static AdventDayRunner<Integer, Integer> createDayFour() throws AdventOfCodeException {
		return new DayFour("DayFourData.txt", "Four");
	}
	
	public static AdventDayRunner<String, String> createDayFive() throws AdventOfCodeException {
		return new DayFive("DayFiveData.txt", "Five");
	}
	
	public static AdventDayRunner<Integer, Integer> createDaySix() throws AdventOfCodeException {
		return new DaySix("DaySixData.txt", "Six");
	}
}
