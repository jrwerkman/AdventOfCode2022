package nl.jrwer.challenge.advent.day19;

class GeodeRobot extends Robot {
	final int costObisdian;
	
	public GeodeRobot(int costOre, int costObisdian) {
		super(costOre);
		
		this.costObisdian = costObisdian;
	}

	@Override
	public boolean canBuy(State state) {
		return costOre <= state.ore && costObisdian <= state.obisidian;
	}

	@Override
	public void buy(State state) {
		state.ore -= costOre;
		state.obisidian -= costObisdian;
	}

	@Override
	public void add(State state) {
		state.geodeRobots++;
	}
}