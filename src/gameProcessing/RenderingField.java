package gameProcessing;

public class RenderingField {
	
	public static void doRendering(GameSession game) {
		for (int row = -1; row <= game.getRows(); row++) {
			for (int column = 0; column <= game.getColumns(); column++) {
				if (row == -1 && column == 0 || row == 0 && column == 0) {
					System.out.print("   ");
				}
				else if (row == 0) {
					System.out.print("----");
					if (column == game.getRows()) System.out.print("\n");
				}	
				else if (row == -1) {
					if (column < 10) {
						System.out.print("  " + column + " ");
						if (column == game.getRows()) System.out.print("\n");
					}
					else {
						System.out.print(" "+ column + " ");
						if (column == game.getRows()) doRenderingLegend(game, row); System.out.print("\n");
					}
				}
				else if (column == 0 && row > 0) {
					if (row < 10) {
						System.out.print(" " + row + "|");
					}
					else {
						System.out.print(row + "|");
					}
				}
				else {
						System.out.print("  " + game.showCell(column, row) + " ");
				}
			}
			if (row != game.getColumns() && row > 0) { 
				doRenderingLegend(game, row);
				System.out.println("\n  |");
				}
		}
		System.out.println("\n\n");
	}
	
	private static void doRenderingLegend(GameSession game, int row) {
		switch (row) {
		case -1 : System.out.print(!game.isExploded() ? "  Bomberman." : "  Bomberman is EXPLODED!!!"); break;
		
		case 1 : System.out.print("  Quantity of bombs: " + game.getQuantityOfBombs()); break;
		case 2 : System.out.print("  Hidden fields: "); break;
		
		case 4 : System.out.print(!game.isExploded() ? "  For exit type \"EXIT\"." : "  Press any key to continue..."); break;
		
		case 6 : System.out.print(!game.isExploded() ? "  Enter coordinate: type coordinate like row-column." : ""); break;
		case 7 : System.out.print(!game.isExploded() ? "  !Use \"-\" beetwen \"row\" and \"column\", where \"row\" is column number and \"column\" is row number." : ""); break;
		}
	}
}
