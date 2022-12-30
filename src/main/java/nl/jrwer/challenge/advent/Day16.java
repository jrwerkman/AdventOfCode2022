package nl.jrwer.challenge.advent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 16: Proboscidea Volcanium ---
//
//The sensors have led you to the origin of the distress signal: yet another handheld device, just like the one the Elves gave you. However, you don't see any Elves around; instead, the device is surrounded by elephants! They must have gotten lost in these tunnels, and one of the elephants apparently figured out how to turn on the distress signal.
//
//The ground rumbles again, much stronger this time. What kind of cave is this, exactly? You scan the cave with your handheld device; it reports mostly igneous rock, some ash, pockets of pressurized gas, magma... this isn't just a cave, it's a volcano!
//
//You need to get the elephants out of here, quickly. Your device estimates that you have 30 minutes before the volcano erupts, so you don't have time to go back out the way you came in.
//
//You scan the cave for other options and discover a network of pipes and pressure-release valves. You aren't sure how such a system got into a volcano, but you don't have time to complain; your device produces a report (your puzzle input) of each valve's flow rate if it were opened (in pressure per minute) and the tunnels you could use to move between the valves.
//
//There's even a valve in the room you and the elephants are currently standing in labeled AA. You estimate it will take you one minute to open a single valve and one minute to follow any tunnel from one valve to another. What is the most pressure you could release?
//
//For example, suppose you had the following scan output:
//
//Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
//Valve BB has flow rate=13; tunnels lead to valves CC, AA
//Valve CC has flow rate=2; tunnels lead to valves DD, BB
//Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
//Valve EE has flow rate=3; tunnels lead to valves FF, DD
//Valve FF has flow rate=0; tunnels lead to valves EE, GG
//Valve GG has flow rate=0; tunnels lead to valves FF, HH
//Valve HH has flow rate=22; tunnel leads to valve GG
//Valve II has flow rate=0; tunnels lead to valves AA, JJ
//Valve JJ has flow rate=21; tunnel leads to valve II
//
//All of the valves begin closed. You start at valve AA, but it must be damaged or jammed or something: its flow rate is 0, so there's no point in opening it. However, you could spend one minute moving to valve BB and another minute opening it; doing so would release pressure during the remaining 28 minutes at a flow rate of 13, a total eventual pressure release of 28 * 13 = 364. Then, you could spend your third minute moving to valve CC and your fourth minute opening it, providing an additional 26 minutes of eventual pressure release at a flow rate of 2, or 52 total pressure released by valve CC.
//
//Making your way through the tunnels like this, you could probably open many or all of the valves by the time 30 minutes have elapsed. However, you need to release as much pressure as possible, so you'll need to be methodical. Instead, consider this approach:
//
//== Minute 1 ==
//No valves are open.
//You move to valve DD.
//
//== Minute 2 ==
//No valves are open.
//You open valve DD.
//
//== Minute 3 ==
//Valve DD is open, releasing 20 pressure.
//You move to valve CC.
//
//== Minute 4 ==
//Valve DD is open, releasing 20 pressure.
//You move to valve BB.
//
//== Minute 5 ==
//Valve DD is open, releasing 20 pressure.
//You open valve BB.
//
//== Minute 6 ==
//Valves BB and DD are open, releasing 33 pressure.
//You move to valve AA.
//
//== Minute 7 ==
//Valves BB and DD are open, releasing 33 pressure.
//You move to valve II.
//
//== Minute 8 ==
//Valves BB and DD are open, releasing 33 pressure.
//You move to valve JJ.
//
//== Minute 9 ==
//Valves BB and DD are open, releasing 33 pressure.
//You open valve JJ.
//
//== Minute 10 ==
//Valves BB, DD, and JJ are open, releasing 54 pressure.
//You move to valve II.
//
//== Minute 11 ==
//Valves BB, DD, and JJ are open, releasing 54 pressure.
//You move to valve AA.
//
//== Minute 12 ==
//Valves BB, DD, and JJ are open, releasing 54 pressure.
//You move to valve DD.
//
//== Minute 13 ==
//Valves BB, DD, and JJ are open, releasing 54 pressure.
//You move to valve EE.
//
//== Minute 14 ==
//Valves BB, DD, and JJ are open, releasing 54 pressure.
//You move to valve FF.
//
//== Minute 15 ==
//Valves BB, DD, and JJ are open, releasing 54 pressure.
//You move to valve GG.
//
//== Minute 16 ==
//Valves BB, DD, and JJ are open, releasing 54 pressure.
//You move to valve HH.
//
//== Minute 17 ==
//Valves BB, DD, and JJ are open, releasing 54 pressure.
//You open valve HH.
//
//== Minute 18 ==
//Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
//You move to valve GG.
//
//== Minute 19 ==
//Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
//You move to valve FF.
//
//== Minute 20 ==
//Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
//You move to valve EE.
//
//== Minute 21 ==
//Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
//You open valve EE.
//
//== Minute 22 ==
//Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
//You move to valve DD.
//
//== Minute 23 ==
//Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
//You move to valve CC.
//
//== Minute 24 ==
//Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
//You open valve CC.
//
//== Minute 25 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//== Minute 26 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//== Minute 27 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//== Minute 28 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//== Minute 29 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//== Minute 30 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//This approach lets you release the most pressure possible in 30 minutes with this valve layout, 1651.
//
//Work out the steps to release the most pressure in 30 minutes. What is the most pressure you can release?


class Day16 {
	public static void main(String[] args) {
		try {
			Day16 day = new Day16();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	String[] input = new String[] {
			"input-day-16.txt",
			"input-day-16-example.txt",
			"input-day-16-example2.txt",
			"input-day-16-example3.txt",
			"input-day-16-example4.txt",
			"input-day-16-example5.txt",
	};
	
	int[] answers = new int[] {1906, 1651, 2640, 13468, 1288, 2400};
	int[] answers2 = new int[] {0, 1707, 2670, 12887, 1484, 3680};
	
	public void start() {
		execute(input[0], answers[0]);

//		for(int i=0; i<input.length; i++)
//			execute(input[i], answers[i]);
	}
	
	public void execute(String input, int answer) {
		List<Valve> valves = new InputLoader(input).getInput();
		
		long start = System.currentTimeMillis();
		Routes routes = new Routes(valves);
		Settings settings = new Settings(1, 30);
		Calculator c = new Calculator(valves, routes, settings);
		System.out.println("Run: " + input + ", Number of routes: " + routes.size());
		int result = c.maxPresureRelease();
		System.out.println("Max flown: " + result + " - Correct: " + (result == answer));
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
		
		// Answer: 2056
	}
	
	class Settings {
		final int persons;
		final int time;
		
		public Settings(int persons, int time) {
			this.time = time;
			this.persons = persons;
		}
	}
	
	class Calculator {
		final Settings settings;
		final List<Valve> valves;
		final Routes routes;
		
		final Valve startValve;
		
		public Calculator(List<Valve> valves, Routes routes, Settings settings) {
			this.settings = settings;
			this.valves = valves;
			this.routes = routes;
			this.startValve = getStartValve();
		}
		
		private Valve getStartValve() {
			for(Valve v : valves) 
				if(v.name.equals("AA"))
					return v;
			
			return null;
		}
		
		public int maxPresureRelease() {
			Path start = new Path(settings, startValve);
			
			try {
				LinkedList<Path> q = new LinkedList<>();
				q.add(start);
				
				Path bestPath = start;
				
				while(!q.isEmpty()) {
					Path p = q.removeFirst();
					
					if(p.state.time <= settings.time && p.state.flown > bestPath.state.flown)
						bestPath = p;

					if(p.state.time <= settings.time)
						q.addAll(p.nextPaths(routes));
				}

				System.out.println(bestPath);
				
				return bestPath.state.flown;
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return -1;
		}
		

	}
	
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
	
	class Routes implements Iterable<Route> {
		final List<Valve> valves;
		final List<Route> routes;
		
		public Routes(List<Valve> valves) {
			this.valves = valves;
			this.routes = init();
		}
		
		private List<Route> init() {
			List<Route> routes = new ArrayList<>();
			
			for(int i=0; i<valves.size(); i++)
				for(int j=0; j<valves.size(); j++)
					if(i != j && valves.get(j).flowRate != 0)
						routes.add(initRoute(valves.get(i), valves.get(j)));
			
			return routes;
		}
		
		private Route initRoute(Valve from, Valve to) {
			Route start = new Route(from, from, 0);
			
			List<Valve> visited = new ArrayList<>();
			LinkedList<Route> q = new LinkedList<>();
			q.add(start);
			
			while(!q.isEmpty()) {
				Route r = q.removeFirst();
				
				if(r.to.name.equals(to.name)) {
					return r;
				} else  {
					int nextTime = r.steps + 1;
					
					for(String nextName : r.to.tunnelsLeadTo) {
						Valve nextTo = get(nextName);
						
						if(!visited.contains(nextTo)) {
							visited.add(nextTo);
							q.add(new Route(from, nextTo, nextTime));
						}
					}
				}
			}
			
			return null;
		}
		
		private Valve get(String name) {
			for(Valve v : valves)
				if(v.name.equals(name))
					return v;
			
			System.out.println("name not found: " + name);
			return null;
		}

		@Override
		public Iterator<Route> iterator() {
			return routes.iterator();
		}
		
		public int size() {
			return routes.size();
		}
	}
	
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
	
	class Valve {
		String name;
		int flowRate;
		String[] tunnelsLeadTo;
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Name: ").append(name)
					.append(" - ")
					.append("Flow rate: ").append(flowRate)
					.append(" - ")
					.append("Tunnel to: ");
					
			if(tunnelsLeadTo != null)
				for(String l : tunnelsLeadTo)
					sb.append(l).append(", ");
			
			return sb.toString();
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Valve)
				return name.equals(((Valve) obj).name);

			return false;
		}
	}
	
	class InputLoader extends BasicInputLoader<Valve>{
		
		public static final String REGEX = "[^-0-9]+";
		
		public InputLoader(String file) {
			super(file);
		}

		@Override
		protected Valve handleLine(String line) {
			Valve v = new Valve();
			v.name = line.substring(6, 8);
			v.flowRate = Integer.parseInt(line.replaceAll(REGEX, ""));
			v.tunnelsLeadTo = tunnelsLeadTo(line);

			return v;
		}
		
		public String[] tunnelsLeadTo(String line) {
			String[] split = line.split(";");
			String arr = split[1].substring(23).replaceAll(" ", "");
			
			return arr.split(",");
		}
	}
}
