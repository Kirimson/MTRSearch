package Model;

import java.util.LinkedList;

public class Line {
	
	private String name;
	private LinkedList<Station> stationList;
	
	/**
	 * This the constrcutor of this class
	 * @param lineName
	 */
	public Line(String lineName){
		name = lineName;
		stationList = new LinkedList<Station>();;
	}
	
	/**
	 * This class adds the {@link Station} to the stationList ArrayList
	 * @param station to be added
	 */
	public void addStation(Station station) {
		stationList.add(station);
	}
	
	/**
	 * This method is to get a termini
	 * @return the name of the termini, where it goes from.
	 */
	public String getTermini(){
		String first = stationList.getFirst().getName();
		String last = stationList.getLast().getName();
		
		return name + " goes from: " + first + " to " + last;
	}
	
	/**
	 * This method retrieves the name of the line
	 * @return the name of the line retrieved
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This methods returns the string representation of the lines
	 */
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

	/**
	 * This station retrieves ant {@link Station} the user is searching for
	 * @return the {@link Station} the user is searching for
	 */
	public LinkedList<Station> getStations(){
		return stationList;
	}
	
}
