package nl.jrwer.challenge.advent.day08;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<TreeLine>{

	public InputLoader(String file) {
		super(file);
	}

	protected TreeLine handleLine(String line) {
		return new TreeLine(line);
	}
}