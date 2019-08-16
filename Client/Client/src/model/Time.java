package model;

public class Time {
	
	private String name;
	private String start;
	private String end;
	
	

	public Time() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Time(String name, String start, String end) {
		super();
		this.name = name;
		this.start = start;
		this.end = end;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	
	

}
