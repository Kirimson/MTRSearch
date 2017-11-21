package View;

import java.util.Scanner;

import Model.LineList;
import Model.Reader;

public class MtrCLI {

	private Reader reader;
	private LineList lines;
	
	private boolean isDone;
	
	public MtrCLI() {
		lines = new LineList();
		reader = new Reader();
	}
	
	public void run() {
		lines.addLines((reader.CreateLines()));
		
		while(!isDone)
		{
			System.out.println("Enter Command: ");
			Scanner scanner = new Scanner(System.in);
			
			String commandString = scanner.nextLine();
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
				default: System.out.println("Sorry. Thats not a command");
			}
			
		}		
	}
	
}
