package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import Model.*;

public class MtrCLI {

	private Reader reader;
	private LineList lines;
	private StationList stations;
	
	private boolean isDone;
	
	/**
	 * Class Constructor initialises lineList, stationList and reader objects.
	 * 
	 */
	public MtrCLI() {
		lines = new LineList();
		stations = new StationList();
		reader = new Reader();
	}
	
	/**
	 * A control hub for the program. Handles and delegates what actions to take dependent on what the user enters. <br/>
	 * Termini: {@link LineList #lineTermini} <br/>
	 * List: {@link LineList #toString} <br/>
	 * Connected: {@link LineList #findConnectedlines} <br/>
	 * Path: {@link MtrCLI #findPath}<br/>
	 * Help: {@link MtrCLI #helpMe} <br/>
	 */
	public void run() {
		stations.addStations(reader.createStations());
		lines.addLines((reader.createLines(stations)));
		
		Scanner scanner = new Scanner(System.in);
		
		while(!isDone)
		{
			// Read user string
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
				//Error caught later
			}
			
			// Go to case dependent on what user enters or error handle
			switch(command) {
				case "termini": System.out.println(lines.lineTermini(rest));break;
				case "list": System.out.println(lines.toString(rest));break;
				case "connected": System.out.println(lines.findConnectedLines(rest));break;
				case "path": System.out.println(findPath(rest));break;
				case "help": System.out.println(helpMe());break;
				case "quit": System.out.println("Goodbye.");isDone = true;break;
				default: System.out.println("Sorry. That's not a command. Type 'help' for a list of available commands");
			}
			System.out.print("\n");
		}
		scanner.close();
	}

	/**
	 * Help messages.
	 * 
	 * @return String of help messages
	 */
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
	
	/**
	 * Finding a path between two user given stations. Uses {@link StationList #findPath(Station, Station)} and then traces
	 * the search back to find a path which then reverses the path before outputting to console.
	 * 
	 * @param startEnd 
	 * @return fastest path between two stations
	 */
	public String findPath(String startEnd) {
		
		Station start = null;
		Station goalStation = null;
		
		String[] stationsString = startEnd.split(",");
		
		//check if there are at least 2 parameters
		if(stationsString.length >= 2) {
			//check if there are more then 3 arguments and give a warning if there is
			if(stationsString.length >= 3)
				System.out.println("Warning: More than two stations have been provided. Only the first two will be used.");
			
			//set start and goal stations
			start = stations.getStation(stationsString[0].trim());
			goalStation = stations.getStation(stationsString[1].trim());
		}
		else
			return "Error: Please enter two stations, in the format <Start Station>,<End Station>. type 'help' for further assistence.";
		
		if(start == null)
			return "Error: Station '"+stationsString[0]+"' doesn't exist. Please enter a valid MTR station";
		
		if(goalStation == null)
			return "Error: Station '"+stationsString[1]+"' doesn't exist. Please enter a valid MTR station";

		
		//use the BFS search
		HashMap<Station, ArrayList<Station>> path = stations.findPath(start, goalStation);
		Station currChild = goalStation;
		
		//the path that will be returned and add the goal to the beginning (path is made in reverse)
		ArrayList<String> realPath = new ArrayList<String>();
		
		realPath.add(goalStation.getName());
		
		//backtrace way through the BFS using the HashMapwith ArrayList as the value, getting last station for each child
		while(path.get(currChild) != null) {
			ArrayList<Station> currentList = path.get(currChild);
			
			realPath.add(currentList.get(currentList.size()-1).getName());
			currChild = currentList.get(currentList.size()-1);
		}
		
		//reverse path to be in the correct order
		Collections.reverse(realPath);

		//format the path correctly
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
