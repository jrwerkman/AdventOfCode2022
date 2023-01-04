package nl.jrwer.challenge.advent.day13;

import java.util.List;

import nl.jrwer.challenge.advent.input.MultipleLinesInputLoader;

class InputLoader extends MultipleLinesInputLoader<Pair>{
	
	int number = 0;
	
	public InputLoader(String file) {
		super(file, 2);
	}

	@Override
	protected Pair handleLines(String[] lines) {
		number++;
		return new Pair(lines, number);
	}

	@Override
	protected void postProcess(List<Pair> objects) {

	}
}