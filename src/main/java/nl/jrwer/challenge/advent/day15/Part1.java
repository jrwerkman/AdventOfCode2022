package nl.jrwer.challenge.advent.day15;

import java.util.List;

class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Sensor> sensors = new InputLoader("input-day-15.txt").getInput();

		long start = System.currentTimeMillis();
		BeaconMap map = new BeaconMap(sensors);
		System.out.println("Occupied possition: " + map.positionNotBeacon(2000000));
		
		long end = System.currentTimeMillis();
		System.out.println("Solution took " + (end - start) + " ms");
	}
}
