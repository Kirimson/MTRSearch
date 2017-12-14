package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

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
	
	public LinkedList<String> bfs(Station start, Station goal){
		
		LinkedList<Station> openSet = new LinkedList<Station>();
		
		HashSet<Station> closedSet = new HashSet<Station>();
		
		HashMap<Station, Station> path = new HashMap<Station, Station>();
		
		openSet.add(start);
		
		while(!openSet.isEmpty()) {
			Station current = openSet.pop();
			
			path.put(current, null);
			
			for(Station linkedStation : current.getLinkedStations()) {
				if(closedSet.contains(linkedStation))
					continue;
				
				if(linkedStation == goal) {
					path.put(linkedStation, current);
					System.out.println("key:"+linkedStation.getName()+" value:"+current.getName());
					openSet.removeAll(openSet);
					break;
//					return path;
				}
				
				if(openSet.contains(linkedStation) == false) {
					path.put(linkedStation, current);
					
					System.out.println("key:"+linkedStation.getName()+" value:"+current.getName());
					
					openSet.add(linkedStation);
				}
			}
			
			closedSet.add(current);
			
		}
		
		LinkedList<String> realPath = new LinkedList<String>();
		
		Station currChild = goal;
		
		realPath.add(goal.getName());
		
		while(path.get(currChild) != null) {
			realPath.add(path.get(currChild).getName());
			System.out.println(path.get(currChild));
			
			currChild = path.get(currChild);
			System.out.println(path.get(currChild));
			
		}
		
		Collections.reverse(realPath);
		
		return realPath;
	}

}
