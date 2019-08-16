package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Guest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//..................................................
	//instance field
	private String GuestID;
	private String guestName;
	private String cellNo;
	private int numberOfGuest;
	private String startDate;
	private String endDate;
	private int stayingTime;
	private Room roomID;
	
	 ArrayList<Receipt> listOfReceipt;
	
	
	

	
	
	@Override
	public String toString() {
		return "Guest [GuestID=" + GuestID + ", guestName=" + guestName
				+ ", cellNo=" + cellNo + ", numberOfGuest=" + numberOfGuest
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", stayingTime=" + stayingTime + ", roomID=" + roomID
				+ ", listOfReceipt=" + listOfReceipt + "]";
	}

	public ArrayList<Receipt> getListOfReceipt() {
		return listOfReceipt;
	}

	public void setListOfReceipt(ArrayList<Receipt> listOfReceipt) {
		this.listOfReceipt = listOfReceipt;
	}

	public Guest() {
		super();
		GuestID = GenerateCode();
	}

	public Guest(String guestID, String guestName, String cellNo,
			int numberOfGuest, String startDate, String endDate,
			int stayingTime, Room roomID, double stayingPrice,
			double servicePrice) {
		super();
		GuestID = GenerateCode();
		this.guestName = guestName;
		this.cellNo = cellNo;
		this.numberOfGuest = numberOfGuest;
		this.startDate = startDate;
		this.endDate = endDate;
		this.stayingTime = stayingTime;
		this.roomID = roomID;
	
		
	}
//...............................................................

	public String getGuestID() {
		return GuestID;
	}

	public void setGuestID(String guestID) {
		GuestID = guestID;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public int getNumberOfGuest() {
		return numberOfGuest;
	}

	public void setNumberOfGuest(int numberOfGuest) {
		this.numberOfGuest = numberOfGuest;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getStayingTime() {
		return stayingTime;
	}

	public void setStayingTime(int stayingTime) {
		this.stayingTime = stayingTime;
	}

	public Room getRoom() {
		return roomID;
	}

	public void setRoomID(Room roomID) {
		this.roomID = roomID;
	}


	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String GenerateCode(){
		int random = (int)(Math.random()*999999) + 100000;
		String result=new String();
		result="G"+random;
		
		return result;
		}

	
	
	
	//.....................................................
	

}
