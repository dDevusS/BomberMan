package gameProcessing;

public class RenderingField {
	
	public static void doRendering(GameSession game) {
		// TODO: разобраться с rows и columns. все перепутано.
		for (int row = -1; row <= game.getColumns(); row++) {
			for (int column = 0; column <= game.getRows(); column++) {
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
		case -1 : System.out.print("  Bomberman."); break;
		case 1 : System.out.print("  Quantity of bombs: " + game.getQuantityOfBombs()); break;
		case 2 : System.out.print("  Hidden fields: "); break;
		case 4 : System.out.print("  For exit type \"EXIT\"."); break;
		case 6 : System.out.print("  Enter coordinate: type coordinate like y-x."); break;
		case 7 : System.out.print("  !Use \"-\" beetwen \"y\" and \"x\", where \"y\" is column number and \"x\" is row number."); break;
		}
	}
}
