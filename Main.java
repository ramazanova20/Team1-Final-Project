import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		DataSet my_dataSet = new DataSet();
		
		try {
			my_dataSet.loadFromFile("dataset.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
