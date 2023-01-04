package nl.jrwer.challenge.advent.day14;

import java.util.List;

class Part1 {
	public static void main(String[] args) {
		try {
			Part1 day = new Part1();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void start() {
		List<String> rocks = new InputLoader("input-day-14.txt").getInput();

		RockMap map = new RockMap(rocks);

		while(map.nextRock()) {
//			map.print();
		}
		
//		map.print();
		System.out.println(map.amount);
	}
}
