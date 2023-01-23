package nl.jrwer.challenge.advent.day16;

public class Settings {
	public final int persons;
	public final int time;
	public final int totalTime;
	
	public Settings(int persons, int time) {
		this.time = time;
		this.persons = persons;
		this.totalTime = time * persons;
	}
}