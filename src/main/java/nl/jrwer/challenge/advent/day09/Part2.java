package nl.jrwer.challenge.advent.day09;

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
		List<Motion> motions = new InputLoader("input-day-09.txt").getInput();

		Rope rope = new Rope(9);

		for(Motion motion : motions)
			rope.move(motion);
		
//		rope.printResult();
		
		System.out.println(rope.getTail().visitedPositions());
	}
}
