package gameEntity;

import java.io.Serializable;
import java.util.Objects;

public class Coordinate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int column;
	private int row;
	
	public Coordinate(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	public Coordinate(String[] arrayCoordinate) {
		this.column = Integer.parseInt(arrayCoordinate[1].replaceAll("\\s", ""));
		this.row = Integer.parseInt(arrayCoordinate[0].replaceAll("\\s", ""));
	}
	
	public Coordinate shiftCoordinate(int y, int x) {
		return new Coordinate(this.column + y, this.row + x);
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
