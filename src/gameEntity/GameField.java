package gameEntity;

import java.util.HashMap;
import java.util.Random;

public class GameField {

	private HashMap<Coordinate, Cell> gameField = new HashMap<>();

	public GameField(int columns, int rows, int quantityOfBombs) {
		Random random = new Random();
		int counterBombs = 0;

		while (counterBombs < quantityOfBombs) {
			int column = random.nextInt(1, columns);
			int row = random.nextInt(1 , rows);
			Coordinate randomCoordinate = new Coordinate(column, row);

			if (gameField.get(randomCoordinate) == null || !gameField.get(randomCoordinate).isBomb()) {
				gameField.put(randomCoordinate, new Cell(column, row, 9));
				counterBombs++;

				for (int y = 1; y >= -1; y--) {
					for (int x = -1; x <= 1; x++) {
						if (gameField.get(new Coordinate(column + y, row + x)) == null) {
							gameField.put(new Coordinate(column + y, row + x), new Cell(column + y, row + x, 1));
						} else if (!gameField.get(new Coordinate(column + y, row + x)).isBomb()) {
							gameField.get(new Coordinate(column + y, row + x)).plusStep();
						}
					}
				}
			}
		}
	}

	public HashMap<Coordinate, Cell> getGameField() {
		return gameField;
	}
}
