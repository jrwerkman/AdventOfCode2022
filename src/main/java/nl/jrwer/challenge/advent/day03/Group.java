package nl.jrwer.challenge.advent.day03;

import java.util.HashSet;
import java.util.Set;

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