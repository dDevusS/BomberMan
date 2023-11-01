package gameProcessing;

import java.io.File;

public class Game {

	public static void main(String[] args) {
		launchMainMenu();
	}
	
	static void launchMainMenu() {
		System.out.println("""
				BomberMan.
			------------------
			""");
		
		String workingDirectiry = System.getProperty("user.dir");
		String fileName = "GameSession.save";
		String filePath = workingDirectiry + File.separator + fileName;
		
		File newFile = new File(filePath);
		
		if (newFile.exists() && ProcessingSaveGame.loadGame() != null) {
			System.out.println("Continue game (Press c)");
		}
		
		System.out.println("""
			Start new game (press s)
			Exit (press e)
				""");
		while (true) {
			switch (UserAction.input.nextLine().toLowerCase()) {
				case "s" : startNewGame(); break;
				case "c" : ProcessingSaveGame.loadGame().startGame();; break;
				case "e" : System.exit(0); break;
				default :
					System.out.println("Uncorrect command.");
			}
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
}
