package nl.jrwer.challenge.advent.day16;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

public class InputLoader extends BasicInputLoader<Valve>{
	
	public static final String REGEX = "[^-0-9]+";
	
	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected Valve handleLine(String line) {
		Valve v = new Valve(
				line.substring(6, 8),
				Integer.parseInt(line.replaceAll(REGEX, "")),
				tunnelsLeadTo(line));

		return v;
	}
	
	public String[] tunnelsLeadTo(String line) {
		String[] split = line.split(";");
		String arr = split[1].substring(23).replaceAll(" ", "");
		
		return arr.split(",");
	}
}