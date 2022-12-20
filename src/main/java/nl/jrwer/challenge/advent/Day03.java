package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//--- Day 3: Rucksack Reorganization ---
//
//One Elf has the important job of loading all of the rucksacks with supplies for the jungle journey. Unfortunately, that Elf didn't quite follow the packing instructions, and so a few items now need to be rearranged.
//
//Each rucksack has two large compartments. All items of a given type are meant to go into exactly one of the two compartments. The Elf that did the packing failed to follow this rule for exactly one item type per rucksack.
//
//The Elves have made a list of all of the items currently in each rucksack (your puzzle input), but they need your help finding the errors. Every item type is identified by a single lowercase or uppercase letter (that is, a and A refer to different types of items).
//
//The list of items for each rucksack is given as characters all on a single line. A given rucksack always has the same number of items in each of its two compartments, so the first half of the characters represent items in the first compartment, while the second half of the characters represent items in the second compartment.
//
//For example, suppose you have the following list of contents from six rucksacks:
//
//vJrwpWtwJgWrhcsFMMfFFhFp
//jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
//PmmdzqPrVvPwwTWBwg
//wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
//ttgJtRGJQctTZtZT
//CrZsJsPPZsGzwwsLwLmpwMDw
//
//    The first rucksack contains the items vJrwpWtwJgWrhcsFMMfFFhFp, which means its first compartment contains the items vJrwpWtwJgWr, while the second compartment contains the items hcsFMMfFFhFp. The only item type that appears in both compartments is lowercase p.
//    The second rucksack's compartments contain jqHRNqRjqzjGDLGL and rsFMfFZSrLrFZsSL. The only item type that appears in both compartments is uppercase L.
//    The third rucksack's compartments contain PmmdzqPrV and vPwwTWBwg; the only common item type is uppercase P.
//    The fourth rucksack's compartments only share item type v.
//    The fifth rucksack's compartments only share item type t.
//    The sixth rucksack's compartments only share item type s.
//
//To help prioritize item rearrangement, every item type can be converted to a priority:
//
//    Lowercase item types a through z have priorities 1 through 26.
//    Uppercase item types A through Z have priorities 27 through 52.
//
//In the above example, the priority of the item type that appears in both compartments of each rucksack is 16 (p), 38 (L), 42 (P), 22 (v), 20 (t), and 19 (s); the sum of these is 157.
//
//Find the item type that appears in both compartments of each rucksack. What is the sum of the priorities of those item types?

public class Day03 {
	public static void main(String[] args) {
		try {
			Day03 day = new Day03();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Rucksack> rucksacks = new InputLoader().getRucksacks();
		
		int total = 0;
		for(Rucksack rucksack : rucksacks) {
			total += rucksack.getPriority();
		}
		
		System.out.println(total);
	}
	
	class Rucksack extends Priority {
		private char[] firstCompartment;
		private char[] secondCompariment;
		
		public Rucksack(String content) {
			prepare(content);
		}
		
		private void prepare(String content) {
			int length = content.length() / 2;
			
			this.firstCompartment = content.substring(0, length).toCharArray();
			this.secondCompariment = content.substring(length).toCharArray();
			
			if(this.firstCompartment.length != this.secondCompariment.length)
				throw new RuntimeException("Rucksack error: " + content);
		}
		
		@Override
		public char getItem() {
			if(item != 0)
				return item;
			
			for(char first : firstCompartment)
				for(char second : secondCompariment)
					if(first == second) {
						item = first;
						return item;
					}
			
			return 0;
		}

	}
	
	abstract class Priority {
		protected char item = 0;
		protected int priority = 0;
		
		public int getPriority() {
			if(priority != 0)
				return priority;
			
			char item = getItem();
			
			// A-Z
			if(item >= 65 && item <= 90)
				priority = item - 38;
			// a-z
			else if(item >= 97 && item <= 122)
				priority = item - 96;
		
			return priority;
		}
		
		abstract char getItem();
	}

	class InputLoader {
		public List<Rucksack> getRucksacks() {
			List<Rucksack> list = new ArrayList<>();
			
	        try (InputStream inputStream = Day03.class.getClassLoader().getResourceAsStream("input-day-03.txt");
	        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	        	String line;
	        	
	            while ((line = reader.readLine()) != null) {
	            	if(line.isBlank())
	            		continue;
	            	
	            	list.add(new Rucksack(line));
	            }
	        } catch (IOException e) {
				e.printStackTrace();
			}
	        
	        return list;
		}
	}
}
