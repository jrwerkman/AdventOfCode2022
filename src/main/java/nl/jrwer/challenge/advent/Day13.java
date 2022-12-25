package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.MultipleLinesInputLoader;

//--- Day 13: Distress Signal ---
//
//You climb the hill and again try contacting the Elves. However, you instead receive a signal you weren't expecting: a distress signal.
//
//Your handheld device must still not be working properly; the packets from the distress signal got decoded out of order. You'll need to re-order the list of received packets (your puzzle input) to decode the message.
//
//Your list consists of pairs of packets; pairs are separated by a blank line. You need to identify how many pairs of packets are in the right order.
//
//For example:
//
//[1,1,3,1,1]
//[1,1,5,1,1]
//
//[[1],[2,3,4]]
//[[1],4]
//
//[9]
//[[8,7,6]]
//
//[[4,4],4,4]
//[[4,4],4,4,4]
//
//[7,7,7,7]
//[7,7,7]
//
//[]
//[3]
//
//[[[]]]
//[[]]
//
//[1,[2,[3,[4,[5,6,7]]]],8,9]
//[1,[2,[3,[4,[5,6,0]]]],8,9]
//
//Packet data consists of lists and integers. Each list starts with [, ends with ], and contains zero or more comma-separated values (either integers or other lists). Each packet is always a list and appears on its own line.
//
//When comparing two values, the first value is called left and the second value is called right. Then:
//
//    If both values are integers, the lower integer should come first. If the left integer is lower than the right integer, the inputs are in the right order. If the left integer is higher than the right integer, the inputs are not in the right order. Otherwise, the inputs are the same integer; continue checking the next part of the input.
//    If both values are lists, compare the first value of each list, then the second value, and so on. If the left list runs out of items first, the inputs are in the right order. If the right list runs out of items first, the inputs are not in the right order. If the lists are the same length and no comparison makes a decision about the order, continue checking the next part of the input.
//    If exactly one value is an integer, convert the integer to a list which contains that integer as its only value, then retry the comparison. For example, if comparing [0,0,0] and 2, convert the right value to [2] (a list containing 2); the result is then found by instead comparing [0,0,0] and [2].
//
//Using these rules, you can determine which of the pairs in the example are in the right order:
//
//== Pair 1 ==
//- Compare [1,1,3,1,1] vs [1,1,5,1,1]
//  - Compare 1 vs 1
//  - Compare 1 vs 1
//  - Compare 3 vs 5
//    - Left side is smaller, so inputs are in the right order
//
//== Pair 2 ==
//- Compare [[1],[2,3,4]] vs [[1],4]
//  - Compare [1] vs [1]
//    - Compare 1 vs 1
//  - Compare [2,3,4] vs 4
//    - Mixed types; convert right to [4] and retry comparison
//    - Compare [2,3,4] vs [4]
//      - Compare 2 vs 4
//        - Left side is smaller, so inputs are in the right order
//
//== Pair 3 ==
//- Compare [9] vs [[8,7,6]]
//  - Compare 9 vs [8,7,6]
//    - Mixed types; convert left to [9] and retry comparison
//    - Compare [9] vs [8,7,6]
//      - Compare 9 vs 8
//        - Right side is smaller, so inputs are not in the right order
//
//== Pair 4 ==
//- Compare [[4,4],4,4] vs [[4,4],4,4,4]
//  - Compare [4,4] vs [4,4]
//    - Compare 4 vs 4
//    - Compare 4 vs 4
//  - Compare 4 vs 4
//  - Compare 4 vs 4
//  - Left side ran out of items, so inputs are in the right order
//
//== Pair 5 ==
//- Compare [7,7,7,7] vs [7,7,7]
//  - Compare 7 vs 7
//  - Compare 7 vs 7
//  - Compare 7 vs 7
//  - Right side ran out of items, so inputs are not in the right order
//
//== Pair 6 ==
//- Compare [] vs [3]
//  - Left side ran out of items, so inputs are in the right order
//
//== Pair 7 ==
//- Compare [[[]]] vs [[]]
//  - Compare [[]] vs []
//    - Right side ran out of items, so inputs are not in the right order
//
//== Pair 8 ==
//- Compare [1,[2,[3,[4,[5,6,7]]]],8,9] vs [1,[2,[3,[4,[5,6,0]]]],8,9]
//  - Compare 1 vs 1
//  - Compare [2,[3,[4,[5,6,7]]]] vs [2,[3,[4,[5,6,0]]]]
//    - Compare 2 vs 2
//    - Compare [3,[4,[5,6,7]]] vs [3,[4,[5,6,0]]]
//      - Compare 3 vs 3
//      - Compare [4,[5,6,7]] vs [4,[5,6,0]]
//        - Compare 4 vs 4
//        - Compare [5,6,7] vs [5,6,0]
//          - Compare 5 vs 5
//          - Compare 6 vs 6
//          - Compare 7 vs 0
//            - Right side is smaller, so inputs are not in the right order
//
//What are the indices of the pairs that are already in the right order? (The first pair has index 1, the second pair has index 2, and so on.) In the above example, the pairs in the right order are 1, 2, 4, and 6; the sum of these indices is 13.
//
//Determine which pairs of packets are already in the right order. What is the sum of the indices of those pairs?


class Day13 {
	public static void main(String[] args) {
		try {
			Day13 day = new Day13();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public void start() {
		List<Pair> pairs = new InputLoader("input-day-13.txt").getInput();

		int result = 0;
		
//		for(Pair pair : pairs) {
//			pair.print();
//		}
		
		for(Pair pair : pairs) {
			System.out.println("Comparing pair: " + pair.number);
			
			if(pair.compare()) {
				System.out.println("Pair is right order: " + pair.number);
				
				result += pair.number;
			}
		}
		
		System.out.println("Result: " + result);
		
		// Answer: 5185
	}
	
	class ListElement implements Element {
		List<Element> element = new ArrayList<>();
		
		public ListElement() {
			
		}
		
		public ListElement(String input) {
			parseInput(this, input, 1);
		}
		
		private int parseInput(ListElement parentElement, String input, int index) {
			for(int i=index; i<input.length() - 1; i++) {
				if(input.charAt(i) == '[') {
					ListElement childElement = new ListElement();
					parentElement.element.add(childElement);
					
					i = parseInput(childElement, input, i + 1);
				} else if(input.charAt(i) == ']') {
					return i;
				} else if(input.charAt(i) == ',') {
					// do nothing
				} else {
					IntegerElement element = new IntegerElement();
					i = getDigit(element, input, i);
					
					parentElement.element.add(element);
				}
			}
			
			return input.length();
		}
		
		private int getDigit(IntegerElement element, String input, int i) {
			int charsForDigit = 0;
			
			for(;i<input.length()-1; i++) {
				if(input.charAt(i) == ']' || input.charAt(i) == ',')
					break;
				
				charsForDigit++;
			}
			
			element.value = Integer.parseInt(input.substring(i - charsForDigit, i));
			
			return i;
		}		
		
		public Element get(int i) {
			return element.get(i);
		}
		
		public int size() {
			return element.size();
		}

		@Override
		public Integer compare(Element other) {
			ListElement lOther;
			
			if(other instanceof IntegerElement) {
				lOther = new ListElement();
				lOther.element.add(other);
			} else if (other instanceof ListElement) {
				lOther = (ListElement) other;
			} else {
				throw new RuntimeException("Uncomparable class: " + other.getClass().getSimpleName());
			}
			
			int till = size() < lOther.size() ? size() : lOther.size();
			
			for(int i=0; i<till; i++) {
				Element elem1 = get(i);
				Element elem2 = lOther.get(i);
				
				Integer result = elem1.compare(elem2);
				
				if(result != null && result != 0)
					return result;
			}

			return size() - lOther.size();
		}

		@Override
		public void print(StringBuilder sb) {
			sb.append('[');
			for(Element e : element) {
				e.print(sb);
			}
			sb.append(']');
		}
	}
	
	class IntegerElement implements Element {
		int value;

		@Override
		public Integer compare(Element other) {
			if(other instanceof IntegerElement) {
				IntegerElement iOther = (IntegerElement) other;
				
				return value - iOther.value;
			} else if (other instanceof ListElement) {
				ListElement thisListElem = new ListElement();
				thisListElem.element.add(this);
				
				return thisListElem.compare(other);
			} else {
				throw new RuntimeException("Uncomparable class: " + other.getClass().getSimpleName());
			}
		}

		@Override
		public void print(StringBuilder sb) {
			sb.append(value).append(',');
			
		}
	}
	
	interface Element {
		Integer compare(Element other);
		void print(StringBuilder sb);
	}
	
	class Pair {
		int number;
		ListElement left = new ListElement();
		ListElement right = new ListElement();
		
		public Pair(String[] input, int number) {
			left = new ListElement(input[0]);
			right = new ListElement(input[1]);

			this.number = number;
		}
		
		public boolean compare() {
			int results = left.compare(right);
			
			return results < 0;
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();

			left.print(sb);
			sb.append('\n');
			
			right.print(sb);
			sb.append("\n\n");
			
			System.out.println(sb.toString());
		}
	}
	
	class InputLoader extends MultipleLinesInputLoader<Pair>{
		
		int number = 0;
		
		public InputLoader(String file) {
			super(file, 2);
		}

		@Override
		protected Pair handleLines(String[] lines) {
			number++;
			return new Pair(lines, number);
		}

		@Override
		protected void postProcess(List<Pair> objects) {

		}
	}
}
