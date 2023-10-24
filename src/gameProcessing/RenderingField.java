package gameProcessing;

public class RenderingField {
	
	public static void doRendering(GameSession game) {
		
		for (int y = -1; y <= game.getColumns(); y++) {
			for (int x = 0; x <= game.getRows(); x++) {
				if (y == -1 && x == 0 || y == 0 && x == 0) {
					System.out.print("   ");
				}
				else if (y == 0) {
					System.out.print("----");
					if (x == game.getRows()) System.out.print("\n");
				}	
				else if (y == -1) {
					if (x < 10) {
						System.out.print("  " + x + " ");
						if (x == game.getRows()) System.out.print("\n");
					}
					else {
						System.out.print(" "+ x + " ");
						if (x == game.getRows()) System.out.print("\n");
					}
				}
				else if (x == 0 && y > 0) {
					if (y < 10) {
						System.out.print(" " + y + "|");
					}
					else {
						System.out.print(y + "|");
					}
				}
				else {
						System.out.print("  " + game.showCell(x, y) + " ");
				}
			}
			if (y != game.getColumns() && y > 0) System.out.println("\n  |");
		}
	}
}
