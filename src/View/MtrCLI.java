package View;

import java.util.ArrayList;
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
				case "path": System.out.println(findPath(rest));break;
				case "b": System.out.println(testa(rest));;break;
				case "help": System.out.println(helpMe());;break;
				default: System.out.println("Sorry. That's not a command. Type 'help' for a list of available commands");
			}
		}
		scanner.close();
	}
	
//	private String test(String rest) {
//		return stations.getStation(rest).getLinkedStations();
//	}
	
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
	
	public String findPath(String statString) {
	
		String stationA = statString.split(",")[0].trim();
		String stationB = statString.split(",")[1].trim();
	
		boolean found = false;
		
		ArrayList<String> path = new ArrayList<String>();
		path.add(stationA);
		path = stations.recursivePath(stationA, stationB, path);
		
		StringBuffer sb = new StringBuffer();
		
		for(String s  : path) {
			if(s.equals(path.get(path.size()-1)))
				sb.append(s);
			else
				sb.append(s+" -> ");
		}
		
		return sb.toString();
	}
	
//	public String findPath(String statString) {
//		Station stationA = stations.getStation(statString.split(",")[0].trim());
//		Station stationB = stations.getStation(statString.split(",")[1].trim());
//	
//		boolean found = false;
//		
//		HashSet<String> lineList = new HashSet<String>();
//		
//		HashSet<String> linesA = stationA.getLines();
//		HashSet<String> linesB = stationB.getLines();
//		
////		while(!found){
//		String newA = "";
//		String newB = "";
//			for(String a : linesA)
//			{
//				newA = a;
//				for(String b : linesB)
//				{
//					newB = b;
//					for(String bL : lines.hashConn(b.toLowerCase())){
//						if(lines.hashConn(a.toLowerCase()).contains(bL))
//						{
//							System.out.println("share a connected line: "+bL);
//							found = true;
//						}
//					}
//				}
//			}
//			if(found = false){
//				
//			}
////		}
//		
//		return stationA.getName();
//	}
	
}
