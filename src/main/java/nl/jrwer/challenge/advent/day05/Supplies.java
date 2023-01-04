package nl.jrwer.challenge.advent.day05;

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