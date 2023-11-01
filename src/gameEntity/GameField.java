package gameEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class GameField implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private HashMap<Coordinate, Cell> gameField = new HashMap<>();

	public GameField(int rows, int columns) {
		this.createClearField(rows, columns);
	}

	public HashMap<Coordinate, Cell> getGameField() {
		return gameField;
	}
	
	private void createClearField(int rows, int columns) {
		for (int row = 1; row <= rows; row++) {
			for (int column = 1; column <= columns; column++) {
				gameField.put(new Coordinate(row, column), new Cell(row, column, 0));
			}
		}
	}
	
	public void createBombs(int rows, int columns, int quantityOfBombs) {
		Random random = new Random();
		int counterBombs = 0;

		while (counterBombs < quantityOfBombs) {
			int row = random.nextInt(1 , rows);
			int column = random.nextInt(1, columns);
			Coordinate randomCoordinate = new Coordinate(row, column);

			if (!gameField.get(randomCoordinate).isBomb() && !gameField.get(randomCoordinate).isBeginingCell()) {
				gameField.get(randomCoordinate).setStepsFromBomb(9);
				counterBombs++;

				for (int y = 1; y >= -1; y--) {
					for (int x = -1; x <= 1; x++) {
						if (gameField.get(new Coordinate(column, row).shiftCoordinate(y, x)) != null && 
								!gameField.get(new Coordinate(column, row).shiftCoordinate(y, x)).isBomb()) {
							gameField.get(new Coordinate(column, row).shiftCoordinate(y, x)).plusStep();
						}
					}
				}
			}
		}
	}
}
