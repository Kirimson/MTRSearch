package Model;

import java.util.LinkedList;

public class Line {
	
	private String name;
	private LinkedList<Station> stationList;
	
	/**
	 * Creates a new {@link Line} object, using lineName as the Line's name
	 * @param lineName the name of the {@link Line}
	 */
	public Line(String lineName){
		name = lineName;
		stationList = new LinkedList<Station>();
	}
	
	/**
	 * Adds the {@link Station} to this Line's ArrayList of stations
	 * @param station to be added
	 */
	public void addStation(Station station) {
		stationList.add(station);
	}
	
	/**
	 * Returns the start and end stations of the Line
	 * @return the name of the termini
	 */
	public String getTermini(){
		String first = stationList.getFirst().getName();
		String last = stationList.getLast().getName();
		
		return name + " goes from: " + first + " to " + last;
	}
	
	/**
	 * Retrieves the name of the line
	 * @return the name of the line retrieved
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the string representation of the lines
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
	 * Retrieves the list of {@link Station} objects in stationList
	 * @return the LinkedList of stations contained by the Line
	 */
	public LinkedList<Station> getStations(){
		return stationList;
	}
	
}
