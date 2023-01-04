package nl.jrwer.challenge.advent.day20;

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
	
	public static final long KEY = 811_589_153L;
	
	public void start() {
		List<Number> numbers = new InputLoader("input-day-20.txt", KEY).getInput();
		
		long start = System.currentTimeMillis();
		Decryptor decryptor = new Decryptor(numbers);
		
		for(int i=0; i<10; i++)
			decryptor.decrypt();
		
		System.out.println("\nSum of coordinates: " + decryptor.getCoordinates());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
