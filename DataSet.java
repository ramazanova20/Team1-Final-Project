import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class DataSet {
	private List<HashMap<String, String>> data;
	
	public DataSet() {
		data = new LinkedList<HashMap<String, String>>();
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
			
			HashMap<String, String> hm = new HashMap<String, String>();
					
			for(String key : keys)
				hm.put(key, st.nextToken());
			
			data.add(hm);
		}
		
			
		
	}
}
