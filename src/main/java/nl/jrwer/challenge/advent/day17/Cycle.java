package nl.jrwer.challenge.advent.day17;

class Cycle {
	int occurences = 0;
	
	long cycleStartStone = 0;
	long cycleStartTop = 0;
	
	public void setStart(State state) {
		cycleStartStone = state.rockCount;
		cycleStartTop = state.top;
	}
}