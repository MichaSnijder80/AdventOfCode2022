package org.micha.apps.adventofcode2022;

import org.micha.apps.adventofcode2022.days.AdventDayRunner;
import org.micha.apps.adventofcode2022.days.AdventOfCodeDayFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Application {

	public static void main(String[] args) {
		try {
			AdventDayRunner<?, ?> createDay = AdventOfCodeDayFactory.createDaySix();
			String resultpartOneAsString = createDay.partOne().toString();
			log.info("Day {}, part one executed. Result was: {}", createDay::getIdentifierOfDay, () -> resultpartOneAsString);
			String resultpartTwoAsString = createDay.partTwo().toString();
			log.info("Day {}, part two executed. Result was: {}", createDay::getIdentifierOfDay, () -> resultpartTwoAsString);
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
		}
	}
}
