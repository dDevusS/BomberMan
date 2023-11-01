package gameProcessing;

public class RenderingField {
	
	public static void renderGameSession(GameSession game) {
		renderHead(game.getColumns());
		for (int row = 1; row <= game.getRows() * 2; row++) {
			renderRow(game, row);
		}
	}
	
	private static void renderLegend(GameSession game, int row) {
		switch (row) {
		case 1 : System.out.print(!game.isExploded() ? "  Bomberman." : "  Bomberman is EXPLODED!!!"); break;
		
		case 3 : System.out.print("  Quantity of bombs: " + game.getQuantityOfBombs()); break;
		case 4 : System.out.print("  Hidden fields: " + game.getHiddenCells()); break;
		case 5 : System.out.print("  Your turn: " + game.getCounterTurns()); break;
		
		case 7 : System.out.print(!game.isExploded() ? "  For exit type \"EXIT\"." : ""); break;
		
		case 9 : System.out.print(!game.isExploded() ? "  Enter coordinate: type coordinate like row-column." : ""); break;
		case 10 : System.out.print(!game.isExploded() ? "  !Use \"-\" beetwen \"row\" and \"column\", where \"row\" is column number and \"column\" is row number." : ""); break;
		}
	}
	
	private static void renderHead(int columns) {
		System.out.print("   ");
		for (int column = 1; column <= columns; column++) {
			printElementOfField(column);
			}
		System.out.print("\n   ");
		for (int column = 1; column <= columns; column++) {
			System.out.print("----");
		}
		System.out.print("\n");
	}
	
	private static void renderRow(GameSession game, int row) {
		if (row % 2 != 0) {
			renderRowNumber(adaptRowToCoordinate(row));
			for (int column = 1; column <= game.getColumns(); column++) {
				renderCell(game, adaptRowToCoordinate(row), column);
			}
			renderLegend(game, row);
		}
		else {
			System.out.print(row < game.getRows() * 2 ? "\n  |" : "\n");
			for (int column = 1; column <= game.getColumns(); column++) {
				System.out.print("    ");
			}
			renderLegend(game, row);
			System.out.print("\n");
		}
	}
	
	private static void renderRowNumber(int row) {
		if (row < 10) {
			System.out.print(" " + row + "|");
		}
		else {
			System.out.print(row + "|");
		}
	}
	
	private static void renderCell(GameSession game, int row, int column) {
		if (!game.isExploded()) {
		printElementOfField(game.getCell(row, column));
		}
		else {
			printElementOfField(game.getCell(row, column).showCell());
		}
	}
	
	private static void printElementOfField(Object element) {
		String elementString = element.toString();
		if (elementString.length() == 1) {
			System.out.print("  " + elementString + " ");
		}
		else if (elementString.length() == 2) {
			System.out.print(" " + elementString + " ");
		}
	}
	
	private static int adaptRowToCoordinate(int row) {
		return row - row / 2;
	}
}
