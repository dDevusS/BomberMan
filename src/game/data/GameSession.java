package game.data;

import java.io.Serializable;

import game.data.gameEntity.Cell;
import game.data.gameEntity.Coordinate;
import game.data.gameEntity.GameField;

public class GameSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rows;
	private int columns;
	private int quantityOfBombs;
	private int hiddenCells;
	private int counterTurns = 0;
	private GameField gameField;
	private boolean isExploded = false;
	private boolean isStopped;

	public GameSession(int rows, int columns, int quantutyOfBombs) {
		this.rows = rows;
		this.columns = columns;
		this.quantityOfBombs = quantutyOfBombs;
		this.gameField = new GameField(rows, columns);
		this.hiddenCells = columns * rows - quantutyOfBombs;
	}

	public void startGame() {
		RenderingGame.renderGameSession(this);
		isStopped = false;

		while (!isExploded & !isWon() & !isStopped) {
			UserAction.makeTurn(this);
			RenderingGame.renderGameSession(this);
		}
		if (isExploded || isWon()) {
			ProcessingSaveGame.deleteSaveGame();
		}
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

	public Cell getCell(int row, int column) {
		return gameField.getGameField().get(new Coordinate(row, column));
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

	public void stopGame() {
		this.isStopped = true;
	}
}
