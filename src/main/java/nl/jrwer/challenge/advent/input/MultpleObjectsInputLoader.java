package nl.jrwer.challenge.advent.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.Day04;

public abstract class MultpleObjectsInputLoader<T> {
	
	private final String file;
	private List<T> list = new ArrayList<>();
	
	public MultpleObjectsInputLoader(String file) {
		this.file = file;
	}
	
	public List<T> getInput() {
		
        try (InputStream inputStream = Day04.class.getClassLoader().getResourceAsStream(file);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        	String line;
        	
            while ((line = reader.readLine()) != null) {
            	if(line.isBlank())
            		continue;
            	
            	List<T> obj = handleLine(line);
            	
            	if(obj != null)
            		list.addAll(obj);
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        return list;
	}
	
	protected abstract List<T> handleLine(String line);
}
