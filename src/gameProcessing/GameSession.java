package gameProcessing;

import java.util.HashMap;

import gameEntity.Cell;
import gameEntity.Coordinate;
import gameEntity.GameField;

public class GameSession {

	private int columns;
	private int rows;
	private int quantityOfBombs;
	private GameField gameField;
	
	public GameSession(int columns, int rows, int quantutyOfBombs) {
		this.columns = columns;
		this.rows = rows;
		this.quantityOfBombs = quantutyOfBombs;
		this.gameField = new GameField(columns, rows, quantutyOfBombs);
	}
	
	public void isPlay() {}
	
	public void isPause() {}
}
