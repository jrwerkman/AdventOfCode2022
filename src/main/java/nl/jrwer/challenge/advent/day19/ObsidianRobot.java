package nl.jrwer.challenge.advent.day19;

class ObsidianRobot extends Robot {
	final int costClay;
	
	public ObsidianRobot(int costOre, int costClay) {
		super(costOre);
		
		this.costClay = costClay;
	}

	@Override
	public boolean canBuy(State state) {
		return costOre <= state.ore && costClay <= state.clay;
	}

	@Override
	public void buy(State state) {
		state.ore -= costOre;
		state.clay -= costClay;
	}

	@Override
	public void add(State state) {
		state.obsidianRobots++;
	}
}