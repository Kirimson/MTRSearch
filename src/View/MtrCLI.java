package View;

import java.util.HashSet;
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
		
		while(!isDone)
		{
			System.out.println("Enter Command: ");
			Scanner scanner = new Scanner(System.in);
			
			String commandString = scanner.nextLine().toLowerCase();
			commandString = commandString.replaceAll("\\s+"," ");
			
			String command = commandString.split(" ")[0];
			command = command.trim();
			
			String rest = "";
			try {
				rest = commandString.split(" ", 2)[1];		
				rest = rest.trim();
			} catch(ArrayIndexOutOfBoundsException e) {
				//nothing we can do.
			}
		
			switch(command) {
				case "termini": System.out.println(lines.lineTermini(rest));break;
				case "list": System.out.println(lines.toString(rest));break;
				case "connected": System.out.println(lines.findConnectedLines(rest));break;
				case "help": System.out.println(helpMe());;break;
				default: System.out.println("Sorry. That's not a command. Type 'help' for a list of available commands");
			}
		}
	}
	
	public String helpMe() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("List of available commands:\n");
		sb.append("termini <Line> - Displays the terminal stations of a specified line\n");
		sb.append("list <Line> - Displays all the stations of a specified line\n");
		sb.append("connected <Line> - Displays all lines connected to a specified line\n");
		sb.append("help - Displays this page");
		
		return sb.toString();
		
	}
	
}
