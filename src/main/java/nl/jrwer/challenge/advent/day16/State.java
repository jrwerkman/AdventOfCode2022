package nl.jrwer.challenge.advent.day16;

class State {

	final int time;
	final int flowRate;
	final int flown;
	
	public State(int time, int flowRate, int flown) {
		this.time = time;
		this.flowRate = flowRate;
		this.flown = flown;
	}
}