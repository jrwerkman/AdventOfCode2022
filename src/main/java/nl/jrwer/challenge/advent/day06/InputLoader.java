package nl.jrwer.challenge.advent.day06;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<DataStreamBuffer>{

	public InputLoader(String file) {
		super(file);
	}

	protected DataStreamBuffer handleLine(String line) {
		return new DataStreamBuffer(line);
	}
}