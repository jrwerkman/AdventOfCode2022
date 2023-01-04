package nl.jrwer.challenge.advent;

import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 19: Not Enough Minerals ---
//
//Your scans show that the lava did indeed form obsidian!
//
//The wind has changed direction enough to stop sending lava droplets toward you, so you and the elephants exit the cave. As you do, you notice a collection of geodes around the pond. Perhaps you could use the obsidian to create some geode-cracking robots and break them open?
//
//To collect the obsidian from the bottom of the pond, you'll need waterproof obsidian-collecting robots. Fortunately, there is an abundant amount of clay nearby that you can use to make them waterproof.
//
//In order to harvest the clay, you'll need special-purpose clay-collecting robots. To make any type of robot, you'll need ore, which is also plentiful but in the opposite direction from the clay.
//
//Collecting ore requires ore-collecting robots with big drills. Fortunately, you have exactly one ore-collecting robot in your pack that you can use to kickstart the whole operation.
//
//Each robot can collect 1 of its resource type per minute. It also takes one minute for the robot factory (also conveniently from your pack) to construct any type of robot, although it consumes the necessary resources available when construction begins.
//
//The robot factory has many blueprints (your puzzle input) you can choose from, but once you've configured it with a blueprint, you can't change it. You'll need to work out which blueprint is best.
//
//For example:
//
//Blueprint 1:
//  Each ore robot costs 4 ore.
//  Each clay robot costs 2 ore.
//  Each obsidian robot costs 3 ore and 14 clay.
//  Each geode robot costs 2 ore and 7 obsidian.
//
//Blueprint 2:
//  Each ore robot costs 2 ore.
//  Each clay robot costs 3 ore.
//  Each obsidian robot costs 3 ore and 8 clay.
//  Each geode robot costs 3 ore and 12 obsidian.
//
//(Blueprints have been line-wrapped here for legibility. The robot factory's actual assortment of blueprints are provided one blueprint per line.)
//
//The elephants are starting to look hungry, so you shouldn't take too long; you need to figure out which blueprint would maximize the number of opened geodes after 24 minutes by figuring out which robots to build and when to build them.
//
//Using blueprint 1 in the example above, the largest number of geodes you could open in 24 minutes is 9. One way to achieve that is:
//
//== Minute 1 ==
//1 ore-collecting robot collects 1 ore; you now have 1 ore.
//
//== Minute 2 ==
//1 ore-collecting robot collects 1 ore; you now have 2 ore.
//
//== Minute 3 ==
//Spend 2 ore to start building a clay-collecting robot.
//1 ore-collecting robot collects 1 ore; you now have 1 ore.
//The new clay-collecting robot is ready; you now have 1 of them.
//
//== Minute 4 ==
//1 ore-collecting robot collects 1 ore; you now have 2 ore.
//1 clay-collecting robot collects 1 clay; you now have 1 clay.
//
//== Minute 5 ==
//Spend 2 ore to start building a clay-collecting robot.
//1 ore-collecting robot collects 1 ore; you now have 1 ore.
//1 clay-collecting robot collects 1 clay; you now have 2 clay.
//The new clay-collecting robot is ready; you now have 2 of them.
//
//== Minute 6 ==
//1 ore-collecting robot collects 1 ore; you now have 2 ore.
//2 clay-collecting robots collect 2 clay; you now have 4 clay.
//
//== Minute 7 ==
//Spend 2 ore to start building a clay-collecting robot.
//1 ore-collecting robot collects 1 ore; you now have 1 ore.
//2 clay-collecting robots collect 2 clay; you now have 6 clay.
//The new clay-collecting robot is ready; you now have 3 of them.
//
//== Minute 8 ==
//1 ore-collecting robot collects 1 ore; you now have 2 ore.
//3 clay-collecting robots collect 3 clay; you now have 9 clay.
//
//== Minute 9 ==
//1 ore-collecting robot collects 1 ore; you now have 3 ore.
//3 clay-collecting robots collect 3 clay; you now have 12 clay.
//
//== Minute 10 ==
//1 ore-collecting robot collects 1 ore; you now have 4 ore.
//3 clay-collecting robots collect 3 clay; you now have 15 clay.
//
//== Minute 11 ==
//Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
//1 ore-collecting robot collects 1 ore; you now have 2 ore.
//3 clay-collecting robots collect 3 clay; you now have 4 clay.
//The new obsidian-collecting robot is ready; you now have 1 of them.
//
//== Minute 12 ==
//Spend 2 ore to start building a clay-collecting robot.
//1 ore-collecting robot collects 1 ore; you now have 1 ore.
//3 clay-collecting robots collect 3 clay; you now have 7 clay.
//1 obsidian-collecting robot collects 1 obsidian; you now have 1 obsidian.
//The new clay-collecting robot is ready; you now have 4 of them.
//
//== Minute 13 ==
//1 ore-collecting robot collects 1 ore; you now have 2 ore.
//4 clay-collecting robots collect 4 clay; you now have 11 clay.
//1 obsidian-collecting robot collects 1 obsidian; you now have 2 obsidian.
//
//== Minute 14 ==
//1 ore-collecting robot collects 1 ore; you now have 3 ore.
//4 clay-collecting robots collect 4 clay; you now have 15 clay.
//1 obsidian-collecting robot collects 1 obsidian; you now have 3 obsidian.
//
//== Minute 15 ==
//Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
//1 ore-collecting robot collects 1 ore; you now have 1 ore.
//4 clay-collecting robots collect 4 clay; you now have 5 clay.
//1 obsidian-collecting robot collects 1 obsidian; you now have 4 obsidian.
//The new obsidian-collecting robot is ready; you now have 2 of them.
//
//== Minute 16 ==
//1 ore-collecting robot collects 1 ore; you now have 2 ore.
//4 clay-collecting robots collect 4 clay; you now have 9 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 6 obsidian.
//
//== Minute 17 ==
//1 ore-collecting robot collects 1 ore; you now have 3 ore.
//4 clay-collecting robots collect 4 clay; you now have 13 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 8 obsidian.
//
//== Minute 18 ==
//Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
//1 ore-collecting robot collects 1 ore; you now have 2 ore.
//4 clay-collecting robots collect 4 clay; you now have 17 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 3 obsidian.
//The new geode-cracking robot is ready; you now have 1 of them.
//
//== Minute 19 ==
//1 ore-collecting robot collects 1 ore; you now have 3 ore.
//4 clay-collecting robots collect 4 clay; you now have 21 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 5 obsidian.
//1 geode-cracking robot cracks 1 geode; you now have 1 open geode.
//
//== Minute 20 ==
//1 ore-collecting robot collects 1 ore; you now have 4 ore.
//4 clay-collecting robots collect 4 clay; you now have 25 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 7 obsidian.
//1 geode-cracking robot cracks 1 geode; you now have 2 open geodes.
//
//== Minute 21 ==
//Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
//1 ore-collecting robot collects 1 ore; you now have 3 ore.
//4 clay-collecting robots collect 4 clay; you now have 29 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 2 obsidian.
//1 geode-cracking robot cracks 1 geode; you now have 3 open geodes.
//The new geode-cracking robot is ready; you now have 2 of them.
//
//== Minute 22 ==
//1 ore-collecting robot collects 1 ore; you now have 4 ore.
//4 clay-collecting robots collect 4 clay; you now have 33 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 4 obsidian.
//2 geode-cracking robots crack 2 geodes; you now have 5 open geodes.
//
//== Minute 23 ==
//1 ore-collecting robot collects 1 ore; you now have 5 ore.
//4 clay-collecting robots collect 4 clay; you now have 37 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 6 obsidian.
//2 geode-cracking robots crack 2 geodes; you now have 7 open geodes.
//
//== Minute 24 ==
//1 ore-collecting robot collects 1 ore; you now have 6 ore.
//4 clay-collecting robots collect 4 clay; you now have 41 clay.
//2 obsidian-collecting robots collect 2 obsidian; you now have 8 obsidian.
//2 geode-cracking robots crack 2 geodes; you now have 9 open geodes.
//
//However, by using blueprint 2 in the example above, you could do even better: the largest number of geodes you could open in 24 minutes is 12.
//
//Determine the quality level of each blueprint by multiplying that blueprint's ID number with the largest number of geodes that can be opened in 24 minutes using that blueprint. In this example, the first blueprint has ID 1 and can open 9 geodes, so its quality level is 9. The second blueprint has ID 2 and can open 12 geodes, so its quality level is 24. Finally, if you add up the quality levels of all of the blueprints in the list, you get 33.
//
//Determine the quality level of each blueprint using the largest number of geodes it could produce in 24 minutes. What do you get if you add up the quality level of all of the blueprints in your list?



class Day19 {
	public static void main(String[] args) {
		try {
			Day19 day = new Day19();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Blueprint> blueprints = new InputLoader("input-day-19.txt").getInput();
		
		long start = System.currentTimeMillis();
		int result = 0;
		for(Blueprint b : blueprints) {
			Factory f = new Factory(b, 24);
			int maxGeodes = f.maxGeodes();
			
			System.out.println(String.format("Blueprint %d:\t Opened %d geodes", b.number, maxGeodes));
			
			result += b.number * maxGeodes;
		}
		
		System.out.println("");
		System.out.println("Result: " + result);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
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
	
	class ClayRobot extends Robot {
		
		public ClayRobot(int costOre) {
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
			state.clayRobots++;
		}
	}
	
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
	
	abstract class Robot {
		final int costOre;
		
		public Robot(int costOre) {
			this.costOre = costOre;
		}
		
		abstract public boolean canBuy(State state);
		abstract public void buy(State state);
		abstract public void add(State state);
	}
	
	class InputLoader extends BasicInputLoader<Blueprint>{
				
		public static final String REGEX = "[^-0-9]+";

		
		public InputLoader(String file) {
			super(file);
		}

		@Override
		protected Blueprint handleLine(String line) {
			String[] split = line.split(":");
			String[] robots = split[1].split("\\.");
			
			OreRobot oreRobot = new OreRobot(Integer.parseInt(robots[0].replaceAll(REGEX, "")));
			ClayRobot clayRobot = new ClayRobot (Integer.parseInt(robots[1].replaceAll(REGEX, "")));
			
			String[] oRobot = robots[2].split("ore");
			ObsidianRobot obsidianRobot = new ObsidianRobot(
					Integer.parseInt(oRobot[0].replaceAll(REGEX, "")),
					Integer.parseInt(oRobot[1].replaceAll(REGEX, "")));
			
			String[] gRobot = robots[3].split("ore");
			GeodeRobot geodeRobot = new GeodeRobot(
					Integer.parseInt(gRobot[0].replaceAll(REGEX, "")),
					Integer.parseInt(gRobot[1].replaceAll(REGEX, "")));
			
			
			return new Blueprint(Integer.parseInt(split[0].replaceAll(REGEX, "")),
					oreRobot, clayRobot, obsidianRobot, geodeRobot);
		}
	}
}
