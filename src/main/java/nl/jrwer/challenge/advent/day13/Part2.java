package nl.jrwer.challenge.advent.day13;

import java.util.Collections;
import java.util.Comparator;
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
		List<ListElement> entries = new SecondInputLoader("input-day-13.txt").getInput();

		ListElement dividerPacket2 = new ListElement("[[2]]");
		ListElement dividerPacket6 = new ListElement("[[6]]");
		
		entries.add(dividerPacket2);
		entries.add(dividerPacket6);
		
		Collections.sort(entries, new Comparator<Element>() {
			@Override
			public int compare(Element elem1, Element elem2) {
				return elem1.compare(elem2);
			}
		});
		
		int index1 = entries.indexOf(dividerPacket2) + 1;
		int index2 = entries.indexOf(dividerPacket6) + 1;
		
		System.out.println("Index divider packet [2]: " + index1);
		System.out.println("Index divider packet [6]: " + index2);
		System.out.println("Result: " + index1 * index2);
	}
}
