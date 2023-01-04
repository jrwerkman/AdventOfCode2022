package nl.jrwer.challenge.advent.day14;

import java.util.List;

class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public void start() {
		List<String> rocks = new InputLoader("input-day-14.txt").getInput();

		NewRockMap map = new NewRockMap(rocks);
		System.out.println(map.floor);
		
		while(map.nextRock()) {
//			map.print();
		}
		
		map.print();
		
		// TODO optimize speed
		System.out.println(map.amount);
	}
}
