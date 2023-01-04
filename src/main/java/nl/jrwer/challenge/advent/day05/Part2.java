package nl.jrwer.challenge.advent.day05;

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
		List<Move> moves = new InputLoader("input-day-05.txt").getInput();
		
		String topCrates = "";
		
		for(Move move : moves)
			topCrates = move.move9001();

		System.out.println(topCrates);
	}
}
