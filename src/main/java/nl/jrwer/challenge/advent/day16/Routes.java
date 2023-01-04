package nl.jrwer.challenge.advent.day16;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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