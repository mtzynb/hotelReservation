package model;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Receipt implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	//..................................................
		//instance field
		private String ReceiptID;
		private String ReceiptName;
		private double ReceiptPrice;
		private Food Food;
		private int numberOfFood;
		
		//.....................................................
		
		
		
		
		
		public int getNumberOfFood() {
			return numberOfFood;
		}



		public void setNumberOfFood(int numberOfFood) {
			this.numberOfFood = numberOfFood;
		}



		public Receipt() {
			super();
			ReceiptID = GenerateCode();
			// TODO Auto-generated constructor stub
		}
		
		
	
		public Receipt( String receiptName,
				double receiptPrice, model.Food food) {
			super();
			ReceiptID = GenerateCode();
			ReceiptName = receiptName;
			ReceiptPrice = receiptPrice;
			Food = food;
		}



		public String getReceiptID() {
			return ReceiptID;
		}
		public void setReceiptID(String receiptID) {
			ReceiptID = receiptID;
		}
		public String getReceiptName() {
			return ReceiptName;
		}
		public void setReceiptName(String receiptName) {
			ReceiptName = receiptName;
		}
		public Food getFood() {
			return Food;
		}
		public void setFood(Food food) {
			Food = food;
		}
		public double getReceiptPrice() {
			return ReceiptPrice;
		}
		public void setReceiptPrice(double receiptPrice) {
			ReceiptPrice = receiptPrice;
		}
		@Override
		public String toString() {
			return "Receipt [ReceiptID=" + ReceiptID + ", ReceiptName="
					+ ReceiptName + ", ReceiptPrice=" + ReceiptPrice
					+ ", numberOfFood=" + numberOfFood + ", Food=" + Food + "]";
		}
		
		
		public String GenerateCode(){
			int random = (int)(Math.random()*999999) + 100000;
			String result=new String();
			result="REC"+random;
			
			return result;
			}

}
