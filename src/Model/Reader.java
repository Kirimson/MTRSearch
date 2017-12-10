package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Reader {

	private Scanner scan;
	private File csv;
	public Reader() {
		csv = new File("files/MTR.csv");
		try {
			scan = new Scanner(csv, "utf8");
		} catch (FileNotFoundException e) {
			System.out.println("File not found, please check that MTR.csv is in the files/ directory.");
		}
	}
	
	public HashMap<String, Station> createStations(){
		HashMap<String, Station> stationList = new HashMap<String, Station>();
		
		while(scan.hasNextLine()) {
			String lastStation = null;
			String[] arr = scan.nextLine().split(",");
			String[] stations = Arrays.copyOfRange(arr, 1, arr.length);
			
			for(String stationString : stations) {
				
				Station station = stationList.get(stationString.toLowerCase());
				
				if(station == null)
				{
					station = new Station(stationString);
					stationList.put(stationString.toLowerCase(),station);
				}
				
				if(lastStation != null){
					stationList.get(stationString.toLowerCase()).addLinkedStation(stationList.get(lastStation.toLowerCase()));
					stationList.get(lastStation.toLowerCase()).addLinkedStation(stationList.get(stationString.toLowerCase()));
				}
				
				lastStation = stationString;
			}
		}
		
		return stationList;
	}
	
	public ArrayList<Line> CreateLines(StationList stationList){
		
		ArrayList<Line> lines = new ArrayList<Line>();
		
		try {
			scan = new Scanner(csv, "utf8");
		} catch (FileNotFoundException e) {
			System.out.println("not there");
		}
		
		while(scan.hasNextLine()) {
			String[] arr = scan.nextLine().split(",");
			
			String lineName = arr[0];
			String[] stations = Arrays.copyOfRange(arr, 1, arr.length);
			
			Line line = new Line(lineName);
			
			for(String s : stations) {
				stationList.getStation(s).addLine(lineName);
				line.addStation(stationList.getStation(s));
			}
			
			lines.add(line);
			
		}
		scan.close();
		
		return lines;
	}
	
}
