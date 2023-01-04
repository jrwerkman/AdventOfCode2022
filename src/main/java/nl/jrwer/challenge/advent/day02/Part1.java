package nl.jrwer.challenge.advent.day02;

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
		List<Round> rounds = new InputLoader().getRounds();

		int totalPoints = 0;
		for(Round r : rounds)
			totalPoints += r.getPoints();
		
		System.out.println("Total points: " + totalPoints);
		
	}
}
