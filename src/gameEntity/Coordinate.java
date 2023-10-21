package gameEntity;

import java.util.Objects;

public class Coordinate {
	
	private int column;
	private int row;
	
	public Coordinate(int column, int row) {
		this.column = column;
		this.row = row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return column == other.column && row == other.row;
	}
}