package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LineList {
	private HashMap<String, Line> lineList;
	
	/**
	 * Class Constructor initialises lineList HashMap.
	 * <p>
	 * 
	 */
	public LineList() {
		lineList = new HashMap<String, Line>();
	}
	/**
	 * 
	 * Uses {@link Line #getName} to get the correct Key for the Line
	 * <p>
	 *
	 * @param 		ArrayList of lines
	 */
	public void addLines(ArrayList<Line> lines) {
		for(Line line : lines)
		{
			lineList.put(line.getName().toLowerCase(), line);
		}
	}
	/**
	 * 
	 * Returns a line dependent on the Line name specified
	 * <p>
	 *
	 *	 
	 * @param 		A string variable, name
	 * @return 		A Line object
	 */
	public Line getLine(String name) {
		return lineList.get(name);
	}
	/**
	 * 
	 * Uses the getTermini method from {@link Line #getTermini} to retrieve two termini.
	 * <p>
	 *
	 *	 
	 * @param 		A string variable, name
	 * @return 		a string variable, termini
	 */
	public String lineTermini(String name) {
		String termini = "";
		try {
			termini = getLine(name.toLowerCase()).getTermini();
		} catch (NullPointerException e) {
			return "Error: Line '"+name+"' doesn't exist. Please use a valid MTR line";
		}
		return termini;
	}
	
	/**
	 * 
	 * Uses methods {@link Line #getStations} and {@link Station #getLines} in order to get all connected stations
	 * <p>
	 *
	 * @param 		A string variable, name
	 * @return 		HashSet of connected lines
	 */
	public HashSet<String> getConnected(String name){
		HashSet<String> lineNames = new HashSet<String>();
		
		for(Station s : lineList.get(name).getStations()) {
			lineNames.addAll(s.getLines());
		}
		return lineNames;
	}
	
	/**
	 * Returns a string of all the connected lines.
	 * 
	 * <p>
	 *
	 * @param 		A string variable, name
	 * @return 		A string of all connected lines
	 */
	public String findConnectedLines(String name) {
		StringBuffer sb = new StringBuffer();
		
		HashSet<String> lineNames = null;
		
		try {
			lineNames = getConnected(name);
		} catch (NullPointerException e) {
			return "Error: Line '"+name+"' doesn't exist. Please use a valid MTR line";
		}
		
		
		sb.append(getLine(name.toLowerCase()).getName()+" is connected to:\n");
		
		for(String l : lineNames) {
			if(!l.toLowerCase().equals(name))
				sb.append(l+"\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * Formatting a string for output in the console.
	 * <p>
	 *
	 * @param 		A string variable, name
	 * @return 		A formatted string
	 */
	public String toString(String name) {
		String line = "";
		try {
			line = getLine(name.toLowerCase()).toString();
		} catch (NullPointerException e) {
			return "Error: Line '"+name+"' doesn't exist. Please use a valid MTR line";
		}
		return line;
	}
	
}
