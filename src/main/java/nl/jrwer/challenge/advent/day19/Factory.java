package nl.jrwer.challenge.advent.day19;

class Factory {
	final int maxTime;
	final Blueprint blueprint;
	State bestState = new State();
	long iterations = 0;
	
	public Factory(Blueprint blueprint, int maxTime) {
		this.blueprint = blueprint;
		this.maxTime = maxTime;
	}
	
	public int maxGeodes() {
		start(bestState);
		
		System.out.println("");
		System.out.println(bestState);
		
		return bestState.geode;
	}
	
	public void start(State current) {
		iterations++;
		
		if(iterations % 1_000_000_000L == 0)
			System.out.print(".");
		
		if(current.geode > bestState.geode)
			bestState = current;
		
		if(current.time < maxTime)
			next(current);			
	}
	
	public void next(State current) {
		if(current.time == maxTime - 1) {
			current.next();
			start(current);
		} else if(blueprint.geodeRobot.canBuy(current)) {
			buy(blueprint.geodeRobot, current);
		} else {
			if(current.obsidianRobots < blueprint.geodeRobot.costObisdian)
				buy(blueprint.obsidianRobot, current);
			if(current.clayRobots < blueprint.obsidianRobot.costClay)
				buy(blueprint.clayRobot, current);
			if(current.oreRobots < blueprint.costMaxOre)
				buy(blueprint.oreRobot, current);

			current.next();
			start(current);
		}
	}
	
	public void buy(Robot robot, State state) {
		if(robot.canBuy(state)) {
			State clone = state.clone();
			
			// remove resources
			robot.buy(clone);
			
			// pass one minute, but not with new robot
			clone.next();
			
			// add the new robot
			robot.add(clone);
			
			start(clone);
		}
	}
}