package nl.jrwer.challenge.advent.day16;

import java.util.Objects;

public class Valve {
	
	final String name;
	final int flowRate;
	final String[] tunnelsLeadTo;
	
	public Valve(String name, int flowRate, String[] tunnelsLeadTo) {
		this.name = name;
		this.flowRate = flowRate;
		this.tunnelsLeadTo = tunnelsLeadTo;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Name: ").append(name)
				.append(" - ")
				.append("Flow rate: ").append(flowRate)
				.append(" - ")
				.append("Tunnel to: ");
				
		if(tunnelsLeadTo != null)
			for(String l : tunnelsLeadTo)
				sb.append(l).append(", ");
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Valve)
			return name.equals(((Valve) obj).name);

		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}