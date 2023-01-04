package nl.jrwer.challenge.advent.day19;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

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