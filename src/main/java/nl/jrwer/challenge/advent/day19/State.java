package nl.jrwer.challenge.advent.day19;

class State {
	byte time = 0;
	
	byte oreRobots = 1;
	byte clayRobots = 0;
	byte obsidianRobots = 0;
	byte geodeRobots = 0;
	
	byte ore = 0;
	byte clay = 0;
	byte obisidian = 0;
	byte geode = 0;
	
	public void next() {
		time++;
		
		ore += oreRobots;
		clay += clayRobots;
		obisidian += obsidianRobots;
		geode += geodeRobots;
	}
	
	public State clone() {
		State clone = new State();
		clone.time = time;
		
		clone.oreRobots = oreRobots;
		clone.clayRobots = clayRobots;
		clone.obsidianRobots = obsidianRobots;
		clone.geodeRobots = geodeRobots;
		
		clone.ore = ore;
		clone.clay = clay;
		clone.obisidian = obisidian;
		clone.geode = geode;
		
		return clone;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(time).append(';')
			.append(oreRobots).append(',')
			.append(clayRobots).append(',')
			.append(obsidianRobots).append(',')
			.append(geodeRobots).append(';')
			.append(ore).append(',')
			.append(clay).append(',')
			.append(obisidian).append(',')
			.append(geode);
		
		return sb.toString();
	}
}