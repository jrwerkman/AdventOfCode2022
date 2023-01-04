package nl.jrwer.challenge.advent.day10;

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
		List<Instruction> instructions = new InputLoader("input-day-10.txt").getInput();

		Register register = new Register(20, 40);

		int total = 0;
		
		for(Instruction instruction : instructions) {
			Integer measurement = register.executeInstuction(instruction);
			
			if(measurement != null) {
				System.out.println(measurement);
				total += measurement;
			}
		}
		
		System.out.println(total);
	}
}
