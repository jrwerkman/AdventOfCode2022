package nl.jrwer.challenge.advent;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.squareup.tape2.QueueFile;

//The first half of this puzzle is complete! It provides one gold star: *
//--- Part Two ---
//
//You're worried that even with an optimal approach, the pressure released won't be enough. What if you got one of the elephants to help you?
//
//It would take you 4 minutes to teach an elephant how to open the right valves in the right order, leaving you with only 26 minutes to actually execute your plan. Would having two of you working together be better, even if it means having less time? (Assume that you teach the elephant before opening any valves yourself, giving you both the same full 26 minutes.)
//
//In the example above, you could teach the elephant to help you as follows:
//
//== Minute 1 ==
//No valves are open.
//You move to valve II.
//The elephant moves to valve DD.
//
//== Minute 2 ==
//No valves are open.
//You move to valve JJ.
//The elephant opens valve DD.
//
//== Minute 3 ==
//Valve DD is open, releasing 20 pressure.
//You open valve JJ.
//The elephant moves to valve EE.
//
//== Minute 4 ==
//Valves DD and JJ are open, releasing 41 pressure.
//You move to valve II.
//The elephant moves to valve FF.
//
//== Minute 5 ==
//Valves DD and JJ are open, releasing 41 pressure.
//You move to valve AA.
//The elephant moves to valve GG.
//
//== Minute 6 ==
//Valves DD and JJ are open, releasing 41 pressure.
//You move to valve BB.
//The elephant moves to valve HH.
//
//== Minute 7 ==
//Valves DD and JJ are open, releasing 41 pressure.
//You open valve BB.
//The elephant opens valve HH.
//
//== Minute 8 ==
//Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
//You move to valve CC.
//The elephant moves to valve GG.
//
//== Minute 9 ==
//Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
//You open valve CC.
//The elephant moves to valve FF.
//
//== Minute 10 ==
//Valves BB, CC, DD, HH, and JJ are open, releasing 78 pressure.
//The elephant moves to valve EE.
//
//== Minute 11 ==
//Valves BB, CC, DD, HH, and JJ are open, releasing 78 pressure.
//The elephant opens valve EE.
//
//(At this point, all valves are open.)
//
//== Minute 12 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//...
//
//== Minute 20 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//...
//
//== Minute 26 ==
//Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
//
//With the elephant helping, after 26 minutes, the best you could do would release a total of 1707 pressure.
//
//With you and an elephant working together for 26 minutes, what is the most pressure you could release?


class Day16Part2 extends Day16 implements Serializable {
	private static final long serialVersionUID = -7777475603737921673L;

	public static void main(String[] args) {
		try {
			Day16Part2 day = new Day16Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		execute(input[0], answers2[0]);
//		execute(input[1], answers2[1]);

//		for(int i=2; i<input.length; i++)
//			execute(input[i], answers2[i]);
	}
	
	public void execute(String input, int answer) {
		List<Valve> valves = new InputLoader(input).getInput();
		
		long start = System.currentTimeMillis();
		Routes routes = new Routes(valves);
		Settings settings = new Settings(2, 26);
		CalculatorPart2 c = new CalculatorPart2(valves, routes, settings);
		System.out.println("Run: " + input + ", Number of routes: " + routes.size());
		int result = c.maxPresureReleaseFileQueue();
		System.out.println("Max flown: " + result + " - Correct: " + (result == answer));
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
	class CalculatorPart2 extends Calculator {
		final Paths startPaths;
		
		public CalculatorPart2(List<Valve> valves, Routes routes, Settings settings) {
			super(valves, routes, settings);
			
			this.startPaths = new Paths(settings, startValve);
		}
		
		public int maxPresureReleaseFileQueue() {
			String basePath = System.getProperty("java.io.tmpdir");
			System.out.println(basePath);
			File file = new File(basePath, "tempQueue.txt");
			
			try (QueueFile queueFile = new QueueFile.Builder(file).build()){
				// Remove all elements.
				queueFile.clear();
				queueFile.add(startPaths.getBytes());
				
				Paths bestPath = startPaths;
				int maxQueueSize = 0;
				
				while(!queueFile.isEmpty()) {
					byte[] bytes = queueFile.peek();
					queueFile.remove();
					
					Paths p = fromBytes(bytes, settings, valves);
					
					if(p.getTotalTime() <= settings.totalTime && p.getFlown() >= bestPath.getFlown()) {
						System.out.println("New best: Time: " + p.getTotalTime() + ", Flown: " + p.getFlown());
						bestPath = p;
					}

					if(p.getTotalTime() >= bestPath.getTotalTime() && p.getFlown() < bestPath.getFlown()) {
						//System.out.println("Removed: Time: " + p.getTotalTime() + ", Flown: " + p.getFlown());
					} else if(p.getTotalTime() < settings.totalTime) {
						List<Paths> nextPaths = p.nextPaths(routes);
						
						for(Paths nextPath : nextPaths)
							queueFile.add(nextPath.getBytes());
					}
					
					if(queueFile.size() > maxQueueSize)
						maxQueueSize = queueFile.size();
				}

				System.out.println("Max queue size: " + maxQueueSize);
				System.out.println(bestPath);
				
				// Remove all elements.
				queueFile.clear();
				
				return bestPath.getFlown();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return -1;
		}
		
		public int maxPresureReleaseObjects() {
			try {
				LinkedList<Paths> q = new LinkedList<>();
				q.add(startPaths);
				
				Paths bestPath = startPaths;
				int maxQueueSize = 0;
				
				while(!q.isEmpty()) {
					Paths p = q.removeFirst();
					
					if(p.getTotalTime() <= settings.totalTime && p.getFlown() > bestPath.getFlown()) {
						System.out.println("New best: Time: " + p.getTotalTime() + ", Flown: " + p.getFlown());
						bestPath = p;
					}
	
					if(p.getTotalTime() >= bestPath.getTotalTime() && p.getFlown() < bestPath.getFlown()) {
						//System.out.println("Removed: Time: " + p.getTotalTime() + ", Flown: " + p.getFlown());
					} else if(p.getTotalTime() < settings.totalTime)
						q.addAll(p.nextPaths(routes));
					
					if(q.size() > maxQueueSize)
						maxQueueSize = q.size();
				}
				
				System.out.println("Max queue size: " + maxQueueSize);
				System.out.println(bestPath);
				
				return bestPath.getFlown();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return -1;
		}
	}	
	
	class Paths {

		final Settings settings;
		final Path[] paths;
		
		public Paths(Settings settings, Valve start) {
			this.settings = settings;
			this.paths = getInitalPaths(start);
		}
		
		public Paths(Settings settings, Path[] paths) {
			this.settings = settings;
			this.paths = paths;
		}
		
		private Path[] getInitalPaths(Valve start) {
			Path[] initialPaths = new Path[settings.persons];

			for(int i=0; i<settings.persons; i++)
				initialPaths[i] = new Path(i, settings, start);
			
			return initialPaths;
		}

		public List<Paths> nextPaths(Routes routes) {
			if(getTime() >= settings.time)
				return new ArrayList<>();
			
			Path lowestTimePath = getLowestTimePath();
			
			if(lowestTimePath == null)
				throw new RuntimeException("");
			
			List<Route> nextRoutes = get(routes, lowestTimePath.currentValve);
			List<Paths> next = new ArrayList<>();
			
			for(Route r : nextRoutes)
				nextValve(lowestTimePath, next, r);
			
			if(next.isEmpty()) 
				finishPaths(next);
			
			return next;
		}
		
		public void finishPaths(List<Paths> next) {
			Path[] nextPaths = Arrays.copyOf(paths, paths.length);
			
			for(int i=0; i<nextPaths.length; i++) {
				int remainingTime = settings.time - nextPaths[i].state.time;
				
				if(remainingTime > 0) {
					Path p = nextPaths[i];
					State state = p.state;
					State nextState = new State(state.time + remainingTime, 
							state.flowRate,
							p.flow(remainingTime));
					
					nextPaths[i] = new Path(i, settings, nextState, p.currentValve, p.openedValves);
				}
			}
			
			next.add(new Paths(settings, nextPaths));
		}
		
		
		public void nextValve(Path path, List<Paths> next, Route r) {
			if(path.state.time + (r.steps + 1) > path.settings.time)
				return;
			
			Path[] nextPaths = Arrays.copyOf(paths, paths.length);
			
			nextPaths[path.number] = new Path(path.number, settings,
					path.nextState(r),
					r.to,
					path.copy(path.openedValves, r.to));
			
			next.add(new Paths(settings, nextPaths));
		}		
		
		public List<Route> get(Routes routes, Valve currentValve) {
			List<Route> nextRoutes = new ArrayList<>();
			
			for(Route r : routes)
				if(r.from.name.equals(currentValve.name))
					if(!isValveOpened(r.to))
						nextRoutes.add(r);

			return nextRoutes;
		}
		
		private boolean isValveOpened(Valve valve) {
			for(Path p : paths)
				if(p.isValveOpened(valve))
					return true;
			
			return false;
		}		
		
		public Path getLowestTimePath() {
			int time = settings.time;
			Path path = null;
			
			for(int index=0; index<paths.length; index++) 
				if(paths[index].state.time <= time) {
					time = paths[index].state.time;
					path = paths[index];
				}
			
			return path;
		}
		
		public int getTime() {
			int higestTime = 0;
			
			for(Path p : paths)
				if(p.state.time > higestTime)
					higestTime = p.state.time;
			
			return higestTime;
		}		
		
		public int getTotalTime() {
			int time = 0;
			
			for(Path p : paths)
				time += p.state.time;
			
			return time;
		}
		
		public boolean isTime(Paths ps) {
			int time = 0;
			
			for(Path p : paths)
				time += p.state.time;
			
			return time >= settings.totalTime;
		}
		
		public int getFlown() {
			int flown = 0;
			
			for(Path p : paths)
				flown += p.state.flown;
			
			return flown;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for(Path p : paths)
				sb.append(p.number).append(": ").append(p).append('\n');
			
			return sb.toString();
		}
		
		public String getString() {
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<paths.length; i++) {
				Path p = paths[i];
				
				sb.append(p.state.time)
						.append(":").append(p.state.flowRate)
						.append(":").append(p.state.flown);
				
				sb.append(",");
				sb.append(p.currentValve.name);
				
				for(Valve v : p.openedValves) {
					sb.append(",");
					sb.append(v.name);
				}
				
				if(i < paths.length - 1)
					sb.append(";");
			}
			
//			System.out.println(sb.toString());
			
			return sb.toString();
		}
		
		public byte[] getBytes() {
			return getString().getBytes();
		}
		

	}
	
	public Paths fromString(String str, Settings settings, List<Valve> valves) {
		String[] splitPaths = str.split(";");
		Path[] paths = new Path[splitPaths.length];
		
		for(int i=0; i<splitPaths.length; i++) {
			String[] split = splitPaths[i].split(",");
			
			String[] strState = split[0].split(":");
			
			State state = new State(Integer.parseInt(strState[0]), 
					Integer.parseInt(strState[1]), 
					Integer.parseInt(strState[2]));
			
			Valve currentValve = getValve(split[1], valves);
			
			Valve[] openedValves = new Valve[0];
			
			if(split.length > 2) {
				openedValves = new Valve[split.length - 2];
				
				for(int j=2; j< split.length; j++) {
					openedValves[j - 2] = getValve(split[j], valves);
				}
			}
			
			paths[i] = new Path(i, settings, state, currentValve, openedValves);
		}
		
		return new Paths(settings, paths);
	}
	
	public Paths fromBytes(byte[] bytes, Settings settings, List<Valve> valves) {
		return fromString(new String(bytes), settings, valves);
	}
	
	private Valve getValve(String name, List<Valve> valves) {
		for(Valve v : valves)
			if(v.name.equals(name))
				return v;
		
		return null;
	}	
}
