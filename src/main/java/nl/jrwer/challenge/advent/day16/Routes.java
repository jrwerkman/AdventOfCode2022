package nl.jrwer.challenge.advent.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Routes {
	final List<Valve> valves;
	final Map<String, List<Route>> routes;
	
	public Routes(List<Valve> valves) {
		this.valves = valves;
		this.routes = init();
	}
	
	private Map<String, List<Route>> init() {
		Map<String, List<Route>> routes = new HashMap<>();
		
		for(int i=0; i<valves.size(); i++)
			for(int j=0; j<valves.size(); j++)
				if(i != j && valves.get(j).flowRate != 0) {
					Valve from = valves.get(i);
					Valve to = valves.get(j);
					
					Route r = initRoute(from, to); 
					
					if(routes.containsKey(from.name)) {
						routes.get(from.name).add(r);
					} else {
						List<Route> list = new ArrayList<>();
						list.add(r);
						
						routes.put(from.name, list);
					}
				}
		
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
					Valve nextTo = getValve(nextName);
					
					if(!visited.contains(nextTo)) {
						visited.add(nextTo);
						q.add(new Route(from, nextTo, nextTime));
					}
				}
			}
		}
		
		return null;
	}
	
	private Valve getValve(String name) {
		for(Valve v : valves)
			if(v.name.equals(name))
				return v;
		
		System.out.println("name not found: " + name);
		return null;
	}
	
	public List<Route> get(String nameFromValve) {
		return this.routes.get(nameFromValve);
	}
	
	public int size() {
		return routes.size();
	}
}