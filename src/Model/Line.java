package Model;

import java.util.Arrays;
import java.util.LinkedList;

public class Line {
	
	private String name;
	private LinkedList<String> stationList;
	
	public Line(String lineName, String[] stations){
		name = lineName;
		stationList = new LinkedList<String>(Arrays.asList(stations));
	}
	
	public String getTermini(){
		String first = stationList.getFirst();
		String last = stationList.getLast();
		
		return name + " goes from: " + first + " to " + last;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(name + ": ");
		
		for(String s: stationList) {
			if(s.equals(stationList.getLast()))
				sb.append(s);
			else
				sb.append(s+" <-> ");
		}
		
		return sb.toString();
	}

}
