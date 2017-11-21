package Model;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	private Line getLine(String name) {
		return lineList.get(name);
	}
	
	public String lineTermini(String name) {
		String termini = "";
		try {
			termini = getLine(name.toLowerCase()).getTermini();
		} catch (NullPointerException e) {
			termini = "Sorry, this line doesn't exist.";
		}
		return termini;
	}
	
	public String toString(String name) {
		String line = "";
		try {
			line = getLine(name.toLowerCase()).toString();
		} catch (NullPointerException e) {
			line = "Sorry, this line doesn't exist.";
		}
		return line;
	}
	
}
