package main.client;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import org.file.IOFile;

import model.Client;
import model.Guest;
import model.Room;

import ui.ClientLogin;



public class ClientMain  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<Guest> ClientList;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		
		ClientList = new ArrayList<Guest>();
		if (new File("Client.Client").exists())
			ClientList = (ArrayList<Guest>)IOFile.readObject("Client.Client");



		ClientLogin clientlogin = new ClientLogin();
		clientlogin.setVisible(true);



	}

}
