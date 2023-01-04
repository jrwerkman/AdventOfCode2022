package nl.jrwer.challenge.advent.day15;

import java.util.List;

class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Sensor> sensors = new InputLoader("input-day-15.txt").getInput();

		long start = System.currentTimeMillis();
		NewBeaconMap map = new NewBeaconMap(sensors);
		Coord c = map.findGap(0,4000000);
		long result = (4000000L * (long) c.x) + (long) c.y;
		
		System.out.println("Result coords: " + c);
		System.out.println("Tuning frequency: " + result);
		long end = System.currentTimeMillis();

		System.out.println("Solution took " + (end - start) + " ms");
	}
}
