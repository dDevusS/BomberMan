package gameProcessing;

import java.util.HashMap;

import gameEntity.Cell;
import gameEntity.Coordinate;

public class RenderingField {
	
	public static void doRendering(GameSession game) {
		
		HashMap<Coordinate, Cell> gameField = game.getGameField().getGameField();
		
		for (int y = -2; y <= game.getColumns(); y++) {
			for (int x = -1; x <= game.getRows(); x++) {
				if (y == -2 && x == -1 || y == -1 && x == -1) {
					System.out.print("   ");
				}
				else if (y == -1) {
					System.out.print("---");
					if (x == game.getRows()) System.out.print("\n");
				}	
				else if (y == -2) {
					if (x < 10) {
						System.out.print(" " + x + " ");
						if (x == game.getRows()) System.out.print("\n");
					}
					else {
						System.out.print(x + " ");
						if (x == game.getRows()) System.out.print("\n");
					}
				}
				else if (x == -1 && y > -1) {
					if (y < 10) {
						System.out.print(" " + y + "|");
					}
					else {
						System.out.print(y + "|");
					}
				}
				else {
					if (gameField.get(new Coordinate(y, x)) == null) {
						System.out.print(" 0 ");
					}
					else {
						System.out.print(" " + gameField.get(new Coordinate(y, x)).getStepsFromBomb() + " ");
					}
				}
			}
			if (y != game.getColumns() && y > -1) System.out.println("\n  |");
		}
	}
}
