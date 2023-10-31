package gameEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class GameField implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private HashMap<Coordinate, Cell> gameField = new HashMap<>();

	public GameField(int columns, int rows) {
		this.createClearField(columns, rows);
	}

	public HashMap<Coordinate, Cell> getGameField() {
		return gameField;
	}
	
	private void createClearField(int columns, int rows) {
		for (int column = 1; column <= columns; column++) {
			for (int row = 1; row <= rows; row++) {
				gameField.put(new Coordinate(column, row), new Cell(column, row, 0));
			}
		}
	}
	
	public void createBombs(int columns, int rows, int quantityOfBombs) {
		Random random = new Random();
		int counterBombs = 0;

		while (counterBombs < quantityOfBombs) {
			int column = random.nextInt(1, columns);
			int row = random.nextInt(1 , rows);
			Coordinate randomCoordinate = new Coordinate(column, row);

			if (!gameField.get(randomCoordinate).isBomb() && !gameField.get(randomCoordinate).isBeginingCell()) {
				gameField.get(randomCoordinate).setStepsFromBomb(9);
				counterBombs++;

				for (int y = 1; y >= -1; y--) {
					for (int x = -1; x <= 1; x++) {
						if (gameField.get(new Coordinate(column + y, row + x)) != null && 
								!gameField.get(new Coordinate(column + y, row + x)).isBomb()) {
							gameField.get(new Coordinate(column + y, row + x)).plusStep();
						}
					}
				}
			}
		}
	}
}
