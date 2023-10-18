package game_processing;

import java.util.HashMap;
import game_entity.Cell;
import game_entity.Coordinate;
import game_entity.GameField;

public class GameSession {

	private int columns;
	private int rows;
	private int quantityOfBombs;
	private HashMap<Coordinate, Cell> gameField;
	
	public GameSession(int columns, int rows, int quantutyOfBombs) {
		this.columns = columns;
		this.rows = rows;
		this.quantityOfBombs = quantutyOfBombs;
		this.gameField = new HashMap<Coordinate, Cell>();
	}
	
	public void isPlay() {}
	
	public void isPause() {}
}
