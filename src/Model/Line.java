package Model;

import java.util.LinkedList;

public class Line {
	
	private String name;
	private LinkedList<Station> stationList;
	
	public Line(String lineName){
		name = lineName;
		stationList = new LinkedList<Station>();;
	}
	
	public void addStation(Station station) {
		stationList.add(station);
	}
	
	public String getTermini(){
		String first = stationList.getFirst().getName();
		String last = stationList.getLast().getName();
		
		return name + " goes from: " + first + " to " + last;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(name + ": ");
		
		for(Station s: stationList) {
			if(s.getName().equals(stationList.getLast().getName()))
				sb.append(s.getName());
			else
				sb.append(s.getName()+" <-> ");
		}
		
		return sb.toString();
	}

	public LinkedList<Station> getLinkedStations(){
		return stationList;
	}
	
}
