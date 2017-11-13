package Model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Line {
	
	private String name;
	private LinkedList<StationNode> list;
	
	public Line(String lineName, ArrayList<String> stations){
		
	}
	
	public String getTermini(){
		String first = list.getFirst().getName();
		String last = list.getLast().getName();
		
		return  "Line " + name + " goes from: " + first + " to " + last;
	}

}
