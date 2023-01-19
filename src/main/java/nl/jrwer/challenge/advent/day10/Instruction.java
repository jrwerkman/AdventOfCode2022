package nl.jrwer.challenge.advent.day10;

abstract class Instruction {
	protected final int value;
	protected final int cycles;
	
	protected Instruction(int cycles, int value) {
		this.cycles = cycles;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "value: " + value + ", cycles: " + cycles;
	}
}