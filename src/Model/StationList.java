package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class StationList {

	private HashMap<String, Station> stationList;
	
	public StationList() {
		stationList = new HashMap<String, Station>();
	}
	
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

	public ArrayList<String> recursivePath(String current, String goal, ArrayList<String> path, HashMap<String, Integer> discovered) {
		
			if(discovered.get(current) == null) {
				discovered.put(current, 0);
			}else {
				discovered.put(current, discovered.get(current)+1);
			}
		
			Station linked = stationList.get(current).getLinkedStations().get(discovered.get(current));
			String linkedName = linked.getName().toLowerCase();

			if(discovered.get(linkedName) == null) {
				discovered.put(linkedName, -1);
			}
			
			if(discovered.get(linkedName) >= stationList.get(linkedName).getLinkedStations().size()-1) {
				discovered.put(current, discovered.get(current)+1);
				try {
					linked = stationList.get(current).getLinkedStations().get(discovered.get(current));
					linkedName = linked.getName().toLowerCase();
				} catch(IndexOutOfBoundsException e) {
					return path;
				}
				
			}
			
			if(discovered.get(current) < stationList.get(current).getLinkedStations().size()) {
				path.add(linkedName);
				
				if(!linkedName.equals(goal))
					path = recursivePath(linkedName, goal, path, discovered);
			}
			
		return path;
	}

	public String newR(Station current, Station goalStation, String path, HashMap<Station, Integer> discovered, Station prior) {

		if(stationHasMoreConnections(current, discovered)) {
			//first linked station
			Station linked = discoverStation(current, discovered);
			
			if(linked.equals(goalStation)) {
				path += " > "+linked.getName();
				return path;
			} else if(linked != prior) {
				path += " > "+linked.getName();
				//that wasn't it. go to the next one
				path = newR(linked, goalStation, path, discovered, current);
			}else if(stationHasMoreConnections(current, discovered)) {
				linked = discoverStation(current, discovered);
				if(linked.equals(goalStation)) {
					path += " > "+linked.getName();
					return path;
				} else {
					path += " > "+linked.getName();
					path = newR(linked, goalStation, path, discovered, current);
				}
			} else {
				path += " > "+linked.getName();
				path = newR(prior, goalStation, path, discovered, current);
			}
			
		}
		
		return path;
	}

	private Station discoverStation(Station station, HashMap<Station, Integer> discovered) {
		if(discovered.get(station) == null)
			discovered.put(station, 1);
		else
			discovered.put(station, discovered.get(station)+1);
		
		return station.getLinkedStations().get(discovered.get(station)-1);
	}

	private boolean stationHasMoreConnections(Station station, HashMap<Station, Integer> discovered) {
		if(discovered.get(station) == null)
			return true;
		
		if(discovered.get(station) >= station.getLinkedStations().size())
			return false;
		
		return true;
	}

}
