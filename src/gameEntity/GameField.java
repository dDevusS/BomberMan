package gameEntity;

import java.util.HashMap;
import java.util.Random;

public class GameField {

	private HashMap<Coordinate, Cell> gameField = new HashMap<>();

	public GameField(int columns, int rows, int quantityOfBombs) {
		this.createClearField(columns, rows);
		this.createBombs(columns, rows, quantityOfBombs);
	}

	public HashMap<Coordinate, Cell> getGameField() {
		return gameField;
	}
	
	public String showCell(int colum, int row) {
		// TODO: Manage method with visible cells;
		if (this.getGameField().get(new Coordinate(colum, row)) == null) {
			return "0";
		}
		
		return Integer.toString(this.getGameField().get(new Coordinate(colum, row)).getStepsFromBomb());
	}
	
	private void createClearField(int columns, int rows) {
		for (int column = 1; column <= columns; column++) {
			for (int row = 1; row <= rows; row++) {
				gameField.put(new Coordinate(column, row), new Cell(column, row, 0));
			}
		}
	}
	
	private void createBombs(int columns, int rows, int quantityOfBombs) {
		Random random = new Random();
		int counterBombs = 0;

		while (counterBombs < quantityOfBombs) {
			int column = random.nextInt(1, columns);
			int row = random.nextInt(1 , rows);
			Coordinate randomCoordinate = new Coordinate(column, row);

			if (!gameField.get(randomCoordinate).isBomb()) {
				gameField.get(randomCoordinate).setStepsFromBomb(9);
				counterBombs++;

				for (int y = 1; y >= -1; y--) {
					for (int x = -1; x <= 1; x++) {
						if (gameField.get(new Coordinate(column + y, row + x)) != null && !gameField.get(new Coordinate(column + y, row + x)).isBomb()) {
							gameField.get(new Coordinate(column + y, row + x)).plusStep();
						}
					}
				}
			}
		}
	}
	
	private void createVisibleZone() {}
}
