package nl.jrwer.challenge.advent.day10;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Instruction>{

	public InputLoader(String file) {
		super(file);
	}

	protected Instruction handleLine(String line) {
		if(line.startsWith("noop"))
			return new Noop();
		
		String[] split = line.split(" ");
		
		return new AddX(Integer.parseInt(split[1]));
	}
}