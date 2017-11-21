package View;

import java.util.ArrayList;

import Model.Line;
import Model.Reader;

public class Main {
	
	private static MtrCLI cli;
	
	public static void main(String args[]) {
		cli = new MtrCLI();
		
		cli.run();
	}
}
