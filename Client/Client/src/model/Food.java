package model;

import java.io.Serializable;

public class Food implements  Serializable{
private static final long serialVersionUID = 1L;
	
	//..................................................
	//instance field
    private String foodID;
	private String foodName;
	private String foodType;
	private double price;
	

	

	boolean exist;
	
	
	
	//......................................................
	public Food (){
		super();
		this.foodID =GenerateCode();
	}
	
	public Food(String foodID, String foodName, String foodType, double price,
			boolean exist) {
		super();
		this.foodID =GenerateCode();
		this.foodName = foodName;
		this.foodType = foodType;
		this.price = price;
		this.exist = exist;
	}
	
	//...................................................................
	
	
public String getFoodID() {
		return foodID;
	}

	public void setFoodID(String foodID) {
		this.foodID = foodID;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//.................................................................

	public String GenerateCode(){
		int random = (int)(Math.random()*999999) + 100000;
		String result=new String();
		result="F"+random;
		
		return result;
		}
	
	@Override
	public String toString() {
		return foodName;
	}
}
