package nl.jrwer.challenge.advent.day16;

class Route {
	final Valve from;
	final Valve to;
	final int steps;

	public Route(Valve from, Valve to, int steps) {
		this.from = from;
		this.to = to;
		this.steps = steps;
	}
	
	@Override
	public String toString() {
		return String.format("From %s - To %s, in %d minutes", from.name, to.name, steps);
	}
}	