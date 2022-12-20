package nl.jrwer.challenge.advent;

import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Part Two ---
//
//Your device's communication system is correctly detecting packets, but still isn't working. It looks like it also needs to look for messages.
//
//A start-of-message marker is just like a start-of-packet marker, except it consists of 14 distinct characters rather than 4.
//
//Here are the first positions of start-of-message markers for all of the above examples:
//
//    mjqjpqmgbljsphdztnvjfqwrcgsmlb: first marker after character 19
//    bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 23
//    nppdvjthqldpwncqszvftbrmjlhg: first marker after character 23
//    nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 29
//    zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 26
//
//How many characters need to be processed before the first start-of-message marker is detected?

public class Day06Part2 extends Day06 {
	public static void main(String[] args) {
		try {
			Day06Part2 day = new Day06Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*
mjqjpqmgbljsphdztnvjfqwrcgsmlb
bvwbjplbgvbhsrlpgdmjqwftvncz
nppdvjthqldpwncqszvftbrmjlhg
nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
	 */
	
	public void start() {
		List<DataStreamBuffer> streams = new InputLoader("input-day-06.txt").getInput();
		
		for(DataStreamBuffer stream : streams) {
			System.out.println(stream.firstMarkerAtferPosition(14));
		}
	}
	
	class InputLoader extends BasicInputLoader<DataStreamBuffer>{

		public InputLoader(String file) {
			super(file);
		}

		protected DataStreamBuffer handleLine(String line) {
			return new DataStreamBuffer(line);
		}
	}
}
