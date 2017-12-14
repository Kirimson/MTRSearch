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
	 * This method
	 * @param list
	 */
	public void addStations(HashMap<String, Station> list) {
		stationList = list;
	}
	
	public Station getStation(String name) {
		return stationList.get(name.toLowerCase());
	}
	
	public Collection<Station> getValues(){
		return stationList.values();
	}
	
	public ArrayList<Station> getLinkedStations(String name){
		return stationList.get(name.toLowerCase()).getLinkedStations();
	}
	
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
