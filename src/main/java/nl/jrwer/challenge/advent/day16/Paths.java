package nl.jrwer.challenge.advent.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paths {

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

		for (int i = 0; i < settings.persons; i++)
			initialPaths[i] = new Path(i, settings, start);

		return initialPaths;
	}

	public List<Paths> nextPaths(Routes routes) {
		if (getTime() >= settings.time)
			return new ArrayList<>();

		Path lowestTimePath = getLowestTimePath();

		if (lowestTimePath == null)
			throw new RuntimeException("");

		List<Route> nextRoutes = get(routes, lowestTimePath.currentValve);
		List<Paths> next = new ArrayList<>();

		for (Route r : nextRoutes)
			nextValve(lowestTimePath, next, r);

		if (next.isEmpty())
			finishPaths(next);

		return next;
	}

	public void finishPaths(List<Paths> next) {
		Path[] nextPaths = new Path[paths.length];

		for (int i = 0; i < nextPaths.length; i++) {
			int remainingTime = settings.time - paths[i].state.time;

			if (remainingTime > 0) {
				Path p = paths[i];
				State state = p.state;
				State nextState = new State(state.time + remainingTime, state.flowRate, p.flow(remainingTime));

				nextPaths[i] = new Path(i, settings, nextState, p.currentValve, p.openedValves);
			} else {
				nextPaths[i] = paths[i];
			}
		}

		next.add(new Paths(settings, nextPaths));
	}

	public void nextValve(Path path, List<Paths> next, Route r) {
		if (path.state.time + (r.steps + 1) > path.settings.time)
			return;

		Path[] nextPaths = Arrays.copyOf(paths, paths.length);

		nextPaths[path.number] = new Path(path.number, settings, path.nextState(r), r.to,
				path.copy(path.openedValves, r.to));

		next.add(new Paths(settings, nextPaths));
	}

	public List<Route> get(Routes routes, Valve currentValve) {
		List<Route> possibleNextRoutes = routes.get(currentValve.name);
		List<Route> nextRoutes = new ArrayList<>();
		
		for (Route r : possibleNextRoutes)
			if (!isValveOpened(r.to))
				nextRoutes.add(r);

		return nextRoutes;
	}

	private boolean isValveOpened(Valve valve) {
		for (Path p : paths)
			if (p.isValveOpened(valve))
				return true;

		return false;
	}

	public Path getLowestTimePath() {
		int time = settings.time;
		Path path = null;

		for (int index = 0; index < paths.length; index++)
			if (paths[index].state.time <= time) {
				time = paths[index].state.time;
				path = paths[index];
			}

		return path;
	}

	public int getTime() {
		int higestTime = 0;

		for (Path p : paths)
			if (p.state.time > higestTime)
				higestTime = p.state.time;

		return higestTime;
	}

	public int getTotalTime() {
		int time = 0;

		for (Path p : paths)
			time += p.state.time;

		return time;
	}

	public boolean isTime(Paths ps) {
		int time = 0;

		for (Path p : paths)
			time += p.state.time;

		return time >= settings.totalTime;
	}

	public int getFlown() {
		int flown = 0;

		for (Path p : paths)
			flown += p.state.flown;

		return flown;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Path p : paths)
			sb.append(p.number).append(": ").append(p).append('\n');

		return sb.toString();
	}

	public String getString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < paths.length; i++) {
			Path p = paths[i];

			sb.append(p.state.time).append(":").append(p.state.flowRate).append(":").append(p.state.flown);

			sb.append(",");
			sb.append(p.currentValve.name);

			for (Valve v : p.openedValves) {
				sb.append(",");
				sb.append(v.name);
			}

			if (i < paths.length - 1)
				sb.append(";");
		}

//			System.out.println(sb.toString());

		return sb.toString();
	}
}