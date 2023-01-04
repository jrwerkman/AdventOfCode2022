package nl.jrwer.challenge.advent.day18;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Cube>{
			
	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected Cube handleLine(String line) {
		String[] split = line.split(",");
		
		return new Cube(
				Integer.parseInt(split[0]),
				Integer.parseInt(split[1]),
				Integer.parseInt(split[2]));
	}
}