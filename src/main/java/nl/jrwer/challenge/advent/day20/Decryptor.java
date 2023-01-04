package nl.jrwer.challenge.advent.day20;

import java.util.List;

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
		for (int i = 0; i < length; i++) {
			Number currentNumber = getByIndex(i);

			if (currentNumber.value == 0)
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
			System.err.println("currentIndex: " + currentIndex + ", " + currentNumber);

			throw e;
		}
	}

	private Number getByIndex(int index) {
		for (Number n : numbers)
			if (n.originalIndex == index)
				return n;

		throw new ArrayIndexOutOfBoundsException(index);
	}

	private Number getByValue(int value) {
		for (Number n : numbers)
			if (n.value == value)
				return n;

		throw new RuntimeException("Number not found with value :" + value);
	}

	private int getNewIndex(Number number, int currentIndex) {
		int distance = (int) (number.value % stepLength);
		int newIndex = currentIndex + distance;

		if (newIndex < 0)
			newIndex = stepLength + newIndex;

		if (newIndex > stepLength)
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

		if (index + amount >= length)
			indexValueAfter = (index + amount) % length;
		else
			indexValueAfter = index + amount;

		Number next = numbers.get(indexValueAfter);
		System.out.println("Value after " + amount + "(index: " + indexValueAfter + ") is: " + next.value);

		return next.value;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Number n : numbers)
			sb.append(n.value).append(", ");

		return sb.toString();
	}

	public void print() {
		System.out.println(this);
	}
}