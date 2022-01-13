import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
	
	public void loadFromFile(String path) throws IOException {
		
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		String firstLine = br.readLine();
		
		StringTokenizer st = new StringTokenizer(firstLine, ",");
		List<String> keys = new ArrayList<String>();
		
		
		while(st.hasMoreTokens())
			keys.add(st.nextToken());
		
		String line;
		
		while((line = br.readLine()) != null) {
			System.out.println(line);
			st = new StringTokenizer(line, ",");
			System.out.println(st.countTokens());
			
			HashMap<String, String> hm = new HashMap<String, String>();
					
			for(String key : keys)
				hm.put(key, st.nextToken());
			
			this.data.add(hm);
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
<<<<<<< HEAD
	
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
	
//	i. equal (eq)
//	ii. greater than (gt)
//	iii. less than (lt)
//	iv. greater and equal to (ge)
//	v. less and equal to (le)
//	vi. between (bt)
//	vii. null
	
	public List<HashMap<String, String>> filter(List<String[]> filters){
		for(String[] ft : filters) {
			String field = ft[0];
			String predicate = ft[1];
			String value = ft[2];
			
		}
	}

=======
>>>>>>> 4fe06c5efaf9c59cfef2e84273718eba8eea2750
}
