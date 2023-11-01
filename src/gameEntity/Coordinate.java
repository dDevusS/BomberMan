package gameEntity;

import java.io.Serializable;
import java.util.Objects;

public class Coordinate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int row;
	private int column;
	
	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public Coordinate(String[] arrayCoordinate) {
		this.row = Integer.parseInt(arrayCoordinate[0].trim());
		this.column = Integer.parseInt(arrayCoordinate[1].trim());
	}
	
	public Coordinate shiftCoordinate(int y, int x) {
		return new Coordinate(this.row + y, this.column + x);
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
