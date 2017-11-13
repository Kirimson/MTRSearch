package Model;

import java.util.HashSet;

public class StationList {
	private HashSet<StationNode> stations;
	
	public StationNode getStation(String name){
		for(StationNode s : stations){
			if (s.getName() == name)
				return s;
		}
		return null;
	}
	
	public void addStation(String station){
		if(getStation(station) == null)
			stations.add(new StationNode(station));
	}
}
