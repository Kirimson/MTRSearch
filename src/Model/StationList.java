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

	public ArrayList<String> recursivePath(String stationA, String stationB, ArrayList<String> path) {
		
		String goal = stationB;
		ArrayList<String> stationPath = path;
		boolean found = false;
		
			for(Station linked : stationList.get(stationA).getLinkedStations()) {
				String linkedName = linked.getName().toLowerCase();
				
				if(linkedName.equals(stationB.toLowerCase())) {
					path.add(linkedName);
					return path;
				}
				else
				{
					path.add(linkedName);
					stationA = linkedName;
					recursivePath(stationA, stationB, path);
				}
				
			}
		
		return path;
	}

}
