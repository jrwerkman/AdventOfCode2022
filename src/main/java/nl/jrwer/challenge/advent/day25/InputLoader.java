package nl.jrwer.challenge.advent.day25;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Snafu>{

	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected Snafu handleLine(String line) {
		return new Snafu(line);
	}
}