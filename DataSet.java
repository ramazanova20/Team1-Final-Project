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
	
	
	
}
