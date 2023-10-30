package gameProcessing;

import java.io.Serializable;

import gameEntity.Cell;
import gameEntity.Coordinate;
import gameEntity.GameField;

public class GameSession implements Serializable {

	private int columns;
	private int rows;
	private int quantityOfBombs;
	private int visibleCells;
	private int counterTurns = 0;
	private GameField gameField;
	private boolean isExploded = false;
	
	public GameSession(int columns, int rows, int quantutyOfBombs) {
		this.columns = columns;
		this.rows = rows;
		this.quantityOfBombs = quantutyOfBombs;
		this.gameField = new GameField(columns, rows);
	}
	
	public void startGame() {
		RenderingField.doRendering(this);
		while (true) {
			UserAction.makeTurn(this);
			RenderingField.doRendering(this);
			if (isExploded) {
				break;
			}
		}
		Game.launchMainMenu();
	}
	
	public GameField getGameField() {
		return gameField;
	}
	
	public String showCell(int colum, int row) {
		if (isExploded && this.gameField.getGameField().get(new Coordinate(colum, row)).isBomb()) {
			return "*";
		}
		else if (isExploded) {
			return Integer.toString(this.gameField.getGameField().get(new Coordinate(colum, row)).getStepsFromBomb());
		}
		else if (!this.gameField.getGameField().get(new Coordinate(colum, row)).isVisible()) {
			return ".";
		}
		else if (this.gameField.getGameField().get(new Coordinate(colum, row)).isBomb()) {
			return "*";
		}
		
		return Integer.toString(this.gameField.getGameField().get(new Coordinate(colum, row)).getStepsFromBomb());
	}

	public void isPlay() {}
	
	public void isPause() {}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public int getCounterTurns() {
		return counterTurns;
	}
	
	public void increaceCounterTurn() {
		this.counterTurns++;
	}

	public int getQuantityOfBombs() {
		return quantityOfBombs;
	}
	
	public void makeExplosion() {
		this.isExploded = true;
	}
	
	public Cell getCell(Coordinate coordinate) {
		return gameField.getGameField().get(coordinate);
	}

	public boolean isExploded() {
		return isExploded;
	}
}
