package nl.jrwer.challenge.advent.day07;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<String>{

	public InputLoader(String file) {
		super(file);
	}

	protected String handleLine(String line) {
		return line;
	}
}