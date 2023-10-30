package gameProcessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game {

	public static void main(String[] args) {
		launchMainMenu();
	}
	
	static void launchMainMenu() {
		System.out.println("""
				BomberMan.
			------------------
			Start new game (press s)
			Exit (press e)
				""");
		
		switch (UserAction.input.nextLine().toLowerCase()) {
			//case "1" : startNewGame(); break;
			case "s" : startNewGame(); break;
			//case "2" : startNewGame(); break;
			case "e" : System.exit(0); break;
			default :
				System.out.println("Uncorrect command.");
		}
	}
	
	private static void startNewGame() {
		System.out.println("""
				Choose dificult level.
			------------------------------
			(press from 1 to 3)
			1 - easy
			2 - medium
			3 - hard
				""");
		
		GameSession game = chooseDificult();
		game.startGame();
	}
	
	private static GameSession chooseDificult() {
		while (true) {
			switch (UserAction.input.nextLine()) {
			case "1" : return new GameSession(10, 10, 20);
			case "2" : return new GameSession(10, 10, 30);
			case "3" : return new GameSession(10, 10, 40);
			default :
				System.out.println("Uncorrect command.");
			}
		}
	}
	
	private static void saveGame(GameSession game) {
		try {
			FileOutputStream fos = new FileOutputStream("GameSession.save");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(game);
			
			oos.close();
		} 
		catch (Exception e) {
			String workingDirectiry = System.getProperty("User.dir");
			String fileName = "GameSession.save";
			String filePath = workingDirectiry + File.separator + fileName;
			
			File newFile = new File(filePath);
			
			try {
				newFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			saveGame(game);
		}
	}
	
	private static GameSession continuePreviosGame() {
		try {
			FileInputStream fis = new FileInputStream("GameSession.save");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ois.close();
			return (GameSession)ois.readObject();
		} 
		catch (Exception e) {
			return null;
		}
	}
}
