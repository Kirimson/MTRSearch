package Model;

import java.util.ArrayList;
import java.util.HashSet;

public class Station {

	private String name;
	private HashSet<String> lines;
	private ArrayList<Station> linkedStations;
	//add what stations are connected to this station
	/**
	 * This is the constructor for the class.
	 * @param station name
	 */
	public Station(String name) {
		this.name = name;
		lines = new HashSet<String>();
		linkedStations = new ArrayList<Station>();
	}
	
	/**
	 * This method returns the stations name
	 * @return station's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method returns the the station lines which are listed in a HashSet
	 * @return station lines
	 */
	public HashSet<String> getLines(){
		return lines;
	}
	
	/**
	 * This method is to add a line to the line Hashmap
	 * @param lines in the 	HashSet
	 */
	public void addLine(String line) {
		lines.add(line);
	}
	
	/**
	 * This method is used to retrieve a station line
	 * @return the line that the user searched
	 */
	
	public String getLine() {
		for(String line : lines) {
			return line;
		}
		return "";
	}

/**
 * 	This method is used to add stations to the ArrayList
 * @param s is the station to be added
 */
	public void addLinkedStation(Station s){
		linkedStations.add(s);
	}
	
	/**
	 * This method is used to retrieve stations in the ArrayList
	 * @return station that the user was searching for
	 */
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
