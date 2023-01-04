package nl.jrwer.challenge.advent.day19;

class OreRobot extends Robot {
	
	public OreRobot(int costOre) {
		super(costOre);
	}

	@Override
	public boolean canBuy(State state) {
		return costOre <= state.ore;
	}

	@Override
	public void buy(State state) {
		state.ore -= costOre;
	}

	@Override
	public void add(State state) {
		state.oreRobots++;
	}
}