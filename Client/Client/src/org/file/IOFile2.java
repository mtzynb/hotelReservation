package org.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOFile2 {

	public static boolean save(String n, Object o) {

		FileOutputStream FileOutputStream = null;
		ObjectOutputStream ObjectOutputStream = null;

		try {

			FileOutputStream = new FileOutputStream(n);
			ObjectOutputStream = new ObjectOutputStream(FileOutputStream);
			ObjectOutputStream.writeObject(o);
			ObjectOutputStream.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ObjectOutputStream.close();
				FileOutputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
				return false;

			}

		}

		return true;

	}

	// ...................................................................
	
	public static Object Load(String n) {
		FileInputStream FileInputStream = null;
		ObjectInputStream ObjectInputStream = null;
		Object resultObject;

		try {
			FileInputStream = new FileInputStream(n);
			ObjectInputStream = new ObjectInputStream(FileInputStream);
			resultObject = ObjectInputStream.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		catch (IOException e) {
			e.printStackTrace();
			return null;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				FileInputStream.close();
				ObjectInputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		return resultObject;

	}

}
