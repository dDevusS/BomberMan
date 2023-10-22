package gameProcessing;

import java.util.HashMap;

import gameEntity.Cell;
import gameEntity.Coordinate;

public class RenderingField {
	
	public static void doRendering(GameSession game) {
		
		HashMap<Coordinate, Cell> gameField = game.getGameField().getGameField();
		
		for (int y = 0; y <= game.getColumns(); y++) {
			for (int x = 0; x <= game.getRows(); x++) {
				if (y == 0 && x == 0) {
					System.out.println("   ");
				}
				else if (y == 0 && x != 0) {
					System.out.println(" 1  2  3  4  5  6  7  8  9  10");
				}
				else if (x == 0 && y > 0) {
					System.out.println(" " + y + " ");
				}
				else {
					
				}
			}
		}
	}
}
