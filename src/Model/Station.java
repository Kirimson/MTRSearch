package Model;

import java.util.ArrayList;
import java.util.HashSet;

public class Station {

	private String name;
	private HashSet<String> lines;
	private ArrayList<Station> linkedStations;
	//add what stations are connected to this station
	
	public Station(String name) {
		this.name = name;
		lines = new HashSet<String>();
		linkedStations = new ArrayList<Station>();
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
	
	public String getALine() {
		for(String s : lines) {
			return s;
		}
		return "";
	}
	
	public void addLinkedStation(Station s){
		linkedStations.add(s);
	}
	
	public ArrayList<Station> getLinkedStations(){
		
//		StringBuffer sb = new StringBuffer();
//		System.out.println(name);
//		for(Station s : linkedStations){
//			sb.append(s.getName());
//			sb.append(",");
//		}
		
		return linkedStations;
	}

}
