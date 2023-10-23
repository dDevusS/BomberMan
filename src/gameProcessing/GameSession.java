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
	private GameField gameField;
	
	public GameSession(int columns, int rows, int quantutyOfBombs) {
		this.columns = columns;
		this.rows = rows;
		this.quantityOfBombs = quantutyOfBombs;
		this.gameField = new GameField(columns, rows, quantutyOfBombs);
	}
	
	public void startGame() {
		RenderingField.doRendering(this);
	}
	
	public GameField getGameField() {
		return gameField;
	}

	public void isPlay() {}
	
	public void isPause() {}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}
	
}
