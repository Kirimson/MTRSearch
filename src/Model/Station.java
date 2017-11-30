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
	}
	
	public String sharesLine(Station otherStation) {
		
		for(String line : lines) {
			if(otherStation.getLines().contains(line))
				for(String otherLine : otherStation.getLines()) {
					if(getLines().contains(otherLine)) {
						if(otherLine != line)
							return line+" and "+otherLine;
					}
				}
		}
		return "not";
	}
	
	public String getLine() {
		for(String line : lines) {
			return line;
		}
		return "";
	}

}
