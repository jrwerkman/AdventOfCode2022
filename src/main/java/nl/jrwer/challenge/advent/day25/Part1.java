package nl.jrwer.challenge.advent.day25;

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
		List<Snafu> snafus = new InputLoader("input-day-25.txt").getInput();

		long start = System.currentTimeMillis();
		long total = 0L;
		for (Snafu s : snafus)
			total += s.getDecimal();
		
		System.out.println("Total: " + total);
		System.out.println("SNAFU: " + new Snafu(total).getSnafu());
		long end = System.currentTimeMillis();

		System.out.println("Process took: " + (end - start) + " ms\n");
	}

}
