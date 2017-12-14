package View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
				case "path": System.out.println(bfs(rest));;break;
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
		sb.append("path <StationA>,<StationB> - Displays a path from StationA to StationB\n");
		sb.append("help - Displays this page");
		
		return sb.toString();
		
	}
	
	public String bfs(String statString) {
		
		Station start = stations.getStation(statString.split(",")[0].trim());
		Station goalStation = stations.getStation(statString.split(",")[1].trim());
		HashMap<Station, Station> path = new HashMap<Station, Station>();
		
		LinkedList<String> realPath = stations.bfs(start, goalStation);
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < realPath.size(); i++) {
			String s = realPath.get(i);
			if(i == path.size()-1)
				sb.append(s);
			else
				sb.append(s+" -> ");
		}
		
		return sb.toString();
	}
}
