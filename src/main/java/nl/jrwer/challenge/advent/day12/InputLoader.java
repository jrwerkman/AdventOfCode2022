package nl.jrwer.challenge.advent.day12;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<String>{
	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected String handleLine(String line) {
		return line;
	}
}