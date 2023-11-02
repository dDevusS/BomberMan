package gameProcessing;

import java.io.File;

public class Game {

	public static void main(String[] args) {
		while (true) {
			File savedGame = new File("GameSession.save");
			boolean existSavedGame = savedGame.exists() && ProcessingSaveGame.loadGame() != null;
			launchMainMenu(existSavedGame);
		}
	}
	
	static void launchMainMenu(boolean existSavedGame) {
		File savedGame = new File("GameSession.save");
		if (existSavedGame) {
			GameSession game = ProcessingSaveGame.loadGame();
			RenderingGame.renderMainMenu(game, existSavedGame);
		}
		else {
			RenderingGame.renderMainMenu(null, existSavedGame);
		}
		
		while (true) {
			switch (UserAction.input.nextLine().toLowerCase()) {
				case "s" :
					startNewGame();
					return;
				case "c" :
					if (savedGame.exists() && ProcessingSaveGame.loadGame() != null) {
						ProcessingSaveGame.loadGame().startGame();
						return;
					}
					else {
						System.out.println("Uncorrect command.");
						break;
					}
				case "e" :
					System.exit(0);
					return;
				default :
					System.out.println("Uncorrect command.");
					break;
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
