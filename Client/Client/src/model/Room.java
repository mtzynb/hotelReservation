package model;

import java.io.Serializable;

public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//..................................................
		//instance field
	    private String roomID;
		private String roomName;
		private String roomType;
		private double price;
		private int Capacity;
		

		

		boolean exist;
		
	//......................................................
		
		
		
		
		public String GenerateCode(){
			int random = (int)(Math.random()*999999) + 100000;
			String result=new String();
			result="R"+random;
			
			return result;
			}
		

	public Room(String roomID, String roomName, String roomType, double price,
			int capacity, boolean exist) {
		super();
		this.roomID = GenerateCode();
		this.roomName = roomName;
		this.roomType = roomType;
		this.price = price;
		Capacity = capacity;
		this.exist = exist;
	}


	public Room() {
		super();
		this.roomID = GenerateCode();
	}


	public String getRoomID() {
		return roomID;
	}


	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getCapacity() {
		return Capacity;
	}


	public void setCapacity(int capacity) {
		Capacity = capacity;
	}


	public boolean isExist() {
		return exist;
	}


	public void setExist(boolean exist) {
		this.exist = exist;
	}


	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", roomName=" + roomName
				+ ", roomType=" + roomType + ", price=" + price + ", Capacity="
				+ Capacity + ", exist=" + exist + "]";
	}
	
	
	
	

}
