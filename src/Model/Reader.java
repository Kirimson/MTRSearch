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
		HashMap<String, Station> sList = new HashMap<String, Station>();
		
		while(scan.hasNextLine()) {
			String[] arr = scan.nextLine().split(",");
			String[] stations = Arrays.copyOfRange(arr, 1, arr.length);
			
			for(String s : stations) {
				sList.put(s.toLowerCase(), new Station(s));
			}
		}
		
		return sList;
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
