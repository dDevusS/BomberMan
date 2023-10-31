package gameProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoadGameSession {

	public static void saveGame(GameSession game) {
		try {
			String workingDirectiry = System.getProperty("user.dir");
			String fileName = "GameSession.save";
			String filePath = workingDirectiry + File.separator + fileName;
			
			File newFile = new File(filePath);
			
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(newFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(game);
			
			oos.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GameSession loadGame() {
		try {
			FileInputStream fis = new FileInputStream("GameSession.save");
			ObjectInputStream ois = new ObjectInputStream(fis);
			GameSession game = (GameSession)ois.readObject();
			
			ois.close();
			
			return game;
		} 
		catch (Exception e) {
			return null;
		}
	}

}
