package nl.jrwer.challenge.advent.day17;

import nl.jrwer.challenge.advent.input.SingleLineInputLoader;

class InputLoader extends SingleLineInputLoader<Sequence>{
			
	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected Sequence handleLine(String line) {
		return new Sequence(line);
	}
}