package nl.jrwer.challenge.advent.day05;

class Move {
	final Supplies supplies;
	
	int amount;
	int from;
	int to;
	
	public Move(Supplies supplies, String action) {
		this.supplies = supplies;

		String[] split = action.split(" ");
		
		if(split.length != 6)
			throw new RuntimeException("Wrong input: " + action);
		
		amount = Integer.parseInt(split[1]);
		from = Integer.parseInt(split[3]);
		to = Integer.parseInt(split[5]);
	}
	
	public String move() {
		return supplies.move(amount, from, to);
	}
	
	public String move9001() {
		return supplies.move9001(amount, from, to);
	}
}