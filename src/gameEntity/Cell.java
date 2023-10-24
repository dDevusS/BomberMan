package gameEntity;

public class Cell {
	
	private Coordinate coordinate;
	private int stepsFromBomb = 0;
	private boolean isBomb = false;
	private boolean isVisible = false;
	private boolean isBeginingCell = false;
	
	Cell(int column, int row, int stepsFromBomb) {
		this.coordinate = new Coordinate(column, row);
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
	
	public void makeVisible() {
		this.isVisible = true;
	}

	public void doVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isBeginingCell() {
		return isBeginingCell;
	}

	public void makeBeginingCell(boolean isBeginingCell) {
		this.isBeginingCell = isBeginingCell;
	}
}
