package nl.jrwer.challenge.advent.day04;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Pair>{

	public InputLoader(String file) {
		super(file);
	}

	protected Pair handleLine(String line) {
		return new Pair(line);
	}
}