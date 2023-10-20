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
		//case "1": startNewGame(); break;
		case "s": startNewGame(); break;
		//case "2": startNewGame(); break;
		case "e": System.exit(0); break;
		default:
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
	}
	
	private static void continuePreviosGame() {}
}
