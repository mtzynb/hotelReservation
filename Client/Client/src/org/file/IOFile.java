package org.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class IOFile implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param O
	 * @param fileName
	 * @return
	 */
	public static boolean writeObject(Object O, String fileName){
		FileOutputStream out;
		try {
			out = new FileOutputStream(fileName);
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(O);
			objOut.flush();
			objOut.close();
			out.close();
		} catch (IOException e) {
			return false;
		} 
		return true;
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	 public static Object readObject(String fileName){
		Object result = null;
		try {
			FileInputStream in = new FileInputStream(fileName);
			ObjectInputStream objIn = new ObjectInputStream(in);
			result =  objIn.readObject();
			objIn.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
		return result;
	 }
}
