package nl.jrwer.challenge.advent.day02;

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
		List<SecondRound> rounds = new InputLoader().getSecondRounds();

		int totalPoints = 0;
		for(SecondRound r : rounds)
			totalPoints += r.getPoints();
		
		System.out.println("Total points: " + totalPoints);
		
	}
}
