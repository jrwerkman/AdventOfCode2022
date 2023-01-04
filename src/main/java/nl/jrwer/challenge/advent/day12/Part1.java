package nl.jrwer.challenge.advent.day12;

import java.util.List;

public class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
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
		
		map.findFastestPath();
		
		System.out.println("Step to highest point: " + map.steps);
	}
}
