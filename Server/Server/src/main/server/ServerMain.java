package main.server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;




import model.Food;
import model.Room;

import ui.Login;

public class ServerMain implements Runnable {


	
	
	public static ArrayList<Room> RoomList;
	public static ArrayList<Food> foodList;
	

	public static void main(String[] args) {
		
		
		RoomList = new ArrayList<Room>();
		if (new File("Room.rm").exists())
			RoomList = (ArrayList<Room>) org.File.IOFile.readObject("Room.rm");
		
		foodList = new ArrayList<Food>();
		if (new File("Food.fd").exists())
			foodList = (ArrayList<Food>) org.File.IOFile.readObject("Food.fd");


		new Login().setVisible(true);


	}
	// ..............................................................
		private static void create() {
			try {

				ServerSocket serverSocket = new ServerSocket(2015);
				while (true) {
					
					
					Socket socket = serverSocket.accept();
					new Thread(new ThreadOfServer(socket)).start();
					
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	

	
	// ..............................................................
	
	
	@Override
	public void run() {
		create();

	}

}
