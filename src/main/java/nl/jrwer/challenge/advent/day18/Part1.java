package nl.jrwer.challenge.advent.day18;

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
		List<Cube> cubes = new InputLoader("input-day-18.txt").getInput();
		
		long start = System.currentTimeMillis();
		Grid3D grid = new Grid3D(cubes);
		System.out.println("Exposed surface area: " + grid.exposedSurfaceArea());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
