package nl.jrwer.challenge.advent.day05;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Move>{

	private Supplies supplies = new Supplies();
	
	public InputLoader(String file) {
		super(file);
	}

	protected Move handleLine(String line) {
		Move move = null;
		
		if(line.startsWith("move"))
			move = new Move(supplies, line);
		else
			supplies.addLine(line);
		
		return move;
	}
}