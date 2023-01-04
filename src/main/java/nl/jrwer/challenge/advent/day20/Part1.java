package nl.jrwer.challenge.advent.day20;

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
		List<Number> numbers = new InputLoader("input-day-20.txt").getInput();
		
		long start = System.currentTimeMillis();
		Decryptor decryptor = new Decryptor(numbers);
		decryptor.decrypt();
//		decryptor.print();
		System.out.println("\nSum of coordinates: " + decryptor.getCoordinates());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
