package gameProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ProcessingSaveGame {
	
	private static String userDir = System.getProperty("user.dir");
	private static File file = new File(userDir + File.separator + "GameSession.save");

	public static void saveGame(GameSession game) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			oos.writeObject(game);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GameSession loadGame() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (GameSession)ois.readObject();
		} 
		catch (Exception e) {
			return null;
		}
	}

	public static void deleteSaveGame() {
		if (file.exists()) {
			file.delete();
		}
	}
}
