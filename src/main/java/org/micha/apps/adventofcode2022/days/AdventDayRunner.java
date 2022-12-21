package org.micha.apps.adventofcode2022.days;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import org.micha.apps.adventofcode2022.exceptions.AdventOfCodeException;

import lombok.Getter;

@Getter
public abstract class AdventDayRunner<U, V> {
	
	private final Path pathToData;
	private final String identifierOfDay;
	private final List<String> data;
	
	protected AdventDayRunner(String fileName, String identifierOfDay) throws AdventOfCodeException {
		this.pathToData = getPath(fileName, identifierOfDay);
		this.identifierOfDay = identifierOfDay;
		this.data = readData(fileName, this.pathToData, this.identifierOfDay);
	}
	
	public abstract U partOne() throws AdventOfCodeException;
	public abstract V partTwo() throws AdventOfCodeException;
	
	private Path getPath(String fileName, String identifierOfDay) throws AdventOfCodeException {
		try {
			return Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		} catch (URISyntaxException uriSyntaxException) {
			throw new AdventOfCodeException(
					"There was a problem with the path of the data file with filename '%s' for day %s"
					.formatted(fileName, identifierOfDay)
					, uriSyntaxException);
		}
	}
	
	private List<String> readData(String fileName, Path pathToData, String identifierOfDay) throws AdventOfCodeException {
		try(Stream<String> linesAsStream = Files.lines(pathToData)) {
			return linesAsStream.collect(toUnmodifiableList());
		} catch (IOException ioException) {
			throw new AdventOfCodeException(
					"There was a problem reading data from file '%s' with path '%s' for day %s"
					.formatted(fileName, pathToData.toString(), identifierOfDay), ioException);
		}
	}

}
