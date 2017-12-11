package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import Model.Line;
import Model.LineList;
import Model.Reader;
import Model.Station;
import Model.StationList;

public class MtrCLI {

	private Reader reader;
	private LineList lines;
	private StationList stations;
	
	private boolean isDone;
	
	public MtrCLI() {
		lines = new LineList();
		stations = new StationList();
		reader = new Reader();
	}
	
	public void run() {
		stations.addStations(reader.createStations());
		lines.addLines((reader.CreateLines(stations)));
		
		Scanner scanner = new Scanner(System.in);
		
		while(!isDone)
		{
			System.out.println("Enter Command: ");
			
			String commandString = scanner.nextLine().toLowerCase();
			commandString = commandString.replaceAll("\\s+"," ");
			
			String command = commandString.split(" ")[0];
			command = command.trim();
			
			String rest = "";
			try {
				rest = commandString.split(" ", 2)[1];		
				rest = rest.trim();
			} catch(ArrayIndexOutOfBoundsException e) {
				//Nothing we can do.
			}
		
			switch(command) {
				case "termini": System.out.println(lines.lineTermini(rest));break;
				case "list": System.out.println(lines.toString(rest));break;
				case "connected": System.out.println(lines.findConnectedLines(rest));break;
				case "path": System.out.println(anna(rest));break;
				case "b": System.out.println(test(rest));;break;
				case "c": System.out.println(findPath(rest));;break;
				case "help": System.out.println(helpMe());;break;
				default: System.out.println("Sorry. That's not a command. Type 'help' for a list of available commands");
			}
		}
		scanner.close();
	}
	
	private String test(String rest) {
		StringBuffer sb = new StringBuffer();
		
		for(Station s : stations.getStation(rest).getLinkedStations()) {
			sb.append(s.getName());
		}
		
		return sb.toString();
	}
	
	private Station testa(String rest) {
		return stations.getStation(rest);
	}

	public String helpMe() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("List of available commands:\n");
		sb.append("termini <Line> - Displays the terminal stations of a specified line\n");
		sb.append("list <Line> - Displays all the stations of a specified line\n");
		sb.append("connected <Line> - Displays all lines connected to a specified line\n");
		sb.append("path <StationA>,<StationB> - Displays a path from StationA to StationB\n");
		sb.append("help - Displays this page");
		
		return sb.toString();
		
	}
	
//	public String findPath(String statString) {
//	
//		String stationA = statString.split(",")[0].trim();
//		String stationB = statString.split(",")[1].trim();
//	
//		HashMap<String, Integer> discovered = new HashMap<String, Integer>();
//		
//		ArrayList<String> path = new ArrayList<String>();
//		path.add(stationA);
//		stations.recursivePath(stationA, stationB, path, discovered);
//		
//		StringBuffer sb = new StringBuffer();
//		
//		for(int i = 0; i < path.size(); i++) {
//			String s = path.get(i);
//			if(i == path.size()-1)
//				sb.append(s);
//			else
//				sb.append(s+" -> ");
//		}
//		
//		return sb.toString();
//	}
	
	public String findPath(String statString) {
		
		Station start = stations.getStation(statString.split(",")[0].trim());
		Station goalStation = stations.getStation(statString.split(",")[1].trim());
		HashMap<Station, Integer> discovered = new HashMap<Station, Integer>();
		String path;
		
		path = stations.newR(start, goalStation, start.getName(), discovered, null);
		path += "DONE";
		return path;
	}
	
	public String anna(String statString) {
		
		ArrayList<String> path = new ArrayList<String>();
		Station start = stations.getStation(statString.split(",")[0].trim());
		Station goalStation = stations.getStation(statString.split(",")[1].trim());
		String goalLine = goalStation.getALine();
		String currentLine = start.getALine();
		
		ArrayList<Station> roughPath = new ArrayList<Station>();
//		roughPath = findLinePath();
		
		
		if(currentLine.equals(goalLine)) {
			
			boolean printPath = false;
			boolean reverse = false;
			
			for(Station s : lines.getLine(currentLine.toLowerCase()).getStations()) {
				if(s.equals(start)) {
					reverse = false;
					break;
				}
				
				if(s.equals(goalStation)) {
					reverse = true;
					break;
				}
			}
			
			for(Station s : lines.getLine(currentLine.toLowerCase()).getStations()) {
				if(s.equals(start) || s.equals(goalStation))
					printPath = !printPath;

				if(printPath)
					path.add(s.getName());
			}
			
			if(!reverse)
				path.add(goalStation.getName());
			else
				path.add(start.getName());
			
			if(reverse)
				Collections.reverse(path);
		}
		
		return path.toString();
	}

	
}
