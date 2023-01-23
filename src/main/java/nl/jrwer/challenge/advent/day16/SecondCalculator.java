package nl.jrwer.challenge.advent.day16;

import java.util.List;

public class SecondCalculator extends Calculator {
	final Paths startPaths;
	Paths bestPath;
	
	public SecondCalculator(List<Valve> valves, Routes routes, Settings settings) {
		super(valves, routes, settings);

		this.startPaths = new Paths(settings, startValve);
		this.bestPath = startPaths;
	}
	
	@Override
	public int maxPresureRelease() {
		checkPaths(bestPath);
		
		return bestPath.getFlown();
	}
	
	private void checkPaths(Paths p) {
		if (p.getTotalTime() <= settings.totalTime && p.getFlown() >= bestPath.getFlown())
			bestPath = p;
		
		if (p.getTotalTime() >= bestPath.getTotalTime() && p.getFlown() < bestPath.getFlown()) {
			return;
		} else {
			List<Paths> nextPaths = p.nextPaths(routes);
	
			for (Paths nextPath : nextPaths)
				checkPaths(nextPath);
		}
	}
}
