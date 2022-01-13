import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class DataSet {
	private List<HashMap<String, String>> data;
	
	public DataSet() {
		this.data = new LinkedList<HashMap<String, String>>();
	}
	
	public void generateSample() {
		HashMap<String, String> entry1 = new HashMap<String, String>();
		HashMap<String, String> entry2 = new HashMap<String, String>();
		HashMap<String, String> entry3 = new HashMap<String, String>();
		
		entry1.put(Fields.DATE, "4/4/1993");
		entry1.put(Fields.LOCATION, "Off Barnegat, New Jersey");
		entry1.put(Fields.TYPE, "Goodyear-Zeppelin U.S.S. Akron (airship)");
		
		entry2.put(Fields.DATE, "4/4/1993");
		entry2.put(Fields.LOCATION, "asdsadasdas");
		entry2.put(Fields.TYPE, "adsadada");
		
		this.data.add(entry1);
		this.data.add(entry2);
	}
//	String DATE = "date";
//	String TIME = "time";
//	String LOCATION = "location";
//	String OPERATOR = "operator";
//	String FLIGHT = "flight";
//	String ROUTE = "route";
//	String TYPE = "type";
//	String REGISTRATION = "registration";
//	String CN_IN = "cn.in";
//	String ABOARD = "aboard";
//	String FATALITIES = "fatalities";
//	String GROUND = "ground";
//	String SURVIVORS = "survivors";
//	String SURVIVAL_RATE = "survivalRate";
//	String SUMMARY = "summary";
//	String CLUST_ID = "clustID";
	
	public void loadFromFile(String path) throws IOException {
		
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		
		
		while((line = br.readLine()) != null){
			String[] fieldArray = line.split(",");
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put(Fields.DATE, fieldArray[0]);
			entry.put(Fields.TIME, fieldArray[1]);
			entry.put(Fields.LOCATION, fieldArray[2]);
			entry.put(Fields.OPERATOR, fieldArray[3]);
			entry.put(Fields.FLIGHT, fieldArray[4]);
			entry.put(Fields.ROUTE, fieldArray[5]);
			entry.put(Fields.TYPE, fieldArray[6]);
			entry.put(Fields.REGISTRATION, fieldArray[7]);
			entry.put(Fields.CN_IN, fieldArray[8]);
			entry.put(Fields.ABOARD, fieldArray[9]);
			entry.put(Fields.FATALITIES, fieldArray[10]);
			entry.put(Fields.GROUND, fieldArray[11]);
			entry.put(Fields.SURVIVORS, fieldArray[12]);
			entry.put(Fields.SURVIVAL_RATE, fieldArray[13]);
			entry.put(Fields.SUMMARY, fieldArray[14]);
			entry.put(Fields.CLUST_ID, fieldArray[15]);
			this.data.add(entry); 
		}	
	}
	
	public List<HashMap<String, String>> extractAll() {
		return this.data;
	}
	
	public List<HashMap<String, String>> extractFields(List<String> fields) {
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry :  data) {
			HashMap<String, String> newEntry = new HashMap<String, String>();
			
			for(String key : entry.keySet()) {
				if(fields.contains(key))
					newEntry.put(key, entry.get(key));
			}
			
			if(!newEntry.isEmpty())
				newData.add(newEntry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> extractRange(int start, int end){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(int i = start - 1; i < end; i++) 
			newData.add(data.get(i));
			
		return newData;
	}
	
	public List<HashMap<String, String>> search(HashMap<String, String> sample){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : this.data) {
			boolean found = true;
			
			for(String sampleKey : sample.keySet()) {
				if(!sample.get(sampleKey).equals(entry.get(sampleKey)))
					found = false;
			}
			
			if(found)
				newData.add(entry);
		}
		return newData;
	}
	
	public List<HashMap<String, String>> filterStartsWith(List<HashMap<String, String>> sampleData, String field, String arg){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(entry.get(field).startsWith(arg))
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterEndsWith(List<HashMap<String, String>> sampleData, String field, String arg){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(entry.get(field).endsWith(arg))
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterContains(List<HashMap<String, String>> sampleData, String field, String arg){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(entry.get(field).contains(arg))
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterIsNull(List<HashMap<String, String>> sampleData, String field){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(entry.get(field).equals("null"))
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterEqual(List<HashMap<String, String>> sampleData, String field, String arg){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(entry.get(field).equals(arg))
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterGreater(List<HashMap<String, String>> sampleData, String field, int arg){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(Integer.valueOf(entry.get(field)) > arg)
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterGreaterEqual(List<HashMap<String, String>> sampleData, String field, int arg){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(Integer.valueOf(entry.get(field)) >= arg)
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterLesser(List<HashMap<String, String>> sampleData, String field, int arg){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(Integer.valueOf(entry.get(field)) < arg)
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterLesserEqual(List<HashMap<String, String>> sampleData, String field, int arg){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(Integer.valueOf(entry.get(field)) <= arg)
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterBetween(List<HashMap<String, String>> sampleData, String field, int arg1, int arg2){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			if(Integer.valueOf(entry.get(field)) > arg1 && Integer.valueOf(entry.get(field)) < arg2)
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterDateYear(List<HashMap<String, String>> sampleData, int year){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			DateHolder date = new DateHolder(entry.get(Fields.DATE));
			
			if(date.getYear() == year)
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterDateMonth(List<HashMap<String, String>> sampleData, int month){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			DateHolder date = new DateHolder(entry.get(Fields.DATE));
			
			if(date.getMonth() == month)
				newData.add(entry);
		}
		
		return newData;
	}
	
	public List<HashMap<String, String>> filterDateDay(List<HashMap<String, String>> sampleData, int day){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String> entry : sampleData) {
			DateHolder date = new DateHolder(entry.get(Fields.DATE));
			
			if(date.getDay() == day)
				newData.add(entry);
		}
		
		return newData;
	}
	

	
	public List<HashMap<String, String>> filter(List<String[]> filters){
		List<HashMap<String, String>> newData = new LinkedList<HashMap<String, String>>();
		
		for(HashMap<String, String > entry : data) 
			newData.add(new HashMap<String, String>(entry));
		
		for(String[] ft : filters) {
			String field = ft[0];
			String predicate = ft[1];
			String value = ft[2];
			
			switch(predicate) {
			case Predicates.STARTS_WITH: newData = filterStartsWith(newData, field, value); break;
			case Predicates.ENDS_WITH: newData = filterEndsWith(newData, field, value); break;
			case Predicates.CONTAINS: newData = filterContains(newData, field, value); break;
			case Predicates.EQUALS: newData = filterEqual(newData, field, value); break;
			}
			
			if(field.equals(Fields.DATE)) {
				switch(predicate) {
				case Predicates.IN_YEAR: newData = filterDateYear(newData, Integer.valueOf(value)); break;
				case Predicates.IN_MONTH: newData = filterDateYear(newData, Integer.valueOf(value)); break;
				case Predicates.ON_DAY: newData = filterDateDay(newData, Integer.valueOf(value)); break;
				}
			}
			
			switch(predicate) {
			case Predicates.GREATER_THAN: newData = filterGreater(newData, field, Integer.valueOf(value)); break;
			case Predicates.GREATER_OR_EQUAL: newData = filterGreaterEqual(newData, field, Integer.valueOf(value)); break;
			case Predicates.LESSER_THAN: newData = filterLesser(newData, field, Integer.valueOf(value)); break;
			case Predicates.LESSER_OR_EQUAL: newData = filterLesserEqual(newData, field, Integer.valueOf(value)); break;
			}
			
			
		}
		
		return newData;
		
	}
	
	public void sortAscending(String field) {
		Collections.sort(data, new Comparator<HashMap<String, String>>(){ 
	        public int compare(HashMap<String, String> one, HashMap<String, String> two) { 
	            return one.get(field).compareTo(two.get(field));
	        } 
		});
	}
	
	public void sortDescending(String field) {
		Collections.sort(data, new Comparator<HashMap<String, String>>()
		{ public int compare(HashMap<String, String> one, HashMap<String, String> two) 
		{ String s1 = one.get(field); 
		String s2 = two.get(field); 
		if(s1.equals(s2)) 
			return 0; 
		if(s1.compareTo(s2) > 0) 
			return -1; return 1; } 
		});
	}

}
