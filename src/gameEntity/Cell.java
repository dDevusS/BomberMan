package gameEntity;

import java.io.Serializable;

import gameProcessing.GameSession;

public class Cell implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Coordinate coordinate;
	private int stepsFromBomb = 0;
	private boolean isBomb = false;
	private boolean isVisible = false;
	private boolean isBeginingCell = false;
	
	Cell(int row, int column, int stepsFromBomb) {
		this.coordinate = new Coordinate(row, column);
		this.stepsFromBomb = stepsFromBomb;
		
		if (stepsFromBomb == 9) {
			this.isBomb = true;
		}
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public int getStepsFromBomb() {
		return stepsFromBomb;
	}
	
	public void setStepsFromBomb(int stepsFromBomb) {
		this.stepsFromBomb = stepsFromBomb;
		if (stepsFromBomb == 9) {
			this.isBomb = true;
		}
	}

	public boolean isBomb() {
		return isBomb;
	}
	
	public void plusStep() {
		this.stepsFromBomb++;
	}

	public boolean isVisible() {
		return isVisible;
	}
	
	public void makeVisible(GameSession game) {
		this.isVisible = true;
		game.decriseHiddenCells();
	}

	public boolean isBeginingCell() {
		return isBeginingCell;
	}

	public void makeBeginingCell() {
		this.isBeginingCell = true;
	}
	
	@Override
	public String toString() {
		if (!isVisible) {
			return ".";
		}
		else if (isBomb) {
			return "*";
		}
		return Integer.toString(stepsFromBomb);
	}
	
	public String showCell() {
		if (isBomb) {
			return "*";
		}
		else {
			return Integer.toString(stepsFromBomb);
		}
	}
}
