package Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

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

}
