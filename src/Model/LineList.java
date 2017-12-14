package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LineList {
	private HashMap<String, Line> lineList;
	
	public LineList() {
		lineList = new HashMap<String, Line>();
	}
	
	public void addLines(ArrayList<Line> lines) {
		for(Line line : lines)
		{
			lineList.put(line.getName().toLowerCase(), line);
		}
	}
	
	public Line getLine(String name) {
		return lineList.get(name);
	}
	
	public String lineTermini(String name) {
		String termini = "";
		try {
			termini = getLine(name.toLowerCase()).getTermini();
		} catch (NullPointerException e) {
			return "Sorry, line '"+name+"' doesn't exist. Please use a valid MTR line";
		}
		return termini;
	}
	
	public HashSet<String> hashConn(String name){
		HashSet<String> lineNames = new HashSet<String>();
		
		for(Station s : lineList.get(name).getStations()) {
			lineNames.addAll(s.getLines());
		}
		return lineNames;
	}
	
	public String findConnectedLines(String name) {
		StringBuffer sb = new StringBuffer();
		
		HashSet<String> lineNames = null;
		
		try {
			lineNames = hashConn(name);
		} catch (NullPointerException e) {
			return "Sorry, line '"+name+"' doesn't exist. Please use a valid MTR line";
		}
		
		
		sb.append(getLine(name.toLowerCase()).getName()+" is connected to:\n");
		
		for(String l : lineNames) {
			if(!l.toLowerCase().equals(name))
				sb.append(l+"\n");
		}
		
		return sb.toString();
	}
	
	public String toString(String name) {
		String line = "";
		try {
			line = getLine(name.toLowerCase()).toString();
		} catch (NullPointerException e) {
			return "Sorry, line '"+name+"' doesn't exist. Please use a valid MTR line";
		}
		return line;
	}
	
}
