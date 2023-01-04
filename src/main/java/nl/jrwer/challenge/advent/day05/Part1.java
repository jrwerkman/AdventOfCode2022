package nl.jrwer.challenge.advent.day05;

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
		List<Move> moves = new InputLoader("input-day-05.txt").getInput();
		
		String topCrates = "";
		
		for(Move move : moves)
			topCrates = move.move();

		System.out.println(topCrates);
	}
}
