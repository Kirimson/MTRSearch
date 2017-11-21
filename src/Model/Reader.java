package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reader {

	private Scanner scan;
	
	public Reader() {
		File csv = new File("files/MTR.csv");
		try {
			scan = new Scanner(csv, "utf8");
		} catch (FileNotFoundException e) {
			System.out.println("not there");
		}
	}
	
	public ArrayList<Line> CreateLines(){
		
		ArrayList<Line> lines = new ArrayList<Line>();
		
		while(scan.hasNextLine()) {
			String[] arr = scan.nextLine().split(",");
			
			String lineName = arr[0];
			String[] stations = Arrays.copyOfRange(arr, 1, arr.length);
			
			Line line = new Line(lineName, stations);
			
			lines.add(line);
			
//			System.out.println(line.toString());
			
		}
		scan.close();
		
		return lines;
	}
	
}
