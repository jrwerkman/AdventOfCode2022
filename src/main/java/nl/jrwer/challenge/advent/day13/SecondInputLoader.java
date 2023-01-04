package nl.jrwer.challenge.advent.day13;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class SecondInputLoader extends BasicInputLoader<ListElement>{
	
	public SecondInputLoader(String file) {
		super(file);
	}

	@Override
	protected ListElement handleLine(String line) {
		return new ListElement(line);
	}
}