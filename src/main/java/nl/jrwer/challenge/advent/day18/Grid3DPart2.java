package nl.jrwer.challenge.advent.day18;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Grid3DPart2 extends Grid3D {

	public Grid3DPart2(List<Cube> cubes) {
		super(cubes);
		
		System.out.println(String.format("x-max: %d, y-max: %d, z-max: %d", 
				dim[0], dim[1], dim[2]));
	}
	
	@Override
	public int exposedSurfaceArea() {
		List<Cube> exposedSides = getExposedSides();
		
		System.out.println("Exposed surface area: " + exposedSides.size());

		int exposed = 0;
		
		for(Cube c: exposedSides) {
			if(c.toString().equals("2,2,2"))
				System.out.println("inlist:" + c);				
			
			if(exterior(c))
				exposed++;
		}
		
		return exposed;
	}
	
	private List<Cube> getExposedSides() {
		List<Cube> exposedSides = new ArrayList<>();
		
		for(Cube c : this.cubes) {
			Cube[] nbs = getNeighbours(c);
			
			for(Cube nb : nbs)
				if(!this.cubes.contains(nb))
					exposedSides.add(nb);
		}
		
		return exposedSides;
	}
	
	public boolean exterior(Cube cube) {
		Queue<Cube> q = new ArrayDeque<>();
		Set<Cube> visited = new HashSet<>();
		
		q.add(cube);
		visited.add(cube);
		visited.addAll(this.cubes);
		
		while(!q.isEmpty()) {
			Cube head = q.remove();
			
			if(isBorder(head))
				return true;
			
			addNeighbours(q, visited, head);
		}
		
		return false;
	}

	
	public void addNeighbours(Queue<Cube> q, Set<Cube> visited, Cube cube) {
		Cube[] neihgbours = getNeighbours(cube);
		
		for(Cube n : neihgbours) {
			if(!visited.contains(n)) {
				q.add(n);
				visited.add(n);
			}
		}
	}
	
	public Cube[] getNeighbours(Cube c) {
		return getNeighbours(c.x, c.y, c.z);
	}
	
	public Cube[] getNeighbours(int x, int y, int z) {
		return new Cube[] {
				new Cube(x - 1, y, z),
				new Cube(x + 1, y, z),
				new Cube(x, y - 1, z),
				new Cube(x, y + 1, z),
				new Cube(x, y, z - 1),
				new Cube(x, y, z + 1),
		};
	}
}