package gameEntity;

public class Cell {
	
	private Coordinate coordinate;
	private int stepsFromBomb = 0;
	private boolean isBomb = false;
	
	Cell(int column, int row, int stepsFromBomb) {
		this.coordinate = new Coordinate(column, row);
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public int getStepsFromBomb() {
		return stepsFromBomb;
	}
	
	public void setStepsFromBomb(int stepsFromBomb) {
		this.stepsFromBomb = stepsFromBomb;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
}
