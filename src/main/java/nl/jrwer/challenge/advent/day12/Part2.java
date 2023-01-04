package nl.jrwer.challenge.advent.day12;

import java.util.List;

public class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void start() {
		List<String> input = new InputLoader("input-day-12.txt").getInput();
		Map map = new Map(input);

		System.out.println("Start at: " + map.start.x + "," + map.start.y);
		System.out.println("End at: " + map.end.x + "," + map.end.y);
		
		map.findFastestPathOtherStartingPoint();
		
		System.out.println("Step to highest point: " + map.steps);
	}
}
