package nl.jrwer.challenge.advent.day03;

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
		List<Group> groups = new InputLoader().getGroups();
		
		int total = 0;
		for(Group group : groups) {
			total += group.getPriority();
		}
		
		System.out.println(total);
	}
}
