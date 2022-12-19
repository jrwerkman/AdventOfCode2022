package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


//--- Part Two ---
//
//The Elf finishes helping with the tent and sneaks back over to you. "Anyway, the second column says how the round needs to end: X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
//
//The total score is still calculated in the same way, but now you need to figure out what shape to choose so the round ends as indicated. The example above now goes like this:
//
//    In the first round, your opponent will choose Rock (A), and you need the round to end in a draw (Y), so you also choose Rock. This gives you a score of 1 + 3 = 4.
//    In the second round, your opponent will choose Paper (B), and you choose Rock so you lose (X) with a score of 1 + 0 = 1.
//    In the third round, you will defeat your opponent's Scissors with Rock for a score of 1 + 6 = 7.
//
//Now that you're correctly decrypting the ultra top secret strategy guide, you would get a total score of 12.
//
//Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide?

public class Day02Part2 extends Day02 {
	public static void main(String[] args) {
		try {
			Day02Part2 day = new Day02Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Round> rounds = new InputLoader().getRounds();

		int totalPoints = 0;
		for(Round r : rounds)
			totalPoints += r.getPoints();
		
		System.out.println("Total points: " + totalPoints);
		
	}
	
	class Round {
		public final Hand opponent;
		public final MatchStatus status;
		
		public Round(char opp, char status) {
			this.opponent = Hand.getHand(opp);
			this.status = MatchStatus.getHand(status);
		}
		
		public int getPoints() {
			int points = status.points;
			Hand you = yourHand();
			points += you.points;
			
			return points;
		}
		
		private Hand yourHand() {
			switch(status) {
			case DRAW:
				return opponent;
			case LOSE:
				if(opponent == Hand.ROCK)
					return Hand.SCISSOR;
				else if(opponent == Hand.PAPER)
					return Hand.ROCK;
				else 
					return Hand.PAPER;
			case WIN:
				if(opponent == Hand.ROCK)
					return Hand.PAPER;
				else if(opponent == Hand.PAPER)
					return Hand.SCISSOR;
				else 
					return Hand.ROCK;
			default:
				return null;
			}
		}
	}	
	
	enum MatchStatus {
		WIN(6),
		LOSE(0),
		DRAW(3);
		
		public final int points;
		
		MatchStatus(int points) {
			this.points = points;
		}
		
		public static MatchStatus getHand(char input) {
			switch(input) {
				case 'X':
					return LOSE;
				case 'Y':
					return DRAW;
				case 'Z':
					return WIN;
				default:
					return null;
			}
		}
	}	
	
	class InputLoader {
		public List<Round> getRounds() {
			List<Round> list = new ArrayList<>();
			
	        try (InputStream inputStream = Day02.class.getClassLoader().getResourceAsStream("input-day-02.txt");
	        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	        	String line;
	        	
	            while ((line = reader.readLine()) != null) {
	            	if(line.isBlank())
	            		continue;
	            		
	            	String[] results = line.split(" ");
	            	
	            	char opp = results[0].charAt(0);
	            	char you = results[1].charAt(0);
	            	
	            	list.add(new Round(opp, you));
	            }
	        } catch (IOException e) {
				e.printStackTrace();
			}
	        
	        return list;
		}
	}
}
