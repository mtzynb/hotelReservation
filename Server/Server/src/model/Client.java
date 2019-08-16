package model;

import java.awt.ComponentOrientation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;





public class Client extends Thread implements Serializable{
	private static final long serialVersionUID = 1L;

	private Socket s;
	private InputStream inputStream;
	private OutputStream OutputStream;
	private Request request;
	private String filePathForUpload;
	private String clientName;
	private String fileNameForDownload;
	private String filePathforDownload;
	private  ArrayList<Food> listOfFiles;
	public static   ArrayList<Food> listttttttt;
	
	private  ArrayList<Room> listOfRoom;
	
	//
	private  ArrayList<Receipt> listOfReceipt;
	
	private String ClientID;
	

	private String cellNo;
	private int numberOfGuest;
	private String startDate;
	private String endDate;
	private Room room;
	
	
	
	
	
	

	// ................................................................
	// constructor
	

	public Client(String Typerequest, String clientName) {
		super();
		try {
			
			this.ClientID=GenerateCode();
			this.request = new Request();
			this.request.setTypeRequest(Typerequest);
			this.setClientName(clientName);
			this.request.setClientName(this.getClientName());
			this.s = new Socket("127.0.0.1", 2015);
			this.inputStream = s.getInputStream();
			this.OutputStream = s.getOutputStream();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Client() {
		
		this.ClientID=GenerateCode();
	}

	

	

	// ................................................................
	// getters & setters
	
	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String clientID) {
		ClientID = clientID;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ArrayList<Receipt> getListOfReceipt() {
		return listOfReceipt;
	}

	public void setListOfReceipt(ArrayList<Receipt> listOfReceipt) {
		this.listOfReceipt = listOfReceipt;
	}

	public ArrayList<Food> getListOfFiles() {
		return listOfFiles;
	}

	public ArrayList<Room> getListOfRoom() {
		return listOfRoom;
	}

	public void setListOfFood(ArrayList<Room> listOfFood) {
		this.listOfRoom = listOfFood;
	}
	
	public String GenerateCode(){
		int random = (int)(Math.random()*999999) + 100000;
		String result=new String();
		result="F"+random;
		
		return result;
		}

	public String getfilePathforDownload() {
		return filePathforDownload;
	}

	public void setfilePathforDownload(String filePathToRecive) {
		this.filePathforDownload = filePathToRecive;
	}

	public String getFilePathToUpload() {
		return filePathForUpload;
	}

	public void setFilePathToUpload(String filePath) {
		this.filePathForUpload = filePath;
	}

	public void setfileNameForDownload(String fileNameToRecive) {
		this.fileNameForDownload = fileNameToRecive;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setListOfFiles( ArrayList<Food> listOfFiles) {
		this.listOfFiles = listOfFiles;
	}

	public  ArrayList<Food> getListOfAvailableFiles() {
		return this.listOfFiles;
	}

	// ................................................................
	// private methods
	// ................................................................
	
	private void checkRequest() {

		String r = this.request.getTypeRequest();

		if (r.equals("download")) {

//			this.request.setFileNameToDownload(fileNameForDownload);
//			
//			sendRequest();
//			
//			
//			HistoryOfDownload historyOfDownload = new HistoryOfDownload();
//			File f = new File(filePathforDownload);
//			historyOfDownload.addFilePath(f.getAbsolutePath());
//			historyOfDownload.setStart(new Date());
//			historyOfDownload.setUsername(this.clientName);
//
//			try {
//
//				long size = new Scanner(this.inputStream).nextLong();
//				RandomAccessFile randomAccessFile = null;
//
//				randomAccessFile = new RandomAccessFile(f, "rw");
//
//				ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//				FileChannel fileChannel = randomAccessFile.getChannel();
//				ReadableByteChannel readableByteChannel = Channels
//						.newChannel(this.inputStream);
//
//				int x = 0;
//				int d = -1;
//
//				Frame.DownloadprogressBar(0, true, (int) size, f.getName());
//
//				while ((d = readableByteChannel.read(buffer)) != -1) {
//					buffer.flip();
//					fileChannel.write(buffer);
//					buffer.clear();
//					x += d;
//					Frame.DownloadprogressBar(x, false, (int) size, null);
//
//				}
//
//				Frame.DownloadprogressBar((int) size, false, (int) size, null);
//				fileChannel.close();
//				randomAccessFile.close();
//
//				if (f.length() != 0) {
//
//					JOptionPane.showMessageDialog(null,
//							"فایل مورد نظر با موفقیت دانلود شد.");
//
//					historyOfDownload.setEnd(new Date());
//					HistoryOfDownloadDAO.addDownloadHistoty(historyOfDownload);
//					HistoryOfDownloadDAO.updateHistoryOfDownload();
//
//				} else {
//
//					JOptionPane.showMessageDialog(null, "فایل در دسترس نمی باشد.");
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//
//				try {
//					this.s.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}

		} else if (r.equals("upload")) {

//			File f = new File(this.filePathForUpload);
//			this.request.setFileNameToUpload(f.getName());
//			sendRequest();
//		
//			try {
//
//			
//
//				RandomAccessFile randomAccessFile = null;
//				PrintWriter printWriter = new PrintWriter(this.OutputStream);
//				printWriter.println(f.length());
//				printWriter.flush();
//				randomAccessFile = new RandomAccessFile(f, "r");
//
//				FileChannel fileChannel = randomAccessFile.getChannel();
//				ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//				WritableByteChannel writableByteChannel = Channels
//						.newChannel(this.OutputStream);
//				while (fileChannel.read(buffer) != -1) {
//					buffer.flip();
//					writableByteChannel.write(buffer);
//					buffer.clear();
//				}
//
//				writableByteChannel.close();
//
//				randomAccessFile.close();
//
//				JOptionPane.showMessageDialog(null, "عملیات آپلود فایل انجام شد .");
//			} catch (FileNotFoundException e) {
//				JOptionPane
//						.showMessageDialog(null, "عملیات آپلود فایل انجام نشد .");
//				e.printStackTrace();
//			} catch (IOException e) {
//				JOptionPane
//						.showMessageDialog(null, "عملیات آپلود فایل انجام نشد .");
//
//				e.printStackTrace();
//			} finally {
//				try {
//					this.s.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			
		} else if (r.equals("conectClient")) {

			this.request.setListOfFileRequest(true);
			sendRequest();
			this.listOfFiles = getListFiles();
			this.listOfRoom = getListRoom();
			
			
			
		//	HistoryOfDownloadDAO.loadHistoryOfDownload();
			
			System.out.println("mmmmmmmmmm"+this.listOfFiles.toString());
			listttttttt=this.listOfFiles;
			System.out.println("333333"+listttttttt);
		}
		if (r.equals("exit")) {

			try {

				sendRequest();

			} catch (NullPointerException e) {
				

			}
			try {
				PrintWriter w = new PrintWriter(this.OutputStream);
				w.println("\" " + this.clientName + " \" "
						+ " از سرور خارج شده است .");
				w.flush();
			} catch (NullPointerException e) {

			} finally {
				System.exit(0);
			}
		}



	}
	// ................................................................

		public void requestToServer(String TypeRequest) {

			PrintWriter PrintWriter = new PrintWriter(this.OutputStream);

			if (TypeRequest.equals("download")) {

				Client Client = new Client(TypeRequest, this.clientName);
				Client.setfileNameForDownload(fileNameForDownload);
				Client.setfilePathforDownload(this.filePathforDownload);
				Thread t = new Thread(Client);
				t.start();

			} else if (TypeRequest.equals("upload")) {

				Client client = new Client(TypeRequest, this.clientName);
				client.setFilePathToUpload(this.filePathForUpload);
				new Thread(client).start();

			} else if (TypeRequest.equals("listFile")) {

				PrintWriter.println("listFile");
				PrintWriter.flush();
				this.listOfFiles = getListFiles();

			} else if (TypeRequest.equals("exit")) {

				Client cc = new Client("exit", this.clientName);
				new Thread(cc).start();

			}else if (TypeRequest.equals("listRoom")) {

				PrintWriter.println("listRoom");
				PrintWriter.flush();
				this.listOfRoom = getListRoom();
				}
			

		}
	// ................................................................



	private void sendRequest() {
		try {

			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(
					this.OutputStream);
			ObjectOutputStream.writeObject(this.request);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ................................................................
	public  ArrayList<Food> getListFiles() {

		 ArrayList<Food> temp = null;
		try {

			ObjectInputStream objectInputStream = new ObjectInputStream(
					this.inputStream);
			temp = ( ArrayList<Food>) objectInputStream.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return temp;

	}

	private  ArrayList<Room> getListRoom() {

		 ArrayList<Room> temp = null;
		try {

			ObjectInputStream objectInputStream = new ObjectInputStream(
					this.inputStream);
			temp = ( ArrayList<Room>) objectInputStream.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return temp;

	}

	// ................................................................
	@Override
	public void run() {
		checkRequest();
	}

}
