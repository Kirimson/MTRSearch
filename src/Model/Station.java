package Model;

import java.util.HashSet;

public class Station {

	private String name;
	private HashSet<String> lines;
	private HashSet<Station> linkedStations;

	/**
	 * Constructs a new Station, where name is the Station's name
	 * @param station name
	 */
	public Station(String name) {
		this.name = name;
		lines = new HashSet<String>();
		linkedStations = new HashSet<Station>();
	}
	
	/**
	 * Returns the stations name
	 * @return station's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the the Station's lines it is connected to
	 * @return connected lines to this Station
	 */
	public HashSet<String> getLines(){
		return lines;
	}
	
	/**
	 * Adds a String of a line in string format to the station
	 * @param lines in the 	HashSet
	 */
	public void addLine(String line) {
		lines.add(line);
	}

	/**
	 * Adds a {@link Station} to the ArrayList of linked Stations
	 * @param station is the station to be added
	 */
	public void addLinkedStation(Station station){
		linkedStations.add(station);
	}
	
	/**
	 * Retrieve all linked {@link Station} objects in the linkedStations ArrayList
	 * @return station that the user was searching for
	 */
	public HashSet<Station> getLinkedStations(){
		
		return linkedStations;
	}

}
