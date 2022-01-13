import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static DataSet dataSet;
	private static List<HashMap<String, String>> output;
	
	
	public static void main(String[] args) {
		dataSet = new DataSet();
		output = dataSet.extractAll();
		
		try {
			dataSet.loadFromFile("dataSet.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.print(">> ");
			String query = input.nextLine();
			
			query = query.toLowerCase();
			
			executeQuery(query);
		}
	}
	
	public static void executeQuery(String query) {
		String command = query.split("\\s")[0];
		
		if(command.equals(Commands.LIST))
			executeList(query);
		else if(command.equals(Commands.SEARCH))
			executeSearch(query);
		else if(command.equals(Commands.FILTER))
			executeFilter(query);
		else if(command.equals(Commands.SORT))
			executeSort(query);
		else if(command.equals(Commands.EXPORT))
			try {
				export();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public static void executeList(String query) {
		String[] components = query.split("\\s");
		
		if(components.length == 1) {//list all
			System.out.println(dataSet.extractAll());
			return;
		}
		
		if(components[1].equals(Predicates.RANGE)) { //list range
			int startIndex = Integer.valueOf(components[2]);
			int endIndex = Integer.valueOf(components[3]);
			output = dataSet.extractRange(startIndex, endIndex);
			Printer.printFormatted(output);
			return;
		}
		
		List<String> fieldsList = new LinkedList<String>(); //list fields
		
		for(int i = 1; i < components.length; i++)
			fieldsList.add(components[i]);
		
		output = dataSet.extractFields(fieldsList);
		Printer.printFormatted(output);
		
		return;
			
	}
	
	public static void executeSearch(String query) {
		
		String[] components = query.split("\\s");
		
		HashMap<String, String> sample = new HashMap<String, String>();
		
		for(int i = 1; i < components.length; i += 2)
			sample.put(components[i], components[i + 1]);
		
		output = dataSet.search(sample);
		Printer.printFormatted(output);
	}
	
	public static void executeFilter(String query) {
		String[] components = query.split("\\s");
		List<String[]> filterList = new LinkedList<String[]>();
		
		for (int i = 1; i < components.length; i += 3) {
			String[] filterArray = new String[3];
			filterArray[0] = components[i];
			filterArray[1] = components[i + 1];
			filterArray[2] = components[i + 2];
			
			filterList.add(filterArray);
		}
		
		output = dataSet.filter(filterList);
		Printer.printFormatted(output);
	}
	
	public static void export() throws IOException {
		String str = "";
		
		File exportedFile = new File("exported.csv");
		FileWriter fw = new FileWriter(exportedFile);
		
		for(String key : output.get(0).keySet()) {
			str += key + ",";
		}
		
		str += "\n";
		
		fw.write(str);

	
		for(HashMap<String, String> entry : output) {
			str = "";
			
			for(String key : entry.keySet()) {
				str += entry.get(key) + ",";
			}
			str += "\n";
			fw.write(str);
		}	
		
		fw.close();
		
	}
	
	public static void executeSort(String query) {
		String[] components = query.split("\\s");
		
		if(components[2].equals("asc")) {
			dataSet.sortAscending(components[1]);
			
			Printer.printFormatted(dataSet.extractAll());
		} else if(components[2].equals("desc")) {
			dataSet.sortAscending(components[1]);
			Printer.printFormatted(dataSet.extractAll());
		}
		
		output = dataSet.extractAll();
		return;
	}
	
	
	
}
