package gameProcessing;

import java.util.Scanner;

import gameEntity.Coordinate;

public class UserAction {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void makeTurn(GameSession game) {
		while (true) {
			String userCommand = input.nextLine();
			
			switch (userCommand) {
			case "exit" : System.exit(0); break;
			}
		
			if (isCoordinate(userCommand, game)) {
				Coordinate coordinate = new Coordinate(userCommand.split("-"));
				
				if (game.getCounterTurns() == 0) {
					makeVisibleAreaForFirstTurn(coordinate, game);
					game.increaceCounterTurn();
					return;
				}
				else {
					
				}
			}
		}
	}
	
	private void saveGame() {}
	
	private void openAllZeroCellClosedToChoese(Coordinate coordinate, GameSession game) {
		
	}
	
	private static void makeVisibleAreaForFirstTurn(Coordinate coordinate, GameSession game) {
		for (int row = -1; row <= 1; row++) {
			for (int column = -1; column <= 1; column++) {
				if (game.getGameField().getGameField().get(coordinate.shiftCoordinate(row, column)) != null) {
					game.getGameField().getGameField().get(coordinate.shiftCoordinate(row, column)).makeBeginingCell();
				}
			}
		}
		game.getGameField().createBombs(game.getColumns(), game.getRows(), game.getQuantityOfBombs());
	}
	
	private static boolean isCoordinate(String userCommand, GameSession game) {
		String[] command = userCommand.split("-");
		
		if (command.length == 2) {
			try {
				Integer.parseInt(command[0]);
				Integer.parseInt(command[1]);
				
				if (Integer.parseInt(command[0]) > 0 && Integer.parseInt(command[0]) <= game.getColumns() ||
						Integer.parseInt(command[0]) > 0 && Integer.parseInt(command[0]) <= game.getColumns()) {
					return true;
				}
			} 
			catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}
}
