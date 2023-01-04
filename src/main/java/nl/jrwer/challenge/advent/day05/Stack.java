package nl.jrwer.challenge.advent.day05;

import java.util.ArrayList;
import java.util.List;

class Stack {
	final int index;
	
	List<Character> crates = new ArrayList<>();
	
	public Stack(int index) {
		this.index = index;
	}
	
	public void addToBottom(char crate) {
		crates.add(0, crate);
	}
	
	public void addToTop(char crate) {
		crates.add(crate);
	}
	
	public char removeFromTop() {
		return crates.remove(crates.size() - 1);
	}
	
	public char getTopCrate() {
		if(crates.size() == 0)
			return ' ';
		
		return crates.get(crates.size() -1);
	}
}