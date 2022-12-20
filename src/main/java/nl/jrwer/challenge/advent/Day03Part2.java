package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//--- Part Two ---
//
//As you finish identifying the misplaced items, the Elves come to you with another issue.
//
//For safety, the Elves are divided into groups of three. Every Elf carries a badge that identifies their group. For efficiency, within each group of three Elves, the badge is the only item type carried by all three Elves. That is, if a group's badge is item type B, then all three Elves will have item type B somewhere in their rucksack, and at most two of the Elves will be carrying any other item type.
//
//The problem is that someone forgot to put this year's updated authenticity sticker on the badges. All of the badges need to be pulled out of the rucksacks so the new authenticity stickers can be attached.
//
//Additionally, nobody wrote down which item type corresponds to each group's badges. The only way to tell which item type is the right one is by finding the one item type that is common between all three Elves in each group.
//
//Every set of three lines in your list corresponds to a single group, but each group can have a different badge item type. So, in the above example, the first group's rucksacks are the first three lines:
//
//vJrwpWtwJgWrhcsFMMfFFhFp
//jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
//PmmdzqPrVvPwwTWBwg
//
//And the second group's rucksacks are the next three lines:
//
//wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
//ttgJtRGJQctTZtZT
//CrZsJsPPZsGzwwsLwLmpwMDw
//
//In the first group, the only item type that appears in all three rucksacks is lowercase r; this must be their badges. In the second group, their badge item type must be Z.
//
//Priorities for these items must still be found to organize the sticker attachment efforts: here, they are 18 (r) for the first group and 52 (Z) for the second group. The sum of these is 70.
//
//Find the item type that corresponds to the badges of each three-Elf group. What is the sum of the priorities of those item types?

public class Day03Part2 extends Day03 {
	public static void main(String[] args) {
		try {
			Day03Part2 day = new Day03Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Group> groups = new InputLoader().getGroups();
		
		int total = 0;
		for(Group group : groups) {
			total += group.getPriority();
		}
		
		System.out.println(total);
	}
	
	class Group extends Priority {
		private String[] rucksacks = new String[3];
		private int elvesAdded = 0;
		
		public boolean addElf(String rucksack) {
			if(elvesAdded > 3)
				throw new RuntimeException("Group is full");
			
			rucksacks[elvesAdded] = rucksack;
			elvesAdded++;
			
			return elvesAdded == 3;
		}

		/**
		 * get batch item, so item that each of the three rucksacks carry
		 */
		@Override
		public char getItem() {
			if(elvesAdded != 3)
				throw new RuntimeException("Group is not complete, elves added: " + elvesAdded);
			
			if(item != 0)
				return item;
			
			char[] firstAndSecondDuplicatie = getDuplicates(
					rucksacks[0].toCharArray(), 
					rucksacks[1].toCharArray());
			
			char[] duplicates = getDuplicates(
					firstAndSecondDuplicatie, 
					rucksacks[2].toCharArray());
			
			if(duplicates.length != 1)
				throw new RuntimeException("There should be 1 common item, but found: " + duplicates.length);
			
			item = duplicates[0];
			
			return item;
		}
		
		private char[] getDuplicates(char[] first, char[] second) {
			Set<Character> set = new HashSet<>();
			
			for(char firstChar : first)
				for(char secondChar : second)
					if(firstChar == secondChar)
						set.add(firstChar);
			
			char[] result = new char[set.size()];
			
			int i=0;
			for(Character c : set) {
				result[i] = c;
				i++;
			}
			
			return result;
		}
	}
	
	class InputLoader {
		public List<Group> getGroups() {
			List<Group> list = new ArrayList<>();
			
	        try (InputStream inputStream = Day03Part2.class.getClassLoader().getResourceAsStream("input-day-03.txt");
	        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	        	String line;
	        	
	        	Group group = new Group();
	        	
	            while ((line = reader.readLine()) != null) {
	            	if(line.isBlank())
	            		continue;
	            	
	            	if(group.addElf(line)) {
	            		list.add(group);
	            		group = new Group();
	            	}
	            }
	        } catch (IOException e) {
				e.printStackTrace();
			}
	        
	        return list;
		}
	}
}
