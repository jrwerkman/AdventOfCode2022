package nl.jrwer.challenge.advent.day16;

import java.util.ArrayList;
import java.util.List;

class Path {

	final int number;
	final Settings settings;
	final State state;

	final Valve currentValve;
	final Valve[] openedValves;
	
	public Path(Settings settings, Valve first) {
		this.number = 0;
		this.settings = settings;
		this.currentValve = first;
		this.openedValves = new Valve[0];
		this.state = new State(0, 0, 0);
	}
	
	Path(Settings settings, State state, Valve currentValve, 
			Valve[] openedValves) {
		this.number = 0;
		this.settings = settings;
		this.state = state;
		this.currentValve = currentValve;
		this.openedValves = openedValves;
	}
	
	public Path(int number, Settings settings, Valve first) {
		this.number = number;
		this.settings = settings;
		this.currentValve = first;
		this.openedValves = new Valve[0];
		this.state = new State(0, 0, 0);
	}
	
	Path(int number, Settings settings, State state, Valve currentValve, 
			Valve[] openedValves) {
		this.number = number;
		this.settings = settings;
		this.state = state;
		this.currentValve = currentValve;
		this.openedValves = openedValves;
	}
	
	public int flow(int times) {
		return state.flown + (times * state.flowRate);
	}
	
	public List<Path> nextPaths(Routes routes) {
		return nextValves(routes);
	}
	
	public List<Path> nextValves(Routes routes) {
		List<Path> next = new ArrayList<>();
		List<Route> nextRoutes = get(routes, currentValve);
		
		for(Route r : nextRoutes)
			nextValve(next, r);
		
		if(next.isEmpty()) {
			State nextState = new State(state.time + 1, 
					state.flowRate,
					flow(1));
			
			next.add(new Path(settings,
					nextState,
					currentValve,
					openedValves));
		}
		
		return next;
			
	}
	
	public void nextValve(List<Path> next, Route r) {
		if(state.time + (r.steps + 1) > settings.time)
			return;
		
		next.add(new Path(settings,
				nextState(r),
				r.to,
				copy(openedValves, currentValve)));
	}
	
	public State nextState(Route r) {
		return new State(state.time + r.steps + 1, 
				state.flowRate + r.to.flowRate,
				flow(r.steps + 1));
	}
	
	public List<Route> get(Routes routes, Valve currentValve) {
		List<Route> nextRoutes = new ArrayList<>();
		
		for(Route r : routes)
			if(r.from.name.equals(currentValve.name))
				if(!isValveOpened(r.to))
					nextRoutes.add(r);

		return nextRoutes;
	}
	
	public boolean isValveOpened(Valve valve) {
		for(Valve v : openedValves) 
			if(v.equals(valve))
				return true;
		
		return false;
	}
	
	public Valve[] copy(Valve[] arr, Valve add) {
		Valve[] newArr = new Valve[arr.length + 1];
		
		for(int i=0; i<arr.length; i++)
			newArr[i] = arr[i];
		
		newArr[newArr.length - 1] = add;
	
		return newArr;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Order: ");
		
		for(Valve v : openedValves)
			sb.append(v.name).append(',');
		
		sb.append('\n');
		sb.append(String.format("Time %d, Flown: %d, Flow rate: %d", state.time, state.flown, state.flowRate));
		
		return sb.toString();
	}
}