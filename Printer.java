import java.util.HashMap;


import java.util.List;

//String DATE = "date";
//String TIME = "time";
//String LOCATION = "location";
//String OPERATOR = "operator";
//String FLIGHT = "flight";
//String ROUTE = "route";
//String TYPE = "type";
//String REGISTRATION = "registration";
//String CN_IN = "cn.in";
//String ABOARD = "aboard";
//String FATALITIES = "fatalities";
//String GROUND = "ground";
//String SURVIVORS = "survivors";
//String SURVIVAL_RATE = "survivalRate";
//String SUMMARY = "summary";
//String CLUST_ID = "clustID";

public class Printer {
	public static void printFormatted(List<HashMap<String, String>> data) {
		for(HashMap<String, String> entry : data) {
			for(String key : entry.keySet()) {
				String value = entry.get(key);
				
				if(entry.get(key).equals("null"))
					value = " ";
					
				System.out.println(key + ": " + value);
			}
			
			System.out.println("---------------------------------------------------------------------");
		}

	}
}
