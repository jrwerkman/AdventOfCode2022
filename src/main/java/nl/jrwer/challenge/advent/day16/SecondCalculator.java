package nl.jrwer.challenge.advent.day16;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.squareup.tape2.QueueFile;

class SecondCalculator extends Calculator {
	final Paths startPaths;

	public SecondCalculator(List<Valve> valves, Routes routes, Settings settings) {
		super(valves, routes, settings);

		this.startPaths = new Paths(settings, startValve);
	}

	public int maxPresureReleaseFileQueue() {
		String basePath = System.getProperty("java.io.tmpdir");
		System.out.println(basePath);
		File file = new File(basePath, "tempQueue.txt");

		try (QueueFile queueFile = new QueueFile.Builder(file).build()) {
			// Remove all elements.
			queueFile.clear();
			queueFile.add(startPaths.getBytes());

			Paths bestPath = startPaths;
			int maxQueueSize = 0;

			while (!queueFile.isEmpty()) {
				byte[] bytes = queueFile.peek();
				queueFile.remove();

				Paths p = Paths.fromBytes(bytes, settings, valves);

				if (p.getTotalTime() <= settings.totalTime && p.getFlown() >= bestPath.getFlown()) {
					System.out.println("New best: Time: " + p.getTotalTime() + ", Flown: " + p.getFlown());
					bestPath = p;
				}

				if (p.getTotalTime() >= bestPath.getTotalTime() && p.getFlown() < bestPath.getFlown()) {
					// System.out.println("Removed: Time: " + p.getTotalTime() + ", Flown: " +
					// p.getFlown());
				} else if (p.getTotalTime() < settings.totalTime) {
					List<Paths> nextPaths = p.nextPaths(routes);

					for (Paths nextPath : nextPaths)
						queueFile.add(nextPath.getBytes());
				}

				if (queueFile.size() > maxQueueSize)
					maxQueueSize = queueFile.size();
			}

			System.out.println("Max queue size: " + maxQueueSize);
			System.out.println(bestPath);

			// Remove all elements.
			queueFile.clear();

			return bestPath.getFlown();
		} catch (Exception e) {
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

			while (!q.isEmpty()) {
				Paths p = q.removeFirst();

				if (p.getTotalTime() <= settings.totalTime && p.getFlown() > bestPath.getFlown()) {
					System.out.println("New best: Time: " + p.getTotalTime() + ", Flown: " + p.getFlown());
					bestPath = p;
				}

				if (p.getTotalTime() >= bestPath.getTotalTime() && p.getFlown() < bestPath.getFlown()) {
					// System.out.println("Removed: Time: " + p.getTotalTime() + ", Flown: " +
					// p.getFlown());
				} else if (p.getTotalTime() < settings.totalTime)
					q.addAll(p.nextPaths(routes));

				if (q.size() > maxQueueSize)
					maxQueueSize = q.size();
			}

			System.out.println("Max queue size: " + maxQueueSize);
			System.out.println(bestPath);

			return bestPath.getFlown();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
}