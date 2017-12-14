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
	 * This method add {@link Station} to the stationList hashmap
	 * @param list is the stations in the hashmap
	 */
	public void addStations(HashMap<String, Station> list) {
		stationList = list;
	}
	
	/**
	 * This mehod retrieves the {@link Station} name searched
	 * @param name of station
	 * @return station name in lowercase
	 */
	public Station getStation(String name) {
		return stationList.get(name.toLowerCase());
	}
	
	/**
	 * This method retrieves the values in the code
	 * @return the values from the stationList array
	 */
	public Collection<Station> getValues(){
		return stationList.values();
	}
	
	/**
	 * This method is to retrieve the LinkedStations
	 * @param name of station
	 * @return station user was searching for
	 */
	
	public HashSet<Station> getLinkedStations(String name){
		return stationList.get(name.toLowerCase()).getLinkedStations();
	}
	
	public String gettLinkedStations(String name){
		String s = "";
		
		for(Station st : stationList.get(name.toLowerCase()).getLinkedStations()){
			s += st.getName()+", ";
		}
		
		return s;
	}
	
	/**
	 * This method reads out the name of {@link Station} objects in the object 
	 * @param start is the station the path is starting from
	 * @param goal is the end of the path
	 * @return path
	 */
	public HashMap<Station, ArrayList<Station>> findPath(Station start, Station goal){
		
		LinkedList<Station> openSet = new LinkedList<Station>();
		HashSet<Station> closedSet = new HashSet<Station>();
		HashMap<Station, ArrayList<Station>> path = new HashMap<Station, ArrayList<Station>>();
		
		openSet.add(start);
		
		while(!openSet.isEmpty()) {
			Station current = openSet.pop();
			
			for(Station linkedStation : current.getLinkedStations()) {
				if(closedSet.contains(linkedStation))
					continue;
				
				if(path.get(linkedStation) == null)
					path.put(linkedStation, new ArrayList<Station>());
					
				if(linkedStation == goal) {
					path.get(linkedStation).add(current);
					openSet.removeAll(openSet);
					return path;
				}
				
				if(openSet.contains(linkedStation) == false) {
					path.get(linkedStation).add(current);
					openSet.add(linkedStation);
				}
				
			}
			
			closedSet.add(current);
			
		}
		
		return path;
	}

}
