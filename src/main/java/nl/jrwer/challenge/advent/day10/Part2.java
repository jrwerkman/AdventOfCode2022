package nl.jrwer.challenge.advent.day10;

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
		List<Instruction> instructions = new InputLoader("input-day-10.txt").getInput();

		Screen screen = new Screen();

		for(Instruction instruction : instructions) {
			screen.input(instruction);
		}
		
		// TODO check print
		System.out.println(screen.getFrame());
	}
}
