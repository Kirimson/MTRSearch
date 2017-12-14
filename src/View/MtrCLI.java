package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

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
				case "path": System.out.println(findPath(rest));;break;
				case "help": System.out.println(helpMe());;break;
				default: System.out.println("Sorry. That's not a command. Type 'help' for a list of available commands");
			}
		}
		scanner.close();
	}

	public String helpMe() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("List of available commands:\n");
		sb.append("termini <Line> - Displays the terminal stations of a specified line\n");
		sb.append("list <Line> - Displays all the stations of a specified line\n");
		sb.append("connected <Line> - Displays all lines connected to a specified line\n");
		sb.append("path <Start Station>,<End Station> - Displays a path from <Start Station> to <End Station>\n");
		sb.append("help - Displays this page");
		
		return sb.toString();
	}
	
	public String findPath(String statString) {
		
		Station start = null;
		Station goalStation = null;
		
		String[] stationsString = statString.split(",");
		
		if(stationsString.length >= 2) {
			if(stationsString.length >= 3) {
				System.out.println("Warning: More than two stations have been provided. Only the first two will be used.");
			}
			start = stations.getStation(stationsString[0].trim());
			goalStation = stations.getStation(stationsString[1].trim());
		}
		else
		{
			return "Error: Please enter two stations, in the format <Start Station>,<End Station>. type 'help' for further assistence.";
		}
		
		if(start == null)
			return "Error: Station '"+stationsString[0]+"' doesn't exist. Please enter a valid MTR station";
		
		if(goalStation == null)
			return "Error: Station '"+stationsString[1]+"' doesn't exist. Please enter a valid MTR station";
		
		HashMap<Station, ArrayList<Station>> path = stations.findPath(start, goalStation);
		
		Station currChild = goalStation;
		
		ArrayList<String> realPath = new ArrayList<String>();
		
		realPath.add(goalStation.getName());
		
		while(path.get(currChild) != null) {
			ArrayList<Station> currentList = path.get(currChild);
			
			realPath.add(currentList.get(currentList.size()-1).getName());
			currChild = currentList.get(currentList.size()-1);
		}
		
		Collections.reverse(realPath);
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < realPath.size(); i++) {
			String s = realPath.get(i);
			
			if(i == realPath.size()-1)
				sb.append(s);
			else
				sb.append(s+" -> ");
		}
		
		return sb.toString();
	}
}
