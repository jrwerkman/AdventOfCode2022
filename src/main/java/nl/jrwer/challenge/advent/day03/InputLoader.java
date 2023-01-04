package nl.jrwer.challenge.advent.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class InputLoader {
	public List<Group> getGroups() {
		List<Group> list = new ArrayList<>();
		
        try (InputStream inputStream = Part2.class.getClassLoader().getResourceAsStream("input-day-03.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	String line;
        	
        	Group group = new Group();
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            	
            	if(group.addElf(line)) {
            		list.add(group);
            		group = new Group();
            	}
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
	
	public List<Rucksack> getRucksacks() {
		List<Rucksack> list = new ArrayList<>();
		
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream("input-day-03.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	String line;
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            	
            	list.add(new Rucksack(line));
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
}