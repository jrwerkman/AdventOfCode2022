package nl.jrwer.challenge.advent.day10;

class Screen {
	private final int width = 40;
	
	private Register register = new Register();
	
	StringBuilder screen = new StringBuilder();
	
	public void input(Instruction instruction) {
		for(int i=0; i<instruction.cycles; i++) {
			int cycle = register.getCycle();
			int cursor = register.getRegisterX();

			if(cycle == width) {
				screen.append('\n');
				cycle = register.resetCycle();
			}

			// draw
			if(cursor - 1 <= cycle && cursor + 1 >= cycle)
				screen.append('#');
			else
				screen.append('.');
			
			if(instruction.cycles == i + 1) {
				// increment value
				register.addValue(instruction.value);
			}
			
			register.incrementCycle();
		}
	}
	
	public String getFrame() {
		return screen.toString();
	}
}