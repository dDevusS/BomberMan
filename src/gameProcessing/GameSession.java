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
		RenderingField.doRendering(this);
		while (!isExploded & !isWon()) {
			UserAction.makeTurn(this);
			RenderingField.doRendering(this);
		}
		Game.launchMainMenu();
	}
	
	public GameField getGameField() {
		return gameField;
	}
	
	public String showCell(int colum, int row) {
		if (isExploded && getCell(new Coordinate(colum, row)).isBomb()) {
			return "*";
		}
		else if (isExploded) {
			return Integer.toString(getCell(new Coordinate(colum, row)).getStepsFromBomb());
		}
		else if (!getCell(new Coordinate(colum, row)).isVisible()) {
			return ".";
		}
		else if (getCell(new Coordinate(colum, row)).isBomb()) {
			return "*";
		}
		
		return Integer.toString(getCell(new Coordinate(colum, row)).getStepsFromBomb());
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
