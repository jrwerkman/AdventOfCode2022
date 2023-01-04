package nl.jrwer.challenge.advent.day09;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Motion>{

	public InputLoader(String file) {
		super(file);
	}

	protected Motion handleLine(String line) {
		return new Motion(line);
	}
}