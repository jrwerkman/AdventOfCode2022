package nl.jrwer.challenge.advent.day20;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Number>{
	final long decriptionKey;
	int index = 0;
	
	public InputLoader(String file) {
		this(file, 1);
	}
	
	public InputLoader(String file, long decriptionKey) {
		super(file);
		
		this.decriptionKey = decriptionKey;
	}

	@Override
	protected Number handleLine(String line) {
		Number number = new Number(index, Long.parseLong(line) * decriptionKey);
		index++;
		return number;
	}
}