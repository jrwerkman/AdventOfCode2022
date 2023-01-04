package nl.jrwer.challenge.advent.day07;

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
		List<String> input = new InputLoader("input-day-07.txt").getInput();
		
		Terminal t = new Terminal(input);

		int totalDiskSpace = 70000000;
		int requiredDiskSpace = 30000000;
		int usedDiskSpace = t.root.size;
		int minimalSpaceToFree = requiredDiskSpace - (totalDiskSpace - usedDiskSpace);
		
	
		System.out.println("Used disk space: " + usedDiskSpace);
		System.out.println("Currently free disk space: " + (totalDiskSpace - usedDiskSpace));
		System.out.println("Minimal space to free: " + minimalSpaceToFree);
		
		Directory dirToDelete = t.root; 
		
		for(Directory d : t.directories)
			if(d.size >= minimalSpaceToFree && d.size < dirToDelete.size)
				dirToDelete = d;
		

		System.out.println("Directory to delete: " + dirToDelete.name + " with size: " + dirToDelete.size);
	}
}
