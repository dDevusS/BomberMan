import game_processing.UserAction;

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
		//case "1": startNewGame(); break;
		case "s": startNewGame(); break;
		//case "2": startNewGame(); break;
		case "e": System.exit(0); break;
		default:
			System.out.println("Uncorrect command.");
		}
	}
	
	private static void startNewGame() {}
	
	private static void continuePreviosGame() {}
}
