package nl.jrwer.challenge.advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//--- Day 2: Rock Paper Scissors ---
//
//The Elves begin to set up camp on the beach. To decide whose tent gets to be closest to the snack storage, a giant Rock Paper Scissors tournament is already in progress.
//
//Rock Paper Scissors is a game between two players. Each game contains many rounds; in each round, the players each simultaneously choose one of Rock, Paper, or Scissors using a hand shape. Then, a winner for that round is selected: Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock. If both players choose the same shape, the round instead ends in a draw.
//
//Appreciative of your help yesterday, one Elf gives you an encrypted strategy guide (your puzzle input) that they say will be sure to help you win. "The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors. The second column--" Suddenly, the Elf is called away to help with someone's tent.
//
//The second column, you reason, must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors. Winning every time would be suspicious, so the responses must have been carefully chosen.
//
//The winner of the whole tournament is the player with the highest score. Your total score is the sum of your scores for each round. The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
//
//Since you can't be sure if the Elf is trying to help you or trick you, you should calculate the score you would get if you were to follow the strategy guide.
//
//For example, suppose you were given the following strategy guide:
//
//A Y
//B X
//C Z
//
//This strategy guide predicts and recommends the following:
//
//    In the first round, your opponent will choose Rock (A), and you should choose Paper (Y). This ends in a win for you with a score of 8 (2 because you chose Paper + 6 because you won).
//    In the second round, your opponent will choose Paper (B), and you should choose Rock (X). This ends in a loss for you with a score of 1 (1 + 0).
//    The third round is a draw with both players choosing Scissors, giving you a score of 3 + 3 = 6.
//
//In this example, if you were to follow the strategy guide, you would get a total score of 15 (8 + 1 + 6).
//
//What would your total score be if everything goes exactly according to your strategy guide?
public class Day02 {
	public static void main(String[] args) {
		try {
			Day02 day = new Day02();
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
		public final Hand you;
		
		public Round(char opp, char you) {
			this.opponent = Hand.getHand(opp);
			this.you = Hand.getHand(you);
		}
		
		public int getPoints() {
			int points = matchPoints() + you.points;
			
			return points;
		}
		
		private int matchPoints() {
			if((opponent == Hand.ROCK && you == Hand.PAPER) ||
					(opponent == Hand.PAPER && you == Hand.SCISSOR) ||
					(opponent == Hand.SCISSOR && you == Hand.ROCK) ) {
				return 6;
			} else if((you == Hand.ROCK && opponent == Hand.PAPER) ||
					(you == Hand.PAPER && opponent == Hand.SCISSOR) ||
					(you == Hand.SCISSOR && opponent == Hand.ROCK) ) {
				return 0;
			} else {
				// draw
				return 3;
			}
		}
	}
	
	enum Hand {
		ROCK(1),
		PAPER(2),
		SCISSOR(3);
		
		public final int points;
		
		Hand(int points) {
			this.points = points;
		}
		
		public static Hand getHand(char input) {
			switch(input) {
				case 'A':
				case 'X':
					return ROCK;
				case 'B':
				case 'Y':
					return PAPER;
				case 'C':
				case 'Z':
					return SCISSOR;
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
