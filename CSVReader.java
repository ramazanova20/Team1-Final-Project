import java.util.LinkedList;

public class CSVReader {
	private LinkedList<LinkedList<String>> buffer;
	
	public CSVReader() {
		this.buffer = new LinkedList<LinkedList<String>>();
	}
	
	public LinkedList<LinkedList<String>> getBuffer() {
		return this.buffer;
	}
	
	public void setBuffer(LinkedList<LinkedList<String>> newBuffer) {
		this.buffer = newBuffer;
	}
}
