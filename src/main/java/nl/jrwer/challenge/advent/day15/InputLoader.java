package nl.jrwer.challenge.advent.day15;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

class InputLoader extends BasicInputLoader<Sensor>{
	
	public static final String REGEX = "[^-0-9]+";
	
	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected Sensor handleLine(String line) {
		String[] split = line.split(":");
		String[] sensorS = split[0].split(",");
		String[] beaconS = split[1].split(",");
		
		Beacon beacon = new Beacon(getInt(beaconS[0]), getInt(beaconS[1]));
		Sensor sensor = new Sensor(getInt(sensorS[0]), getInt(sensorS[1]), beacon);

		return sensor;
	}
	
	private int getInt(String input) {
		return Integer.parseInt(input.replaceAll(REGEX, ""));
	}

}