package nl.jrwer.challenge.advent.day10;

class Register {
	private int xRegister = 1;
	private int passedCycles = 0;
	
	private int cycleToMeasure;
	private final int increment;
	
	public Register() {
		this.increment = 0;
	}
	
	public Register(int startCycleToMeasure, int increment) {
		this.cycleToMeasure = startCycleToMeasure;
		this.increment = increment;
		
	}
	
	public Integer executeInstuction(Instruction instruction) {
		Integer measurement = null;
		
		for(int i=0; i<instruction.cycles; i++) {
			if(instruction.cycles == i + 1)
				xRegister += instruction.value;
			
			passedCycles++;
			
			if(passedCycles == cycleToMeasure) {
				measurement = passedCycles * xRegister;
				cycleToMeasure += increment;
			}
		}
		
		return measurement;
	}
	
	public void addValue(int value) {
		xRegister += value;
	}
	
	public void incrementCycle() {
		passedCycles++;
	}
	
	public int getCycle() {
		return passedCycles;
	}
	
	public int getRegisterX() {
		return xRegister;
	}
	
	public void resetCycle() {
		passedCycles = 0;
	}
}