package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 5: Supply Stacks ---
//
//The expedition can depart as soon as the final supplies have been unloaded from the ships. Supplies are stored in stacks of marked crates, but because the needed supplies are buried under many other crates, the crates need to be rearranged.
//
//The ship has a giant cargo crane capable of moving crates between stacks. To ensure none of the crates get crushed or fall over, the crane operator will rearrange them in a series of carefully-planned steps. After the crates are rearranged, the desired crates will be at the top of each stack.
//
//The Elves don't want to interrupt the crane operator during this delicate procedure, but they forgot to ask her which crate will end up where, and they want to be ready to unload them as soon as possible so they can embark.
//
//They do, however, have a drawing of the starting stacks of crates and the rearrangement procedure (your puzzle input). For example:
//
//    [D]    
//[N] [C]    
//[Z] [M] [P]
// 1   2   3 
//
//move 1 from 2 to 1
//move 3 from 1 to 3
//move 2 from 2 to 1
//move 1 from 1 to 2
//
//In this example, there are three stacks of crates. Stack 1 contains two crates: crate Z is on the bottom, and crate N is on top. Stack 2 contains three crates; from bottom to top, they are crates M, C, and D. Finally, stack 3 contains a single crate, P.
//
//Then, the rearrangement procedure is given. In each step of the procedure, a quantity of crates is moved from one stack to a different stack. In the first step of the above rearrangement procedure, one crate is moved from stack 2 to stack 1, resulting in this configuration:
//
//[D]        
//[N] [C]    
//[Z] [M] [P]
// 1   2   3 
//
//In the second step, three crates are moved from stack 1 to stack 3. Crates are moved one at a time, so the first crate to be moved (D) ends up below the second and third crates:
//
//        [Z]
//        [N]
//    [C] [D]
//    [M] [P]
// 1   2   3
//
//Then, both crates are moved from stack 2 to stack 1. Again, because crates are moved one at a time, crate C ends up below crate M:
//
//        [Z]
//        [N]
//[M]     [D]
//[C]     [P]
// 1   2   3
//
//Finally, one crate is moved from stack 1 to stack 2:
//
//        [Z]
//        [N]
//        [D]
//[C] [M] [P]
// 1   2   3
//
//The Elves just need to know which crate will end up on top of each stack; in this example, the top crates are C in stack 1, M in stack 2, and Z in stack 3, so you should combine these together and give the Elves the message CMZ.
//
//After the rearrangement procedure completes, what crate ends up on top of each stack?

public class Day05 {
	public static void main(String[] args) {
		try {
			Day05 day = new Day05();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Move> moves = new InputLoader("input-day-05.txt").getInput();
		
		String topCrates = "";
		
		for(Move move : moves)
			topCrates = move.move();

		System.out.println(topCrates);
	}
	
	class Move {
		final Supplies supplies;
		
		int amount;
		int from;
		int to;
		
		public Move(Supplies supplies, String action) {
			this.supplies = supplies;

			String[] split = action.split(" ");
			
			if(split.length != 6)
				throw new RuntimeException("Wrong input: " + action);
			
			amount = Integer.parseInt(split[1]);
			from = Integer.parseInt(split[3]);
			to = Integer.parseInt(split[5]);
		}
		
		public String move() {
			return supplies.move(amount, from, to);
		}
		
		public String move9001() {
			return supplies.move9001(amount, from, to);
		}
	}
	
	class Supplies {
		int totalStacks = 0;
		Stack[] stacks;
		
		public void addLine(String input) {
			if(totalStacks == 0)
				createStacks(input);
			
			if(!input.contains("["))
				return;
			
			for(int i=0; i<totalStacks; i++) {
				int start = i * 4;
				int end = start + 4;
				
				if(end > input.length())
					end = input.length();
				
				String crate = input.substring(start, end)
						.trim()
						.replace("[", "")
						.replace("]", "");
				
				if(crate.length() == 1) 
					stacks[i].addToBottom(crate.charAt(0));
			}
		}
		
		private void createStacks(String input) {
			int length = input.length();
			totalStacks = (length / 4) + 1;
			
			stacks = new Stack[totalStacks];
			
			for(int i=0; i<totalStacks; i++)
				stacks[i] = new Stack(i);
		}
		
		public String move(int amount, int from, int to) {
			for(int i=0; i<amount; i++) {
				char removed = stacks[from - 1].removeFromTop();
				stacks[to - 1].addToTop(removed);
			}
			
			return getTopCrates();
		}
		
		public String move9001(int amount, int from, int to) {
			char[] crates = new char[amount];
			
			for(int i=0; i<amount; i++)
				crates[i] = stacks[from - 1].removeFromTop();
			
			for(int i=amount; i>0; i--)
				stacks[to - 1].addToTop(crates[i-1]);
			
			return getTopCrates();
		}
		
		public String getTopCrates() {
			StringBuilder sb = new StringBuilder();
			
			for(Stack stack : stacks)
				sb.append(stack.getTopCrate());
			
			return sb.toString();
		}
	}
	
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
	
	class InputLoader extends BasicInputLoader<Move>{

		private Supplies supplies = new Supplies();
		
		public InputLoader(String file) {
			super(file);
		}

		protected Move handleLine(String line) {
			Move move = null;
			
			if(line.startsWith("move"))
				move = new Move(supplies, line);
			else
				supplies.addLine(line);
			
			return move;
		}
	}
}
