package gameProcessing;

import java.io.Serializable;

import gameEntity.Cell;
import gameEntity.Coordinate;
import gameEntity.GameField;

public class GameSession implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int columns;
	private int rows;
	private int quantityOfBombs;
	private int hiddenCells;
	private int counterTurns = 0;
	private GameField gameField;
	private boolean isExploded = false;
	
	public GameSession(int columns, int rows, int quantutyOfBombs) {
		this.columns = columns;
		this.rows = rows;
		this.quantityOfBombs = quantutyOfBombs;
		this.gameField = new GameField(columns, rows);
		this.hiddenCells = columns * rows - quantutyOfBombs;
	}
	
	public void startGame() {
		RenderingField.renderGameSession(this);
		while (!isExploded & !isWon()) {
			UserAction.makeTurn(this);
			RenderingField.renderGameSession(this);
		}
		Game.launchMainMenu();
	}
	
	public GameField getGameField() {
		return gameField;
	}

	public boolean isWon() {
		return hiddenCells == 0;
	}

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
	
	public Cell getCell(int column, int row) {
		return gameField.getGameField().get(new Coordinate(column, row));
	}
	
	public Cell getCell(Coordinate coordinate) {
		return gameField.getGameField().get(coordinate);
	}

	public boolean isExploded() {
		return isExploded;
	}

	public void decriseHiddenCells() {
		hiddenCells--;
	}

	public int getHiddenCells() {
		return hiddenCells;
	}
}
