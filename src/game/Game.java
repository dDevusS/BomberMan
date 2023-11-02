package game;

import java.io.File;

import game.data.GameSession;
import game.data.ProcessingSaveGame;
import game.data.RenderingGame;
import game.data.UserAction;

public class Game {

	private static final int DEFAULT_HEIGHT = 10;
	private static final int DEFAULT_WEDTH = 10;
	private static final int DEFAULT_QUANTITY_BOMBS_EASY = 20;
	private static final int DEFAULT_QUANTITY_BOMBS_MEDIUM = 30;
	private static final int DEFAULT_QUANTITY_BOMBS_HARD = 40;
	private static String userDir = System.getProperty("user.dir");
	private static File savedGame = new File(userDir + File.separator + "GameSession.save");

	public static void main(String[] args) {
		while (true) {
			boolean existSavedGame = savedGame.exists() && ProcessingSaveGame.loadGame() != null;
			launchMainMenu(existSavedGame);
		}
	}

	static void launchMainMenu(boolean existSavedGame) {
		if (existSavedGame) {
			GameSession game = ProcessingSaveGame.loadGame();
			RenderingGame.renderMainMenu(game, existSavedGame);
		} else {
			RenderingGame.renderMainMenu(null, existSavedGame);
		}

		while (true) {
			switch (UserAction.input.nextLine().toLowerCase()) {
			case "s":
				startNewGame();
				return;
			case "c":
				if (savedGame.exists() && ProcessingSaveGame.loadGame() != null) {
					ProcessingSaveGame.loadGame().startGame();
					return;
				} else {
					System.out.println("Uncorrect command.");
					break;
				}
			case "e":
				System.exit(0);
				return;
			default:
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
			case "1":
				return new GameSession(DEFAULT_HEIGHT, DEFAULT_WEDTH, DEFAULT_QUANTITY_BOMBS_EASY);
			case "2":
				return new GameSession(DEFAULT_HEIGHT, DEFAULT_WEDTH, DEFAULT_QUANTITY_BOMBS_MEDIUM);
			case "3":
				return new GameSession(DEFAULT_HEIGHT, DEFAULT_WEDTH, DEFAULT_QUANTITY_BOMBS_HARD);
			default:
				System.out.println("Uncorrect command.");
			}
		}
	}
}
