package org.micha.apps.adventofcode2022.exceptions;

public class AdventOfCodeException extends Exception {
	private static final long serialVersionUID = 4611512493864570154L;
	
	public AdventOfCodeException() {
		super();
	}
	
	public AdventOfCodeException(String message) {
		super(message);
	}
	
	public AdventOfCodeException(String message, Throwable cause) {
		super(message, cause);
	}

}
