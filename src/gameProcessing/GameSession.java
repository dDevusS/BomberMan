package gameProcessing;

import java.util.HashMap;

import gameEntity.Cell;
import gameEntity.Coordinate;
import gameEntity.GameField;

public class GameSession {

	private int columns;
	private int rows;
	private int quantityOfBombs;
	private int visibleCell;
	private int counterTurns = 0;
	private GameField gameField;
	
	public GameSession(int columns, int rows, int quantutyOfBombs) {
		this.columns = columns;
		this.rows = rows;
		this.quantityOfBombs = quantutyOfBombs;
		this.gameField = new GameField(columns, rows);
	}
	
	public void startGame() {
		RenderingField.doRendering(this);
	}
	
	public GameField getGameField() {
		return gameField;
	}
	
	public String showCell(int colum, int row) {
		if (!this.gameField.getGameField().get(new Coordinate(colum, row)).isVisible()) {
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
}
