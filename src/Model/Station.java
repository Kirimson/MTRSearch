package Model;

import java.util.HashSet;

public class Station {

	private String name;
	private HashSet<String> lines;
	
	public Station(String name) {
		this.name = name;
		lines = new HashSet<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public HashSet<String> getLines(){
		return lines;
	}
	
	public void addLine(String line) {
		lines.add(line);
//		System.out.println(name+": "+ line);
	}

}
