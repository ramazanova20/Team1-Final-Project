
public class DateHolder {
	private int day;
	private int month;
	private int year;
	
	public DateHolder(String dateString) {
		String[] dateArray = dateString.split("/");
		
		this.day = Integer.valueOf(dateArray[1]);
		this.month = Integer.valueOf(dateArray[0]);
		this.year = Integer.valueOf(dateArray[2]);
	}
	
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
