package nl.jrwer.challenge.advent.day18;

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
		List<Cube> cubes = new InputLoader("input-day-18.txt").getInput();
		
		long start = System.currentTimeMillis();
		Grid3DPart2 grid = new Grid3DPart2(cubes);
		System.out.println("\nResult: Exterior surface area: " + grid.exposedSurfaceArea());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
