package nl.jrwer.challenge.advent.day09;

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
		List<Motion> motions = new InputLoader("input-day-09.txt").getInput();

		Rope rope = new Rope(1);

		for(Motion motion : motions)
			rope.move(motion);

//		rope.printResult();
		
		System.out.println(rope.getTail().visitedPositions());
	}
	

	

	

	

	

	
	
	
	

}
