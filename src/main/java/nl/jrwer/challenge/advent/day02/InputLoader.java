package nl.jrwer.challenge.advent.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class InputLoader {
	public List<Round> getRounds() {
		List<Round> list = new ArrayList<>();
		
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream("input-day-02.txt");
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
	
	public List<SecondRound> getSecondRounds() {
		List<SecondRound> list = new ArrayList<>();
		
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream("input-day-02.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	String line;
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            		
            	String[] results = line.split(" ");
            	
            	char opp = results[0].charAt(0);
            	char you = results[1].charAt(0);
            	
            	list.add(new SecondRound(opp, you));
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}	
}