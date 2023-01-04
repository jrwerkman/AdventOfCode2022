package nl.jrwer.challenge.advent.day16;

class Settings {
	final int persons;
	final int time;
	final int totalTime;
	
	public Settings(int persons, int time) {
		this.time = time;
		this.persons = persons;
		this.totalTime = time * persons;
	}
}