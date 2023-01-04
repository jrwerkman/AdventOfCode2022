package nl.jrwer.challenge.advent.day19;

class Blueprint {
	final int number;
	final OreRobot oreRobot;
	final ClayRobot clayRobot;
	final ObsidianRobot obsidianRobot;
	final GeodeRobot geodeRobot;
	
	final int costMaxOre;
	
	public Blueprint(int number, OreRobot oreRobot, ClayRobot clayRobot, 
			ObsidianRobot obsidianRobot, GeodeRobot geodeRobot) {
		this.number = number;
		this.oreRobot = oreRobot;
		this.clayRobot = clayRobot;
		this.obsidianRobot = obsidianRobot;
		this.geodeRobot = geodeRobot;
		
		costMaxOre = getMaxOreCost();
		
	}
	
	public int getMaxOreCost() {
		int max = 0;
		
		max = oreRobot.costOre > max ? oreRobot.costOre : max;
		max = clayRobot.costOre > max ? clayRobot.costOre : max;
		max = obsidianRobot.costOre > max ? obsidianRobot.costOre : max;
		max = geodeRobot.costOre > max ? geodeRobot.costOre : max;
		
		return max;
	}
}