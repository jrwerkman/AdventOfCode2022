package nl.jrwer.challenge.advent.day16;

import java.util.LinkedList;
import java.util.List;

public class Calculator {
	protected final Settings settings;
	protected final List<Valve> valves;
	protected final Routes routes;
	
	protected final Valve startValve;
	
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
			int maxQueueSize = 0;
			
			while(!q.isEmpty()) {
				Path p = q.removeFirst();
				
				if(p.state.time <= settings.time && p.state.flown > bestPath.state.flown)
					bestPath = p;

				if(p.state.time <= settings.time)
					q.addAll(p.nextPaths(routes));
				
				if(q.size() > maxQueueSize)
					maxQueueSize = q.size();
			}

			System.out.println("Max queue size: " + maxQueueSize);
			System.out.println(bestPath);
			
			return bestPath.state.flown;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	

}