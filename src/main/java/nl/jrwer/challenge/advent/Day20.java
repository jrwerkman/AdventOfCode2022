package nl.jrwer.challenge.advent;

import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 20: Grove Positioning System ---
//
//It's finally time to meet back up with the Elves. When you try to contact them, however, you get no reply. Perhaps you're out of range?
//
//You know they're headed to the grove where the star fruit grows, so if you can figure out where that is, you should be able to meet back up with them.
//
//Fortunately, your handheld device has a file (your puzzle input) that contains the grove's coordinates! Unfortunately, the file is encrypted - just in case the device were to fall into the wrong hands.
//
//Maybe you can decrypt it?
//
//When you were still back at the camp, you overheard some Elves talking about coordinate file encryption. The main operation involved in decrypting the file is called mixing.
//
//The encrypted file is a list of numbers. To mix the file, move each number forward or backward in the file a number of positions equal to the value of the number being moved. The list is circular, so moving a number off one end of the list wraps back around to the other end as if the ends were connected.
//
//For example, to move the 1 in a sequence like 4, 5, 6, 1, 7, 8, 9, the 1 moves one position forward: 4, 5, 6, 7, 1, 8, 9. To move the -2 in a sequence like 4, -2, 5, 6, 7, 8, 9, the -2 moves two positions backward, wrapping around: 4, 5, 6, 7, 8, -2, 9.
//
//The numbers should be moved in the order they originally appear in the encrypted file. Numbers moving around during the mixing process do not change the order in which the numbers are moved.
//
//Consider this encrypted file:
//
//1
//2
//-3
//3
//-2
//0
//4
//
//Mixing this file proceeds as follows:
//
//Initial arrangement:
//1, 2, -3, 3, -2, 0, 4
//
//1 moves between 2 and -3:
//2, 1, -3, 3, -2, 0, 4
//
//2 moves between -3 and 3:
//1, -3, 2, 3, -2, 0, 4
//
//-3 moves between -2 and 0:
//1, 2, 3, -2, -3, 0, 4
//
//3 moves between 0 and 4:
//1, 2, -2, -3, 0, 3, 4
//
//-2 moves between 4 and 1:
//1, 2, -3, 0, 3, 4, -2
//
//0 does not move:
//1, 2, -3, 0, 3, 4, -2
//
//4 moves between -3 and 0:
//1, 2, -3, 4, 0, 3, -2
//
//Then, the grove coordinates can be found by looking at the 1000th, 2000th, and 3000th numbers after the value 0, wrapping around the list as necessary. In the above example, the 1000th number after 0 is 4, the 2000th is -3, and the 3000th is 2; adding these together produces 3.
//
//Mix your encrypted file exactly once. What is the sum of the three numbers that form the grove coordinates?


class Day20 {
	public static void main(String[] args) {
		try {
			Day20 day = new Day20();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void start() {
//		System.out.println(-5354 % 4999);
//		System.out.println(4966 + -355);
//		
//		if(true)
//			return;
		List<Number> numbers = new InputLoader("input-day-20.txt").getInput();
		
		long start = System.currentTimeMillis();
		Decryptor decryptor = new Decryptor(numbers);
		decryptor.decrypt();
//		decryptor.print();
		System.out.println("\nSum of coordinates: " + decryptor.getCoordinates());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
		
		// 442 is to low
	}
	
	class Decryptor {
		final List<Number> numbers;
		final int length;
		final int stepLength;
		
		public Decryptor(List<Number> numbers) {
			this.numbers = numbers;
			this.length = numbers.size();
			this.stepLength = length - 1;
		}
		
		public void decrypt() {
			for(int i=0; i<length; i++) {
				Number currentNumber = getByIndex(i);
				
				if(currentNumber.value == 0)
					continue;

				move(currentNumber);
				
//				System.out.print(i + "("+currentNumber.value+"): ");
//				print();
			}
		}
		
		private void move(Number currentNumber) {
			int currentIndex = 0;
			
			try {
				currentIndex = numbers.indexOf(currentNumber);
				int newIndex = getNewIndex(currentNumber, currentIndex);

				numbers.remove(currentIndex);
				numbers.add(newIndex, currentNumber);
			} catch (Exception e) {
				System.err.println("currentIndex: " + currentIndex + ", " +  currentNumber);
				
				throw e;
			}
		}
		
		private Number getByIndex(int index) {
			for(Number n : numbers) 
				if(n.originalIndex == index)
					return n;
			
			throw new ArrayIndexOutOfBoundsException(index);
		}

		private Number getByValue(int value) {
			for(Number n : numbers) 
				if(n.value == value)
					return n;
			
			throw new RuntimeException("Number not found with value :" + value);
		}

		private int getNewIndex(Number number, int currentIndex) {
			int distance = (int) (number.value % stepLength);
			int newIndex = currentIndex + distance;
			
			if(newIndex < 0) 
				newIndex = stepLength + newIndex;
				
			if(newIndex > stepLength)
				newIndex = newIndex - stepLength;
			
			return newIndex;
		}
		
		public long getCoordinates() {
			Number numberNull = getByValue(0);
			int index = numbers.indexOf(numberNull);
			System.out.println("Index of value 0: " + index);
			long coords = 0;
			
			coords += getValueAfter(index, 1000);
			coords += getValueAfter(index, 2000);
			coords += getValueAfter(index, 3000);
			
			return coords;
		}
		
		private long getValueAfter(int index, int amount) {
			int indexValueAfter = 0;

			if(index + amount >= length)
				indexValueAfter = (index + amount) % length;
			else
				indexValueAfter = index + amount;
				
			Number next = numbers.get(indexValueAfter);
			System.out.println("Value after " + amount + "(index: "+indexValueAfter+") is: " + next.value);
			
			return next.value;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for(Number n : numbers)
				sb.append(n.value).append(", ");
			
			return sb.toString();
		}
		
		public void print() {
			System.out.println(this);
		}
	}
	
	class Number {
		final int originalIndex;
		final long value;
		
		public Number(int originalIndex, long value) {
			this.originalIndex = originalIndex;
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Number) {
				Number c = (Number) obj;
				
				return c.originalIndex == originalIndex && c.value == value;
			}
			
			return false;
		}
		
		@Override
		public String toString() {
			return "originalIndex: " + originalIndex + ", value: " + value;
		}
	}
	
	class InputLoader extends BasicInputLoader<Number>{
		int index = 0;
		public InputLoader(String file) {
			super(file);
		}

		@Override
		protected Number handleLine(String line) {
			Number number = new Number(index, Long.parseLong(line));
			index++;
			return number;
		}
	}
}
