package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class StationList {

	private HashMap<String, Station> stationList;
	
	/**
	 * This is the constructor of this class
	 */
	public StationList() {
		stationList = new HashMap<String, Station>();
	}
	
	/**
	 * This method add {@link Station} to the stationList HashMap
	 * @param list is the stations in the HashMap
	 */
	public void addStations(HashMap<String, Station> list) {
		stationList = list;
	}
	
	/**
	 * Retrieves the {@link Station} specified by the name parameter
	 * @param name of station
	 * @return {@link Station} object specified
	 */
	public Station getStation(String name) {
		return stationList.get(name.toLowerCase());
	}
	
	/**
	 * Finds the {@link Station} specified as goal from the {@link Station} specified as start.
	 * Uses Breadth First Search to find the goal station
	 * @param start is the station the path is starting from
	 * @param goal is the end of the path
	 * @return HashMap containing a station, and the station the algorithm used last to get to this station
	 */
	public HashMap<Station, ArrayList<Station>> findPath(Station start, Station goal){
		
		//create open and closed sets for search data 
		LinkedList<Station> openSet = new LinkedList<Station>();
		HashSet<Station> closedSet = new HashSet<Station>();
		HashMap<Station, ArrayList<Station>> path = new HashMap<Station, ArrayList<Station>>();
		
		//add the starting station to the open set (to search)
		openSet.add(start);
		
		//while there are still stations to be searched
		while(!openSet.isEmpty()) {
			
			//pop the current station from the open set
			Station current = openSet.pop();
			
			//for every station that is linked to the current station
			for(Station linkedStation : current.getLinkedStations()) {
				
				//if this station has already been fully searched, don't search it
				if(closedSet.contains(linkedStation))
					continue;
				
				//if this station hasn't been looked at before, set up the ArrayList value for this station's parents
				if(path.get(linkedStation) == null)
					path.put(linkedStation, new ArrayList<Station>());
				
				//if the linked station is the goal
				if(linkedStation == goal) {
					//add the current station (the parent) to the ArrayList
					path.get(linkedStation).add(current);
					return path;
				}
				
				//if the open set does not contain this station already
				if(openSet.contains(linkedStation) == false) {
					//add the current station (the parent) to the ArrayList
					path.get(linkedStation).add(current);
					
					//add this station to the open set, to be searched later
					openSet.add(linkedStation);
				}
				
			}
			
			//add the current station to the closed set after it has been fully searched
			closedSet.add(current);
			
		}
		
		return path;
	}

}
