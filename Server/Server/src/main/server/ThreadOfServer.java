package main.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


import model.Food;
import model.Request;
import model.Room;

import ui.Frame;

public class ThreadOfServer implements Runnable {

	private Request request;
	private Socket socketOfclient;
	private InputStream inputStream;
	private OutputStream outputStream;

	// ....................................................................
	// costructor

	public ThreadOfServer(Socket s) {
		super();
		this.socketOfclient = s;
		try {

			this.outputStream = this.socketOfclient.getOutputStream();
			this.inputStream = this.socketOfclient.getInputStream();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ....................................................................
	// ............................private_methods.........................
	// ....................................................................
	
	
		private void SendlistForClient() {

			try {

				ObjectOutputStream objectOutputStream = new ObjectOutputStream(
						this.outputStream);
				
				
				ArrayList<Food> foodAvalableList=new ArrayList<Food>();
				if (ServerMain.foodList != null) {

					ArrayList<Food> foodList=ServerMain.foodList;
					for (Food food : foodList) {
						
						if(food.isExist()==true){
							
							foodAvalableList.add(food);
						}
						
					}
					

					
				}
				
				
				objectOutputStream.writeObject(foodAvalableList);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private void SendRoomForClient() {

			try {

				ObjectOutputStream objectOutputStream = new ObjectOutputStream(
						this.outputStream);
				
				
				ArrayList<Room> roomAvalableList=new ArrayList<Room>();
				if (ServerMain.RoomList != null) {

					ArrayList<Room> RoomList=ServerMain.RoomList;
					for (Room room : RoomList) {
						
						if(room.isExist()==true){
							
							roomAvalableList.add(room);
						}
						
					}
					

					
				}
				
				
				objectOutputStream.writeObject(roomAvalableList);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	// ....................................................................
		
	private void checkTypeRequestFromClient() {

		String typeRequest = this.request.getTypeRequest();

		if (typeRequest.equals("conectClient")) {


			SendlistForClient();
			SendRoomForClient();

			while (this.socketOfclient.isConnected()) {

				try {

					Scanner s = new Scanner(this.inputStream);
					String request = s.nextLine();

					if (request.equals("listFile")) {

						SendlistForClient();
					}
					
					if(request.equals("listRoom")){
						SendRoomForClient();
						
					}

				} catch (NoSuchElementException e) {

				}
			}

		

		} else if (typeRequest.equals("exit")) {

			Scanner scanner = new Scanner(this.inputStream);
			String S = scanner.nextLine();
			
		}
		
		else if (typeRequest.equals("listFile")){
			SendlistForClient();
		}

	}

	// ....................................................................
	private Request getRequest() {

		Request r = null;

		try {

			ObjectInputStream objectinputStream = new ObjectInputStream(
					this.inputStream);
			r = (Request) objectinputStream.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return r;

	}

	
	

	// ....................................................................
	@Override
	public void run() {
		this.request = getRequest();
		checkTypeRequestFromClient();
	}

}
