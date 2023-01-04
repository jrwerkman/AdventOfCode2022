package nl.jrwer.challenge.advent.day19;

abstract class Robot {
	final int costOre;
	
	public Robot(int costOre) {
		this.costOre = costOre;
	}
	
	abstract public boolean canBuy(State state);
	abstract public void buy(State state);
	abstract public void add(State state);
}