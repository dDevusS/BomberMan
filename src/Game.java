import gameProcessing.GameSession;
import gameProcessing.UserAction;

public class Game {

	public static void main(String[] args) {
		launchMainMenu();
	}
	
	private static void launchMainMenu() {
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
		
		switch (UserAction.input.nextLine()) {
			case "1" : GameSession game = new GameSession(10, 10, 20); break;
			case "2" : GameSession game = new GameSession(10, 10, 30); break;
			case "3" : GameSession game = new GameSession(10, 10, 40); break;
			default :
				System.out.println("Uncorrect command.");
		}
	}
	
	private static void continuePreviosGame() {}
	
}
