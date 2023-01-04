package nl.jrwer.challenge.advent.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class InputLoader {
	public List<Elf> getElves() {
		List<Elf> list = new ArrayList<>();
		
        try (InputStream inputStream = Part1.class.getClassLoader().getResourceAsStream("input-day-01.txt");
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            
            int number = 1;
            Elf elf = new Elf(number);
            
            while ((line = reader.readLine()) != null) {
                if(line.isBlank()) {
                	list.add(elf);
                	number ++;
                	elf = new Elf(number);
                } else {
                	elf.calories += Integer.parseInt(line);
                }
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
}